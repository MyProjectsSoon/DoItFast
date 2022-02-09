package com.example.doitfast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
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

    private EditText etFullName;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnSignUp;
    private TextView txtSignin;

    //Firebase
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail2);
        etPassword = findViewById(R.id.etPassword2);
        btnSignUp = findViewById(R.id.btnSignup);
        txtSignin = findViewById(R.id.txtSignin);

        auth = FirebaseAuth.getInstance();

    }

    private boolean Validate()
    {
        if(TextUtils.isEmpty(etFullName.getText().toString()))
        {
            etFullName.setError("Input Required");
            return true;
        }

        if(TextUtils.isEmpty(etEmail.getText().toString()))
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

    public void onSignupClick(View view)
    {
        //check if empty
        if(Validate())
        {
            return;
        }



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