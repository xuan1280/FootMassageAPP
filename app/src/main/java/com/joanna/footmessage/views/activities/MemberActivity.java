package com.joanna.footmessage.views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.joanna.footmessage.R;
import com.joanna.footmessage.modles.entities.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemberActivity extends AppCompatActivity {
    private static final String TAG = "MemberActivity";
    private User user;
    @BindView(R.id.accountTxt) TextView accountTxt;
    @BindView(R.id.nameTxt) TextView nameTxt;
    @BindView(R.id.genderTxt) TextView genderTxt;
    @BindView(R.id.ageTxt) TextView ageTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        user = (User) getIntent().getSerializableExtra("user");
        accountTxt.setText(user.getAccount());
        nameTxt.setText(user.getName());
        genderTxt.setText(user.getGender() == 0? "女" : "男");
        ageTxt.setText(String.valueOf(user.getAge()));
    }

    public void onWriteQuestionnaireBtnClick(View view) {
    }

    public void onModifyBtnClick(View view) {
    }
}
