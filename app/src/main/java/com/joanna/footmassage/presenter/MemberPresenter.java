package com.joanna.footmassage.presenter;

import android.os.Handler;
import android.util.Log;

import com.joanna.footmassage.modles.entities.Question;
import com.joanna.footmassage.modles.entities.DiagnosisResult;
import com.joanna.footmassage.modles.entities.User;
import com.joanna.footmassage.modles.models.BasicModel;
import com.joanna.footmassage.modles.models.ModifyUserInformationModel;
import com.joanna.footmassage.modles.models.QuestionnaireAnswerModel;
import com.joanna.footmassage.modles.models.ResponseModel;
import com.joanna.footmassage.modles.repositories.UserRepository;
import com.joanna.footmassage.views.base.MemberView;

import java.io.IOException;

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
            ResponseModel responseModel;
            try {
                responseModel = userRepository.getHealthQuestions();
                if (responseModel.getCode() == 200)
                    handler.post(() -> memberView.onQuestionnaireGotSuccessfully((Question[]) responseModel.getData()));
                else
                    handler.post(() -> memberView.onQuestionnaireGotFailed());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void sendHealthQuestionnaire(QuestionnaireAnswerModel questionnaireAnswerModel) {
        Log.d(TAG, "sendHealthQuestionnaire");
        new Thread(() -> {
            ResponseModel responseModel;
            try {
                responseModel = userRepository.sendHealthQuestionnaire(questionnaireAnswerModel);
                if (responseModel.getCode() == 200)
                    handler.post(() -> memberView.onQuestionnaireSavedSuccessfully());
                else
                    handler.post(() -> memberView.onQuestionnaireSavedFailed());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void modifyUserInformation(ModifyUserInformationModel modifyUserInformationModel) {
        Log.d(TAG, "modifyUserInformation");
        new Thread(() -> {
            ResponseModel responseModel;
            try {
                responseModel = userRepository.modifyUserInformation(modifyUserInformationModel);
                if (responseModel.getCode() == 200)
                    handler.post(() -> memberView.onModifyUserInformationSuccessfully((User) responseModel.getData()));
                else
                    handler.post(() -> memberView.onModifyUserInformationFailed());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void getUserRecord(BasicModel basicModel) {
        Log.d(TAG, "modifyUserInformation");
        new Thread(() -> {
            ResponseModel responseModel;
            try {
                responseModel = userRepository.getDiagnosisRecord(basicModel);
                if (responseModel.getCode() == 200)
                    handler.post(() -> memberView.onDiagnosisRecordGotSuccessfully((DiagnosisResult[]) responseModel.getData()));
                else
                    handler.post(() -> memberView.onDiagnosisRecordGotFailed());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
