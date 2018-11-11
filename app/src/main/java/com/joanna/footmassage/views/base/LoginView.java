package com.joanna.footmassage.views.base;

import com.joanna.footmassage.modles.entities.User;

public interface LoginView {
    void onSignInSuccessfully(User user);

    void onAccountNoFound();

    void onPasswordNotCorrect();
}
