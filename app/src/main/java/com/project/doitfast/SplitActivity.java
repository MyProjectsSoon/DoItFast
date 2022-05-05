package com.project.doitfast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SplitActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split);




    }



    public void onQueueClick(View v)
    {
        Intent intent=new Intent(this,HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //push username
        Intent intent2 = getIntent();
        String username = intent2.getStringExtra("UserName");
        intent.putExtra("UserName", username);

        //push queue
        String parking = "queue";
        intent.putExtra("Parking", parking);

        startActivity(intent);
        finish();
    }

    public void onParkingClick(View v)
    {
        Intent intent=new Intent(this,HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //push username
        Intent intent2 = getIntent();
        int userid = intent2.getIntExtra("id",-1);
        String username = intent2.getStringExtra("UserName");
        intent.putExtra("UserName", username);
        intent.putExtra("Userid", userid);

        //push parking
        String parking = "parking";
        intent.putExtra("Parking", parking);

        startActivity(intent);
        finish();
    }

}