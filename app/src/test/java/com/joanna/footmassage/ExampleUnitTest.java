package com.joanna.footmassage;

import android.util.Log;

import com.joanna.footmassage.modles.entities.DiagnosisResult;
import com.joanna.footmassage.modles.models.DiagnosisResultModel;
import com.joanna.footmassage.modles.models.PressureDataModel;
import com.joanna.footmassage.modles.models.ResponseModel;
import com.joanna.footmassage.modles.repositories.DiagnosisRepository;
import com.joanna.footmassage.modles.repositories.DiagnosisRetrofitRepository;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void sendData_isCorrect() throws Exception {
        DiagnosisRepository diagnosisRepository = new DiagnosisRetrofitRepository();
        ResponseModel responseModel;
        try {
            PressureDataModel pressureDataModel = new PressureDataModel("joanna", "7501", 204, new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 0, "2018-12-13 19:42:30");
            responseModel = diagnosisRepository.sendPressureData(pressureDataModel);
            assertEquals(200, responseModel.getCode());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getResult_isCorrect() throws Exception {
        DiagnosisRepository diagnosisRepository = new DiagnosisRetrofitRepository();
        ResponseModel responseModel;
        try {
            DiagnosisResultModel diagnosisResultModel = new DiagnosisResultModel("joanna", "7501");
            diagnosisResultModel.setRId(204);
            responseModel = diagnosisRepository.getDiagnosisResult(diagnosisResultModel);
            assertEquals(200, responseModel.getCode());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}