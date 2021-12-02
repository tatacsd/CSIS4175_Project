package com.group2.katching;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class DepositActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        Button btnIncAmount = findViewById(R.id.increase_btn_deposit);
        Button btnDecAmount = findViewById(R.id.decrease_btn_deposit);
        ImageButton btnDeposit = findViewById(R.id.deposit_btn);
        TextView valueDeposit = findViewById(R.id.tv_value_deposit);

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

        btnDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}