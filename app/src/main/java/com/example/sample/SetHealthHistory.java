package com.example.sample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class SetHealthHistory extends AppCompatActivity {
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_health_history);

        Intent intent=getIntent();
        String petname=intent.getStringExtra("petname");
        final String petsublist=intent.getStringExtra("petsublist");
        Toast.makeText(this,petname+"->"+petsublist,Toast.LENGTH_LONG).show();

        String[] date={"01/12/2012","02/10/2020","23/10/1998","09/08/1997"};
        String[] problem={"Problem of Asthama and shivering due to ceasers and trauma","Chronic Disorder","Shivering, No Movement and Alergy due to high concentration environmental locations","Alergy"};
        HealthHistoryAdapter adapter=new HealthHistoryAdapter(this, date, problem);
        list=(ListView)findViewById(R.id.health_list);
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

    public void new_history(View view) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.new_health_history, null);
        dialogBuilder.setView(dialogView);

        Button add=dialogView.findViewById(R.id.add_problem);
        final EditText petproblem = (EditText) dialogView.findViewById(R.id.petproblem);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check_Problem(petproblem.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Added Medical History", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }else{
                    Toast.makeText(getApplicationContext(), "Please check the typed note", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean check_Problem(String problem) {
        if(problem.trim().length()==0){
            return false;
        }
        return true;
    }

}
