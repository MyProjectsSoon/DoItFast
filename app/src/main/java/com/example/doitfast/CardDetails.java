package com.example.doitfast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class CardDetails extends AppCompatActivity {

    private EditText etCardNo;
    private EditText etCardName;
    private EditText etCardExpiration;
    private EditText etCardCVC;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        etCardNo=findViewById(R.id.etCardNo);
        etCardName=findViewById(R.id.etCardName);
        etCardExpiration=findViewById(R.id.etCardExpiration);
        etCardCVC=findViewById(R.id.etCardCVC);


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

    private void onPayClick(View view)
    {
        Intent intent=new Intent(this,InvoiceActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

}