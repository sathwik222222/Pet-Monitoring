package com.example.sample;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

public class HealthHistoryAdapter extends ArrayAdapter<String> {
    public HealthHistoryAdapter(@NonNull Context context,String[] name,String[] problem) {
        super(context,R.layout.list_item);

    }
}
