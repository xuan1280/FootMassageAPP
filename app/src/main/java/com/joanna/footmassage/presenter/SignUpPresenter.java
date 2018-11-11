package com.joanna.footmassage.presenter;

import android.os.Handler;
import android.util.Log;

import com.joanna.footmassage.modles.entities.User;
import com.joanna.footmassage.modles.models.ResponseModel;
import com.joanna.footmassage.modles.models.SignUpModel;
import com.joanna.footmassage.modles.repositories.UserRepository;
import com.joanna.footmassage.views.base.SignUpView;

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

