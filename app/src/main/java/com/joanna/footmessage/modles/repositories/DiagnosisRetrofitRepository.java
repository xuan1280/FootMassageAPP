package com.joanna.footmessage.modles.repositories;

import android.util.Log;

import com.joanna.footmessage.Secret;
import com.joanna.footmessage.modles.entities.DiagnosisResult;
import com.joanna.footmessage.modles.entities.PressureData;
import com.joanna.footmessage.modles.models.DiagnosisResultModel;
import com.joanna.footmessage.modles.models.PressureDataModel;
import com.joanna.footmessage.modles.models.ResponseModel;
import com.joanna.footmessage.utils.ResponseUtils;
import com.joanna.footmessage.views.activities.MainActivity;

import java.io.IOException;
import java.util.Date;
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

    public DiagnosisRetrofitRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Secret.IP)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        diagnosisAPI = retrofit.create(DiagnosisRetrofitRepository.DiagnosisAPI.class);
    }

    @Override
    public ResponseModel<Integer> startDiagnosis(int id, String account, String token) throws IOException {
        Response<ResponseModel<Integer>> response = diagnosisAPI.startDiagnosis(id, account, token).execute();
        ResponseModel<Integer> responseModel = ResponseUtils.getBody(response);
        assert responseModel != null;
        Log.d(TAG, "startDiagnosis--");
        return responseModel;
    }

    @Override
    public ResponseModel<Integer> sendPressureData(PressureDataModel pressureDataModel) throws IOException {
        Response<ResponseModel<Integer>> response = diagnosisAPI.sendPressureData(pressureDataModel.getAccount(), pressureDataModel.getToken(),
                pressureDataModel.getRId(), pressureDataModel.getPressureDataList(), pressureDataModel.getPainful()).execute();
        ResponseModel<Integer> responseModel = ResponseUtils.getBody(response);
        assert responseModel != null;
        Log.d(TAG, responseModel.toString());
        return responseModel;
    }

    @Override
    public ResponseModel<DiagnosisResult> getDiagnosisResult(DiagnosisResultModel diagnosisResultModel) throws IOException {
        Response<ResponseModel<DiagnosisResult>> response = diagnosisAPI.getDiagnosisResult(diagnosisResultModel.getAccount(), diagnosisResultModel.getToken(), diagnosisResultModel.getRId()).execute();
        ResponseModel<DiagnosisResult> responseModel = ResponseUtils.getBody(response);
        assert responseModel != null;
        Log.d(TAG, responseModel.toString());
        return responseModel;
    }

    public interface DiagnosisAPI {
        String RESOURCE = "PHP/FAPP";

        @Headers("Content-Type:application/x-www-form-urlencoded")
        @FormUrlEncoded
        @POST(RESOURCE + "/getRID.php")
        Call<ResponseModel<Integer>> startDiagnosis(@Field("id") int id,
                                                    @Field("account") String account,
                                                    @Field("skey") String token);

        @Headers("Content-Type:application/x-www-form-urlencoded")
        @FormUrlEncoded
        @POST(RESOURCE + "/sendPressureData")
        Call<ResponseModel<Integer>> sendPressureData(@Field("account") String account,
                                                      @Field("skey") String token,
                                                      @Field("RID") int rId,
                                                      @Field("pressureData") List<PressureData> pressureData,
                                                      @Field("painful") int painful);

        @Headers("Content-Type:application/x-www-form-urlencoded")
        @FormUrlEncoded
        @POST("/getDiagnosisResult")
        Call<ResponseModel<DiagnosisResult>> getDiagnosisResult(@Field("account") String account,
                                                                @Field("skey") String token,
                                                                @Field("RID") int rId);
    }
}
