package com.fitucab.ds1617b.fitucab.Model;

import android.app.Activity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jesus on 01/07/17.
 */

public class MockM06 {


    public static User getMockUser(){
        User mock = new User(1);
        return mock;
    }

    public static ArrayList<Training> getMockTraining(){
        ArrayList<Training> mockTrain = new ArrayList<>();
        ArrayList<Activit> actList = new ArrayList<Activit>();

        Activit mockAct1 = new Activit(1,"Correr",4);
        Activit mockAct2 = new Activit(2,"Nadar",3);
        actList.add(mockAct1);
        actList.add(mockAct2);
        Training defaultT = new Training(1,"default",actList);
        defaultT.set_trainingOrderInView(1);
        mockTrain.add(defaultT);

        Training training1 = new Training(2,"Maraton CAF",actList);
        training1.set_trainingOrderInView(2);
        mockTrain.add(training1);

        Training training2 = new Training(3,"Carrera Gatetorade",actList);
        training2.set_trainingOrderInView(3);
        mockTrain.add(training2);

        Training training4 = new Training(4,"Diario",actList);
        training4.set_trainingOrderInView(4);
        mockTrain.add(training4);

        return mockTrain;
    }

}
