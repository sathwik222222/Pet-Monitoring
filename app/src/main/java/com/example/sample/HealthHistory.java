package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class HealthHistory extends AppCompatActivity {
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_history);
        Intent intent=getIntent();
        String petname=intent.getStringExtra("petname");
        final String petsublist=intent.getStringExtra("petsublist");
        Toast.makeText(this,petname+"->"+petsublist,Toast.LENGTH_LONG).show();
        /**
         *
         *
         *
         * Retrieve Data from
         * database
         * date and pet problem
         * and set
         * date , problem
         *
         *
         *
         * **/


        String[] date={"01/12/2012","02/10/2020","23/10/1998","09/08/1997"};
        String[] problem={"Problem of Asthama and shivering due to ceasers and trauma","Chronic Disorder","Shivering, No Movement and Alergy due to high concentration environmental locations","Alergy"};
        HealthHistoryAdapter adapter=new HealthHistoryAdapter(this, date, problem);
        list=(ListView)findViewById(R.id.healthlist);
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
