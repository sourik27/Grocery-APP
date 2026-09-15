package com.example.groceryapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner;
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning;

import java.util.HashMap;
import java.util.Map;

public class Add_Products extends AppCompatActivity {

    private FirebaseFirestore db;
    Button openCamaraBtn, ConfirmBtn;
    private GmsBarcodeScanner scanner;
    EditText EnterBarcodeInput, EnterProductNameInput, EnterProductPriceInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_products);

        db = FirebaseFirestore.getInstance();

        openCamaraBtn = findViewById(R.id.openCamaraBtn);
        ConfirmBtn = findViewById(R.id.ConfirmBtn);
        EnterBarcodeInput = findViewById(R.id.EnterBarcodeInput);
        EnterProductNameInput = findViewById(R.id.EnterProductNameInput);
        EnterProductPriceInput = findViewById(R.id.EnterProductPriceInput);

        GmsBarcodeScannerOptions options = new GmsBarcodeScannerOptions.Builder()
                .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
                .build();

        scanner = GmsBarcodeScanning.getClient(this, options);

        openCamaraBtn.setOnClickListener(v -> scanCode());

        ConfirmBtn.setOnClickListener(CB -> confirmBtn());
    }

    private void scanCode() {
        scanner.startScan()
                .addOnSuccessListener(barcode -> {
                    String rawValue = barcode.getRawValue();
                    if (rawValue != null) {
                        EnterBarcodeInput.setText(rawValue);
                        Toast.makeText(this, "Barcode Scanned!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void confirmBtn() {
        String barcode = EnterBarcodeInput.getText().toString().trim();
        String rawName = EnterProductNameInput.getText().toString().trim();
        String priceStr = EnterProductPriceInput.getText().toString().trim();

        if (barcode.isEmpty()) {
            EnterBarcodeInput.setError("Enter a barcode id");
            return;
        }

        if (rawName.isEmpty()) {
            EnterProductNameInput.setError("Enter a product name");
            return;
        }

        // Converts the product name entirely to lowercase for the database
        String name = rawName.toLowerCase();

        double price;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            EnterProductPriceInput.setError("Enter a valid price number");
            return;
        }

        Map<String, Object> addingProduct = new HashMap<>();
        addingProduct.put("Barcode_ID", barcode);
        addingProduct.put("Name", name);
        addingProduct.put("Price", price);

        db.collection("Products")
                .document(name)
                .set(addingProduct)
                .addOnSuccessListener(b -> {
                    Toast.makeText(this, "Product added successfully!", Toast.LENGTH_SHORT).show();
                    clearFields();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error saving product: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void clearFields() {
        EnterBarcodeInput.setText("");
        EnterProductNameInput.setText("");
        EnterProductPriceInput.setText("");
    }
}