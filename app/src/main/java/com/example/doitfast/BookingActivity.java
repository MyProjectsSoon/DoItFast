package com.example.doitfast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.doitfast.items.TimePickerFragment;

public class BookingActivity extends AppCompatActivity {

    private TextView tvCompany;
    private TextView tvTime;
    private Button btnTime;
    private Button btnDate;
    private Button btnConfirm;
    private RadioGroup radioGroup;
    private ImageView ivCompany;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        tvCompany = findViewById(R.id.tvCompany);
        tvTime = findViewById(R.id.tvTime);
        btnTime = findViewById(R.id.btnTime);
        btnDate = findViewById(R.id.btnDate);
        btnConfirm = findViewById(R.id.btnConfirm);
        radioGroup = findViewById(R.id.radioGroup);
        ivCompany = findViewById(R.id.ivCompany);

        //recieve intent
        Intent intent = getIntent();
        String title = intent.getStringExtra("company title");
        String hours = intent.getStringExtra("working hours");

        tvCompany.setText(title);
        tvTime.setText(hours);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
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