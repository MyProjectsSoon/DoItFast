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
    private TextView userEmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imgExit = findViewById(R.id.imgExit);

        userEmail = findViewById(R.id.txv_username);
        // receive from SignIn
        Intent intent = getIntent();
        String email = intent.getStringExtra("UserEmail");
        userEmail.setText(email);





    }

    public void onServiceClick(View view)
    {
        Intent intent=new Intent(this,ServicesActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

}