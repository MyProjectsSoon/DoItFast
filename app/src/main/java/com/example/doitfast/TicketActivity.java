package com.example.doitfast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doitfast.model.Ticket;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class TicketActivity extends AppCompatActivity {

    //Log
    private static final String TAG = TicketActivity.class.getSimpleName();
    //UI
    private TextView txtTitle;
    private TextView tvText;
    private TextView tvTicket;

    private Button btnTicket;
    private TextView tvQueue;
    private TextView tvMinutes;
    private ImageView ivCancel;
    private ImageView ivQR;

    //QR Code
    private Bitmap bitmap;
    private QRGEncoder qrgEncoder;

    //Firebase
    private FirebaseDatabase database;
    private DatabaseReference ref;


    //object
    private Ticket t;

    //Intents
    private String title;

    //global variables
    private static int myCount;

    private static String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        //UI
        txtTitle = findViewById(R.id.tvTicketTitle);
        tvText = findViewById(R.id.tvText);
        tvTicket = findViewById(R.id.tvTicket);
        btnTicket = findViewById(R.id.btnTicket);
        tvQueue = findViewById(R.id.tvQueue);
        tvMinutes = findViewById(R.id.tvMinutes);
        ivCancel = findViewById(R.id.ivCancel);
        ivQR = findViewById(R.id.ivQR);

        //set ticket to indivisible
        tvText.setVisibility(View.INVISIBLE);
        tvTicket.setVisibility(View.INVISIBLE);
        ivQR.setVisibility(View.INVISIBLE);

        //Set title
        Intent intent = getIntent();
        String username = intent.getStringExtra("UserName");
        String title = intent.getStringExtra("message_key");
        txtTitle.setText(title);

        //Firebase
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("queues");
        System.out.println(title);

        //create ticket
        t = new Ticket(0,username,title);



        getFromFirebase(new OnDataReceiveCallback(){
            public void onDataReceived(String queueCounter){

                /*if(t.getDisplayQueue()==0)
                {
                    t.setDisplayQueue(t.getQueue());
                }*/

                //set text
                tvTicket.setText(String.valueOf(t.getQueue()));
                tvQueue.setText(String.valueOf(t.getDisplayQueue()));
                tvMinutes.setText(String.valueOf(t.getDisplayQueue()));


            }
        });


    }





    public interface OnDataReceiveCallback {
        void onDataReceived(String ticket);
    }

    private void getFromFirebase(OnDataReceiveCallback callback){
        ref.addValueEventListener( new ValueEventListener(){
            @Override
            public void onDataChange( @NonNull DataSnapshot dataSnapshot ){

                Intent intent = getIntent();
                title = intent.getStringExtra("message_key");

                myCount=0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String tick = snapshot.child("service").getValue().toString();
                    if(title.equals(tick))
                    {
                        myCount++;
                    }}

                t.setQueue(myCount);
                t.setDisplayQueue(myCount);


                callback.onDataReceived(String.valueOf(myCount));
            }

            @Override
            public void onCancelled( @NonNull DatabaseError databaseError ){
            }
        });
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



    public void onTicketClick(View view)
    {

        //setting ticket display
        t.addQueue();
        t.setDisplayQueue(myCount);
        t.subtractDisplayQueue();

        //Add ticket
        key = ref.push().getKey();
        ref.child(key).setValue(t).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });

        //notify user
        Toast.makeText(this, "Ticket booked successfully", Toast.LENGTH_SHORT).show();

        //Visibility of buttons
        btnTicket.setVisibility(View.INVISIBLE);
        btnTicket.setEnabled(false);
        tvText.setVisibility(View.VISIBLE);
        tvTicket.setVisibility(View.VISIBLE);
        ivQR.setVisibility(View.VISIBLE);

        // QR Code
        // below line is for getting
        // the windowmanager service.
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // initializing a variable for default display.
        Display display = manager.getDefaultDisplay();

        // creating a variable for point which
        // is to be displayed in QR Code.
        Point point = new Point();
        display.getSize(point);

        // getting width and
        // height of a point
        int width = point.x;
        int height = point.y;

        // generating dimension from width and height.
        int dimen = width < height ? width : height;
        dimen = dimen * 3 / 4;


        // setting this dimensions inside our qr code
        // encoder to generate our qr code.
        QRGEncoder qrgEncoder = new QRGEncoder(key, null, QRGContents.Type.TEXT, dimen);
        try {
            // getting our qrcode in the form of bitmap.
            bitmap = qrgEncoder.encodeAsBitmap();
            // the bitmap is set inside our image
            // view using .setimagebitmap method.
            ivQR.setImageBitmap(bitmap);
        } catch (WriterException e) {
            // this method is called for
            // exception handling.
            Log.e("Tag", e.toString());
        }


    }

    public void onCancelClick(View view){

        System.out.println(key);
        ref.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    ref.child(key).setValue(null);
                    Toast.makeText(TicketActivity.this, "Your position in the queue has been cleared", Toast.LENGTH_SHORT).show();
                    btnTicket.setVisibility(View.VISIBLE);
                    btnTicket.setEnabled(true);
                    tvText.setVisibility(View.INVISIBLE);
                    tvTicket.setVisibility(View.INVISIBLE);
                    ivQR.setVisibility(View.INVISIBLE);
                }
                else
                {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }




}