package com.joanna.footmessage.views.base;

import com.joanna.footmessage.modles.entities.User;

public interface SignUpView {
    void onSignUpSuccessfully(User user);
    void onAccountHasExisted();
}
