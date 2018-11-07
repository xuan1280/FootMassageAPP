package com.joanna.footmessage.factory;

import com.joanna.footmessage.modles.repositories.DiagnosisRepository;
import com.joanna.footmessage.modles.repositories.UserRepository;

public interface ComponentAbstractFactory {
    UserRepository getUserRepository();
    DiagnosisRepository getDiagnosisRepository();
}
