package com.example.renuka;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GraphActivity extends AppCompatActivity {


        private CustomBarChartView chartView;  // Your custom canvas-based chart view

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_graph);  // Make sure this XML has your chart view
//            CustomBarChartView pieChartView = findViewById(R.id.pieChartView);

//            FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//            db.collection("PostureData")
//                    .get()
//                    .addOnCompleteListener(task -> {
//                        if (task.isSuccessful()) {
//                            Map<String, Float> fieldSums = new java.util.HashMap<>();
//
//                            for (QueryDocumentSnapshot doc : task.getResult()) {
//                                Map<String, Object> fields = doc.getData();
//
//                                for (Map.Entry<String, Object> entry : fields.entrySet()) {
//                                    if (entry.getValue() instanceof Number) {
//                                        float value = ((Number) entry.getValue()).floatValue();
//                                        String key = entry.getKey();
//                                        fieldSums.put(key, fieldSums.getOrDefault(key, 0f) + value);
//                                    }
//                                }
//                            }
//
//                            pieChartView.setData(fieldSums);
//                        }
//                    });


            chartView = findViewById(R.id.customBarChart);  // Match the ID from your layout

            FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection("PostureData")
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            List<Float> values = new ArrayList<>();
                            List<String> labels = new ArrayList<>();

                            int docIndex = 1;
                            for (QueryDocumentSnapshot doc : task.getResult()) {
                                Map<String, Object> allFields = doc.getData();

                                for (Map.Entry<String, Object> entry : allFields.entrySet()) {
                                    Object val = entry.getValue();
                                    if (val instanceof Number) {
                                        values.add(((Number) val).floatValue());
                                        labels.add("Doc" + docIndex + ":" + entry.getKey());
                                    }
                                }
                                docIndex++;
                            }

                            // Now send it to your canvas-based bar chart
                            chartView.setData(values, labels);

                        } else {
                            Log.e("Firestore", "Error getting documents: ", task.getException());
                        }
                    });
        }
    }
