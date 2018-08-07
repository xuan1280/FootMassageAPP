package com.joanna.footmessage.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.joanna.footmessage.R;

public class DiagnosisActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);


        //btn結束頁面跳轉
        Button nextPageBtn結果 = (Button)findViewById(R.id.btn結束);
        nextPageBtn結果.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(DiagnosisActivity.this , DiagnosticResultActivity.class);
                startActivity(intent);
            }
        });

    }
}