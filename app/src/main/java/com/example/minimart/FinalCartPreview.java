package com.example.minimart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FinalCartPreview extends AppCompatActivity implements  com.example.minimart.FinalCartAdapter.HomeCallBack, com.example.minimart.FinalCartAdapter.CallBackUs{
    RecyclerView recyclerView;
    Button check;
    TextView textView;
    RecyclerView cartRecyclerView;
    LinearLayout proceedToBook;
    Context context;
    public static int cart_count = 0;
    public static TextView grandTotal;
    public static double grandTotalplus;
    public static ArrayList<ModelStoreFood> temparraylist;
    public static ArrayList<ModelStoreFood> finalarray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_preview);
        recyclerView = findViewById(R.id.recycler_view_cart);
        context = this;
        temparraylist = new ArrayList<>();
        proceedToBook = findViewById(R.id.proceed_to_book);
        grandTotal = findViewById(R.id.grand_total_cart);
/*
        androidx.appcompat.widget.Toolbar toolbar =  findViewById(R.id.total_main_bar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        } else {
            throw new NullPointerException("Something went wrong");
        }
*/
        FinalCartPreview.cart_count = 0;


        temparraylist.addAll(finalarray);
        finalarray.clear();
        Log.d("sizecart_11", String.valueOf(temparraylist.size()));
        Log.d("sizecart_22", String.valueOf(finalarray.size()));
        for (int i = 0; i < finalarray.size(); i++) {
            for (int j = i + 1; j < finalarray.size(); j++) {
                if (finalarray.get(i).getProductId() == finalarray.get(j).getProductId()) {
                    finalarray.get(i).setQuantity(finalarray.get(j).getQuantity());
                    finalarray.get(i).setPrice(finalarray.get(j).getPrice());
                    finalarray.remove(j);
                    j--;

                }
            }
            cart_count = finalarray.size();
        }
        // this code is for get total cash
        for (int i = 0; i < temparraylist.size(); i++) {
            grandTotalplus = (grandTotalplus + temparraylist.get(i).getPrice() * temparraylist.get(i).getQuantity());
        }
        grandTotalplus = Double.parseDouble(String.format("%.2f", grandTotalplus));

        Log.d("total", String.valueOf(grandTotalplus));
        grandTotal.setText("$ " + String.format("%.2f", grandTotalplus));
        cartRecyclerView = findViewById(R.id.recycler_view_cart);
        proceedToBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(FinalCartPreview.this, "order placed", Toast.LENGTH_SHORT).show();
                if (grandTotalplus > 0) {
                    startActivity(new Intent(getApplicationContext(), com.example.minimart.ShippingDetails.class));
                } else {
                    Toast.makeText(FinalCartPreview.this, "Please add items to cart", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        com.example.minimart.FinalCartAdapter cartAdapter = new com.example.minimart.FinalCartAdapter (temparraylist, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        cartRecyclerView.setLayoutManager(mLayoutManager);
        cartRecyclerView.setAdapter(cartAdapter);
    }
    @Override
    public void updateCartCount(Context context) {
        invalidateOptionsMenu();
    }


    @Override
    public void addCartItemView() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        grandTotalplus = 0;
        for (int i = 0; i < temparraylist.size(); i++) {
            FinalCartPreview.cart_count = (temparraylist.size());
        }
        finalarray.addAll(temparraylist);
    }


}

