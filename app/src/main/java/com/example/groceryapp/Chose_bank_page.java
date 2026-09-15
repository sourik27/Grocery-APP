package com.example.groceryapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class Chose_bank_page extends AppCompatActivity {

    CardView amexBtn;
    CardView paypalBtn, applePayBtn, mastercardBtn;

    private double totalAmount;
    private ArrayList<Product> scannedProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chose_bank_page);

        if (getIntent() != null) {
            totalAmount = getIntent().getDoubleExtra("TOTAL", 0.0);
            scannedProducts = (ArrayList<Product>) getIntent().getSerializableExtra("PRODUCT_LIST");
        }

        amexBtn = findViewById(R.id.amexBtn);
        paypalBtn = findViewById(R.id.paypalBtn);
        mastercardBtn = findViewById(R.id.mastercardBtn);
        applePayBtn = findViewById(R.id.applePayBtn);

        amexBtn.setOnClickListener(v -> openPaymentPage("American Express"));
        paypalBtn.setOnClickListener(v -> openPaymentPage("PayPal"));
        mastercardBtn.setOnClickListener(v -> openPaymentPage("Mastercard"));
        applePayBtn.setOnClickListener(v -> openPaymentPage("Apple Pay"));
    }

    private void openPaymentPage(String selectedBank) {
        Intent intent = new Intent(this, payment_page.class);
        intent.putExtra("TOTAL", totalAmount);
        intent.putExtra("BANK_NAME", selectedBank);
        intent.putExtra("PRODUCT_LIST", scannedProducts);
        startActivity(intent);
    }
}