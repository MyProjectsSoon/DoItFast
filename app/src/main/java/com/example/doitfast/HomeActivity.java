package com.example.doitfast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imgExit = findViewById(R.id.imgExit);

        userName = findViewById(R.id.txv_username);
        // receive from SignIn
        Intent intent = getIntent();
        String email = intent.getStringExtra("UserEmail");
        String username = intent.getStringExtra("UserName");
        userName.setText(username);





    }

    public void onFIACClick(View view)
    {
        Intent intent=new Intent(this,ServicesActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("message_key", FIAC);
        startActivity(intent);
        finish();
    }

    public void onMOHClick(View view)
    {
        Intent intent=new Intent(this,ServicesActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("message_key", MOH);
        startActivity(intent);
        finish();
    }

    public void onMOIClick(View view)
    {
        Intent intent=new Intent(this,ServicesActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("message_key", MOI);
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
}