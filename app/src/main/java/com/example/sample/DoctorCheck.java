package com.example.sample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class DoctorCheck extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_check);



        ((Button)findViewById(R.id.search)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail="";
                expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
                expandableListDetail = ExpandableListDataPump.getData2(mail);
                expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
                expandableListAdapter = new CustomExpandableListAdapter(getApplicationContext(), expandableListTitle, expandableListDetail);
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
                            Intent intent=new Intent(getApplicationContext(),SetHealthHistory.class);
                            intent.putExtra("petname",petname);
                            intent.putExtra("petsublist",petsublist);
                            startActivity(intent);
                        }else if(petsublist.equals("Vaccine Remainder")){
                            Intent intent=new Intent(getApplicationContext(),SetRemainder.class);
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
        });

    }


    public void Logout(View view) {

    }

    public void Searching(View view) {



    }
}
