package com.example.sample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccount extends AppCompatActivity {
    public String name,mail,phone,password,user_type;
    public EditText nameET,mailET,phoneET,passwordET;
    public RadioGroup userTypeRG;
    RadioButton rb;
    private ProgressBar loadingProgressBar;
    private FirebaseAuth mAuth;
    private String TAG="CreateAccount";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        nameET=findViewById(R.id.usersname);
        mailET=findViewById(R.id.usermail);
        phoneET=findViewById(R.id.userphone);
        passwordET=findViewById(R.id.userpwd);
        userTypeRG=findViewById(R.id.user_type);

        //TODO
        //loadingProgressBar = (ProgressBar)findViewById(R.id.loading);

        mAuth = FirebaseAuth.getInstance();


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

            //TODO
            //loadingProgressBar.setVisibility(View.VISIBLE);
            Log.d(TAG,"email : "+mail);
            mAuth.createUserWithEmailAndPassword(mail, password)
                    .addOnCompleteListener(CreateAccount.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //TODO
                            //loadingProgressBar.setVisibility(View.GONE);
                            Log.d("created user","yes");
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                if(user!=null) {
                                    Log.d("user created",user.toString());
                                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                            .setDisplayName(name)
                                            .build();
                                    user.updateProfile(profileUpdates)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Log.d(TAG, "User profile updated.");
                                                    }
                                                }
                                            });
                                    user.sendEmailVerification()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Log.d(TAG, "Email sent.");
                                                        Toast.makeText(CreateAccount.this, "An Email is on its Way , Please Verify yourself.",
                                                                Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            });
                                    FirebaseAuth.getInstance().signOut();
                                    Intent mainIntent = new Intent(CreateAccount.this, MainActivity.class);
                                    startActivity(mainIntent);
                                    finish();
                                }
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(CreateAccount.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
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
