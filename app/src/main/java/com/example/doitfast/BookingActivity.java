package com.example.doitfast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doitfast.model.Parking;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BookingActivity extends AppCompatActivity {

    private static final String TAG = "" ;

    private static final String TAG_DATETIME_FRAGMENT = "TAG_DATETIME_FRAGMENT";

    private TextView tvCompany;
    private TextView tvTime;
    private Button btnTime;
    private Button btnConfirm;
    private RadioGroup radioGroup;
    private ImageView ivCompany;
    private SwitchDateTimeDialogFragment dateTimeDialogFragment;

    //Reference to the Firebase realtime database
    private FirebaseDatabase database;

    //Reference to a specific node in the database
    private DatabaseReference reference;


    //info parking
    private Date arrive;
    private int hours;
    private float price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        tvCompany = findViewById(R.id.tvCompany);
        tvTime = findViewById(R.id.tvTime);
        btnTime = findViewById(R.id.btnTime);
        btnConfirm = findViewById(R.id.btnConfirm);
        radioGroup = findViewById(R.id.radioGroup);
        ivCompany = findViewById(R.id.ivCompany);

        //Get the database object and a reference to the members collection
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("parking");



        //recieve intent
        Intent intent = getIntent();
        String title = intent.getStringExtra("company title");
        String hours = intent.getStringExtra("working hours");
        int image = intent.getIntExtra("image",0);

        ivCompany.setImageResource(image);
        tvCompany.setText(title);
        tvTime.setText(hours);

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

    public void onDateClick(View view)
    {

        //datetime picker
        // Construct SwitchDateTimePicker
        dateTimeDialogFragment = (SwitchDateTimeDialogFragment) getSupportFragmentManager().findFragmentByTag(TAG_DATETIME_FRAGMENT);
        if (dateTimeDialogFragment == null) {
            dateTimeDialogFragment = SwitchDateTimeDialogFragment.newInstance(
                    getString(R.string.label_datetime_dialog),
                    getString(android.R.string.ok),
                    getString(android.R.string.cancel)
            );
        }

        dateTimeDialogFragment.startAtCalendarView();
        dateTimeDialogFragment.set24HoursMode(false);
        dateTimeDialogFragment.setHighlightAMPMSelection(false);

        //minimum date
      /*  dateTimeDialogFragment.setMinimumDateTime(new GregorianCalendar().getTime());
        System.out.println(new GregorianCalendar().getTime());*/

        final SimpleDateFormat myDateFormat = new SimpleDateFormat("d MMM yyyy HH:mm", java.util.Locale.getDefault());



        // Set datetime listener
        dateTimeDialogFragment.setOnButtonClickListener(new SwitchDateTimeDialogFragment.OnButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Date date) {
                btnTime.setText(myDateFormat.format(date));
                arrive = date;
            }

            @Override
            public void onNegativeButtonClick(Date date) {

            }
        });

        dateTimeDialogFragment.show(getSupportFragmentManager(), "dialog_time");
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.first:
                if (checked)
                    System.out.println("1");
                    hours = 1;
                    price = 5;
                    break;
            case R.id.second:
                if (checked)
                    System.out.println("2");
                    hours = 2;
                    price = 10;
                    break;
            case R.id.third:
                if (checked)
                    System.out.println("3");
                    hours = 3;
                    price = 15;
                    break;
        }
    }

    public void onConfirmClick(View view)
    {
        final Parking parking = new Parking(arrive,hours,price);

        String key = reference.push().getKey();
        reference.child(key).setValue(parking).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                String message = "Parking Addded sucessfully";
                Toast.makeText(BookingActivity.this, message, Toast.LENGTH_SHORT).show();

            }
        });

        Intent intent=new Intent(this,ParkingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

}

