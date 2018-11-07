package com.joanna.footmessage.factory;

import com.joanna.footmessage.modles.repositories.DiagnosisRepository;
import com.joanna.footmessage.modles.repositories.DiagnosisRetrofitRepository;
import com.joanna.footmessage.modles.repositories.UserRepository;
import com.joanna.footmessage.modles.repositories.UserRetrofitRepository;

public class ReleasedComponentAbstractFactory implements ComponentAbstractFactory {
    private UserRepository userRepository;
    private DiagnosisRepository diagnosisRepository;

    public ReleasedComponentAbstractFactory() {
        userRepository = new UserRetrofitRepository();
        diagnosisRepository = new DiagnosisRetrofitRepository();
    }

    @Override
    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Override
    public DiagnosisRepository getDiagnosisRepository() {
        return diagnosisRepository;
    }
}
