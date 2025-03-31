package com.example.renuka;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView fullName,pitchText;
    String userId="user123";
    FirebaseFirestore fStore;
    String userName="Renuka";
    String mobile = "7745086002";
    String email="bhavsarrenu04@gmail.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        fStore=FirebaseFirestore.getInstance();
        fullName=findViewById(R.id.text);
        pitchText=findViewById(R.id.textView);
//        Object DocumentReference;
//        DocumentReference documentReference = fStore.collection("PostureData").document(userId);
//        Map<String, Object> user = new HashMap<>();
//        user.put("fName",userName);
//        user.put("phoneno",mobile);
//        user.put("mail",email);
//        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                Log.d(TAG,"onSuccess User Profile is Created for"+userId);
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.d(TAG,"OnFailure"+ e.toString());
//            }
//
//        });
        DocumentReference documentRef=fStore.collection("PostureData").document(userId);
        documentRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
               // mobNo.setText(value.getString("pitch"));
                fullName.setText(value.getString("timestamp"));
            Double Pitch1 = value.getDouble("pitch");
            String pp=Pitch1.toString();
                pitchText.setText(pp);

            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}