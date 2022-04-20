package com.example.doitfast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class InvoiceActivity extends AppCompatActivity {

    private TextView tvInvoiceNo;
    private TextView tvUsername;
    private TextView tvDate;
    private TextView tvEndTime;
    private TextView tvInvoicePlate;
    private TextView tvParkingNo;
    private TextView tvAmount;
    private ImageView ivQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        tvInvoiceNo=findViewById(R.id.tvInvoiceNo);
        tvUsername=findViewById(R.id.tvUsername);
        tvDate=findViewById(R.id.tvDate);
        tvEndTime=findViewById(R.id.tvEndTime);
        tvInvoicePlate=findViewById(R.id.tvinvoicePlate);
        tvParkingNo=findViewById(R.id.tvParkingNo);
        tvAmount=findViewById(R.id.tvAmount);
        ivQR=findViewById(R.id.ivQR);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(this,SplitActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}