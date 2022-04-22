package com.example.doitfast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class ParkingActivity extends AppCompatActivity {

    private Button btncode1,btncode2,btncode3,btncode4;
    private Button btncode5,btncode6,btncode7,btncode8;
    private Button btncode9,btncode10,btncode11,btncode12;
    private Button btncode13,btncode14,btncode15,btncode16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);

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
                    System.out.println("2");
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
                    System.out.println("3");
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
        Intent intent=new Intent(this,VechileDetails.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}


