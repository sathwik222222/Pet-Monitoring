package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public void Login(View view) {

        RadioGroup radioGroup=(RadioGroup)findViewById(R.id.user_type);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton rb1 = (RadioButton) findViewById(selectedId);




        if(rb1.getText()==null){
            Toast.makeText(this, "Please select Doctor / Patient", Toast.LENGTH_SHORT).show();
        }
        String utype=rb1.getText().toString();

        Toast.makeText(this, utype, Toast.LENGTH_SHORT).show();

        String mailid=((EditText)findViewById(R.id.usermail)).getText().toString();
        String password=((EditText)findViewById(R.id.userpwd)).getText().toString();




        String mail_regex = "^[A-Za-z]+([_A-Za-z0-9])+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(mail_regex);
        Matcher matcher = pattern.matcher(mailid);


        Log.d("Mail ID",mailid+" "+mailid.length()+" , "+mailid.trim().length());
        Log.d("Password",password+" "+password.length()+" , "+password.trim().length());





        if(mailid.trim().length()==0){
            Toast.makeText(this, "Please Enter valid Mail", Toast.LENGTH_SHORT).show();
            return;
        }if(!matcher.matches()){
            Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
            return;
        }if(password.trim().length()==0){
            Toast.makeText(this, "Please Enter valid Password", Toast.LENGTH_SHORT).show();
            return;
        }if(password.contains(" ")){
            Toast.makeText(this, "Password should not contains spaces", Toast.LENGTH_SHORT).show();
            return;
        }if(password.length()<6){
            Toast.makeText(this, "Password Length is minimum 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }if(selectedId==-1){
            Toast.makeText(MainActivity.this,"Please select Doctor / Patient", Toast.LENGTH_SHORT).show();
            return;
        }


        if(Authentication_Successfull(utype,mailid,password)) {
            changeToPetsIntent(view);
        }else{
            Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
        }



    }

    private boolean Authentication_Successfull(String user_type,String mailid,String password) {
        boolean flag=true;
        /**
         *
         *
         *
         * Code for Authentication
         * and
         * Save Shared Preferences
         *
         *
         *
         *
         *
         *
         * */

        return flag;
    }
}

