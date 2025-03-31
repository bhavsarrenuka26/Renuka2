package com.example.renuka;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ProfilePage extends AppCompatActivity {
    TextView fullName, email, mobNo;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_page);

        //String user1="user123";

            fullName = findViewById(R.id.tv_name);
            email = findViewById(R.id.tv_email);
            mobNo = findViewById(R.id.tv_mobile);
            fAuth = FirebaseAuth.getInstance();
            fStore = FirebaseFirestore.getInstance();
            userId = fAuth.getCurrentUser().getUid();
            DocumentReference documentReference = fStore.collection("UserData").document(userId);
//   DocumentReference documentRefe=fStore.collection("PostureData").document(user1);
            documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    mobNo.setText(value.getString("phoneno"));
                    fullName.setText(value.getString("fName"));
                    email.setText(value.getString("mail"));
                }


            });
//        documentRefe.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                email.setText(value.getString("pitch0"));
//            }
//        });


        }


        public void logout(View view) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), LoginPage.class));
            finish();
        }
    }
