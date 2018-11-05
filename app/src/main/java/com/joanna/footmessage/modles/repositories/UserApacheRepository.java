package com.joanna.footmessage.modles.repositories;

import android.annotation.SuppressLint;
import android.util.Log;

import com.google.gson.Gson;
import com.joanna.footmessage.Secret;
import com.joanna.footmessage.modles.entities.User;
import com.joanna.footmessage.modles.models.ResponseModel;
import com.joanna.footmessage.modles.models.SignInModel;
import com.joanna.footmessage.modles.models.SignUpModel;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserApacheRepository implements UserRepository {
    private final static String TAG = "UserHTTPClientRepository";
    @SuppressLint("LongLogTag")
    @Override
    public ResponseModel<User> signIn(SignInModel signInModel) throws IOException {
        HttpPost httpPost = new HttpPost(Secret.IP + "PHP/FAPP/login.php");

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("account", signInModel.getAccount()));
        params.add(new BasicNameValuePair("password", signInModel.getPassword()));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "utf-8");
        httpPost.setEntity(entity);

        HttpResponse httpResponse = new DefaultHttpClient().execute(httpPost);
        String strResult = EntityUtils.toString(httpResponse.getEntity(), HTTP.UTF_8);
        Log.d(TAG, strResult);

        Gson gson = new Gson();
        ResponseModel<User> responseModel = gson.fromJson(strResult, ResponseModel.class);
        assert responseModel != null;
        Log.d(TAG, responseModel.toString());
        return responseModel;
    }

    @Override
    public ResponseModel<User> signUp(SignUpModel signUpModel) throws IOException {
        return null;
    }
}
