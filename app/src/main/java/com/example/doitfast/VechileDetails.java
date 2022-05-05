package com.example.doitfast;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.example.doitfast.model.Invoice;
import com.example.doitfast.model.Vehicle;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

    //Reference to the Firebase realtime database
    private FirebaseDatabase database;

    //Reference to a specific node in the database
    private DatabaseReference reference;
    private DatabaseReference reference1;

    //Variable to count number of object in database
    long maxid = 0;

    //Variable to count number of object in database
    long maxid1 = 0;

    long count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vechile_details);

        spnSource = findViewById(R.id.spnSource);
        spnCategory = findViewById(R.id.spnCategory);
        spnCode=findViewById(R.id.spnCode);
        etPlateNo=findViewById(R.id.etPlateNo);

        //Get the database object and a reference to the members collection
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("vehicle");
        reference1 = database.getReference("invoice");

        //here step 1 count number of members in database and put it in variable maxid
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    maxid = (dataSnapshot.getChildrenCount());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //here step 1 count number of members in database and put it in variable maxid
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    maxid1 = (dataSnapshot.getChildrenCount());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





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
        int userid = intent1.getIntExtra("Userid",-1);
        String username = intent1.getStringExtra("UserName");
        long parking_id = intent1.getLongExtra("parkingid",-1);
        String parking_no = intent1.getStringExtra("parkingno");
        float price = intent1.getFloatExtra("price",0);
        int hour = intent1.getIntExtra("hour",00);
        int min = intent1.getIntExtra("min",00);
        int day = intent1.getIntExtra("day",00);
        int mon = intent1.getIntExtra("mon",00);
        int year = intent1.getIntExtra("year",0000);


        plateNo = Integer.parseInt(etPlateNo.getText().toString());

        //here step 2 to create object
        final long id = maxid + 1;
        //add info parking in firebase
        final Vehicle vehicle = new Vehicle(plateNo,category,source,code,payment);

        //String key = reference.push().getKey();
        reference.child(String.valueOf(id)).setValue(vehicle).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                String message = "Vehicle Addded sucessfully";
                Toast.makeText(VechileDetails.this, message, Toast.LENGTH_SHORT).show();

            }
        });

        if(!payment.equals("credit card"))
        {
            //here step 2 to create object
            final long id1 = maxid1 + 1;
            //add info parking in firebase
            final Invoice invoice = new Invoice(id1,userid,username,parking_id,id,-1);

            //String key = reference.push().getKey();
            reference1.child(String.valueOf(id1)).setValue(invoice).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    String message = "invoice Addded sucessfully";
                    Toast.makeText(VechileDetails.this, message, Toast.LENGTH_SHORT).show();

                }
            });
        }


        Intent intent;
        if(payment.equals("credit card"))
        {
            intent = new Intent(this, CardDetails.class);


        }
        else
        {
            intent = new Intent(this, InvoiceActivity.class);
        }



        //send info vehicle to card or invoice activity
        intent.putExtra("Userid",userid);
        intent.putExtra("UserName",username);
        intent.putExtra("vehicleid",id);
        intent.putExtra("vehicleno",plateNo);
        intent.putExtra("vehiclesource",source);
        intent.putExtra("parkingid",parking_id);
        intent.putExtra("parkingno",parking_no);
        intent.putExtra("price",price);
        intent.putExtra("hour",hour);
        intent.putExtra("min",min);
        intent.putExtra("day", day);
        intent.putExtra("mon", mon);
        intent.putExtra("year", year);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();


    }

    public long Count()
    {
        //here step 1 count number of members in database and put it in variable maxid
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    count = (dataSnapshot.getChildrenCount());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return count;
    }
    public void delete(View view)
    {


        long c = Count();

        // 1 - get input
        reference.child(String.valueOf(c)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists())
                {
                    reference.child(String.valueOf(count)).setValue(null); //Delete the Member
                    String message = "Vehicle Deleted";
                    Toast.makeText(VechileDetails.this, message, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String message = "No Vehicle Found";
                    Toast.makeText(VechileDetails.this, message, Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}