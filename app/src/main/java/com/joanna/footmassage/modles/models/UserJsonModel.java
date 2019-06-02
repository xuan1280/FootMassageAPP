package com.joanna.footmassage.modles.models;

import com.joanna.footmassage.modles.entities.PressureData;
import com.joanna.footmassage.modles.entities.Record;

import java.util.List;

public class UserJsonModel {
    private int id;
    private String token;
    private String name;
    private String account;
    private String password;
    private int age;
    private int gender;
    private List<Integer> questionnaireAnswers;
    private List<Record> records;
}
