package com.example.hp.college;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import Entity.College;
import Entity.Courses;
import Entity.User;

public class LoginActivity extends AppCompatActivity {

    Button login;
    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        seedfunction();
        setContentView(R.layout.activity_login);
        login= (Button) findViewById(R.id.login);
        username= (EditText) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.password);
        final SharedPreferences sharedpreferences = getSharedPreferences("Diet", Context.MODE_PRIVATE);
        if(sharedpreferences.getBoolean("logged",false))
        {
            startActivity(new Intent(LoginActivity.this, SearchActivity.class));
            finish();
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().length() == 0 || password.getText().toString().length() == 0) {

                    Toast.makeText(getApplicationContext(), "Username/Password required!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<User> log = User.find(User.class, "username = ? and password = ?", username.getText().toString(), password.getText().toString());
                if (log.size() == 0) {
                    Toast.makeText(getApplicationContext(), "Invalid credentials!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getApplicationContext(), "Welcome " + log.get(0).name, Toast.LENGTH_SHORT).show();
                sharedpreferences.edit().putBoolean("logged", true).commit();
                sharedpreferences.edit().putLong("userid", log.get(0).getId()).commit();

                startActivity(new Intent(LoginActivity.this, SearchActivity.class));
                finish();
            }
        });

    }

    //for seed data
    private void seedfunction() {
        List<User> userList=User.listAll(User.class);
        if(userList.size()==0)
        {
            new User("user1","test name1","pass").save();
            new User("user2","test name2","pass").save();

            new College("St. Paul's College","","","Kalamassery, HMT Colony, Ernakulam, Kerala 683503","Kalamassery","10.0340046","76.3323177").save();
            new Courses("MCA",College.findById(College.class,1)).save();
            new Courses("BCA",College.findById(College.class,1)).save();

            new College("Government Model Engineering College","","","Thrikkakara, Ernakulam, Kerala 682021","Thrikkakara","10.034223","76.3327952").save();
            new Courses("B Tech",College.findById(College.class,2)).save();
            new Courses("M Tech",College.findById(College.class,2)).save();
            new Courses("MCA",College.findById(College.class,2)).save();


            new College("CMC School Of Nursing","","","Medical College - NAD Rd, Cochin Medical College Junction, HMT Colony, North Kalamassery, Kalamassery, Kochi, Kerala 683503","Kalamassery","10.035306","76.3318098").save();
            new Courses("Nursing",College.findById(College.class,3)).save();

            new College("Rajagiri College Of Social Sciences","","","Rajagiri College Road, South Kalamassery, Kalamassery, Kochi, Kerala 682039","Kalamassery","10.0355999","76.3105791").save();
            new Courses("MCA",College.findById(College.class,4)).save();
            new Courses("BCA",College.findById(College.class,4)).save();
            new Courses("M tech",College.findById(College.class,4)).save();
            new Courses("B tech",College.findById(College.class,4)).save();


            new College("Union Christian College","","","UC College Rd, Aluva, Kerala 683102","Aluva","10.0004919","76.2493437").save();
            new Courses("MCA",College.findById(College.class,5)).save();
            new Courses("BCA",College.findById(College.class,5)).save();
            new Courses("M tech",College.findById(College.class,5)).save();
            new Courses("B tech",College.findById(College.class,5)).save();

            new College("Mar Athanasius College of Engineering","","","Kozhippilly - College Juction Rd, Kothamangalam, Kerala 686666","Kothamangalam","10.0004919","76.2493437").save();
            new Courses("MCA",College.findById(College.class,6)).save();
            new Courses("BCA",College.findById(College.class,6)).save();
            new Courses("M tech",College.findById(College.class,6)).save();
            new Courses("B tech",College.findById(College.class,6)).save();

            new College("Malankara Orthodox Syrian Church Medical College","","","Medical College Road, PO kolencherry, Kochi, Kerala 682311","kolencherry","10.0004919","76.2493437").save();
            new Courses("Nursing",College.findById(College.class,7)).save();
            new Courses("MBBS",College.findById(College.class,7)).save();




        }
    }


}
