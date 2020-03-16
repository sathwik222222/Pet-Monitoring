package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Remainder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remainder);

        Intent intent=getIntent();
        String petname=intent.getStringExtra("petname");
        final String petsublist=intent.getStringExtra("petsublist");
        Toast.makeText(this,petname+"->"+petsublist,Toast.LENGTH_LONG).show();


    }
}
