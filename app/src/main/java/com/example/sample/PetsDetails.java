package com.example.sample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PetsDetails extends AppCompatActivity {
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets_details);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new_pet(view);
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                String petname=expandableListTitle.get(groupPosition);
                String petsublist=expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition);


                if(petsublist.equals("Health History")){
                    Intent intent=new Intent(getApplicationContext(),HealthHistory.class);
                    intent.putExtra("petname",petname);
                    intent.putExtra("petsublist",petsublist);
                    startActivity(intent);
                }else if(petsublist.equals("Vaccine Remainder")){
                    Intent intent=new Intent(getApplicationContext(),Remainder.class);
                    intent.putExtra("petname",petname);
                    intent.putExtra("petsublist",petsublist);
                    startActivity(intent);
                }

                Toast.makeText(
                        getApplicationContext(),
                        expandableListTitle.get(groupPosition)+ " -> "+ expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition),
                        Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });
    }


    public void new_pet(View view) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.add_pet, null);
        dialogBuilder.setView(dialogView);

        Button add=dialogView.findViewById(R.id.add);
        final EditText petname = (EditText) dialogView.findViewById(R.id.petname);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(check_Duplicate(petname.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Duplicate Pet Found", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Pet Added", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }
            }
        });
    }
    public boolean check_Duplicate(String name){
        boolean flag=false;

        /**
        * check whether the pet name is already added or not
        * if added
        *   alert as duplicate
        * else
        *   add the pet
        * */

        return flag;
    }


    public void Logout(View view) {

        SharedPreferences sharedpreferences = getSharedPreferences("PetMonitoring", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        Toast.makeText(PetsDetails.this,"Logging out....",Toast.LENGTH_SHORT).show();

        editor.putString("user_type", "");
        editor.commit();
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this,MainActivity.class));
        finish();

    }
}
