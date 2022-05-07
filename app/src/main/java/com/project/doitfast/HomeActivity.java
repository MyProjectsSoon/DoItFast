package com.project.doitfast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    //UI
    private ImageView imgExit;
    private TextView userName;

    //Titles
    private String FIAC = "Federal Authority for Identity and Citizenship";
    private String MOH = "Ministry of Health and Prevention";
    private String MOI = "Ministry of Interior";

    //MOH Services
    private String SI = "Submit Inquiries";
    private String RD = "Re-licensing of a doctor";
    private String AML  = "Approve medical leaves";
    private String RR = "Renewal of registration";
    private String IC = "Issue of a certificate";

    //MOI Services
    private String TFP ="Traffic fine payment";
    private String IDL ="Issue driving license";
    private String IVR ="Issue vehicle registration";
    private String RL ="Renew driver's license";
    private String FCR ="File criminal reports";

    //FAIC Services
    private String RE = "Renew EID";
    private String AVE = "Apply for a visa";
    private String FP = "Fee payment";
    private String AEID = "Apply for an EID";
    private String RV = "Renew visa";

    private String username;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imgExit = findViewById(R.id.imgExit);

        userName = findViewById(R.id.txv_username);
        // receive from SignIn
        Intent intent = getIntent();
        String email = intent.getStringExtra("UserEmail");
        username = intent.getStringExtra("UserName");
        userName.setText(username);




        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




    }

    //back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(this,SplitActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("UserName",username);
                startActivity(intent);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onFIACClick(View view)
    {
        Intent intent = IntentSplit();

        //queue
        intent.putExtra("message_key", FIAC);
        intent.putExtra("txt1", RE);
        intent.putExtra("txt2",AVE);
        intent.putExtra("txt3", FP);
        intent.putExtra("txt4", AEID);
        intent.putExtra("txt5", RV);
        intent.putExtra("UserName", username);

        //parking
        int image = R.drawable.faic;
        intent.putExtra("company title",FIAC);
        intent.putExtra("working hours","Mon - Sun   7:00 AM - 3:00 PM");
        intent.putExtra("image",image);


        startActivity(intent);
        finish();
    }

    public void onMOHClick(View view)
    {
        Intent intent = IntentSplit();

        intent.putExtra("message_key", MOH);

        intent.putExtra("txt1", RD);
        intent.putExtra("txt2",SI);
        intent.putExtra("txt3", AML);
        intent.putExtra("txt4", RR);
        intent.putExtra("txt5", IC);
        intent.putExtra("UserName", username);

        //parking
        int image = R.drawable.moh;
        intent.putExtra("company title",MOH);
        intent.putExtra("working hours","Mon - Thu   8:00 AM - 3:00 PM");
        intent.putExtra("image",image);


        startActivity(intent);
        finish();
    }

    public void onMOIClick(View view)
    {
        Intent intent = IntentSplit();

        intent.putExtra("message_key", MOI);
        intent.putExtra("txt1", TFP);
        intent.putExtra("txt2",IDL);
        intent.putExtra("txt3", IVR);
        intent.putExtra("txt4", RL);
        intent.putExtra("txt5", FCR);
        intent.putExtra("UserName", username);

        //parking
        int image = R.drawable.moi;
        intent.putExtra("company title",MOI);
        intent.putExtra("working hours","Mon - Thu   7:30 AM - 3:00 PM");
        intent.putExtra("image",image);

        startActivity(intent);
        finish();
    }

    public void onExitClick(View view)
    {
        Intent intent = new Intent(HomeActivity.this,SignInActivity.class);
        intent.putExtra("LOGOUT","logout");
        startActivity(intent);
        finish();
    }

    public Intent IntentSplit()
    {
        Intent intentSplit = getIntent();
        String option = intentSplit.getStringExtra("Parking");


        if(option.equals("parking"))
        {
            intentSplit = new Intent(this, BookingActivity.class);
        }
        else
        {
            intentSplit = new Intent(this, ServicesActivity.class);
        }



        intentSplit.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intentSplit;

    }
}