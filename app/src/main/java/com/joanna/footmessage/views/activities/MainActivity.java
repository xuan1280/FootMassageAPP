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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private final static String TAG = "MainActivity";
    private User user;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerlayout;
    @BindView(R.id.userInfoNavBtn)
    Button userInfoNavBtn;
    @BindView(R.id.diagnosisNavBtn)
    Button diagnosisNavBtn;
    @BindView(R.id.healthInfoNavBtn)
    Button healthInfoNavBtn;
    @BindView(R.id.userRecordNavBtn)
    Button userRecordNavBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        user = (User) getIntent().getSerializableExtra("user");

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerlayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerlayout.addDrawerListener(toggle);
        toggle.syncState();

        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);

        setListeners();
    }

    private void setListeners() {
        userInfoNavBtn.setOnClickListener(this);
        diagnosisNavBtn.setOnClickListener(this);
        healthInfoNavBtn.setOnClickListener(this);
        userRecordNavBtn.setOnClickListener(this);
    }

    public void onUserInfoBtnClick(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, MemberActivity.class);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
        } else if (id == R.id.nav_gallery) {
        } else if (id == R.id.nav_slideshow) {
        } else if (id == R.id.nav_manage) {
        } else if (id == R.id.nav_share) {
        } else if (id == R.id.nav_send) {
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
