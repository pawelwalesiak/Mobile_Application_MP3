package com.example.shopinglist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ProductListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);


    }
    public void openProductListActivity(View view)
    {
        Intent intent = new Intent(this, AddProductActivity.class);
        startActivity(intent);
    }
}