package com.joanna.footmessage.views.activities;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.joanna.footmessage.ModifyUserInformationAlertDialog;
import com.joanna.footmessage.R;
import com.joanna.footmessage.modles.entities.Question;
import com.joanna.footmessage.modles.entities.User;
import com.joanna.footmessage.modles.models.ModifyUserInformationModel;
import com.joanna.footmessage.modles.repositories.UserRetrofitRepository;
import com.joanna.footmessage.presenter.MemberPresenter;
import com.joanna.footmessage.views.base.MemberView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemberActivity extends AppCompatActivity implements MemberView {
    private static final String TAG = "MemberActivity";
    private User user;
    private MemberPresenter memberPresenter;
    @BindView(R.id.accountTxt)
    TextView accountTxt;
    @BindView(R.id.nameTxt)
    TextView nameTxt;
    @BindView(R.id.genderTxt)
    TextView genderTxt;
    @BindView(R.id.ageTxt)
    TextView ageTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        user = (User) getIntent().getSerializableExtra("user");

        memberPresenter = new MemberPresenter(new UserRetrofitRepository());
        memberPresenter.setMemberView(this);

        setupUserInformation();
    }

    private void setupUserInformation() {
        accountTxt.setText(user.getAccount());
        nameTxt.setText(user.getName());
        genderTxt.setText(user.getGender() == 0 ? "女" : "男");
        ageTxt.setText(String.valueOf(user.getAge()));
    }

    public void onUserRecordBtnClick(View view) {
        // todo
    }

    public void onWriteQuestionnaireBtnClick(View view) {
        memberPresenter.getHealthQuestionnaire();
    }

    public void onModifyBtnClick(View view) {
        // todo
        Log.d(TAG, "onModifyBtnClick");
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(R.layout.dialog_modify_user_information);
        alertDialogBuilder.setTitle("修改資料");

        alertDialogBuilder.setNegativeButton("Cancel", (dialog, which) ->
            Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show());
        alertDialogBuilder.setPositiveButton("OK", (dialog, which) -> {
            EditText nameEdt = findViewById(R.id.nameEdt);
            EditText passwordEdt = findViewById(R.id.passwordEdt);
            EditText ageEdt = findViewById(R.id.ageEdt);
            RadioButton maleRadBtn = findViewById(R.id.maleRadBtn);
            int gender = (maleRadBtn.isChecked()) ? 1 : 0;
            ModifyUserInformationModel modifyUserInformationModel = new ModifyUserInformationModel(
                    user.getAccount(), user.getToken(), nameEdt.getText().toString(), passwordEdt.getText().toString(),
                    Integer.parseInt(ageEdt.getText().toString()), gender);
            memberPresenter.modifyUserInformation(modifyUserInformationModel);
        });
        AlertDialog alertDialog = alertDialogBuilder.create();

        TextView accountTxt = findViewById(R.id.accountTxt);
        EditText nameEdt = findViewById(R.id.nameEdt);
        EditText passwordEdt = findViewById(R.id.passwordEdt);
        EditText ageEdt = findViewById(R.id.ageEdt);
        accountTxt.setText(user.getAccount());
        nameEdt.setText(user.getName());
        passwordEdt.setText(user.getPassword());
        ageEdt.setText(user.getAge());

        alertDialog.show();
    }

    @Override
    public void onQuestionnaireGotSuccessfully(List<Question> questions) {
        // todo
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setView(R.layout.dialog_questionnaire);
        alertDialog.setNegativeButton("Cancel", (dialog, which) -> {
            Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onQuestionnaireGotFailed() {
        Toast.makeText(this, "取得問卷失敗", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onModifyUserInformationSuccessfully(User user) {
        setupUserInformation();
    }

    @Override
    public void onModifyUserInformationFailed() {
        Toast.makeText(this, "修改失敗", Toast.LENGTH_LONG).show();
    }
}
