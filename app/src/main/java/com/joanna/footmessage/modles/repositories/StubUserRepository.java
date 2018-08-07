package com.joanna.footmessage.modles.repositories;

import android.util.Log;

import com.joanna.footmessage.modles.entities.User;
import com.joanna.footmessage.modles.models.ResponseModel;
import com.joanna.footmessage.modles.models.SignInModel;

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
        users.add(new User("joanna", "123123"));
        users.add(new User("ben", "ben"));
        users.add(new User("wan", "wan"));
        users.add(new User("chen", "chen"));
        for (User user: users)
            usersMap.put(user.getAccount(), user);
    }

    @Override
    public ResponseModel<User> signIn(SignInModel signInModel) throws IOException {
        Log.d(TAG, "signIn");
        User user = usersMap.get(signInModel.getAccount());
        if (user == null)
            return new ResponseModel<>(40401, "No matched account found.", null);
        if (user.getPassword().equals(signInModel.getPassword()))
            return new ResponseModel<>(200, "successful.", user);
        return new ResponseModel<>(40402, "The password is incorrect", null);
    }

    @Override
    public ResponseModel<User> signUp(String account, String password, String name) throws IOException {
        //TODO: sign up
        return null;
    }
}
