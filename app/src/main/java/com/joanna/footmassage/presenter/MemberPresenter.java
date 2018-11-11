package com.joanna.footmassage.presenter;

import android.os.Handler;
import android.util.Log;

import com.joanna.footmassage.modles.entities.Question;
import com.joanna.footmassage.modles.entities.User;
import com.joanna.footmassage.modles.models.ModifyUserInformationModel;
import com.joanna.footmassage.modles.models.ResponseModel;
import com.joanna.footmassage.modles.repositories.UserRepository;
import com.joanna.footmassage.views.base.MemberView;

import java.util.List;

public class MemberPresenter {
    private final static String TAG = "MemberPresenter";
    private UserRepository userRepository;
    private MemberView memberView;
    private Handler handler = new Handler();

    public MemberPresenter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setMemberView(MemberView memberView) {
        this.memberView = memberView;
    }

    public void getHealthQuestionnaire() {
        Log.d(TAG, "getHealthQuestions");
        new Thread(() -> {
            ResponseModel responseModel = userRepository.getHealthQuestions();
            if (responseModel.getCode() == 200)
                handler.post(() -> memberView.onQuestionnaireGotSuccessfully((List<Question>) responseModel.getData()));
            else
                handler.post(() -> memberView.onQuestionnaireGotFailed());
        }).start();
    }

    public void sendHealthQuestionnaire(List<Question> questions) {
        Log.d(TAG, "sendHealthQuestionnaire");
        new Thread(() -> {
            ResponseModel responseModel = userRepository.sendHealthQuestionnaire(questions);
            if (responseModel.getCode() == 200)
                handler.post(() -> memberView.onQuestionnaireSavedSuccessfully((List<Question>) responseModel.getData()));
            else
                handler.post(() -> memberView.onQuestionnaireSavedFailed());
        }).start();
    }

    public void modifyUserInformation(ModifyUserInformationModel modifyUserInformationModel) {
        Log.d(TAG, "modifyUserInformation");
        new Thread(() -> {
            ResponseModel responseModel = userRepository.modifyUserInformation(modifyUserInformationModel);
            if (responseModel.getCode() == 200)
                handler.post(() -> memberView.onModifyUserInformationSuccessfully((User) responseModel.getData()));
            else
                handler.post(() -> memberView.onModifyUserInformationFailed());
        }).start();
    }
}