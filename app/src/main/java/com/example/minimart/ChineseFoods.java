
package com.example.minimart;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ChineseFoods extends AppCompatActivity  {
    RecyclerView recyclerView;
    TextView textView;
    RecyclerView.LayoutManager rvlayoutManager;
    public static ArrayList<com.example.minimart.ModelFood> foodsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese_foods);
        recyclerView = findViewById(R.id.rv);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.total_main_bar);
        toolbar.setTitle("Home");
        //setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        } else {
//            throw new NullPointerException("Something went wrong");
        }

        foodsList = new ArrayList<>();
        foodsList.add(new ModelFood(R.drawable.o_himalaya, "Himalaya", "Beauty", 9.99, 17, 20));
        foodsList.add(new ModelFood(R.drawable.o_enchanteur, "Enchanture", "Beauty", 7.99, 18, 20));
        foodsList.add(new ModelFood(R.drawable.o_neutrogina, "Neutrogina Cream", "Beauty", 6.99, 19, 20));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        rvlayoutManager = new LinearLayoutManager(this);
        com.example.minimart.ChineseFoodAdapter foodAdapter = new com.example.minimart.ChineseFoodAdapter (this, foodsList);
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
