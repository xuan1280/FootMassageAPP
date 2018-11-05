package com.joanna.footmessage.views.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.joanna.footmessage.R;
import com.joanna.footmessage.modles.entities.User;
import com.joanna.footmessage.modles.models.SignInModel;
import com.joanna.footmessage.modles.models.SignUpModel;
import com.joanna.footmessage.modles.repositories.StubUserRepository;
import com.joanna.footmessage.modles.repositories.UserRetrofitRepository;
import com.joanna.footmessage.presenter.SignUpPresenter;
import com.joanna.footmessage.views.base.SignUpView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements SignUpView{
    private final static String TAG = "LoginActivity";
    private SignUpPresenter signUpPresenter;
    @BindView(R.id.accountEdt) EditText accountEdt;
    @BindView(R.id.passwordEdt) EditText passwordEdt;
    @BindView(R.id.nameEdt) EditText nameEdt;
    @BindView(R.id.ageEdt) EditText ageEdt;
    @BindView(R.id.maleRadBtn) RadioButton maleRadBtn;
    @BindView(R.id.femaleRadBtn) RadioButton femaleRadBtn;
    @BindView(R.id.sign_up_progress) View progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        setPasswordEdtActionListener();
        signUpPresenter = new SignUpPresenter(new UserRetrofitRepository());
        signUpPresenter.setSignUpView(this);
    }

    private void setPasswordEdtActionListener() {
        passwordEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptSignUp();
                    return true;
                }
                return false;
            }
        });
    }

    private void attemptSignUp() {
        accountEdt.setError(null);
        passwordEdt.setError(null);

        String account = accountEdt.getText().toString();
        String password = passwordEdt.getText().toString();
        String name = nameEdt.getText().toString();
        String age = ageEdt.getText().toString();
        int gender = judgeWhichRadioButtonChosen();

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

        if (TextUtils.isEmpty(name)) {
            nameEdt.setError(getString(R.string.field_required));
            focusView = nameEdt;
            cancel = true;
        }

        if (TextUtils.isEmpty(age)) {
            nameEdt.setError(getString(R.string.field_required));
            focusView = nameEdt;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            SignUpModel signUpModel = new SignUpModel(account, password, name, Integer.parseInt(age), gender);
            signUpPresenter.signUp(signUpModel);
        }
    }

    private int judgeWhichRadioButtonChosen() {
        if (maleRadBtn.isChecked())
            return 1;
        else if (femaleRadBtn.isChecked())
            return 0;
        return 1;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        progressView.setVisibility(show ? View.VISIBLE : View.GONE);
        progressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    public void onSignUpBtnClick(View view) {
        attemptSignUp();
    }

    @Override
    public void onSignUpSuccessfully(User user) {
        Log.d(TAG, user.getName() + " signed up successfully");
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
