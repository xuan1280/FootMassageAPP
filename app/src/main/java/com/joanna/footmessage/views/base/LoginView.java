package com.joanna.footmessage.views.base;

import com.joanna.footmessage.modles.entities.User;

public interface LoginView {
    void onSignInSuccessfully(User user);
    void onAccountNoFound();
    void onPasswordNotCorrect();
}
