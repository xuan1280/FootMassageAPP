package com.joanna.footmassage.views.base;

import com.joanna.footmassage.modles.entities.Question;
import com.joanna.footmassage.modles.entities.DiagnosisResult;
import com.joanna.footmassage.modles.entities.User;

public interface MemberView {
    void onQuestionnaireGotSuccessfully(Question[] questions);

    void onQuestionnaireGotFailed();

    void onQuestionnaireSavedSuccessfully();

    void onQuestionnaireSavedFailed();

    void onModifyUserInformationSuccessfully(User user);

    void onModifyUserInformationFailed();

    void onDiagnosisRecordGotSuccessfully(DiagnosisResult[] diagnosisResults);

    void onDiagnosisRecordGotFailed();
}
