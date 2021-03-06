package com.example.minimart;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.braintreepayments.cardform.view.CardForm;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.minimart.FinalCartPreview.grandTotalplus;
import static com.example.minimart.FinalCartPreview.temparraylist;

public class BookMyOrder extends AppCompatActivity {
    LinearLayout placeorder;
    CardForm cardForm;
    AlertDialog.Builder alertBuilder;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference_Card,databaseReference_Cart;
    TextView total_amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_my_order);
        firebaseAuth=FirebaseAuth.getInstance();
        total_amount = (TextView) findViewById(R.id.shipping_total_cart);
        databaseReference_Card= FirebaseDatabase.getInstance().getReference().child("Shipping and Card Details");
        total_amount.setText("$"+String.valueOf(grandTotalplus));
        databaseReference_Cart= FirebaseDatabase.getInstance().getReference().child("Cart Details");
        FirebaseUser user=firebaseAuth.getCurrentUser();
        placeorder = findViewById(R.id.buynow);
        cardForm = findViewById(R.id.card_form);
        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .setup(BookMyOrder.this);
        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER |InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cardForm.isValid())
                {
                alertBuilder = new AlertDialog.Builder(BookMyOrder.this);
                alertBuilder.setTitle("Confirm before purchase");
                alertBuilder.setMessage("Card Number: "+cardForm.getCardNumber()+ " \n"+"Card Expiry date : "+cardForm.getExpirationDateEditText().getText().toString()
                        + " \n"+ "CVV: "+cardForm.getCvv());
                alertBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        com.example.minimart.BookMyOrderModel bookMyOrder = new com.example.minimart.BookMyOrderModel ();
                        bookMyOrder.setName(OrderModel.name);
                        bookMyOrder.setAddress(OrderModel.address);
                        bookMyOrder.setPhonenumber(OrderModel.phonenumber);
                        bookMyOrder.setPincode(OrderModel.pincode);
                        bookMyOrder.setCardnumber(cardForm.getCardNumber());
                        bookMyOrder.setCardExpiration(cardForm.getExpirationMonth() + "/" + cardForm.getExpirationYear());
                        bookMyOrder.setcardCVV(cardForm.getCvv());
                        bookMyOrder.setCardpostalcode(cardForm.getPostalCode());
                        bookMyOrder.setTotalamount(grandTotalplus);
                        databaseReference_Card.child("user information").setValue(bookMyOrder);
                        databaseReference_Cart.child("cart information").setValue(temparraylist);
                        Toast.makeText(BookMyOrder.this, "Thank you for purchase", Toast.LENGTH_LONG).show();
                        addNotification();
                        startActivity(new Intent(BookMyOrder.this,OrderConfirmationPage.class));
                    }
                });
                    alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                          //  Toast.makeText(BookMyOrder.this , "Thank you for purchase",Toast.LENGTH_LONG).show();
                        }
                    });
                    AlertDialog alertDialog = alertBuilder.create();
                    alertDialog.show();
                }
                else
                {
                    Toast.makeText(BookMyOrder.this , "Please Complete the form",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.fnlogo)
                        .setContentTitle("Flying Plates")
                        .setContentText("Thanks for placing the order. Your order will be delivered shortly");
        Intent notificationIntent = new Intent(this, HomeDashBoard.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
        {
            NotificationChannel nChannel = new NotificationChannel("1", "NOTIFICATION_CHANNEL_NAME", NotificationManager.IMPORTANCE_HIGH);
            nChannel.enableLights(true);
            assert manager != null;
            builder.setChannelId("1");
            manager.createNotificationChannel(nChannel);
        }
        assert manager != null;
        manager.notify(0, builder.build());
    }
}
