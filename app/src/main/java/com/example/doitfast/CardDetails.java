package com.example.doitfast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doitfast.model.Card;
import com.example.doitfast.model.Invoice;
import com.example.doitfast.model.Parking;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CardDetails extends AppCompatActivity {

    private EditText etCardNo;
    private EditText etCardName;
    private EditText etCardExpiration;
    private EditText etCardCVC;

    //Reference to the Firebase realtime database
    private FirebaseDatabase database;

    //Reference to a specific node in the database
    private DatabaseReference reference;
    private DatabaseReference reference1;

    //Variable to count number of object in database
    long maxid = 0;

    //Variable to count number of object in database
    long maxid1 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        etCardNo=findViewById(R.id.etCardNo);
        etCardName=findViewById(R.id.etCardName);
        etCardExpiration=findViewById(R.id.etCardExpiration);
        etCardCVC=findViewById(R.id.etCardCVC);

        //Get the database object and a reference to the members collection
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("card");
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

    public void onPayClick(View view)
    {
        //receive info parking from vehicle activity
        Intent intent1 = getIntent();
        int userid = intent1.getIntExtra("Userid",-1);
        String username = intent1.getStringExtra("UserName");
        long parking_id = intent1.getLongExtra("parkingid",-1);
        long vehicle_id = intent1.getLongExtra("vehicleid",-1);
        int plateno = intent1.getIntExtra("vehicleno",-1);
        String source = intent1.getStringExtra("vehiclesource");
        String parking_no = intent1.getStringExtra("parkingno");
        float price = intent1.getFloatExtra("price",0);
        int hour = intent1.getIntExtra("hour",00);
        int min = intent1.getIntExtra("min",00);
        int day = intent1.getIntExtra("day",00);
        int mon = intent1.getIntExtra("mon",00);
        int year = intent1.getIntExtra("year",0000);

        //get user input
        String cardn = etCardNo.getText().toString().trim();
        int cardNo = Integer.valueOf(cardn);
        String cardName = etCardName.getText().toString();
        String exp = etCardExpiration.getText().toString();
        int cvc = Integer.parseInt(etCardCVC.getText().toString());

        //here step 2 to create object
        final long id = maxid + 1;
        //add info parking in firebase
        final Card card = new Card(cardNo,cardName,exp,cvc);

        //String key = reference.push().getKey();
        reference.child(String.valueOf(id)).setValue(card).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                String message = "Card Addded sucessfully";
                Toast.makeText(CardDetails.this, message, Toast.LENGTH_SHORT).show();

            }
        });


        //here step 2 to create object
        final long id1 = maxid1 + 1;
        //add info parking in firebase
        final Invoice invoice = new Invoice(id1,userid,username,parking_id,vehicle_id,id);

        //String key = reference.push().getKey();
        reference1.child(String.valueOf(id1)).setValue(invoice).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                String message = "invoice Addded sucessfully";
                Toast.makeText(CardDetails.this, message, Toast.LENGTH_SHORT).show();

            }
        });

        Intent intent=new Intent(this,InvoiceActivity.class);

        //send info to invoice activity
        intent.putExtra("cardid",id);
        intent.putExtra("Userid",userid);
        intent.putExtra("UserName",username);
        intent.putExtra("vehicleid",vehicle_id);
        intent.putExtra("vehicleno",plateno);
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

}