package com.group2.katching;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class DepositActivity extends AppCompatActivity {

    Button btnIncAmount = findViewById(R.id.increase_btn_deposit);
    Button btnDecAmount = findViewById(R.id.decrease_btn_deposit);
    ImageButton btnDeposit = findViewById(R.id.deposit_btn);
    TextView valueDeposit = findViewById(R.id.tv_value_deposit);
    Double depValue = 0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        btnDecAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(depValue <= 0) {
                    Toast.makeText(DepositActivity.this, "Negative values not accepted", Toast.LENGTH_SHORT).show();
                } if(depValue == 0){
                    Toast.makeText(DepositActivity.this, "Value for deposit should be bigger than zero", Toast.LENGTH_SHORT).show();
                } else {
                    depValue -= 0.50;
                    valueDeposit.setText(depValue.toString());
                }
            }
        });

        btnIncAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                depValue += 0.50;
                valueDeposit.setText(depValue.toString());
            }
        });

        btnDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}