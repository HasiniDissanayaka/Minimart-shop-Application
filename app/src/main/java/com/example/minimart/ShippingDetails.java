package com.example.minimart;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.minimart.FinalCartPreview.grandTotalplus;

public class ShippingDetails extends AppCompatActivity {
    EditText editText,name,address,phonenumber,zipcode;
    TextView amount,promotion,total_amount,couponstatus,shipping_amount;
    LinearLayout placeorder;
    TableLayout tableLayout;
    double promocode;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_details);
        editText = findViewById(R.id.coupon);
        name = findViewById(R.id.TextName);
        address =  findViewById(R.id.textAddress);
        phonenumber = findViewById(R.id.textPhoneNumber);
        zipcode = findViewById(R.id.textZipCode);
        amount = findViewById(R.id.TotalAmount);
        promotion =  findViewById(R.id.TotalPromotionalAmount);
        couponstatus =  findViewById(R.id.coupon_status);
        shipping_amount = findViewById(R.id.shipping_total_cart);
        couponstatus.setVisibility(View.GONE);
        promotion.setVisibility(View.GONE);
        placeorder = findViewById(R.id.proceed_to_checkout);
        total_amount = findViewById(R.id.TotalFinalAmount);
        button = findViewById(R.id.Enter);
        total_amount.setText("$"+grandTotalplus);
        amount.setText("$"+grandTotalplus);
        shipping_amount.setText("$"+grandTotalplus);
/*

        Toolbar toolbar = (Toolbar) findViewById(R.id.total_main_bar_shipping);
        toolbar.setTitle("Cart");
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        } else {
            throw new NullPointerException("Something went wrong");
        }
*/

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String couponcode= editText.getText().toString().trim();
                if(TextUtils.isEmpty(couponcode))
                {
                    Toast.makeText(getApplicationContext(),"Please enter a coupon code",Toast.LENGTH_SHORT).show();
                    couponstatus.setVisibility(View.GONE);
                    promotion.setVisibility(View.GONE);
                    shipping_amount.setText("$"+grandTotalplus);
                    return;
                }

                if(couponcode.equalsIgnoreCase("FIRSTTIME")&&grandTotalplus>5)
                {
                    Toast.makeText(getApplicationContext(),"Promotional Code Successfully",Toast.LENGTH_SHORT).show();
                    editText.setText(null);
                    promocode=grandTotalplus-5;
                    promotion.setText("-$"+5);
                    couponstatus.setVisibility(View.VISIBLE);
                    promotion.setVisibility(View.VISIBLE);
                    total_amount.setText("$"+promocode);
                    shipping_amount.setText("$"+promocode);
                }
                else if(!couponcode.equalsIgnoreCase("FIRSTTIME"))
                {
                    Toast.makeText(getApplicationContext(),"Invalid Promotional Code",Toast.LENGTH_SHORT).show();
                    editText.setText(null);
                    promotion.setText("");
                    couponstatus.setVisibility(View.GONE);
                    promotion.setVisibility(View.GONE);
                    total_amount.setText("$"+grandTotalplus);
                    shipping_amount.setText("$"+grandTotalplus);
                }
                else if(grandTotalplus<=5)
                {
                    Toast.makeText(getApplicationContext(),"Amount must be greater than $5",Toast.LENGTH_SHORT).show();
                    editText.setText(null);
                    promotion.setText("");
                    couponstatus.setVisibility(View.GONE);
                    promotion.setVisibility(View.GONE);
                    total_amount.setText("$"+grandTotalplus);
                    shipping_amount.setText("$"+grandTotalplus);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid Promotional Code",Toast.LENGTH_SHORT).show();
                    editText.setText(null);
                    promotion.setText("");
                    total_amount.setText("$"+grandTotalplus);
                    shipping_amount.setText("$"+grandTotalplus);
                    couponstatus.setVisibility(View.GONE);
                    promotion.setVisibility(View.GONE);
                }
            }
        });
        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String shipping_name= name.getText().toString().trim();
                if(TextUtils.isEmpty(shipping_name))
                {
                    Toast.makeText(getApplicationContext(),"Please enter your name",Toast.LENGTH_SHORT).show();
                    return;
                }
                String shipping_address= address.getText().toString().trim();
                if(TextUtils.isEmpty(shipping_address))
                {
                    Toast.makeText(getApplicationContext(),"Please enter your address",Toast.LENGTH_SHORT).show();
                    return;
                }
                String shipping_phonenumber= phonenumber.getText().toString().trim();
                if(shipping_phonenumber.length()>10)
                {
                    Toast.makeText(getApplicationContext(),"Please enter a valid mobile number",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(shipping_phonenumber))
                {
                    Toast.makeText(getApplicationContext(),"Please enter your mobile number",Toast.LENGTH_SHORT).show();
                    return;
                }
                String shipping_zipcode = zipcode.getText().toString().trim();
                if(TextUtils.isEmpty(shipping_zipcode)) {
                    Toast.makeText(getApplicationContext(), "Please enter your zipcode", Toast.LENGTH_SHORT).show();
                    return;
                }
                OrderModel orderModel = new OrderModel(shipping_name,shipping_address,shipping_phonenumber,shipping_zipcode);
                orderModel.setName(shipping_name);
                orderModel.setAddress(shipping_address);
                orderModel.setPhonenumber(shipping_phonenumber);
                orderModel.setPincode(shipping_zipcode);
               /* BookMyOrderModel.name = shipping_name;
                BookMyOrderModel.address = shipping_address;
                BookMyOrderModel.phonenumber = shipping_phonenumber;
                BookMyOrderModel.pincode = shipping_zipcode;*/
                startActivity(new Intent(getApplicationContext(),BookMyOrder.class));
            }
        });

    }
  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), FinalCartPreview.class);
        startActivityForResult(myIntent, 0);
        return true;

    }*/
}
