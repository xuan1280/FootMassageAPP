package com.joanna.footmassage.modles.repositories;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.joanna.footmassage.DataBaseOpenHelper;
import com.joanna.footmassage.modles.entities.DiagnosisResult;
import com.joanna.footmassage.modles.entities.Question;
import com.joanna.footmassage.modles.entities.User;
import com.joanna.footmassage.modles.models.BasicModel;
import com.joanna.footmassage.modles.models.ModifyUserInformationModel;
import com.joanna.footmassage.modles.models.QuestionnaireAnswerModel;
import com.joanna.footmassage.modles.models.ResponseModel;
import com.joanna.footmassage.modles.models.SignInModel;
import com.joanna.footmassage.modles.models.SignUpModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

public class UserSqliteRepository implements UserRepository {
    private final static String TAG = "UserSqliteRepository";
    private SQLiteDatabase database;
    private DataBaseOpenHelper dataBaseOpenHelper;
    private String token = "7501";

    public UserSqliteRepository(Context context) {
        dataBaseOpenHelper = new DataBaseOpenHelper(context);
        database = dataBaseOpenHelper.getWritableDatabase();
        createUsers();
    }

    private void createUsers() {
        Log.d(TAG, "createUsers");
        List<User> users = new ArrayList<>();
        users.add(new User("7501", 1, "joanna", "000", "Joanna", 21, 0));

        for (User user: users) {
            Cursor cursor = database.rawQuery("SELECT * FROM User WHERE account = ? and password = ?",
                    new String[]{user.getAccount(), user.getPassword()});
            if (cursor.getCount() == 0){
                Log.d(TAG, "create user: " + user.getName());
                String sql = String.format("INSERT INTO User (name, account, password, age, gender) " +
                        "VALUES ('%s', '%s', '%s', '%d', '%d')", user.getName(), user.getAccount(), user.getPassword(), user.getAge(), user.getGender());
                database.execSQL(sql);
            }
            cursor.close();
        }

    }

    @Override
    public ResponseModel<User> signIn(SignInModel signInModel) throws IOException {
        Log.d(TAG, "signIn");
        Cursor cursor = database.rawQuery("SELECT * FROM User WHERE account = ? and password = ?",
                new String[]{signInModel.getAccount(), signInModel.getPassword()});
        if (cursor.getCount() == 0)
            return new ResponseModel<>(101, "No matched account found.", null);
        User signInUser = getUserFromCursor(cursor);
        cursor.close();
        Log.d(TAG, signInUser.toString());
        if (signInUser.getPassword().equals(signInModel.getPassword()))
            return new ResponseModel<>(200, "successful.", signInUser);
        return new ResponseModel<>(102, "The password is incorrect", null);
    }

    private User getUserFromCursor(Cursor cursor) {
        cursor.moveToFirst();
        int id = cursor.getInt(0);
        String name = cursor.getString(1);
        String account = cursor.getString(2);
        String password = cursor.getString(3);
        int age = cursor.getInt(4);
        int gender = cursor.getInt(5);
        return new User(token, id, name, account, password, age, gender);
    }

    @Override
    public ResponseModel<User> signUp(SignUpModel signUpModel) throws IOException {
        return null;
    }

    @Override
    public ResponseModel<Question[]> getHealthQuestions() throws IOException {
        return null;
    }

    @Override
    public ResponseModel sendHealthQuestionnaire(QuestionnaireAnswerModel questionnaireAnswerModel) throws IOException {
        return null;
    }

    @Override
    public ResponseModel<User> modifyUserInformation(ModifyUserInformationModel modifyUserInformationModel) throws IOException {
        return null;
    }

    @Override
    public ResponseModel<DiagnosisResult[]> getDiagnosisRecord(BasicModel basicModel) throws IOException {
        return null;
    }
}
