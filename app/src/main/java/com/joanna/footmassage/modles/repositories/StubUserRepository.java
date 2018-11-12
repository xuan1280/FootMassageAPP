package com.joanna.footmassage.modles.repositories;

import android.util.Log;

import com.joanna.footmassage.modles.entities.Question;
import com.joanna.footmassage.modles.entities.User;
import com.joanna.footmassage.modles.models.ModifyUserInformationModel;
import com.joanna.footmassage.modles.models.QuestionnaireAnswerModel;
import com.joanna.footmassage.modles.models.ResponseModel;
import com.joanna.footmassage.modles.models.SignInModel;
import com.joanna.footmassage.modles.models.SignUpModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class StubUserRepository implements UserRepository {
    private final static String TAG = "StubUserRepository";
    private Map<String, User> usersMap = new HashMap<>();
    private String[] questionList = {"請問您是否經常熬夜?", "請問您是否有吸菸、飲酒的習慣?",
            "請問您是否有喝咖啡、濃茶或吃甜食的習慣?", "請問您是否經常暴飲暴食或三餐不正常?",
            "請問您是否長期處於生活緊張、工作勞累的狀態?", "請問您是否經常吃加工食品或重口味的食物?",
            "請問您是否經常任意服用成藥?", "請問您是否容易感到疲倦、全身無力?", "請問您是否有規律的排便?",
            "請問您的體重是否莫名的減輕?", "請問您是否知道自己有肝方面的症狀或疾病?",
            "請問您是否知道自己有胃方面的症狀或疾病?"};

    public StubUserRepository() {
        createUsers();
    }

    private void createUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("7501", 1, "joanna", "000", "Joanna", 21, 0));
        users.add(new User("0421", 2, "ben", "ben", "Ben", 21, 1));
        users.add(new User("2352", 3, "wan", "wan", "Wan", 21, 0));
        users.add(new User("4432", 4, "chen", "chen", "Chen", 21, 1));
        for (User user : users)
            usersMap.put(user.getAccount(), user);
    }

    @Override
    public ResponseModel<User> signIn(SignInModel signInModel) throws IOException {
        Log.d(TAG, "signIn");
        User signInUser = usersMap.get(signInModel.getAccount());
        if (signInUser == null)
            return new ResponseModel<>(40401, "No matched account found.", null);
        if (signInUser.getPassword().equals(signInModel.getPassword()))
            return new ResponseModel<>(200, "successful.", signInUser);
        return new ResponseModel<>(40402, "The password is incorrect", null);
    }

    @Override
    public ResponseModel<User> signUp(SignUpModel signUpModel) throws IOException {
        Log.d(TAG, "signUp");
        User signUpUser = new User(String.valueOf(new Random().nextInt(8999) + 1000), usersMap.size() + 1, signUpModel.getAccount(),
                signUpModel.getPassword(), signUpModel.getName(), signUpModel.getAge(), signUpModel.getGender());
        User user = usersMap.get(signUpModel.getAccount());
        if (user == null && !isParameterInvalid(signUpModel)) {
            usersMap.put(signUpUser.getAccount(), signUpUser);
            return new ResponseModel<>(200, "sign up successfully.", signUpUser);
        } else if (user != null)
            return new ResponseModel<>(40001, "account duplicated", null);
        return new ResponseModel<>(40000, "parameter invalid", null);
    }

    @Override
    public ResponseModel<List<Question>> getHealthQuestions() throws IOException {
        List<Question> questions = new ArrayList<>();
        List<String> items1 = new ArrayList<>(Arrays.asList("是","否"));
        List<String> items2 = new ArrayList<>(Arrays.asList("是","否", "不知道"));

        for (int i = 0; i < 12; i++) {
            Question question = new Question(i, questionList[i], (i==11 || i==10)? items2:items1);
            questions.add(question);
        }
        return new ResponseModel<>(200, "成功", questions);
    }

    @Override
    public ResponseModel<List<Question>> sendHealthQuestionnaire(QuestionnaireAnswerModel questionnaireAnswerModel) throws IOException {
        return new ResponseModel<>(200, "成功", questionnaireAnswerModel.getQuestions());
    }

    @Override
    public ResponseModel<User> modifyUserInformation(ModifyUserInformationModel modifyUserInformationModel) throws IOException {
        User user = usersMap.get(modifyUserInformationModel.getAccount());
        user.setName(modifyUserInformationModel.getName());
        user.setPassword(modifyUserInformationModel.getPassword());
        user.setGender(modifyUserInformationModel.getGender());
        user.setAge(modifyUserInformationModel.getAge());
        return new ResponseModel<>(200, "成功", user);
    }

    private boolean isParameterInvalid(SignUpModel signUpModel) {
        if (signUpModel.getAccount().equals("") || signUpModel.getPassword().equals("") || signUpModel.getName().equals(""))
            return true;
        return false;
    }
}
