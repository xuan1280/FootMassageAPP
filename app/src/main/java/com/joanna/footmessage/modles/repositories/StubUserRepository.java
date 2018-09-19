package com.joanna.footmessage.modles.repositories;

import android.util.Log;

import com.joanna.footmessage.modles.entities.User;
import com.joanna.footmessage.modles.models.ResponseModel;
import com.joanna.footmessage.modles.models.SignInModel;
import com.joanna.footmessage.modles.models.SignUpModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StubUserRepository implements UserRepository {
    private final static String TAG = "StubUserRepository";
    private Map<String, User> usersMap = new HashMap<>();

    public StubUserRepository() {
        createUsers();
    }

    private void createUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "joanna", "123", "Joanna"));
        users.add(new User(2, "ben", "ben", "Ben"));
        users.add(new User(3, "wan", "wan", "Wan"));
        users.add(new User(4, "chen", "chen", "Chen"));
        for (User user: users)
            usersMap.put(user.getAccount(), user);
    }

    @Override
    public ResponseModel<User> signIn(SignInModel signInModel) throws IOException {
        Log.d(TAG, "signIn");
        User signInUser = usersMap.get(signInModel.getAccount());
        if (signInUser == null)
            return new ResponseModel<>(40401, "No matched account found.", null);
        if (signInUser.getPassword().equals(signInModel.getPassword()))
            return new ResponseModel<>(200, "successful.", signInUser);
        return new ResponseModel<>(40402, "The password is incorrect", null);
    }

    @Override
    public ResponseModel<User> signUp(SignUpModel signUpModel) throws IOException {
        Log.d(TAG, "signUp");
        User signUpUser = new User(usersMap.size()+1, signUpModel.getName(), signUpModel.getAccount(), signUpModel.getPassword());
        User user = usersMap.get(signUpModel.getAccount());
        if (user == null && !isParameterInvalid(signUpModel)) {
            usersMap.put(signUpUser.getAccount(), signUpUser);
            return new ResponseModel<>(200, "sign up successfully.", signUpUser);
        }
        else if (user != null)
            return new ResponseModel<>(40001, "account duplicated", null);
        return new ResponseModel<>(40000, "parameter invalid", null);
    }

    private boolean isParameterInvalid(SignUpModel signUpModel){
        if (signUpModel.getAccount().equals("") || signUpModel.getPassword().equals("") || signUpModel.getName().equals(""))
            return true;
        return false;
    }
}
