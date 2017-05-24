package com.fitucab.ds1617b.fitucab.Model;

public class Training {
    private int _idTraining;
    private String _trainingName;
    private int _trainingPeriod;
    private int _trainingCalories;

    public Training() {} // Constructor vacio

    //Constructor con id
    public Training(int id, String trainingName, int trainingPeriod, int trainingCalories) {
        this._idTraining = id;
        this._trainingName = trainingName;
        this._trainingPeriod = trainingPeriod;
        this._trainingCalories = trainingCalories;
    }

    //Constructor sin id
    public Training(String trainingName, int trainingPeriod, int trainingCalories) {
        this._trainingName = trainingName;
        this._trainingPeriod = trainingPeriod;
        this._trainingCalories = trainingCalories;
    }

    //Getters and setters basicos

    public int getidTraining() {
        return _idTraining;
    }

    public void setId(int id) {
        this._idTraining = id;
    }

    public String getTrainingName() {
        return _trainingName;
    }

    public void setTrainingName(String trainingName) {
        this._trainingName = trainingName;
    }

    public int getTrainingPeriod() {
        return _trainingPeriod;
    }

    public void setTrainingPeriod(int trainingPeriod) {
        this._trainingPeriod = trainingPeriod;
    }

    public int getTrainingCalories() {
        return _trainingCalories;
    }

    public void setTrainingCalories(int trainingCalories) {
        this._trainingCalories = trainingCalories;
    }


}