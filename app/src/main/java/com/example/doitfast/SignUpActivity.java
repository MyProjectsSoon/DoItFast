package com.example.doitfast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    //Reference for the UI elements
    private EditText etUserName , etEmail , etPassword;


    //Reference to the Firebase realtime database
    private FirebaseAuth auth;

    //create object Alter
    Alert alert = new Alert();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etUserName = findViewById(R.id.etUserName);
        etEmail = findViewById(R.id.etEmail2);
        etPassword = findViewById(R.id.etPassword2);

        //Get firebase auth object
        auth = FirebaseAuth.getInstance();

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


    //navigate to sign in
    public void signin(View view) {
        Intent intent = new Intent(this, SignInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    //validation user input in sign up

    private Boolean validateEmail() {
        String val = etEmail.getText().toString();
        String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})+$";
        if (val.isEmpty()) {
            etEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            etEmail.setError("Invalid etEmail address");
            return false;
        } else {
            etEmail.setError(null);
            return true;
        }
    }

    private boolean Validate()
    {
        //for username
        if(TextUtils.isEmpty(etUserName.getText().toString()))
        {
            etUserName.setError("Field cannot be empty");
            return true;
        }

        //for email
        String email = etEmail.getText().toString();
        if(TextUtils.isEmpty(email))
        {
            etEmail.setError("Field cannot be empty");
            return true;
        }
        String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})+$";
        if (!email.matches(emailPattern))
        {
            etEmail.setError("Invalid Email address \nex:(ex@ex.com)");
            return true;
        }

        //for password
        if(TextUtils.isEmpty(etPassword.getText().toString()))
        {
            etPassword.setError("Field cannot be empty");
            return true;
        }
        return false;
    }

    public void onSignupClick(View view)
    {
        //check validation of input user in sign up
        if(Validate())
        {
            alert.sendMsg("Error", "Fix the errors on the screen", SignUpActivity.this);
        }
        else
        {
            //get UI
            String email=etEmail.getText().toString();
            String password=etPassword.getText().toString();

            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    //Toast and prograssbar
                    String message="Account Created Succesfully";
                    Toast.makeText(SignUpActivity.this,message,Toast.LENGTH_SHORT).show();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    String message="Register Failed"+e.getMessage();
                    Toast.makeText(SignUpActivity.this,message,Toast.LENGTH_SHORT).show();

                }
            });
        }




    }
}