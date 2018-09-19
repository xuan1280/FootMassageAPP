package com.joanna.footmessage.views.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.joanna.footmessage.R;
import com.joanna.footmessage.modles.entities.User;
import com.joanna.footmessage.modles.models.SignUpModel;
import com.joanna.footmessage.modles.repositories.StubUserRepository;
import com.joanna.footmessage.presenter.SignUpPresenter;
import com.joanna.footmessage.views.base.SignUpView;

import butterknife.BindView;

public class SignUpActivity extends AppCompatActivity implements SignUpView{
    private final static String TAG = "LoginActivity";
    private SignUpPresenter signUpPresenter;
    @BindView(R.id.accountEdt) EditText accountEdt;
    @BindView(R.id.passwordEdt) EditText passwordEdt;
    @BindView(R.id.nameEdt) EditText nameEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUpPresenter = new SignUpPresenter(new StubUserRepository());
        signUpPresenter.setSignUpView(this);
    }

    public void onSignUpBtnClick(View view) {
        String account = accountEdt.getText().toString();
        String password = passwordEdt.getText().toString();
        String name = nameEdt.getText().toString();
        SignUpModel signUpModel = new SignUpModel(account, password, name);
        signUpPresenter.signUp(signUpModel);
    }

    @Override
    public void onSignUpSuccessfully(User user) {
        Log.d(TAG, "user sign up successfully");
        Intent intent = new Intent();
        intent.setClass(SignUpActivity.this, MainActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }

    @Override
    public void onAccountHasExisted() {
        Log.d(TAG, "account has existed");
    }
}
