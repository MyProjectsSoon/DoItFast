package com.example.doitfast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.Date;

public class VechileDetails extends AppCompatActivity {

    private EditText etPlateNo;
    private Spinner spnSource;
    private Spinner spnCategory;
    private Spinner spnCode;


    //info vehicle
    private int plateNo;
    private String source;
    private String category;
    private String code;
    private String payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vechile_details);

        spnSource = findViewById(R.id.spnSource);
        spnCategory = findViewById(R.id.spnCategory);
        spnCode=findViewById(R.id.spnCode);
        etPlateNo=findViewById(R.id.etPlateNo);

        // Create an ArrayAdapter using the string array and a default spinner layout to get items
        ArrayAdapter<CharSequence> sourceAdapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_source, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_category, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> codeAdapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_code, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        sourceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        codeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnSource.setAdapter(sourceAdapter);
        spnCategory.setAdapter(categoryAdapter);
        spnCode.setAdapter(codeAdapter);

        //spinners doing something
        spnCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                //display what the user selects
                System.out.println(arg0.getItemAtPosition(position));
                code = arg0.getItemAtPosition(position).toString();
                System.out.println("code : " + code);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                //display what the user selects
                System.out.println(arg0.getItemAtPosition(position));
                category = arg0.getItemAtPosition(position).toString();
                System.out.println("category : " + category);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        spnSource.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                //display what the user selects
                System.out.println(arg0.getItemAtPosition(position));
                source = arg0.getItemAtPosition(position).toString();
                System.out.println("source : " + source);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

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




    //radio button
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.first:
                if (checked)
                    System.out.println("1");
                    payment = "cash";
                break;
            case R.id.second:
                if (checked)
                    System.out.println("2");
                    payment = "credit card";
                break;
            case R.id.third:
                if (checked)
                    System.out.println("3");
                    payment = "paypal";
                break;
        }
    }


    //confirm button
    public void onConfirmClick(View view)
    {
        //receive info parking from parking activity
        Intent intent1 = getIntent();
        String parking_code = intent1.getStringExtra("parkingcode");
        Date arrive = (Date)intent1.getSerializableExtra("arrive");
        int hours = intent1.getIntExtra("hours",0);
        float price = intent1.getFloatExtra("price",0);


        plateNo = Integer.parseInt(etPlateNo.getText().toString());


        Intent intent;
        if(payment.equals("credit card"))
        {
            intent = new Intent(this, CardDetails.class);


        }
        else
        {
            intent = new Intent(this, InvoiceActivity.class);
        }

        //send info parking to card or invoice activity
        intent1.putExtra("parkingcode",parking_code);
        intent1.putExtra("arrive",arrive);
        intent1.putExtra("hours",hours);
        intent1.putExtra("price",price);

        //send info vehicle to card or invoice activity
        intent1.putExtra("plateno",plateNo);
        intent1.putExtra("category",category);
        intent1.putExtra("source",source);
        intent1.putExtra("code",code);
        intent1.putExtra("payment",payment);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();


    }
}