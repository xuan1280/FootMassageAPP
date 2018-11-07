package com.joanna.footmessage.views.activities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.joanna.footmessage.R;
import com.joanna.footmessage.modles.entities.User;
import com.joanna.footmessage.presenter.MemberPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemberActivity extends AppCompatActivity {
    private static final String TAG = "MemberActivity";
    private User user;
    private MemberPresenter memberPresenter;
    @BindView(R.id.accountTxt) TextView accountTxt;
    @BindView(R.id.nameTxt) TextView nameTxt;
    @BindView(R.id.genderTxt) TextView genderTxt;
    @BindView(R.id.ageTxt) TextView ageTxt;
    @BindView(R.id.radBtn1) RadioButton radioButton1;
    @BindView(R.id.radBtn2) RadioButton radioButton2;
    @BindView(R.id.radBtn3) RadioButton radioButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        user = (User) getIntent().getSerializableExtra("user");
        memberPresenter = new MemberPresenter();
        accountTxt.setText(user.getAccount());
        nameTxt.setText(user.getName());
        genderTxt.setText(user.getGender() == 0? "女" : "男");
        ageTxt.setText(String.valueOf(user.getAge()));
    }

    public void onWriteQuestionnaireBtnClick(View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setView(R.layout.dialog_questionnaire);
        alertDialog.setNegativeButton("Cancel", (dialog, which) -> {
            Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
        });
    }

    public void onModifyBtnClick(View view) {
    }
}
