package com.example.renuka;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterPage extends AppCompatActivity {
    EditText fullName, mEmail, mPassword, mPhone;

    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore ;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_page);


            fullName = findViewById(R.id.nameEnter);
            mEmail = findViewById(R.id.enterMail);
            mLoginBtn = findViewById(R.id.alraedyTxt);
            mPassword = findViewById(R.id.passEnter);
            mPhone = findViewById(R.id.enterPhone);
            fAuth = FirebaseAuth.getInstance();
            fStore = FirebaseFirestore.getInstance();
            progressBar = findViewById(R.id.progessround);
            mRegisterBtn = findViewById(R.id.registerbutn);

            if (fAuth.getCurrentUser() != null) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }

            mRegisterBtn.setOnClickListener(v -> {
                final String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                final String userName = fullName.getText().toString();
                final String mobile = mPhone.getText().toString();

                if (userName.isEmpty() || !userName.matches("^[a-zA-Z ]+$")) {
                    fullName.setError("Enter a valid name");
                    return;
                }
                if (mobile.isEmpty() || !mobile.matches("^[6-9]\\d{9}$")) {
                    mPhone.setError("Enter a valid 10-digit mobile number");
                    return;
                }
                if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    mEmail.setError("Enter a valid email");
                    return;
                }

                if (password.isEmpty() || password.length() < 6) {
                    mPassword.setError("Password must be at least 6 characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);


                //register the user
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
//                    sendRegistrationNotification();
                        Toast.makeText(RegisterPage.this, "User Created", Toast.LENGTH_SHORT).show();

                        userId = fAuth.getCurrentUser().getUid();
                        Object DocumentReference;
                        com.google.firebase.firestore.DocumentReference documentReference = fStore.collection("UserData").document(userId);
                        Map<String, Object> user = new HashMap<>();
                        user.put("fName", userName);
                        user.put("phoneno", mobile);
                        user.put("mail", email);
                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "onSuccess User Profile is Created for" + userId);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "OnFailure" + e.toString());
                            }

                        });

                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    } else {
                        Toast.makeText(RegisterPage.this, "Error !!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);

                    }
                });

            });
        }

//    private void sendRegistrationNotification() {
//            String channelId = "registration_channel";
//            String channelName = "Registration Notifications";
//
//            // Step 1: Create Notification Channel (only for Android O+)
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                NotificationChannel channel = new NotificationChannel(
//                        channelId,
//                        channelName,
//                        NotificationManager.IMPORTANCE_DEFAULT
//                );
//                channel.setDescription("Notifications after successful registration");
//
//                NotificationManager notificationManager = getSystemService(NotificationManager.class);
//                if (notificationManager != null) {
//                    notificationManager.createNotificationChannel(channel);
//                }
//            }
//
//            // Step 2: Build the Notification
//            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
//                    .setSmallIcon(R.drawable.ic_logout) // use your drawable here
//                    .setContentTitle("Registration Successful 🎉")
//                    .setContentText("Welcome! You have successfully registered.")
//                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                    .setAutoCancel(true);
//
//            // Step 3: Show the Notification
//            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
//            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
//
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return;
//            }
//            notificationManagerCompat.notify(1, builder.build());
//        }
//

        public void loginPage(View view) {
            startActivity(new Intent(getApplicationContext(),LoginPage.class));
        }

    }



