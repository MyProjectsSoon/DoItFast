package com.example.doitfast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TicketActivity extends AppCompatActivity {

    //UI
    private TextView txtTitle;
    private TextView tvText;
    private TextView tvTicket;

    private Button btnTicket;
    private TextView tvQueue;
    private TextView tvMinutes;
    private ImageView ivCancel;

//todo bigger button
    //todo animation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        //UI
        txtTitle = findViewById(R.id.tvTicketTitle);
        tvText = findViewById(R.id.tvText);
        tvTicket = findViewById(R.id.tvTicket);
        btnTicket = findViewById(R.id.btnTicket);
        tvQueue = findViewById(R.id.tvQueue);
        tvMinutes = findViewById(R.id.tvMinutes);
        ivCancel = findViewById(R.id.ivCancel);

        //set ticket to indivisible
        tvText.setVisibility(View.INVISIBLE);
        tvTicket.setVisibility(View.INVISIBLE);

        //Set title
        Intent intent = getIntent();
        String str = intent.getStringExtra("message_key");
        txtTitle.setText(str);
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

    public void onTicketClick(View view)
    {
        btnTicket.setVisibility(View.INVISIBLE);
        btnTicket.setEnabled(false);
        tvText.setVisibility(View.VISIBLE);
        tvTicket.setVisibility(View.VISIBLE);

    }

}