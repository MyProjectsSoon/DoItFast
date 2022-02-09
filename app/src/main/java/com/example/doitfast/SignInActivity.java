package com.example.doitfast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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


    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private CheckBox cbPassword;
    private TextView txtSignUp;

    //Firebase
    private FirebaseAuth auth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        //cbPassword = findViewById(R.id.cbPassword);
        txtSignUp = findViewById(R.id.txtSignUp);

        auth = FirebaseAuth.getInstance();

    }

    private boolean Validate()
    {


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

    public void onLoginClick(View view)
    {
        //Validate input
        if(Validate()){return;}



        //get UI
        String email=etEmail.getText().toString();
        String password=etPassword.getText().toString();

        auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                //Toast and prograssbar
                String message="Sign In Successful";
                Toast.makeText(SignInActivity.this,message,Toast.LENGTH_SHORT).show();


                //go to mainpage
                Intent intent=new Intent(SignInActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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