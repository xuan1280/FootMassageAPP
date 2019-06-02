package com.joanna.footmassage.modles.repositories;

import com.joanna.footmassage.modles.entities.DiagnosisResult;
import com.joanna.footmassage.modles.entities.Question;
import com.joanna.footmassage.modles.entities.User;
import com.joanna.footmassage.modles.models.BasicModel;
import com.joanna.footmassage.modles.models.ModifyUserInformationModel;
import com.joanna.footmassage.modles.models.QuestionnaireAnswerModel;
import com.joanna.footmassage.modles.models.ResponseModel;
import com.joanna.footmassage.modles.models.SignInModel;
import com.joanna.footmassage.modles.models.SignUpModel;

import java.io.IOException;

public class UserJsonRepository implements UserRepository {
    @Override
    public ResponseModel<User> signIn(SignInModel signInModel) throws IOException {
        return null;
    }

    @Override
    public ResponseModel<User> signUp(SignUpModel signUpModel) throws IOException {
        return null;
    }

    @Override
    public ResponseModel<Question[]> getHealthQuestions() throws IOException {
        return null;
    }

    @Override
    public ResponseModel sendHealthQuestionnaire(QuestionnaireAnswerModel questionnaireAnswerModel) throws IOException {
        return null;
    }

    @Override
    public ResponseModel<User> modifyUserInformation(ModifyUserInformationModel modifyUserInformationModel) throws IOException {
        return null;
    }

    @Override
    public ResponseModel<DiagnosisResult[]> getDiagnosisRecord(BasicModel basicModel) throws IOException {
        return null;
    }
}
