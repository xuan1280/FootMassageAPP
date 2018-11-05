package com.joanna.footmessage.presenter;

import android.os.Handler;
import android.util.Log;

import com.joanna.footmessage.modles.entities.User;
import com.joanna.footmessage.modles.models.ResponseModel;
import com.joanna.footmessage.modles.models.SignUpModel;
import com.joanna.footmessage.modles.repositories.UserRepository;
import com.joanna.footmessage.views.base.SignUpView;

import java.io.IOException;

public class SignUpPresenter {
    private final static String TAG = "SignUpPresenter";
    private UserRepository userRepository;
    private SignUpView signUpView;
    private Handler handler = new Handler();

    public SignUpPresenter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setSignUpView(SignUpView signUpView) {
        this.signUpView = signUpView;
    }

    public void signUp(SignUpModel signUpModel) {
        Log.d(TAG, "sign up.");
        new Thread() {
            @Override
            public void run() {
                ResponseModel<User> responseModel;
                try {
                    responseModel = userRepository.signUp(signUpModel);
                    if (responseModel.getCode() == 201)
                        handler.post(() -> signUpView.onAccountHasExisted());
                    else {
                        handler.post(() -> signUpView.onSignUpSuccessfully(responseModel.getData()));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

}

