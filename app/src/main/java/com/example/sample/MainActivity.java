package com.example.sample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private ProgressBar loadingProgressBar;
    private FirebaseAuth mAuth;
    private String TAG="MainActivity";
    private boolean flag=false;
    private SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO show progress bar while authentication
        //loadingProgressBar = (ProgressBar)findViewById(R.id.loading);

        mAuth = FirebaseAuth.getInstance();

    }
    public void changeToPetsIntent(View view,String utype) {
        if(utype.equals("Doctor")){
            startActivity(new Intent(this,DoctorCheck.class));
        }else{
            startActivity(new Intent(this,PetsDetails.class));
        }
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

        signInWithEmail(view,utype,mailid,password);
    }

    private void signInWithEmail(final View view, final String user_type, String mailid, String password) {

        //TODO
       // loadingProgressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(mailid, password)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //TODO
                        //loadingProgressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if(user.isEmailVerified()){
                                Toast.makeText(MainActivity.this, "Authentication success.",
                                        Toast.LENGTH_SHORT).show();
                                sharedpreferences = getSharedPreferences("PetMonitoring", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedpreferences.edit();

                                editor.putString("user_type", user_type);
                                editor.commit();
                                changeToPetsIntent(view,user_type);
                            }
                            else{
                                Toast.makeText(MainActivity.this,"Please Verify Your Email for Authentication.",Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }


    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        SharedPreferences sh= getSharedPreferences("PetMonitoring", Context.MODE_PRIVATE);

        String utype = sh.getString("user_type", "");
        if(currentUser!=null){
            if(utype.equals("Doctor")){
                startActivity(new Intent(this,DoctorCheck.class));
            }else{
                startActivity(new Intent(this,PetsDetails.class));
            }
        }
    }
}

