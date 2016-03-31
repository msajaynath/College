package com.example.hp.college;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

import Entity.College;

public class DetailActivity extends AppCompatActivity {

    TextView name,address,colfac,hostfac,courses;
    Button route;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        name= (TextView) findViewById(R.id.name);
        address= (TextView) findViewById(R.id.address);
        colfac= (TextView) findViewById(R.id.colfac);
        hostfac= (TextView) findViewById(R.id.hosfac);
        courses= (TextView) findViewById(R.id.course);

        route= (Button) findViewById(R.id.route);
        Intent in=getIntent();
        long id=in.getLongExtra("logid", 0);
        String  coursess=in.getStringExtra("courses");
        Log.d("log",id+"");
        final College c=College.findById(College.class,id);
        name.setText(c.name);
        address.setText(c.address);
        colfac.setText(c.colfacilities);
        hostfac.setText(c.hostelfacilities);
        courses.setText(coursess);
        route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://maps.google.com/maps?&daddr="+c.latitude+","+c.longitude+"";

                //String uri = String.format(Locale.ENGLISH, "geo:%s,%s", c.latitude, c.longitude);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

    }
}
