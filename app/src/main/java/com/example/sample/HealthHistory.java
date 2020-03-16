package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class HealthHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_history);
        Intent intent=getIntent();
        String petname=intent.getStringExtra("petname");
        final String petsublist=intent.getStringExtra("petsublist");
        Toast.makeText(this,petname+"->"+petsublist,Toast.LENGTH_LONG).show();


    }
}
