package com.example.sample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SetRemainder extends AppCompatActivity {
    ListView list;
    String t1="";
    int t2,t3;
    EditText text1,text2,text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_remainder);

        final String[] vaccine={"Distemper","Measles","Parainfluenza","DHPP","Rabies"};
        final int[] days_remaining={12,10,1,19,22};
        final int[] gap_days={30,20,15,25,25};

        final SetRemainderAdapter adapter=new SetRemainderAdapter(this, vaccine, days_remaining,gap_days);
        list=(ListView)findViewById(R.id.remainder_list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                t1=vaccine[position];
                t2=days_remaining[position];
                t3=gap_days[position];

                popUp_Remainder(view);

            }
        });



    }

    public void Logout(View view) {
    }

    public void new_remainder(View view) {
        popUp_Remainder(view);
    }
    public void popUp_Remainder(View view){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.remainder_set, null);
        dialogBuilder.setView(dialogView);

        Button add=dialogView.findViewById(R.id.save_remainder);
        //final EditText petproblem = (EditText) dialogView.findViewById(R.id.petproblem);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        text1=alertDialog.findViewById(R.id.vaccine_name_REM);
        text1.setText(t1);
        text2=alertDialog.findViewById(R.id.remaining_days_REM);
        text2.setText(t2+"");
        text3=alertDialog.findViewById(R.id.interval_days_REM);
        text3.setText(t3+"");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vaccine=text1.getText().toString();
                int rem_days=Integer.parseInt(text2.getText().toString());
                int interval=Integer.parseInt(text3.getText().toString());



                Log.d(vaccine.trim(),vaccine.trim().length()+" ");

                if(interval<=0){
                    Toast.makeText(getApplicationContext(), "Recheck the Interval input", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(vaccine.trim().length()==0||(interval+"").length()==0||(rem_days+"").length()==0){
                    Toast.makeText(getApplicationContext(), "Recheck the input", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean x=Set_Vaccine_Interval(vaccine,interval,rem_days);
                if(x){
                    Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }else{
                    Toast.makeText(getApplicationContext(), "Recheck the input", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });
        t1="";
        t2=0;
        t3=0;
    }

    private boolean Set_Vaccine_Interval(String vaccine,int interval,int rem_days) {
        /**
         *
         *
         *
         * Update
         * or
         * create vaccine interval
         *
         *
         *
         *
         * */
        return true;
    }
}


