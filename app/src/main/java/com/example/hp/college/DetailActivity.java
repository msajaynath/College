package com.example.hp.college;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

import Entity.College;
import Entity.Review;
import Entity.User;

public class DetailActivity extends AppCompatActivity {

    TextView name,address,colfac,hostfac,courses,rank;
    Button route,update;
    ImageView home;
    EditText myReview;
    ListView myReviewList;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        name= (TextView) findViewById(R.id.name);
        address= (TextView) findViewById(R.id.address);
        rank= (TextView) findViewById(R.id.rankText);
        myReview= (EditText) findViewById(R.id.myReview);
        myReviewList= (ListView) findViewById(R.id.reviewlistView);
        colfac= (TextView) findViewById(R.id.colfac);
        hostfac= (TextView) findViewById(R.id.hosfac);
        courses= (TextView) findViewById(R.id.course);
        update= (Button) findViewById(R.id.updateReview);
        home= (ImageView) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();

            }
        });
        route= (Button) findViewById(R.id.route);
        Intent in=getIntent();
         id=in.getLongExtra("logid", 0);
        String  coursess=in.getStringExtra("courses");
        Log.d("log",id+"");
        final College c=College.findById(College.class,id);
        name.setText(c.name);
        address.setText(c.address);
        colfac.setText(c.colfacilities);
        hostfac.setText(c.hostelfacilities);
        rank.setText(c.rank+"");
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


        final SharedPreferences sharedpreferences = getSharedPreferences("Diet", Context.MODE_PRIVATE);
        if(!sharedpreferences.getBoolean("logged",false))
        {

            myReview.setVisibility(View.GONE);
            update.setVisibility(View.GONE);
            List<Review> log = Review.find(Review.class, "college = ?", id+"");
            if(log.size()>0) {
                String[] mobileArrayList=new String[log.size()];

                int i=0;
                for (Review r : log) {

                    mobileArrayList[i++]="@"+r.user.name+" - "+r.review;

                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, mobileArrayList);
                myReviewList.setAdapter(adapter);

            }
        }
else
        {

          final long userid=  sharedpreferences.getLong("userid", 0);

            final List<Review> myReviews = Review.find(Review.class, "college = ? and user=?", id+"",userid+"");
            if(myReviews.size()>0)
            {
                myReview.setText(myReviews.get(0).review);
                update.setText("Update");
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        myReviews.get(0).review=myReview.getText().toString()+"";
                        myReviews.get(0).save();
                        Toast.makeText(getApplicationContext(),"Review updated!!!",Toast.LENGTH_SHORT).show();
                    }
                });


            }

            else {
                myReview.setHint("Please leave your review here...");
                update.setText("Submit");
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new Review(College.findById(College.class,id),User.findById(User.class,userid),myReview.getText().toString()).save();
                        Toast.makeText(getApplicationContext(), "Review updated!!!", Toast.LENGTH_SHORT).show();
                    }
                });


            }
            List<Review> log = Review.find(Review.class, "college = ? and user <>?", id+"",userid+"");
            if(log.size()>0) {
                String[] mobileArrayList=new String[log.size()];

                int i=0;
                for (Review r : log) {

                    mobileArrayList[i++]="@"+r.user.name+" - "+r.review;

                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, mobileArrayList);
                myReviewList.setAdapter(adapter);

            }
        }




    }
}
