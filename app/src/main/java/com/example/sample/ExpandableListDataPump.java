package com.example.sample;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        int len=3;
        String[] petname;


        for(int i=0;i<len;i++) {

            List<String> list = new ArrayList<String>();
            list.add("Vaccine Remainder");
            list.add("Health History");
            expandableListDetail.put("Pet "+i, list);
        }
        return expandableListDetail;
    }
}
