package com.joanna.footmassage.views.base;

import com.joanna.footmassage.modles.entities.User;

public interface SignUpView {
    void onSignUpSuccessfully(User user);

    void onAccountHasExisted();
}
