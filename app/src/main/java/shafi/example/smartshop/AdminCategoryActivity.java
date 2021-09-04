package shafi.example.smartshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminCategoryActivity extends AppCompatActivity {
    private ImageView tShirts, sportsShirts, femaleDresses, sweaters;
    private ImageView glasses, hats, wallets, shoes;
    private ImageView headPhones, laptops, watches, mobilePhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        tShirts = findViewById(R.id.t_shirts);
        sportsShirts = findViewById(R.id.sports);
        femaleDresses = findViewById(R.id.female_dress);
        sweaters = findViewById(R.id.sweathers);

        glasses = findViewById(R.id.glasses);
        hats = findViewById(R.id.hats);
        wallets = findViewById(R.id.purches_bag);
        shoes = findViewById(R.id.shoes);

        headPhones = findViewById(R.id.headphones);
        laptops = findViewById(R.id.laptops);
        watches = findViewById(R.id.watches);
        mobilePhone = findViewById(R.id.mobiles);


        tShirts.setOnClickListener(v -> {
            Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
            intent.putExtra("category", "tShirts");
            startActivity(intent);
        });
        sportsShirts.setOnClickListener(v -> {
            Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
            intent.putExtra("category", "sportsShirts");
            startActivity(intent);
        });
        femaleDresses.setOnClickListener(v -> {
            Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
            intent.putExtra("category", "femaleDresses");
            startActivity(intent);
        });
        sweaters.setOnClickListener(v -> {
            Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
            intent.putExtra("category", "sweaters");
            startActivity(intent);
        });

        glasses.setOnClickListener(v -> {
            Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
            intent.putExtra("category", "glasses");
            startActivity(intent);
        });
        hats.setOnClickListener(v -> {
            Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
            intent.putExtra("category", "hats");
            startActivity(intent);
        });
        wallets.setOnClickListener(v -> {
            Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
            intent.putExtra("category", "wallets");
            startActivity(intent);
        });
        shoes.setOnClickListener(v -> {
            Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
            intent.putExtra("category", "shoes");
            startActivity(intent);
        });

        headPhones.setOnClickListener(v -> {
            Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
            intent.putExtra("category", "headPhones");
            startActivity(intent);
        });
        laptops.setOnClickListener(v -> {
            Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
            intent.putExtra("category", "laptops");
            startActivity(intent);
        });
        watches.setOnClickListener(v -> {
            Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
            intent.putExtra("category", "watches");
            startActivity(intent);
        });
        mobilePhone.setOnClickListener(v -> {
            Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
            intent.putExtra("category", "mobilePhone");
            startActivity(intent);
        });


    }
}