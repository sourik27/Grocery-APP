package com.example.groceryapp;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button goToProductsPageBtn, goToScannerPageBtn, addProductPage, searchProductBtn;
    TextView titleMain;
    ImageView floatingCart, floatingBag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        floatingCart = findViewById(R.id.floatingCart);
        floatingBag = findViewById(R.id.floatingBag);
        titleMain = findViewById(R.id.titleMain);
        goToScannerPageBtn = findViewById(R.id.goToScannerPageBtn);
        addProductPage = findViewById(R.id.addProductPage);
        searchProductBtn = findViewById(R.id.searchProductBtn);
        goToProductsPageBtn = findViewById(R.id.goToProductsPageBtn);

        // Run smooth staggered entrance animations for UI
        playEntranceAnimations();

        // Get screen dimensions for proportional floating movement
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float screenWidth = displayMetrics.widthPixels;
        float screenHeight = displayMetrics.heightPixels;

        // Animate grocery icons drifting and gently rotating
        animateGroceryElement(floatingCart, -screenWidth * 0.25f, screenHeight * 0.2f, 6000, 10f);
        animateGroceryElement(floatingBag, screenWidth * 0.25f, -screenHeight * 0.2f, 7000, -12f);

        // Button bindings & navigation
        goToProductsPageBtn.setOnClickListener(view -> {
            Intent productPage = new Intent(this, AllProductsPage.class);
            startActivity(productPage);
        });
        goToScannerPageBtn.setOnClickListener(view -> {
            Intent ScannerPage = new Intent(this, ScanningProductsPage.class);
            startActivity(ScannerPage);
        });
        addProductPage.setOnClickListener(view -> {
            Intent addProductPageIntent = new Intent(this, Add_Products.class);
            startActivity(addProductPageIntent);
        });
        searchProductBtn.setOnClickListener(view -> {
            Intent searchPage = new Intent(this, Search_products.class);
            startActivity(searchPage);
        });
    }

    private void playEntranceAnimations() {
        View[] elements = {titleMain, goToScannerPageBtn, addProductPage, searchProductBtn, goToProductsPageBtn};

        for (int i = 0; i < elements.length; i++) {
            View view = elements[i];
            view.setAlpha(0f);
            view.setTranslationY(60f);

            view.animate()
                    .alpha(1f)
                    .translationY(0f)
                    .setDuration(600)
                    .setStartDelay(100L * i)
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .start();
        }
    }

    private void animateGroceryElement(View view, float deltaX, float deltaY, long duration, float rotationAngle) {
        // Translation X (Drift horizontally)
        ObjectAnimator driftX = ObjectAnimator.ofFloat(view, "translationX", 0f, deltaX, 0f);
        driftX.setDuration(duration);
        driftX.setRepeatCount(ValueAnimator.INFINITE);
        driftX.setRepeatMode(ValueAnimator.REVERSE);
        driftX.setInterpolator(new AccelerateDecelerateInterpolator());
        driftX.start();

        // Translation Y (Drift vertically)
        ObjectAnimator driftY = ObjectAnimator.ofFloat(view, "translationY", 0f, deltaY, 0f);
        driftY.setDuration(duration + 1000);
        driftY.setRepeatCount(ValueAnimator.INFINITE);
        driftY.setRepeatMode(ValueAnimator.REVERSE);
        driftY.setInterpolator(new AccelerateDecelerateInterpolator());
        driftY.start();

        // Subtle rotation for a lively organic feel
        ObjectAnimator rotate = ObjectAnimator.ofFloat(view, "rotation", 0f, rotationAngle, 0f);
        rotate.setDuration(duration + 500);
        rotate.setRepeatCount(ValueAnimator.INFINITE);
        rotate.setRepeatMode(ValueAnimator.REVERSE);
        rotate.setInterpolator(new AccelerateDecelerateInterpolator());
        rotate.start();
    }
}