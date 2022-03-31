package com.example.doitfast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doitfast.items.Alert;
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

import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    //Reference for the UI elements
    private EditText etUserName , etEmail , etPassword;

    //Reference to the Firebase realtime database
    private FirebaseDatabase database;

    //Reference to a specific node in the database
    private DatabaseReference reference;

    //Reference to the Firebase realtime database
    private FirebaseAuth auth;

    //create object Alter
    Alert alert = new Alert();

    //Variable to count number of object in database
    long maxid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etUserName = findViewById(R.id.etUserName);
        etEmail = findViewById(R.id.etEmail2);
        etPassword = findViewById(R.id.etPassword2);

        //Get the database object and a reference to the members collection
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("members");

        //to generate id number of members
        //1- count number of members in database and put it in variable maxid
        //2- when create object put id as maxid +1


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
            //get user input
            String username = etUserName.getText().toString();
            String email=etEmail.getText().toString();
            String password=etPassword.getText().toString();

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    //to sign up
                    //1- get all emails in database and put it in an array list
                    //2- compare email text with each email in array list
                    //3- if you don't find the same email in the database, application  will sign up

                    //step 1 to get all emails
                    List<String> emailMembers = new ArrayList<>();
                    for (DataSnapshot node : snapshot.getChildren()) {
                        Member member = node.getValue(Member.class);
                        emailMembers.add(member.getEmail());
                    }

                    //step 2 to compare
                    boolean newRegister = true;
                    for (int n = 0; n < emailMembers.size(); n++) {
                        String e = emailMembers.get(n);
                        if (email.equals(e)) {
                            newRegister = false;
                            break;
                        }
                    }


                    // here the application will sign up if it doesn't find same the email in database
                    if (!newRegister) {
                        String message = "a member with same email already exsits";
                        Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_SHORT).show();
                    } else {
                        //here step 2 to create object
                        final long id = maxid + 1;
                        final Member member = new Member(id, username, email, password);

                        reference.child(String.valueOf(id)).setValue(member).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                etUserName.setText("");
                                etEmail.setText("");
                                etPassword.setText("");
                                String message = "Member Addded sucessfully";
                                Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

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