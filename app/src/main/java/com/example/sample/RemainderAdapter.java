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

public class RemainderAdapter extends ArrayAdapter<String> {

    private Activity context;
    private int[] days_remaining;
    private int[] gap_days;
    private String[] vaccine;

    public RemainderAdapter(@NonNull Context context,String[] vaccine, int[] days_remaining,int[] gap_days) {
        super(context, R.layout.remainder_listview,vaccine);
        this.context= (Activity) context;
        this.days_remaining=days_remaining;
        this.gap_days=gap_days;
        this.vaccine=vaccine;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.remainder_listview, null,true);

        TextView vaccine_name = (TextView) rowView.findViewById(R.id.vaccine_name);
        TextView vaccine_gap = (TextView) rowView.findViewById(R.id.vaccine_gap);
        TextView vaccine_remaining_days = (TextView) rowView.findViewById(R.id.vaccine_remaining_days);

        vaccine_name.setText(vaccine[position]);
        vaccine_gap.setText(gap_days[position]+"");
        vaccine_remaining_days.setText(days_remaining[position]+"");

        return rowView;
    }
}
