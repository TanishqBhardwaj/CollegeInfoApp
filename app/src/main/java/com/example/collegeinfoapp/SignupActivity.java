package com.example.collegeinfoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextCreatePassword;
    private EditText editTextConfirmPassword;
    private Button buttonSignup;

    private String email;
    private String createPassword;
    private String confirmPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();

        setView();
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        FirebaseUser currentUser = mAuth.getCurrentUser();
////        updateUI(currentUser);
//    }

    private void setView() {
        editTextEmail = findViewById(R.id.editTextTextEmailAddress);
        editTextCreatePassword = findViewById(R.id.editTextCreatePassword);
        editTextConfirmPassword = findViewById(R.id.editTextTextConfirmPassword);
        buttonSignup = findViewById(R.id.buttonSignup);

        buttonSignup.setOnClickListener(view -> {
            email = editTextEmail.getText().toString();
            createPassword = editTextCreatePassword.getText().toString().trim();
            confirmPassword = editTextConfirmPassword.getText().toString().trim();

            if(createPassword.equals(confirmPassword)) {
                addUser();
            }
            else {
                Toast.makeText(SignupActivity.this, "Passwords do not match!",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addUser() {
        mAuth.createUserWithEmailAndPassword(email, createPassword)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(SignupActivity.this,
                                "Sign up unsuccessful!", Toast.LENGTH_LONG).show();
                    }
                });
    }
}