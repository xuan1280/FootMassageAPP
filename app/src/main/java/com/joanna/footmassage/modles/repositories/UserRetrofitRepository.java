package com.joanna.footmassage.modles.repositories;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.joanna.footmassage.Secret;
import com.joanna.footmassage.modles.entities.Question;
import com.joanna.footmassage.modles.entities.User;
import com.joanna.footmassage.modles.models.ModifyUserInformationModel;
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
    public ResponseModel<List<Question>> getHealthQuestions() {
        return null;
    }

    @Override
    public ResponseModel<List<Question>> sendHealthQuestionnaire(List<Question> questions) {
        return null;
    }

    @Override
    public ResponseModel<User> modifyUserInformation(ModifyUserInformationModel modifyUserInformationModel) {
        return null;
    }

    public interface UserAPI {
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
        @POST("login.php")
        Call<ResponseModel<User>> signIn(@Field("account") String account,
                                         @Field("password") String password);
    }
}
