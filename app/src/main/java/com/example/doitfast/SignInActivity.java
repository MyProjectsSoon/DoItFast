package com.example.doitfast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {


    // References to UI elements
    private EditText etEmail , etPassword;
    private CheckBox cbPassword;


    // Reference to Firebase Auth
    private FirebaseAuth auth;

    //Create object Alert
    Alert alert = new Alert();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        cbPassword = findViewById(R.id.cbPassword);

        //Get firebase auth object
        auth = FirebaseAuth.getInstance();

        // Get reference
        SharedPreferences mypref = getSharedPreferences("MyPref" , Context.MODE_PRIVATE);
        String usrEmail = mypref.getString("UserEmail",null);

        if(usrEmail != null)
        {


            //send to home
            Intent intent = new Intent(this,HomeActivity.class);
            intent.putExtra("UserEmail",usrEmail);
            startActivity(intent);


            Toast.makeText(this, "Welcome to Park Ticket!", Toast.LENGTH_SHORT).show();
        }
//        else
//        {
//            setContentView(R.layout.activity_sign_in);
//        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    //back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(this,WelcomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    //validation user input in SignIn
    private boolean Validate()
    {
        //TextUtils.isEmpty(etEmail.getText().toString())

        String em = etEmail.getText().toString();
        if(em.isEmpty())
        {
            etEmail.setError("Input Required");
            return true;
        }

        if(TextUtils.isEmpty(etPassword.getText().toString()))
        {
            etPassword.setError("Input Required");
            return true;
        }
        return false;
    }

    public void toSignup(View view)
    {
        Intent intent=new Intent(SignInActivity.this,SignUpActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void onLoginClick(View view)
    {
        //Validate input
        if(Validate())
        {
            alert.sendMsg("Error", "Fix the errors on the screen", SignInActivity.this);
        }



        //get user input
        String email=etEmail.getText().toString();
        String password=etPassword.getText().toString();
        boolean boolIsChecked = cbPassword.isChecked();

        auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                //Toast and prograssbar
                String message="Sign In Successful";
                Toast.makeText(SignInActivity.this,message,Toast.LENGTH_SHORT).show();



                if(boolIsChecked)
                {

                    SharedPreferences mypref = getSharedPreferences("MyPref" , Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = mypref.edit();

                    editor.putString("UserEmail",email);
                    editor.putString("UserPass",password);
                    editor.commit();
                }

                //go to main page
                Intent intent=new Intent(SignInActivity.this,HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("UserEmail",email);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                String message="Sign In Failed"+e.getMessage();
                Toast.makeText(SignInActivity.this,message,Toast.LENGTH_SHORT).show();

            }
        });



    }

}