package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccount extends AppCompatActivity {
    public String name,mail,phone,password,user_type;
    public EditText nameET,mailET,phoneET,passwordET;
    public RadioGroup userTypeRG;
    RadioButton rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        nameET=findViewById(R.id.usersname);
        mailET=findViewById(R.id.usermail);
        phoneET=findViewById(R.id.userphone);
        passwordET=findViewById(R.id.userpwd);
        userTypeRG=findViewById(R.id.user_type);



    }

    public void SignUp(View view) {
        name=nameET.getText().toString();
        mail=mailET.getText().toString();
        phone=phoneET.getText().toString();
        password=passwordET.getText().toString();
        Boolean radiostate1=((RadioButton)findViewById(R.id.doctor)).isChecked();
        Boolean radiostate2=((RadioButton)findViewById(R.id.patient)).isChecked();
        if(radiostate1){
            user_type="Doctor";
        }else{
            user_type="Patient";
        }

        if(validateAll(name,mail,phone,password)){
            /**
             *
             *
             *
             *
             * Process to signUp
             *
             *
             *
             *
             *
             * */
            Toast.makeText(this, "Conformation Mail Sent", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,MainActivity.class));

        }
    }
    public  boolean validateAll(String name,String mail,String phone,String password){



        if(!(validateUsersname(name)&&validateMail(mail)&&validatePassword(password)&&validatePhone(phone))){
            return false;
        }

        return true;
    }
    private boolean validateUsersname(String name){
        if(name.contains(" ")){
            Toast.makeText(this, "Spaces are not allowed", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(name.trim().length()==0){
            Toast.makeText(this, "Please Enter valid name", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
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
    private boolean validatePhone(String phone){
        if(phone.contains(" ")){
            Toast.makeText(this, "Spaces are not allowed", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(phone.length()!=10){
            Toast.makeText(this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private boolean validatePassword(String password){
        if(password.contains(" ")){
            Toast.makeText(this, "Spaces are not allowed", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password.length()<6){
            Toast.makeText(this, "Password minimum length is 6", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}
