package com.example.doitfast;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import org.w3c.dom.Text;

public class ServicesActivity extends AppCompatActivity {


    //UI
    private TextView tvTitle;

    //Titles
    private String renewEIDTitle = "Renew EID";
    private String applyTitle = "Apply for Visa";
    private String feeTitle = "Fee Payment";
    private String applyEIDTitle = "Apply for an EID";
    private String renewVisaTitle = "Renew Visa";

    //service texts
    private TextView tvService1;
    private TextView tvService2;
    private TextView tvService3;
    private TextView tvService4;
    private TextView tvService5;

    //intents
    private String str1;
    private String str2;
    private String str3;
    private String str4;
    private String str5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //UI
        tvService1 = findViewById(R.id.tvService1);
        tvService2 = findViewById(R.id.tvService2);
        tvService3 = findViewById(R.id.tvService3);
        tvService4 = findViewById(R.id.tvService4);
        tvService5 = findViewById(R.id.tvService5);
        tvTitle = findViewById(R.id.tvTitle);

        //Intent title
        Intent intent = getIntent();
        String str = intent.getStringExtra("message_key");

        //MOH Intent
         str1 = intent.getStringExtra("txt1");
         str2 = intent.getStringExtra("txt2");
         str3 = intent.getStringExtra("txt3");
         str4 = intent.getStringExtra("txt4");
         str5 = intent.getStringExtra("txt5");

        tvService1.setText(str1);
        tvService2.setText(str2);
        tvService3.setText(str3);
        tvService4.setText(str4);
        tvService5.setText(str5);
        tvTitle.setText(str);


        /*//MOI Intent
        String str6 = intent.getStringExtra("RD");
        String str7 = intent.getStringExtra("SI");
        String str8 = intent.getStringExtra("AML");
        String str9 = intent.getStringExtra("RR");
        String str10 = intent.getStringExtra("IC");

        tvService1.setText(str6);
        tvService2.setText(str7);
        tvService3.setText(str8);
        tvService4.setText(str9);
        tvService5.setText(str10);*/


    }

    //back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(this,HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onRenewIdClick(View view)
    {
        Intent intent=new Intent(this,TicketActivity.class);
        intent.putExtra("message_key", str1);
        intent.putExtra("UserName", intent.getStringExtra("UserName"));
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void onApplyVisaClick(View view)
    {
        Intent intent=new Intent(this,TicketActivity.class);
        intent.putExtra("message_key", str2);
        intent.putExtra("UserName", intent.getStringExtra("UserName"));
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void onFeePaymentClick(View view)
    {
        Intent intent=new Intent(this,TicketActivity.class);
        intent.putExtra("message_key", str3);
        intent.putExtra("UserName", intent.getStringExtra("UserName"));
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void onApplyEIDClick(View view)
    {
        Intent intent=new Intent(this,TicketActivity.class);
        intent.putExtra("message_key", str4);
        intent.putExtra("UserName", intent.getStringExtra("UserName"));
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void onRenewVisaClick(View view)
    {
        Intent intent=new Intent(this,TicketActivity.class);
        intent.putExtra("message_key", str5);
        intent.putExtra("UserName", intent.getStringExtra("UserName"));
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
