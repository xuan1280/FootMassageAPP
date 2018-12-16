package com.joanna.footmassage.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.joanna.footmassage.R;
import com.joanna.footmassage.modles.entities.User;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = "MainActivity";
    private static final int EDIT=1;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        user = (User) getIntent().getSerializableExtra("user");
        showWelcomeDialog();
    }

    private void showWelcomeDialog() {
        new AlertDialog.Builder(this)
                .setTitle("歡迎")
                .setMessage(user.getName() + "，您好～")
                .setNegativeButton("OK", null)
                .show();
    }

    public void onUserInfoBtnClick(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, MemberActivity.class);
        intent.putExtra("user", user);
        startActivityForResult(intent, EDIT);
    }

    public void onDiagnosisBtnClick(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, DiagnosisActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void onHealthInfoBtnClick(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, HealthInformationActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.userInfoNavBtn:
                onUserInfoBtnClick(view);
                break;
            case R.id.diagnosisNavBtn:
                onDiagnosisBtnClick(view);
                break;
            case R.id.healthInfoNavBtn:
                onHealthInfoBtnClick(view);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK)
            switch(requestCode){
                case EDIT:
                    user = (User) data.getSerializableExtra("user");
                    break;
            }
    }
}
