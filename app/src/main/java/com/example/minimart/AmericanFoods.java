package com.example.minimart;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class AmericanFoods extends AppCompatActivity  {
    RecyclerView recyclerView;
    TextView textView;
    RecyclerView.LayoutManager rvlayoutManager;
    public static ArrayList<com.example.minimart.ModelFood> foodsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_american_foods);
        recyclerView = findViewById(R.id.rv);
        Toolbar toolbar = findViewById(R.id.total_main_bar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        } else {
            throw new NullPointerException("Something went wrong");
        }
        foodsList = new ArrayList<>();
        foodsList.add(new ModelFood(R.drawable.o_araliya, "Araliya 5kg", "RICE", 2.00, 1, 20));
        foodsList.add(new ModelFood(R.drawable.o_nipuna_kiri, "Nipuna Kiri 5kg", "RICE", 1.99, 2, 20));
        foodsList.add(new ModelFood(R.drawable.o_nipuna, "Nipuna 5kg", "RICE", 1.89, 3, 20));
        foodsList.add(new ModelFood(R.drawable.o_araliya, "Araliya 10kg", "RICE", 4.00, 4, 20));
        foodsList.add(new ModelFood(R.drawable.o_nipuna_kiri, "Nipuna 10kg", "RICE", 3.88, 5, 20));
        recyclerView.setHasFixedSize(true);
        rvlayoutManager = new LinearLayoutManager(this);
        com.example.minimart.AmericanFoodAdapter foodAdapter = new com.example.minimart.AmericanFoodAdapter (this, foodsList);
        recyclerView.setAdapter(foodAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), HomeDashBoard.class);
        startActivityForResult(myIntent, 0);
        return true;

    }
}
