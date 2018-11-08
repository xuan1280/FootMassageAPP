package com.joanna.footmessage;


import android.app.AlertDialog;
import android.content.Context;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.joanna.footmessage.modles.entities.User;

public class ModifyUserInformationAlertDialog extends AlertDialog {
    private User user;
    private AlertDialog.Builder builder;

    public ModifyUserInformationAlertDialog(Context context) {
        super(context);
    }

    public ModifyUserInformationAlertDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public ModifyUserInformationAlertDialog(Context context, int themeResId, User user) {
        super(context, themeResId);
        this.user = user;
        TextView accountTxt = findViewById(R.id.accountTxt);
        EditText nameEdt = findViewById(R.id.nameEdt);
        EditText passwordEdt = findViewById(R.id.passwordEdt);
        EditText ageEdt = findViewById(R.id.ageEdt);
        RadioButton maleRadBtn = findViewById(R.id.maleRadBtn);
    }
}
