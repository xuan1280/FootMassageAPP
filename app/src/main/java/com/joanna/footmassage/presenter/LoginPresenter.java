package com.joanna.footmassage.presenter;

import android.os.Handler;
import android.util.Log;

import com.joanna.footmassage.modles.entities.User;
import com.joanna.footmassage.modles.models.ResponseModel;
import com.joanna.footmassage.modles.models.SignInModel;
import com.joanna.footmassage.modles.repositories.UserRepository;
import com.joanna.footmassage.views.base.LoginView;

import java.io.IOException;

public class LoginPresenter {
    private final static String TAG = "LoginPresenter";
    private UserRepository userRepository;
    private LoginView loginView;
    private Handler handler = new Handler();

    public LoginPresenter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    public void signIn(final SignInModel signInModel) {
        Log.d(TAG, "signIn");
        new Thread() {
            @Override
            public void run() {
                ResponseModel responseModel;
                try {
                    responseModel = userRepository.signIn(signInModel);
                    Log.d(TAG, responseModel.getMessage());
                    if (responseModel.getCode() == 101)
                        handler.post(() -> loginView.onAccountNoFound());
                    else if (responseModel.getCode() == 102)
                        handler.post(() -> loginView.onPasswordNotCorrect());
                    else
                        handler.post(() -> loginView.onSignInSuccessfully((User) responseModel.getData()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
