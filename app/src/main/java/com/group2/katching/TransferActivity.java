package com.group2.katching;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class TransferActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        //    Toolbar toolbar = findViewById(R.id.toolbar_transfer);
        Button btnIncAmount = findViewById(R.id.increase_btn_transfer);
        Button btnDecAmount = findViewById(R.id.decrease_btn_transfer);
        ImageButton btnTransfer = findViewById(R.id.transfer_btn);
        TextView valueTransfer = findViewById(R.id.tv_value_transfer);
        final Double[] transferValue = {0.00};
//        toolbar.setTitle("Transfer");

        btnDecAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(transferValue[0] <= 0) {
                    Toast.makeText(TransferActivity.this, "Negative values not accepted", Toast.LENGTH_SHORT).show();
                } if(transferValue[0] == 0){
                    Toast.makeText(TransferActivity.this, "The value for transference should be nigger than zero", Toast.LENGTH_SHORT).show();
                }else {
                    transferValue[0] -= 0.50;
                    valueTransfer.setText(transferValue[0].toString());
                }
            }
        });

        btnIncAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transferValue[0] += 0.50;
                valueTransfer.setText(transferValue[0].toString());
            }
        });

        btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}