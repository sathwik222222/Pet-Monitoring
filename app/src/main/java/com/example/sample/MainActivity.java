package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void changeToPetsIntent(View view) {
        startActivity(new Intent(this,PetsDetails.class));
    }


    public void signup(View view) {
        startActivity(new Intent(this,CreateAccount.class));
    }

    public void ForgotPassword(View view) {
        startActivity(new Intent(this,ForgotPassword.class));
    }

}

