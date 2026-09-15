package com.example.groceryapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Digital_Recept extends AppCompatActivity {

    RecyclerView scanned_Products;
    TextView customerNameText;
    Button sendEmailBtn;
    ArrayList<Product> scannedProductList;
    ProductAdapter adapter;
    String userEmail;
    String firstName = "";
    String lastName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital_recept);

        scanned_Products = findViewById(R.id.scanned_Products);
        customerNameText = findViewById(R.id.customerNameText);
        sendEmailBtn = findViewById(R.id.sendEmailBtn);
        scannedProductList = new ArrayList<>();

        scanned_Products.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductAdapter(scannedProductList);
        scanned_Products.setAdapter(adapter);

        if (getIntent() != null) {
            firstName = getIntent().getStringExtra("FIRST_NAME");
            lastName = getIntent().getStringExtra("LAST_NAME");
            userEmail = getIntent().getStringExtra("EMAIL");

            // Retrieve the product list directly from the intent extra
            ArrayList<Product> receivedList = (ArrayList<Product>) getIntent().getSerializableExtra("PRODUCT_LIST");
            if (receivedList != null) {
                scannedProductList.clear();
                scannedProductList.addAll(receivedList);
                adapter.notifyDataSetChanged();
            }
        }

        // Display customer name
        if (firstName != null && lastName != null && !firstName.isEmpty()) {
            customerNameText.setText("Customer: " + firstName + " " + lastName);
        } else {
            customerNameText.setText("Customer: Valued Customer");
        }

        // Handle Receive via Email button click
        sendEmailBtn.setOnClickListener(v -> sendEmailReceipt());
    }

    private void sendEmailReceipt() {
        StringBuilder receiptBuilder = new StringBuilder();
        receiptBuilder.append("Digital Receipt\n");
        receiptBuilder.append("Customer: ").append(firstName != null ? firstName : "").append(" ").append(lastName != null ? lastName : "").append("\n\n");
        receiptBuilder.append("Purchased Items:\n");

        double total = 0.0;
        for (int i = 0; i < scannedProductList.size(); i++) {
            Product p = scannedProductList.get(i);
            receiptBuilder.append((i + 1)).append(". ").append(p.getName()).append(" - €").append(p.getPrice()).append("\n");
            try {
                total += Double.parseDouble(p.getPrice());
            } catch (NumberFormatException ignored) {}
        }
        receiptBuilder.append("\nTotal: €").append(String.format(Locale.GERMANY, "%.2f", total));

        String emailBody = receiptBuilder.toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // Only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{userEmail != null ? userEmail : ""});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Your Grocery App Digital Receipt");
        intent.putExtra(Intent.EXTRA_TEXT, emailBody);

        try {
            startActivity(Intent.createChooser(intent, "Send Email via..."));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No email client found on this device.", Toast.LENGTH_SHORT).show();
        }
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
                    .inflate(R.layout.activity_product, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Product p = list.get(position);
            holder.textProductName.setText(p.getName());
            holder.textProductPrice.setText("€" + p.getPrice());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView textProductName, textProductPrice;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                textProductName = itemView.findViewById(R.id.textProductName);
                textProductPrice = itemView.findViewById(R.id.textProductPrice);
            }
        }
    }
}