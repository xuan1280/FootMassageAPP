package com.joanna.footmassage.views.activities;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.joanna.footmassage.R;
import com.joanna.footmassage.modles.entities.Question;
import com.joanna.footmassage.modles.entities.User;
import com.joanna.footmassage.modles.models.ModifyUserInformationModel;
import com.joanna.footmassage.modles.models.QuestionnaireAnswerModel;
import com.joanna.footmassage.modles.repositories.StubUserRepository;
import com.joanna.footmassage.modles.repositories.UserRetrofitRepository;
import com.joanna.footmassage.presenter.MemberPresenter;
import com.joanna.footmassage.views.base.MemberView;

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
        Log.d(TAG, "onModifyBtnClick");
        view = LayoutInflater.from(MemberActivity.this).inflate(R.layout.dialog_modify_user_information, null);
        AlertDialog.Builder modificationAlertDialog = new AlertDialog.Builder(this);
        modificationAlertDialog.setView(view);
        modificationAlertDialog.setTitle(R.string.modify_information);

        TextView accountTxt = view.findViewById(R.id.accountTxt);
        EditText nameEdt = view.findViewById(R.id.nameEdt);
        EditText passwordEdt = view.findViewById(R.id.passwordEdt);
        EditText ageEdt = view.findViewById(R.id.ageEdt);
        RadioButton maleRadBtn = view.findViewById(R.id.maleRadBtn);
        RadioButton femaleRadBtn = view.findViewById(R.id.femaleRadBtn);
        accountTxt.setText(user.getAccount());
        nameEdt.setText(user.getName());
        passwordEdt.setText(user.getPassword());
        ageEdt.setText(String.valueOf(user.getAge()));
        if (user.getGender() == 0) femaleRadBtn.setChecked(true);
        
        modificationAlertDialog.setNegativeButton(R.string.cancel, (dialog, which) ->
            Toast.makeText(getBaseContext(), R.string.cancel_modify, Toast.LENGTH_SHORT).show());
        
        modificationAlertDialog.setPositiveButton(R.string.confirm, (dialog, which) -> {
            int gender = (maleRadBtn.isChecked()) ? 1 : 0;
            ModifyUserInformationModel modifyUserInformationModel = new ModifyUserInformationModel(
                    user.getAccount(), user.getToken(), nameEdt.getText().toString(), passwordEdt.getText().toString(),
                    Integer.parseInt(ageEdt.getText().toString()), gender);
            memberPresenter.modifyUserInformation(modifyUserInformationModel);
        });

        modificationAlertDialog.show();
    }

    @Override
    public void onQuestionnaireGotSuccessfully(List<Question> questions) {
        showQuestionDialog(questions, 0);
    }

    private void showQuestionDialog(List<Question> questions, int index) {
        AlertDialog.Builder questionnaireAlertDialog = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(MemberActivity.this).inflate(R.layout.dialog_questionnaire, null);
        TextView questionTxt = view.findViewById(R.id.questionTxt);
        RadioButton[] radBtns = {view.findViewById(R.id.radBtn1), view.findViewById(R.id.radBtn2), view.findViewById(R.id.radBtn3)};
        Question question = questions.get(index);
        String questionNumber =  "(" + String.valueOf(index + 1) + "/" + String.valueOf(questions.size()) + ")";
        questionTxt.setText(String.format("%s %s", questionNumber, question.getQuestion()));

        for (int i = 0; i < question.getItems().size(); i++)
            radBtns[i].setText(question.getItems().get(i));
        if (question.getItems().size() == 3) radBtns[2].setVisibility(View.VISIBLE);

        questionnaireAlertDialog.setView(view);
        questionnaireAlertDialog.setNegativeButton(R.string.cancel, (dialog, which) ->
                Toast.makeText(getBaseContext(), R.string.cancel, Toast.LENGTH_SHORT).show());
        questionnaireAlertDialog.setPositiveButton(R.string.confirm, (dialog, which) -> {
            int checkedIndex = 0;
            for (int i = 0; i < radBtns.length; i++)
                if (radBtns[i].isChecked())
                    checkedIndex = i;
            questions.get(index).setAnswer(checkedIndex);
            if (index < questions.size() - 1)
                showQuestionDialog(questions, index + 1);
            else {
                QuestionnaireAnswerModel questionnaireAnswerModel = new QuestionnaireAnswerModel(user.getAccount(), user.getToken(), questions);
                memberPresenter.sendHealthQuestionnaire(questionnaireAnswerModel);
            }
        });
        questionnaireAlertDialog.show();
    }

    @Override
    public void onQuestionnaireGotFailed() {
        Toast.makeText(this, "取得問卷失敗", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onQuestionnaireSavedSuccessfully(List<Question> questions) {
        new AlertDialog.Builder(this)
                .setMessage("感謝填寫問卷~~~")
                .setPositiveButton(R.string.confirm, null)
                .show();
    }

    @Override
    public void onQuestionnaireSavedFailed() {
        Toast.makeText(this, "問卷送出失敗", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onModifyUserInformationSuccessfully(User user) {
        this.user.setName(user.getName());
        this.user.setPassword(user.getPassword());
        this.user.setAge(user.getAge());
        this.user.setGender(user.getGender());
        setupUserInformation();
        new AlertDialog.Builder(this)
                .setMessage("修改資料成功~~~")
                .setPositiveButton(R.string.confirm, null)
                .show();
    }

    @Override
    public void onModifyUserInformationFailed() {
        Toast.makeText(this, "修改失敗", Toast.LENGTH_LONG).show();
    }
}
