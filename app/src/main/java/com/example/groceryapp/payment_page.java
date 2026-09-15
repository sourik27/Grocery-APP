package com.example.groceryapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public class payment_page extends AppCompatActivity {

    TextView showingFinalTotal;
    Button pay, digitalRecept;
    ImageView successImage;
    private ArrayList<Product> scannedProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment_page);

        if (getIntent() != null) {
            scannedProducts = (ArrayList<Product>) getIntent().getSerializableExtra("PRODUCT_LIST");
        }

        showingFinalTotal = findViewById(R.id.showingFinalTotal);
        pay = findViewById(R.id.pay);
        digitalRecept = findViewById(R.id.digitalReceipt);

        double finalTotalAmount = getIntent().getDoubleExtra("TOTAL", 0.0);

        showingFinalTotal.setText(String.format(Locale.GERMANY, "€%.2f", finalTotalAmount));

        pay.setOnClickListener(p -> {
            showingFinalTotal.setText("Successfully paid" + "✅");
            pay.setVisibility(View.GONE);

            digitalRecept.setVisibility(View.VISIBLE);
        });

        digitalRecept.setOnClickListener(dr -> {
            Intent intent = new Intent(this, getInfos.class);
            intent.putExtra("PRODUCT_LIST", scannedProducts);
            startActivity(intent);
        });
    }
}