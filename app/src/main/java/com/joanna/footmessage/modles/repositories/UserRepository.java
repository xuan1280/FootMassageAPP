package com.joanna.footmessage.modles.repositories;

import com.joanna.footmessage.modles.entities.User;
import com.joanna.footmessage.modles.models.ResponseModel;
import com.joanna.footmessage.modles.models.SignInModel;
import com.joanna.footmessage.modles.models.SignUpModel;

import java.io.IOException;

public interface UserRepository {
    ResponseModel<User> signIn(SignInModel signInModel) throws IOException;
    ResponseModel<User> signUp(SignUpModel signUpModel) throws IOException;
}
