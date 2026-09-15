package com.example.groceryapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner;
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ScanningProductsPage extends AppCompatActivity {

    private final List<Product> scannedProductList = new ArrayList<>();
    private ProductAdapter adapter;

    private FirebaseFirestore db;
    private Button openCamaraBtn;
    private Button payBtn;

    private Button add_to_listBtn1;
    private Button add_to_listBtn2;
    private Button add_to_listBtn3;
    private Button add_to_listBtn4;
    private Button add_to_listBtn5;
    private Button add_to_listBtn6;
    private Button add_to_listBtn7;

    private TextView Total_text;
    private RecyclerView scanned_Products;
    private GmsBarcodeScanner scanner;

    // Updated to match exact Firestore naming (lowercase for apple juice, gift card, pizza oven)
    private String AppleJuice = "apple juice";
    private String Calculator = "Calculator";
    private String Chips = "Chips";
    private String Chocolate = "Chocolate";
    private String GiftCard = "gift card";
    private String PizzaOven = "pizza oven";
    private String Salami = "Salami";

    String rawValue;
    double finalTotal = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning_products_page);

        db = FirebaseFirestore.getInstance();

        openCamaraBtn = findViewById(R.id.openCamaraBtn);
        payBtn = findViewById(R.id.payBtn);
        Total_text = findViewById(R.id.Total_text);
        scanned_Products = findViewById(R.id.scanned_Products);

        add_to_listBtn1 = findViewById(R.id.add_to_listBtn1);
        add_to_listBtn2 = findViewById(R.id.add_to_listBtn2);
        add_to_listBtn3 = findViewById(R.id.add_to_listBtn3);
        add_to_listBtn4 = findViewById(R.id.add_to_listBtn4);
        add_to_listBtn5 = findViewById(R.id.add_to_listBtn5);
        add_to_listBtn6 = findViewById(R.id.add_to_listBtn6);
        add_to_listBtn7 = findViewById(R.id.add_to_listBtn7);

        scanned_Products.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductAdapter(scannedProductList);
        scanned_Products.setAdapter(adapter);

        add_to_listBtn1.setOnClickListener(p1 -> {
            db.collection("Products")
                    .whereEqualTo("Name", AppleJuice)
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            DocumentSnapshot document = queryDocumentSnapshots.getDocuments().get(0);
                            addProductToRecyclerView(document);
                        } else {
                            Toast.makeText(this, "Product not found", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        });

        add_to_listBtn2.setOnClickListener(p2 -> {
            db.collection("Products")
                    .whereEqualTo("Name", Calculator)
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            DocumentSnapshot document = queryDocumentSnapshots.getDocuments().get(0);
                            addProductToRecyclerView(document);
                        } else {
                            Toast.makeText(this, "Product not found", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        });

        add_to_listBtn3.setOnClickListener(p3 -> {
            db.collection("Products")
                    .whereEqualTo("Name", Chips)
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            DocumentSnapshot document = queryDocumentSnapshots.getDocuments().get(0);
                            addProductToRecyclerView(document);
                        } else {
                            Toast.makeText(this, "Product not found", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        });

        add_to_listBtn4.setOnClickListener(p4 -> {
            db.collection("Products")
                    .whereEqualTo("Name", Chocolate)
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            DocumentSnapshot document = queryDocumentSnapshots.getDocuments().get(0);
                            addProductToRecyclerView(document);
                        } else {
                            Toast.makeText(this, "Product not found", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        });

        add_to_listBtn5.setOnClickListener(p5 -> {
            db.collection("Products")
                    .whereEqualTo("Name", GiftCard)
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            DocumentSnapshot document = queryDocumentSnapshots.getDocuments().get(0);
                            addProductToRecyclerView(document);
                        } else {
                            Toast.makeText(this, "Product not found", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        });

        add_to_listBtn6.setOnClickListener(p6 -> {
            db.collection("Products")
                    .whereEqualTo("Name", PizzaOven)
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            DocumentSnapshot document = queryDocumentSnapshots.getDocuments().get(0);
                            addProductToRecyclerView(document);
                        } else {
                            Toast.makeText(this, "Product not found", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        });

        add_to_listBtn7.setOnClickListener(p7 -> {
            db.collection("Products")
                    .whereEqualTo("Name", Salami)
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            DocumentSnapshot document = queryDocumentSnapshots.getDocuments().get(0);
                            addProductToRecyclerView(document);
                        } else {
                            Toast.makeText(this, "Product not found", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        });

        GmsBarcodeScannerOptions options = new GmsBarcodeScannerOptions.Builder()
                .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
                .build();

        scanner = GmsBarcodeScanning.getClient(this, options);

        openCamaraBtn.setOnClickListener(v -> scanCode());

        payBtn.setOnClickListener(v -> {
            if(finalTotal == 0.0){
                Toast.makeText(this, "Purchase a product to continue", Toast.LENGTH_SHORT).show();

            }else{
                Intent intent = new Intent(this, Chose_bank_page.class);
                intent.putExtra("TOTAL", finalTotal);
                intent.putExtra("PRODUCT_LIST", (ArrayList<Product>) scannedProductList);
                startActivity(intent);
            }
        });
    }

    private void addProductToRecyclerView(DocumentSnapshot document) {
        String barcodeID = document.getString("Barcode_ID");
        String productName = document.getString("Name");

        Object priceObj = document.get("Price");
        String productPrice = priceObj != null ? priceObj.toString() : "0.00";

        Product scannedProduct = new Product(productName, productPrice, barcodeID);
        scannedProductList.add(scannedProduct);

        adapter.notifyItemInserted(scannedProductList.size() - 1);
        scanned_Products.scrollToPosition(scannedProductList.size() - 1);

        calculateTotal();
        Toast.makeText(this, "Product found: " + productName, Toast.LENGTH_SHORT).show();
    }

    private void scanCode() {
        scanner.startScan()
                .addOnSuccessListener(barcode -> {
                    if (barcode.getRawValue() == null) {
                        Toast.makeText(this, "Invalid barcode scanned", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    rawValue = barcode.getRawValue().trim();

                    db.collection("Products")
                            .whereEqualTo("Barcode_ID", rawValue)
                            .get()
                            .addOnSuccessListener(queryDocumentSnapshots -> {
                                if (!queryDocumentSnapshots.isEmpty()) {
                                    DocumentSnapshot document = queryDocumentSnapshots.getDocuments().get(0);
                                    addProductToRecyclerView(document);
                                } else {
                                    Toast.makeText(this, "Product not found", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(e ->
                                    Toast.makeText(this, "Database error: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                            );
                })
                .addOnCanceledListener(() ->
                        Toast.makeText(this, "Scan Canceled", Toast.LENGTH_SHORT).show()
                )
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Scanner error: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
    }

    private void calculateTotal() {
        double total = 0.0;
        for (Product item : scannedProductList) {
            try {
                total += Double.parseDouble(item.getPrice());
            } catch (NumberFormatException ignored) {}
        }
        Total_text.setText(String.format(Locale.US, "$%.2f", total));
        finalTotal = total;
    }

    private static class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
        private final List<Product> list;

        public ProductAdapter(List<Product> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_2, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Product p = list.get(position);
            holder.text1.setText(p.getName());
            holder.text2.setText("€" + p.getPrice());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView text1, text2;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                text1 = itemView.findViewById(android.R.id.text1);
                text2 = itemView.findViewById(android.R.id.text2);
            }
        }
    }
}