package com.example.groceryapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class getInfos extends AppCompatActivity {

    EditText getNameInput, getlastNameInput, getEMailInput;
    Button confirmBtn_getInfos_page;
    FirebaseFirestore db;
    private ArrayList<Product> scannedProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_get_infos);

        if (getIntent() != null) {
            scannedProducts = (ArrayList<Product>) getIntent().getSerializableExtra("PRODUCT_LIST");
        }

        db = FirebaseFirestore.getInstance();

        getNameInput = findViewById(R.id.getNameInput);
        getlastNameInput = findViewById(R.id.getlastNameInput);
        getEMailInput = findViewById(R.id.getEMailInput);
        confirmBtn_getInfos_page = findViewById(R.id.confirmBtn_getInfos_page);

        confirmBtn_getInfos_page.setOnClickListener(v -> saveUserData());
    }

    private void saveUserData() {
        String name = getNameInput.getText().toString().trim();
        String lastName = getlastNameInput.getText().toString().trim();
        String email = getEMailInput.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> addingUserData = new HashMap<>();
        addingUserData.put("First_Name", name);
        addingUserData.put("Last_Name", lastName);
        addingUserData.put("Email", email);

        // Using the email address as the document ID in Firestore
        db.collection("User Data")
                .document(email)
                .set(addingUserData)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(this, Digital_Recept.class);
                    intent.putExtra("PRODUCT_LIST", scannedProducts);
                    intent.putExtra("FIRST_NAME", name);
                    intent.putExtra("LAST_NAME", lastName);
                    intent.putExtra("EMAIL", email);
                    startActivity(intent);
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error saving data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}