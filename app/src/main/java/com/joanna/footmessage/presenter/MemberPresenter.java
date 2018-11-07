package com.joanna.footmessage.presenter;

import android.os.Handler;

import com.joanna.footmessage.modles.entities.Question;
import com.joanna.footmessage.modles.repositories.UserRepository;

import java.util.List;

public class MemberPresenter {
    private final static String TAG = "SignUpPresenter";
    private UserRepository userRepository;
    private Handler handler = new Handler();

    public MemberPresenter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Question> getHealthQuestions() {
        return userRepository.getHealthQuestions();
    }
}
