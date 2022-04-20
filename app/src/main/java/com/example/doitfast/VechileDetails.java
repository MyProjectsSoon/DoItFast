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

public class VechileDetails extends AppCompatActivity {

    private Spinner spnSource;
    private Spinner spnCategory;
    private Spinner spnCode;
    private EditText etPlateNo;


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
                break;
            case R.id.second:
                if (checked)
                    System.out.println("2");
                break;
            case R.id.third:
                if (checked)
                    System.out.println("3");
                break;
        }
    }


    //confirm button
    public void onConfirmClick(View view)
    {
       Intent intent=new Intent(this,CardDetails.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}