package com.joanna.footmassage.modles.repositories;

import com.joanna.footmassage.modles.entities.Question;
import com.joanna.footmassage.modles.entities.User;
import com.joanna.footmassage.modles.models.ModifyUserInformationModel;
import com.joanna.footmassage.modles.models.QuestionnaireAnswerModel;
import com.joanna.footmassage.modles.models.ResponseModel;
import com.joanna.footmassage.modles.models.SignInModel;
import com.joanna.footmassage.modles.models.SignUpModel;

import java.io.IOException;
import java.util.List;

public interface UserRepository {
    ResponseModel<User> signIn(SignInModel signInModel) throws IOException;

    ResponseModel<User> signUp(SignUpModel signUpModel) throws IOException;

    ResponseModel<List<Question>> getHealthQuestions() throws IOException;

    ResponseModel<List<Question>> sendHealthQuestionnaire(QuestionnaireAnswerModel questionnaireAnswerModel) throws IOException;

    ResponseModel<User> modifyUserInformation(ModifyUserInformationModel modifyUserInformationModel) throws IOException;
}
