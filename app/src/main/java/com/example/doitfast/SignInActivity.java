package com.example.doitfast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doitfast.model.Member;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {


    // References to UI elements
    private EditText etEmail , etPassword;
    private CheckBox cbPassword;

    //Reference to the Firebase realtime database
    private FirebaseDatabase database;

    //Reference to a specific node in the database
    private DatabaseReference reference;
    // Reference to Firebase Auth
    private FirebaseAuth auth;

    //Variable
    private int[] id = new int[1];
    private String[] user = new String[1];


    //Create object Alert
    Alert alert = new Alert();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        etEmail = findViewById(R.id.etEmailSend);
        etPassword = findViewById(R.id.etPassword);
        cbPassword = findViewById(R.id.cbPassword);

        //Get the database object and a reference to the members collection
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("members");

        //Get firebase auth object
        auth = FirebaseAuth.getInstance();

        // receive from home
        Intent intent1 = getIntent();
        String logout = intent1.getStringExtra("LOGOUT");


        if(logout == null)
        {
            // Get reference for Share Preference
            SharedPreferences mypref = getSharedPreferences("MyPref" , Context.MODE_PRIVATE);
            int usrId = mypref.getInt("id",0);
            String usrName = mypref.getString("UserName",null);
            String usrEmail = mypref.getString("UserEmail",null);

            if(usrEmail != null)
            {


                //send to home
                Intent intent = new Intent(this,HomeActivity.class);
                intent.putExtra("id",usrId);
                intent.putExtra("UserName",usrName);
                intent.putExtra("UserEmail",usrEmail);
                startActivity(intent);


                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
            }
        }



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

    public void toSignup(View view)
    {
        Intent intent=new Intent(SignInActivity.this,SignUpActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void toForgetPassword(View view)
    {
        Intent intent=new Intent(SignInActivity.this,ForgetPassword.class);
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
        else
        {
            //get user input
            String email=etEmail.getText().toString();
            String password=etPassword.getText().toString();
            boolean boolIsChecked = cbPassword.isChecked();

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot node : snapshot.getChildren()) {
                        Member member = node.getValue(Member.class);
                        //emailMembers.add(member.getEmail());
                        if (email.equals(member.getEmail())) {
                            id[0] = (int) member.getId();
                            user[0] = member.getUsername();
                            break;
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {

                    //Toast
                    String message="Sign In Successful";
                    Toast.makeText(SignInActivity.this,message,Toast.LENGTH_SHORT).show();

                    String username = SignInActivity.this.user[0];
                    int userid = id[0];

                    if(boolIsChecked)
                    {

                        SharedPreferences mypref = getSharedPreferences("MyPref" , Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = mypref.edit();

                        editor.putInt("id",userid);
                        editor.putString("UserName",username);
                        editor.putString("UserEmail",email);
                        editor.putString("UserPass",password);
                        editor.commit();
                    }

                    //go to home page
                    Intent intent=new Intent(SignInActivity.this,HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("UserEmail",email);
                    intent.putExtra("UserName", username);
                    intent.putExtra("id", userid);
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

}