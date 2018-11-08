package com.joanna.footmessage.modles.repositories;

import android.util.Log;

import com.joanna.footmessage.modles.entities.Question;
import com.joanna.footmessage.modles.entities.User;
import com.joanna.footmessage.modles.models.ModifyUserInformationModel;
import com.joanna.footmessage.modles.models.ResponseModel;
import com.joanna.footmessage.modles.models.SignInModel;
import com.joanna.footmessage.modles.models.SignUpModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class StubUserRepository implements UserRepository {
    private final static String TAG = "StubUserRepository";
    private Map<String, User> usersMap = new HashMap<>();

    public StubUserRepository() {
        createUsers();
    }

    private void createUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("2232", 1, "joanna", "000", "Joanna", 21, 0));
        users.add(new User("0421", 2, "ben", "ben", "Ben", 21, 1));
        users.add(new User("2352", 3, "wan", "wan", "Wan", 21, 0));
        users.add(new User("4432", 4, "chen", "chen", "Chen", 21, 1));
        for (User user : users)
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
        User signUpUser = new User(String.valueOf(new Random().nextInt(8999) + 1000), usersMap.size() + 1, signUpModel.getAccount(),
                signUpModel.getPassword(), signUpModel.getName(), signUpModel.getAge(), signUpModel.getGender());
        User user = usersMap.get(signUpModel.getAccount());
        if (user == null && !isParameterInvalid(signUpModel)) {
            usersMap.put(signUpUser.getAccount(), signUpUser);
            return new ResponseModel<>(200, "sign up successfully.", signUpUser);
        } else if (user != null)
            return new ResponseModel<>(40001, "account duplicated", null);
        return new ResponseModel<>(40000, "parameter invalid", null);
    }

    @Override
    public ResponseModel<List<Question>> getHealthQuestions() {
        return null;
    }

    @Override
    public ResponseModel<User> modifyUserInformation(ModifyUserInformationModel modifyUserInformationModel) {
        return null;
    }

    private boolean isParameterInvalid(SignUpModel signUpModel) {
        if (signUpModel.getAccount().equals("") || signUpModel.getPassword().equals("") || signUpModel.getName().equals(""))
            return true;
        return false;
    }
}
