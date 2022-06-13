
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


public class MexicanFoods extends AppCompatActivity  {
    RecyclerView recyclerView;
    TextView textView;
    RecyclerView.LayoutManager rvlayoutManager;
    public static ArrayList<com.example.minimart.ModelFood> foodsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mexican_foods);
        recyclerView = findViewById(R.id.rv_mexican);
        Toolbar toolbar =  findViewById(R.id.total_main_bar);
        //toolbar.setTitle("Home");
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        } else {
            throw new NullPointerException("Something went wrong");
        }
        foodsList = new ArrayList<>();
        foodsList.add(new ModelFood(R.drawable.o_cocacola, "Cocacola", "Bevarage", 1.00, 11, 20));
        foodsList.add(new ModelFood(R.drawable.o_sprite, "Sprite", "Bevarage", 1.10, 12, 20));
        foodsList.add(new ModelFood(R.drawable.o_sprite, "Sprite", "Bevarage", 2.00, 13, 20));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        rvlayoutManager = new LinearLayoutManager(this);
        MexicanFoodAdapter foodAdapter = new MexicanFoodAdapter(this, foodsList);
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
