package com.joanna.footmessage.utils;

import com.joanna.footmessage.modles.models.ResponseModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Response;

public class ResponseUtils {

    public static <T> ResponseModel<T> convertErrorBody(String json){
        try {
            JSONObject jsonObj = new JSONObject(json);
            return new ResponseModel<>(jsonObj.getInt("code"),
                    jsonObj.getString("message"),
                    null);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> ResponseModel<T> getBody(Response<ResponseModel<T>> response) throws IOException {
        if (response.isSuccessful())
            return response.body();
        else
        {
            String errorResponse = response.errorBody().string();
            return ResponseUtils.convertErrorBody(errorResponse);
        }
    }
}
