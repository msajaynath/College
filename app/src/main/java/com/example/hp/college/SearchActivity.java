package com.example.hp.college;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Entity.College;
import Entity.Courses;

public class SearchActivity extends AppCompatActivity {
    AutoCompleteTextView city, course;
    RadioButton radc, radcou;
    Button search;
    private ImageView logout, my_bio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        logout = (ImageView) findViewById(R.id.logout);
        my_bio = (ImageView) findViewById(R.id.mybio);
        Intent intent=getIntent();
        if(intent.getBooleanExtra("skipped",false))
        {
            logout.setVisibility(View.INVISIBLE);
            my_bio.setVisibility(View.INVISIBLE);


        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedpreferences = getSharedPreferences("Diet", Context.MODE_PRIVATE);

                sharedpreferences.edit().putBoolean("logged", false).commit();
                Toast.makeText(getApplicationContext(), "Logged out!!", Toast.LENGTH_SHORT).show();

                finish();

            }
        });

        my_bio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, MyBioActivity.class);

                startActivity(intent);


            }
        });
        radc = (RadioButton) findViewById(R.id.radcity);
        search = (Button) findViewById(R.id.search);
        radcou = (RadioButton) findViewById(R.id.radcour);
     //   raddist = (RadioButton) findViewById(R.id.raddist);

        city = (AutoCompleteTextView)
                findViewById(R.id.city);
        course = (AutoCompleteTextView)
                findViewById(R.id.course);
        List<College> colList = College.listAll(College.class);
        List<Courses> courseList = College.listAll(Courses.class);
        List<String> sugColl = new ArrayList<String>();
        List<String> sugCity = new ArrayList<String>();
        ;

        for (College s : colList) {
            if (!sugCity.contains(s.city)) {
                sugCity.add(s.city);

            }
        }


        for (Courses s : courseList) {
            if (!sugColl.contains(s.name)) {
                sugColl.add(s.name);

            }
        }
        course.setEnabled(false);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, sugCity);

        city.setThreshold(1);
        city.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, sugColl);

        course.setThreshold(1);
        course.setAdapter(adapter2);
        radc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    course.setEnabled(false);
                    city.setEnabled(true);
                    radcou.setChecked(false);
                    course.setText("");
                   // raddist.setChecked(false);
                }

            }
        });


        radcou.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    course.setEnabled(true);
                    city.setEnabled(false);
                   // raddist.setChecked(false);
                    radc.setChecked(false);
                    city.setText("");


                }

            }
        });

//        raddist.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    course.setEnabled(false);
//                    city.setEnabled(false);
//                    radcou.setChecked(false);
//                    radc.setChecked(false);
//                }
//
//            }
//        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radc.isChecked()) {
                    Intent intent = new Intent(SearchActivity.this, ResultActivity.class);
                    intent.putExtra("iscity", true);
                    intent.putExtra("city", city.getText().toString());
                    startActivity(intent);

                }

                if (radcou.isChecked()) {
                    Intent intent = new Intent(SearchActivity.this, ResultActivity.class);
                    intent.putExtra("iscourse", true);
                    intent.putExtra("course", course.getText().toString());
                    startActivity(intent);

                }

//                if (raddist.isChecked()) {
//                    Intent intent = new Intent(SearchActivity.this, ResultActivity.class);
//                    intent.putExtra("isloc", true);
//                    startActivity(intent);
//
//                }

            }
        });
    }

}
