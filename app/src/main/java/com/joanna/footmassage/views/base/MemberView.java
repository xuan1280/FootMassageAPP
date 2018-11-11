package com.joanna.footmassage.views.base;

import com.joanna.footmassage.modles.entities.Question;
import com.joanna.footmassage.modles.entities.User;

import java.util.List;

public interface MemberView {
    void onQuestionnaireGotSuccessfully(List<Question> questions);

    void onQuestionnaireGotFailed();

    void onQuestionnaireSavedSuccessfully(List<Question> questions);

    void onQuestionnaireSavedFailed();

    void onModifyUserInformationSuccessfully(User user);

    void onModifyUserInformationFailed();
}
