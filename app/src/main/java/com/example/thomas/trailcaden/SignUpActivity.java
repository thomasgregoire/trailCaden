package com.example.thomas.trailcaden;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Joris on 12/02/2018.
 */

public class SignUpActivity extends AppCompatActivity {
    protected EditText passwordEditText;
    protected EditText emailEditText;
    protected EditText nameEditText;
    protected EditText firstNameEditText;
    protected EditText dateEditText;
    protected Button signUpButton;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize FirebaseAuth
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        passwordEditText = (EditText)findViewById(R.id.passwordField);
        emailEditText = (EditText)findViewById(R.id.emailField);
        nameEditText = (EditText)findViewById(R.id.nameField);
        firstNameEditText = (EditText)findViewById(R.id.firstNameField);
        dateEditText = (EditText)findViewById(R.id.dateField);
        signUpButton = (Button)findViewById(R.id.signupButton);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String name = nameEditText.getText().toString();
                String firstName = firstNameEditText.getText().toString();
                String date = dateEditText.getText().toString();

                password = password.trim();
                email = email.trim();
                name = name.trim();
                firstName = firstName.trim();
                date = date.trim();

                if (password.isEmpty() || email.isEmpty() || name.isEmpty() || firstName.isEmpty() || date.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                    builder.setMessage(R.string.signup_error_message)
                            .setTitle(R.string.signup_error_title)
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String uid = task.getResult().getUser().getUid();
                                String email = emailEditText.getText().toString();
                                String name = nameEditText.getText().toString();
                                String firstName = firstNameEditText.getText().toString();
                                String date = dateEditText.getText().toString();

                                Person person = new Person(uid, name, firstName, email, date);
                                mDatabase.child("users").child(uid).push().setValue(person);

                                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                                builder.setMessage(task.getException().getMessage())
                                        .setTitle(R.string.login_error_title)
                                        .setPositiveButton(android.R.string.ok, null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }
                    });
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void showDatePickerDialog(View v) {
        DatePickerDialog newFragment = new DatePickerDialog(this);

        newFragment.show();
        newFragment.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                EditText et = findViewById(R.id.dateField);

                if(month < 10) {
                    et.setText(dayOfMonth + "/0" + (month + 1) + "/" + year);
                } else {
                    et.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                }
            }
        });
    }
}
