package com.example.renuka;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {
    EditText mEmail, mPassword;
    Button mLoginBtn;
    TextView mCreateBtn, forgot;
    ProgressBar progess;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_page);


        mEmail = findViewById(R.id.enterMaillog);
        mLoginBtn = findViewById(R.id.loginbtn);
        mPassword = findViewById(R.id.passLogEnter);
        mCreateBtn = findViewById(R.id.LoginText);
        forgot = findViewById(R.id.forgotTxt);

        fAuth = FirebaseAuth.getInstance();
        progess = findViewById(R.id.progressBar);
        mLoginBtn.setOnClickListener(v -> {
            String email = mEmail.getText().toString().trim();
            String password = mPassword.getText().toString().trim();
            if (TextUtils.isEmpty(email)) {

                mEmail.setError("Email is Required");
                return;
            }
            if (TextUtils.isEmpty(password)) {
                mPassword.setError("Password is Required");
                return;

            }
            if (password.length() < 6) {
                mPassword.setError("Password must be greater than 6 characters");
                return;

            }
            progess.setVisibility(View.VISIBLE);
            fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginPage.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

                } else {
                    Toast.makeText(LoginPage.this, "Error !!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();


                }
            });
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password");
                passwordResetDialog.setMessage("Enter your Email to Received Reset Link");
                passwordResetDialog.setView(resetMail);
                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mail = resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(LoginPage.this, "Reset Link is send to your Email", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginPage.this, "Email is not Send" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                passwordResetDialog.create().show();
            }
        });


    }

    public void registerPage(View view) {
        startActivity(new Intent(getApplicationContext(),RegisterPage.class));
    }

}