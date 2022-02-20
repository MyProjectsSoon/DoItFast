package com.example.doitfast;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvTitle = findViewById(R.id.tvTitle);

        Intent intent = getIntent();
        String str = intent.getStringExtra("message_key");
        tvTitle.setText(str);

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
        intent.putExtra("message_key", renewEIDTitle);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void onApplyVisaClick(View view)
    {
        Intent intent=new Intent(this,TicketActivity.class);
        intent.putExtra("message_key", applyTitle);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void onFeePaymentClick(View view)
    {
        Intent intent=new Intent(this,TicketActivity.class);
        intent.putExtra("message_key", feeTitle);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void onApplyEIDClick(View view)
    {
        Intent intent=new Intent(this,TicketActivity.class);
        intent.putExtra("message_key", applyEIDTitle);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void onRenewVisaClick(View view)
    {
        Intent intent=new Intent(this,TicketActivity.class);
        intent.putExtra("message_key", renewVisaTitle);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
