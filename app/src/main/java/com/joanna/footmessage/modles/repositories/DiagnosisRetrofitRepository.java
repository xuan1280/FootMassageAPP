package com.joanna.footmessage.modles.repositories;

import android.util.Log;

import com.joanna.footmessage.Secret;
import com.joanna.footmessage.modles.entities.DiagnosisResult;
import com.joanna.footmessage.modles.entities.PressureData;
import com.joanna.footmessage.modles.models.ResponseModel;
import com.joanna.footmessage.utils.ResponseUtils;

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

public class DiagnosisRetrofitRepository implements DiagnosisRepository {
    private final static String TAG = "UserRetrofitRepository";
    private DiagnosisAPI diagnosisAPI;

    public DiagnosisRetrofitRepository(DiagnosisAPI userAPI) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Secret.IP)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        diagnosisAPI = retrofit.create(DiagnosisRetrofitRepository.DiagnosisAPI.class);
    }

    @Override
    public ResponseModel<Integer> startDiagnosis(String token) throws IOException {
        Response<ResponseModel<Integer>> response = diagnosisAPI.startDiagnosis(token).execute();
        ResponseModel<Integer> responseModel = ResponseUtils.getBody(response);
        assert responseModel != null;
        Log.d(TAG, responseModel.toString());
        return responseModel;
    }

    @Override
    public ResponseModel<Integer> sendPressureData(String token, List<PressureData> pressureDataList) throws IOException {
        Response<ResponseModel<Integer>> response = diagnosisAPI.sendPressureData(token, pressureDataList).execute();
        ResponseModel<Integer> responseModel = ResponseUtils.getBody(response);
        assert responseModel != null;
        Log.d(TAG, responseModel.toString());
        return responseModel;
    }

    @Override
    public ResponseModel<DiagnosisResult> getDiagnosisResult(String token, int id) throws IOException {
        Response<ResponseModel<DiagnosisResult>> response = diagnosisAPI.getDiagnosisResult(token, id).execute();
        ResponseModel<DiagnosisResult> responseModel = ResponseUtils.getBody(response);
        assert responseModel != null;
        Log.d(TAG, responseModel.toString());
        return responseModel;
    }

    public interface DiagnosisAPI {
        @Headers("Content-Type:application/x-www-form-urlencoded")
        @FormUrlEncoded
        @POST("/startDiagnosis")
        Call<ResponseModel<Integer>> startDiagnosis(@Field("token") String token);

        @Headers("Content-Type:application/x-www-form-urlencoded")
        @FormUrlEncoded
        @POST("/sendPressureData")
        Call<ResponseModel<Integer>> sendPressureData(@Field("token") String token,
                                                      @Field("account") List<PressureData> pressureData);

        @Headers("Content-Type:application/x-www-form-urlencoded")
        @FormUrlEncoded
        @POST("/getDiagnosisResult")
        Call<ResponseModel<DiagnosisResult>> getDiagnosisResult(@Field("token") String token,
                                                      @Field("id") int id);
    }
}
