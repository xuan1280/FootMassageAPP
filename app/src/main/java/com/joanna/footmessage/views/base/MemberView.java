package com.joanna.footmessage.views.base;

import com.joanna.footmessage.modles.entities.Question;
import com.joanna.footmessage.modles.entities.User;

import java.util.List;

public interface MemberView {
    void onQuestionnaireGotSuccessfully(List<Question> questions);

    void onQuestionnaireGotFailed();

    void onModifyUserInformationSuccessfully(User user);

    void onModifyUserInformationFailed();
}
