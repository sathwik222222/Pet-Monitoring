package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class Remainder extends AppCompatActivity {

    public int[] days_remaining;
    public int[] gap_days;
    public String[] vaccine;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remainder);

        Intent intent=getIntent();
        String petname=intent.getStringExtra("petname");
        final String petsublist=intent.getStringExtra("petsublist");
        Toast.makeText(this,petname+"->"+petsublist,Toast.LENGTH_LONG).show();

        vaccine= new String[]{"Distemper", "Measles", "Parainfluenza", "DHPP", "Rabies"};
        days_remaining= new int[]{12, 10, 1, 19, 22};
        gap_days= new int[]{30, 20, 15, 25, 25};

        RemainderAdapter adapter=new RemainderAdapter(this, vaccine, days_remaining,gap_days);
        list=(ListView)findViewById(R.id.remainder);
        list.setAdapter(adapter);

    }

    public void Logout(View view) {
        /**
         *
         *
         *
         *
         *logout
         * and
         * remove shared preferences
         *
         *
         *
         *
         * */
        startActivity(new Intent(this,MainActivity.class));
    }
}
