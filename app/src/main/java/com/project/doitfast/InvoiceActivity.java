package com.project.doitfast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class InvoiceActivity extends AppCompatActivity {

    private TextView tvInvoiceNo; //generate
    private TextView tvUsername; //singnin //done
    private TextView tvDate; //booking //done
    private TextView tvEndTime; //booking //done
    private TextView tvInvoicePlate; //vehicle //done
    private TextView tvParkingNo; // parking //done
    private TextView tvAmount; //booking //done
    private ImageView ivQR; //generate

    //QR Code
    private Bitmap bitmap;

    String username;
    long card_id;
    long vehicle_id;
    long parking_id;
    int userid;

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

        //receive info booking
        Intent intent = getIntent();
        userid = intent.getIntExtra("Userid",-1);
        username = intent.getStringExtra("UserName");
        parking_id = intent.getLongExtra("parkingid",-1);
        vehicle_id = intent.getLongExtra("vehicleid",-1);
        card_id = intent.getLongExtra("cardid",-1);
        int plateno = intent.getIntExtra("vehicleno",-1);
        String source = intent.getStringExtra("vehiclesource");
        String parking_no = intent.getStringExtra("parkingno");
        float price = intent.getFloatExtra("price",0);
        int hour = intent.getIntExtra("hour",00);
        int min = intent.getIntExtra("min",00);
        int day = intent.getIntExtra("day",00);
        int mon = intent.getIntExtra("mon",00);
        int year = intent.getIntExtra("year",0000);

        tvUsername.setText("Username: "+username);

        tvDate.setText( "Date: "+ String.valueOf(day) +
                "/" + String.valueOf(mon) +
                "/" + String.valueOf(year) );

        tvEndTime.setText("Time: "+String.valueOf(hour) + ":" + String.valueOf(min));

        tvAmount.setText("Amount Paid: "+String.valueOf(price) + " AED");

        tvParkingNo.setText("Parking No: " + parking_no);

        String sourceno = "null";
        if(source.equals("AbuDhabi"))
        {
            sourceno = "AUH";
        }
        else if(source.equals("Dubai"))
        {
            sourceno = "DXB";
        }
        else if(source.equals("Sharjah"))
        {
            sourceno = "SHJ";
        }
        else if(source.equals("Ajman"))
        {
            sourceno = "AJM";
        }
        else if(source.equals("Um Al Quwain"))
        {
            sourceno = "UAQ";
        }
        else if(source.equals("RAK"))
        {
            sourceno = "RAK";
        }
        else
        {
            sourceno = "FJR";
        }
        tvInvoicePlate.setText("Car Plate: " + sourceno + " - " + plateno);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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
        QRGEncoder qrgEncoder = new QRGEncoder(String.valueOf(parking_id), null, QRGContents.Type.TEXT, dimen);
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

    //back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(this,SplitActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                username = intent.getStringExtra("UserName");
                startActivity(intent);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}