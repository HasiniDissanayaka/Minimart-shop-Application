
package com.example.minimart;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
        //import static com.example.minimart.IndianCartActivity.temparraylist;

class fIndianFoods extends AppCompatActivity  {
    RecyclerView recyclerView;
    Button check_indian;
    TextView textView;
    private Toolbar mToolbar;
    RecyclerView.LayoutManager rvlayoutManager;
    public static ArrayList<com.example.minimart.ModelFood> foodsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indian_foods);
        recyclerView = findViewById(R.id.rv_indian);
        //check_indian = (Button) findViewById(R.id.checkout_indian);
        Toolbar toolbar = findViewById(R.id.total_main_bar);
        //toolbar.setTitle("Home");
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        //mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        } else {
            throw new NullPointerException("Something went wrong");
        }
        foodsList = new ArrayList<>();
        foodsList.add(new ModelFood(R.drawable.o_panadol, "Panadol", "Medicine", 0.20, 6, 20));
        foodsList.add(new ModelFood(R.drawable.o_vitaminc, "Vitamin C", "Medicine", 9.99, 7, 20));
        foodsList.add(new ModelFood(R.drawable.o_inhaler, "Inhaler", "Medicine", 0.50, 8, 20));
        foodsList.add(new ModelFood(R.drawable.o_panadol, "Panadol", "Medicine", 0.20, 9, 20));
        foodsList.add(new ModelFood(R.drawable.o_vitaminc, "Vitamin C", "Medicine", 9.99, 10, 20));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        rvlayoutManager = new LinearLayoutManager(this);
        com.example.minimart.IndianFoodAdapter foodAdapter = new com.example.minimart.IndianFoodAdapter (this, foodsList);
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
