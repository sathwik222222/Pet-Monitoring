package com.example.sample;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        int len=3;
        String[] petname;

        /*
        *
        * Read the shared Preferences
        * and
        * get usermail
        * with mail we can obtain the
        * list of pets with that user
        *
        *
        *
        *
        * */

        for(int i=0;i<len;i++) {

            List<String> list = new ArrayList<String>();
            list.add("Vaccine Remainder");
            list.add("Health History");
            expandableListDetail.put("Pet "+i, list);
        }
        return expandableListDetail;
    }

    public static HashMap<String, List<String>> getData2(String name) {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        int len=4;
        String[] petname;

        /*
         *
         *
         * with mail we search the
         * list of pets with that user
         *
         *
         *
         * */

        for(int i=0;i<len;i++) {

            List<String> list = new ArrayList<String>();
            list.add("Vaccine Remainder");
            list.add("Health History");
            expandableListDetail.put("Pet "+i, list);
        }
        return expandableListDetail;
    }
}
