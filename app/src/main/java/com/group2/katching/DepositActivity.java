package com.group2.katching;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DepositActivity extends AppCompatActivity {

    FirebaseDatabase mFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        mFirebase = FirebaseDatabase.getInstance();
        DatabaseReference firebase = mFirebase.getReference("users");

        Button btnIncAmount = findViewById(R.id.increase_btn_deposit);
        Button btnDecAmount = findViewById(R.id.decrease_btn_deposit);
        ImageButton btnDeposit = findViewById(R.id.deposit_btn);
        TextView valueDeposit = findViewById(R.id.tv_value_deposit);


        //Retrieve data from deposit fragment intent
        Bundle intentData = getIntent().getExtras();
        String key = intentData.getString("key");
        double[] balance = new double[]{intentData.getDouble("balance")};

        final Double[] depValue = {0.00};

        btnDecAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(depValue[0] <= 0) {
                    Toast.makeText(DepositActivity.this, "Negative values not accepted", Toast.LENGTH_SHORT).show();
                } if(depValue[0] == 0){
                    Toast.makeText(DepositActivity.this, "Value for deposit should be bigger than zero", Toast.LENGTH_SHORT).show();
                } else {
                    depValue[0] -= 0.50;
                    valueDeposit.setText(depValue[0].toString());
                }
            }
        });

        btnIncAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                depValue[0] += 0.50;
                valueDeposit.setText(depValue[0].toString());
            }
        });


        //When a user clicks the deposit button at the bottom of the activity
        btnDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //only validate if key has been properly sent in intent.
                if(key != null) {
                    Double amount = Double.valueOf(valueDeposit.getText().toString());
                    Double newBalance = (balance[0] + amount);
                    Log.v("deposit:", "deposit of " + amount + " successful!"); //log success to console
                    firebase.child(key).child("balance").setValue(newBalance);
                    //Show user successful deposit
                    Toast.makeText(DepositActivity.this, "Deposited $" + amount + " CAD"
                            ,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}