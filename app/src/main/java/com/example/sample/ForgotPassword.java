package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgotPassword extends AppCompatActivity {
    EditText mailET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        mailET=findViewById(R.id.usermail);
    }

    private boolean validateMail(String mail){
        String mail_regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(mail_regex);
        Matcher matcher = pattern.matcher(mail);
        if(!matcher.matches()){
            Toast.makeText(this, "Please Enter valid Email ID", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void SendMail(View view) {
        String mail=mailET.getText().toString();
        if(!validateMail(mail)){
            return;
        }else{
            Toast.makeText(this, "Reset Password Mail Sent", Toast.LENGTH_SHORT).show();
        }
        /**
         *
         *
         *
         *
         *
         *
         * Process of Sending Mail
         *
         *
         *
         *
         *
         *
         * */
    }
}
