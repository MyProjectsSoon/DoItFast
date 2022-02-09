package com.example.doitfast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    private EditText etFullName;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnSignUp;
    private TextView txtSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignUp = findViewById(R.id.btnSignup);
        txtSignin = findViewById(R.id.txtSignin);
    }
}