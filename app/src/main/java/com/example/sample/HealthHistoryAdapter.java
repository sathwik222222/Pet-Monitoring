package com.example.sample;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class HealthHistoryAdapter extends ArrayAdapter<String> {
    String[] date;
    String[] problem;
    private Activity context;
    public HealthHistoryAdapter(@NonNull Context context,String[] date,String[] problem) {
        super(context,R.layout.health_history_list,date);
        this.context= (Activity) context;
        this.date=date;
        this.problem=problem;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.health_history_list, null,true);
        TextView date_TV = (TextView) rowView.findViewById(R.id.date);
        TextView problem_TV = (TextView) rowView.findViewById(R.id.problem);
        date_TV.setText(date[position]);
        problem_TV.setText(problem[position]);

        return rowView;
    }
}
