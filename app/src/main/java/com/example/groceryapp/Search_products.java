package com.example.groceryapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class Search_products extends AppCompatActivity {
    private FirebaseFirestore db;

    TextView confrimationText;
    EditText searchbar;
    Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_products);

        db = FirebaseFirestore.getInstance();

        searchbar = findViewById(R.id.searchbar);
        searchBtn = findViewById(R.id.searchBtn);
        confrimationText = findViewById(R.id.confrimationText);

        searchBtn.setOnClickListener(v -> {
            String searchbarValue = searchbar.getText().toString().trim();

            if (searchbarValue.isEmpty()) {
                searchbar.setError("Please enter a product name");
                return;
            }

            // Converts the search input entirely to lowercase
            String formattedSearchValue = searchbarValue.toLowerCase();

            db.collection("Products")
                    .document(formattedSearchValue)
                    .get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            String name = documentSnapshot.getString("Name");
                            Object price = documentSnapshot.get("Price");

                            String resultText = "Product Found!\n\n" +
                                    "Item: " + name + "\n" +
                                    "Price: €" + price + "\n\n" +
                                    "Status: Available in Store ✅";

                            confrimationText.setText(resultText);
                        } else {
                            String notFoundText = "❌ Product Not Found\n\n" +
                                    "We couldn't find \"" + formattedSearchValue + "\" in our database. Please check the spelling and try again.";

                            confrimationText.setText(notFoundText);
                        }
                    })
                    .addOnFailureListener(e -> {
                        confrimationText.setText("⚠️ Error: " + e.getMessage());
                    });
        });
    }
}