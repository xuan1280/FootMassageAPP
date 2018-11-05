package com.joanna.footmessage.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.joanna.footmessage.R;
import com.joanna.footmessage.modles.entities.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = "MainActivity";
    public static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        user = (User) getIntent().getSerializableExtra("user");

    }

    public void onUserInfoBtnClick(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, MemberActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void onDiagnosisBtnClick(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, DiagnosisActivity.class);
        startActivity(intent);
    }

    public void onHealthInfoBtnClick(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, HealthInformationActivity.class);
        startActivity(intent);
    }

    public void onDiagnosticRecordBtnClick(View view) {
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
            case R.id.userRecordNavBtn:
                onDiagnosticRecordBtnClick(view);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
}
