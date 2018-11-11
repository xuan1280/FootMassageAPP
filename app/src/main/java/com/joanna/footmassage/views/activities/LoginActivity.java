package com.joanna.footmassage.views.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.joanna.footmassage.R;
import com.joanna.footmassage.modles.entities.User;
import com.joanna.footmassage.modles.models.SignInModel;
import com.joanna.footmassage.modles.repositories.UserRetrofitRepository;
import com.joanna.footmassage.presenter.LoginPresenter;
import com.joanna.footmassage.views.base.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginActivity extends AppCompatActivity implements LoginView {
    private final static String TAG = "LoginActivity";
    private LoginPresenter loginPresenter;
    @BindView(R.id.accountEdt)
    EditText accountEdt;
    @BindView(R.id.passwordEdt)
    EditText passwordEdt;
    @BindView(R.id.login_progress)
    View mProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setPasswordEdtActionListener();
        loginPresenter = new LoginPresenter(new UserRetrofitRepository());
        loginPresenter.setLoginView(this);
    }

    private void setPasswordEdtActionListener() {
        passwordEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
    }

    public void onSignInBtnClick(View view) {
        attemptLogin();
    }

    public void onForgotPasswordBtnClick(View view) {
        //TODO: Forgot Password
    }

    public void onCreateAccountBtnClick(View view) {
        //TODO: Create Account
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    private void attemptLogin() {
        accountEdt.setError(null);
        passwordEdt.setError(null);

        String account = accountEdt.getText().toString();
        String password = passwordEdt.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(password)) {
            passwordEdt.setError(getString(R.string.field_required));
            focusView = passwordEdt;
            cancel = true;
        }

        if (TextUtils.isEmpty(account)) {
            accountEdt.setError(getString(R.string.field_required));
            focusView = accountEdt;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            SignInModel signInModel = new SignInModel(account, password);
            loginPresenter.signIn(signInModel);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void onSignInSuccessfully(User user) {
        Log.d(TAG, "user sign in successfully");
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, MainActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }

    @Override
    public void onAccountNoFound() {
        Log.d(TAG, "account is incorrect");
        showProgress(false);
        new AlertDialog.Builder(this)
                .setMessage(getString(R.string.incorrect_account))
                .show();
    }

    @Override
    public void onPasswordNotCorrect() {
        Log.d(TAG, "password is incorrect");
        showProgress(false);
        new AlertDialog.Builder(this)
                .setMessage(getString(R.string.incorrect_password))
                .show();
    }
}

