package com.project.doitfast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.project.doitfast.model.Parking;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class ParkingActivity extends AppCompatActivity {

    private Button btncode1,btncode2,btncode3,btncode4;
    private Button btncode5,btncode6,btncode7,btncode8;
    private Button btncode9,btncode10,btncode11,btncode12;
    private Button btncode13,btncode14,btncode15,btncode16;

    private TextView capacity;

    //Reference to the Firebase realtime database
    private FirebaseDatabase database;

    //Reference to a specific node in the database
    private DatabaseReference reference;

    //Variable to count number of object in database
    long maxid = 0;

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);

        //Get the database object and a reference to the members collection
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("parking");

        //1-4
        btncode1 = findViewById(R.id.btnParking1);
        btncode2 = findViewById(R.id.btnParking2);
        btncode3 = findViewById(R.id.btnParking3);
        btncode4 = findViewById(R.id.btnParking4);

        //5-8
        btncode5 = findViewById(R.id.btnParking5);
        btncode6 = findViewById(R.id.btnParking6);
        btncode7 = findViewById(R.id.btnParking7);
        btncode8 = findViewById(R.id.btnParking8);

        //9-12
        btncode9 = findViewById(R.id.btnParking9);
        btncode10 = findViewById(R.id.btnParking10);
        btncode11 = findViewById(R.id.btnParking11);
        btncode12 = findViewById(R.id.btnParking12);

        //13-16
        btncode13 = findViewById(R.id.btnParking13);
        btncode14 = findViewById(R.id.btnParking14);
        btncode15 = findViewById(R.id.btnParking15);
        btncode16 = findViewById(R.id.btnParking16);


        capacity = findViewById(R.id.tvCapacity);

        Intent intent = getIntent();
        username = intent.getStringExtra("UserName");

        //here step 1 count number of members in database and put it in variable maxid
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    maxid = (dataSnapshot.getChildrenCount());
                    capacity.setText(maxid + " / 48" );
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
                intent.putExtra("UserName",username);
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
                    System.out.println("Block A");
                    //1-4
                    btncode1.setText("A-1");
                    btncode2.setText("A-2");
                    btncode3.setText("A-3");
                    btncode4.setText("A-4");

                    //5-8
                    btncode5.setText("A-5");
                    btncode6.setText("A-6");
                    btncode7.setText("A-7");
                    btncode8.setText("A-8");

                    //9-12
                    btncode9.setText("A-9");
                    btncode10.setText("A-10");
                    btncode11.setText("A-11");
                    btncode12.setText("A-12");

                    //13-16
                    btncode13.setText("A-13");
                    btncode14.setText("A-14");
                    btncode15.setText("A-15");
                    btncode16.setText("A-16");
                break;
            case R.id.second:
                if (checked)
                    System.out.println("Block B");
                    //1-4
                    btncode1.setText("B-1");
                    btncode2.setText("B-2");
                    btncode3.setText("B-3");
                    btncode4.setText("B-4");

                    //5-8
                    btncode5.setText("B-5");
                    btncode6.setText("B-6");
                    btncode7.setText("B-7");
                    btncode8.setText("B-8");

                    //9-12
                    btncode9.setText("B-9");
                    btncode10.setText("B-10");
                    btncode11.setText("B-11");
                    btncode12.setText("B-12");

                    //13-16
                    btncode13.setText("B-13");
                    btncode14.setText("B-14");
                    btncode15.setText("B-15");
                    btncode16.setText("B-16");
                break;
            case R.id.third:
                if (checked)
                    System.out.println("Block C");
                    //1-4
                    btncode1.setText("C-1");
                    btncode2.setText("C-2");
                    btncode3.setText("C-3");
                    btncode4.setText("C-4");

                    //5-8
                    btncode5.setText("C-5");
                    btncode6.setText("C-6");
                    btncode7.setText("C-7");
                    btncode8.setText("C-8");

                    //9-12
                    btncode9.setText("C-9");
                    btncode10.setText("C-10");
                    btncode11.setText("C-11");
                    btncode12.setText("C-12");

                    //13-16
                    btncode13.setText("C-13");
                    btncode14.setText("C-14");
                    btncode15.setText("C-15");
                    btncode16.setText("C-16");
                break;
        }
    }

    public void onParkingClick(View view)
    {
        Button b = (Button)view;
        String buttonText = b.getText().toString();
        System.out.println("Parking code: "+ buttonText);

        //receive info booking from Booking activity
        Intent intent = getIntent();
        int userid = intent.getIntExtra("Userid",-1);
        String username = intent.getStringExtra("UserName");
        Date arrive = (Date)intent.getSerializableExtra("arrive");
        int hours = intent.getIntExtra("hours",0);
        float price = intent.getFloatExtra("price",0);
        int hour = intent.getIntExtra("hour",00);
        int min = intent.getIntExtra("min",00);
        int day = intent.getIntExtra("day",00);
        int mon = intent.getIntExtra("mon",00);
        int year = intent.getIntExtra("year",0000);

        //here step 2 to create object
        final long id = maxid + 1;
        //add info parking in firebase
        final Parking parking = new Parking(buttonText,arrive,hours,price);

        //String key = reference.push().getKey();
        reference.child(String.valueOf(id)).setValue(parking).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                String message = "Parking Addded sucessfully";
                Toast.makeText(ParkingActivity.this, message, Toast.LENGTH_SHORT).show();

            }
        });

        Intent intent1=new Intent(this, VehicleDetails.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //send info Parking to vehicle activity
        intent1.putExtra("Userid",userid);
        intent1.putExtra("UserName",username);
        intent1.putExtra("parkingid",id);
        intent1.putExtra("parkingno",buttonText);
        intent1.putExtra("price",price);
        intent1.putExtra("hour",hour);
        intent1.putExtra("min",min);
        intent1.putExtra("day", day);
        intent1.putExtra("mon", mon);
        intent1.putExtra("year", year);

        startActivity(intent1);
        finish();
    }
}


