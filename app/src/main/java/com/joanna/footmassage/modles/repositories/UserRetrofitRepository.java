package com.joanna.footmassage.modles.repositories;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.joanna.footmassage.Secret;
import com.joanna.footmassage.modles.entities.Question;
import com.joanna.footmassage.modles.entities.User;
import com.joanna.footmassage.modles.models.ModifyUserInformationModel;
import com.joanna.footmassage.modles.models.QuestionnaireAnswerModel;
import com.joanna.footmassage.modles.models.ResponseModel;
import com.joanna.footmassage.modles.models.SignInModel;
import com.joanna.footmassage.modles.models.SignUpModel;
import com.joanna.footmassage.utils.ResponseUtils;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public class UserRetrofitRepository implements UserRepository {
    private final static String TAG = "UserRetrofitRepository";
    private UserAPI userAPI;

    public UserRetrofitRepository() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Secret.IP)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        userAPI = retrofit.create(UserAPI.class);
    }

    @Override
    public ResponseModel<User> signIn(SignInModel signInModel) throws IOException {
        Log.d(TAG, "signIn " + signInModel.getAccount());
        Response<ResponseModel<User>> response = userAPI.signIn(signInModel.getAccount(), signInModel.getPassword()).execute();
        ResponseModel<User> responseModel = ResponseUtils.getBody(response);
        assert responseModel != null;
        Log.d(TAG, responseModel.toString());
        return responseModel;
    }

    @Override
    public ResponseModel<User> signUp(SignUpModel signUpModel) throws IOException {
        Log.d(TAG, "signUp " + signUpModel.getName());
        return userAPI.signUp(signUpModel.getName(), signUpModel.getAccount(), signUpModel.getPassword(), signUpModel.getAge(), signUpModel.getGender()).execute().body();
    }

    @Override
    public ResponseModel<List<Question>> getHealthQuestions() throws IOException {
        return userAPI.getHealthQuestions().execute().body();
    }

    @Override
    public ResponseModel<List<Question>> sendHealthQuestionnaire(QuestionnaireAnswerModel questionnaireAnswerModel) throws IOException {
        List<Question> questions = questionnaireAnswerModel.getQuestions();
        return userAPI.sendQuestionnaireAnswers(questionnaireAnswerModel.getAccount(),
                questionnaireAnswerModel.getToken(), questions.get(0).getAnswer(),
                questions.get(1).getAnswer(), questions.get(2).getAnswer(), questions.get(3).getAnswer(),
                questions.get(4).getAnswer(), questions.get(5).getAnswer(), questions.get(6).getAnswer(),
                questions.get(7).getAnswer(), questions.get(8).getAnswer(), questions.get(9).getAnswer(),
                questions.get(10).getAnswer(), questions.get(11).getAnswer()).execute().body();
    }

    @Override
    public ResponseModel<User> modifyUserInformation(ModifyUserInformationModel modifyUserInformationModel) throws IOException {
        return userAPI.modifyUserInformation(modifyUserInformationModel.getAccount(), modifyUserInformationModel.getToken(),
                modifyUserInformationModel.getName(), modifyUserInformationModel.getPassword(),
                modifyUserInformationModel.getAge(), modifyUserInformationModel.getGender()).execute().body();
    }

    public interface UserAPI {
        @Headers("Content-Type:application/x-www-form-urlencoded")
        @FormUrlEncoded
        @POST("login.php")
        Call<ResponseModel<User>> signIn(@Field("account") String account,
                                         @Field("password") String password);

        @Headers("Content-Type:application/x-www-form-urlencoded")
        @FormUrlEncoded
        @POST("register.php")
        Call<ResponseModel<User>> signUp(@Field("name") String name,
                                         @Field("account") String account,
                                         @Field("password") String password,
                                         @Field("age") int age,
                                         @Field("gender") int gender);

        @Headers("Content-Type:application/x-www-form-urlencoded")
        @FormUrlEncoded
        @GET(".php")
        Call<ResponseModel<List<Question>>> getHealthQuestions();

        @Headers("Content-Type:application/x-www-form-urlencoded")
        @FormUrlEncoded
        @POST(".php")
        Call<ResponseModel<List<Question>>> sendQuestionnaireAnswers(@Field("account") String account,
                                                           @Field("password") String password,
                                                           @Field("Q1") int answer1,
                                                           @Field("Q2") int answer2,
                                                           @Field("Q3") int answer3,
                                                           @Field("Q4") int answer4,
                                                           @Field("Q5") int answer5,
                                                           @Field("Q6") int answer6,
                                                           @Field("Q7") int answer7,
                                                           @Field("Q8") int answer8,
                                                           @Field("Q9") int answer9,
                                                           @Field("Q10") int answer10,
                                                           @Field("Q11") int answer11,
                                                           @Field("Q12") int answer12);

        @Headers("Content-Type:application/x-www-form-urlencoded")
        @FormUrlEncoded
        @POST(".php")
        Call<ResponseModel<User>> modifyUserInformation(@Field("account") String account,
                                                        @Field("skey") String token,
                                                        @Field("name") String name,
                                                        @Field("password") String password,
                                                        @Field("age") int age,
                                                        @Field("gender") int gender);
    }
}
