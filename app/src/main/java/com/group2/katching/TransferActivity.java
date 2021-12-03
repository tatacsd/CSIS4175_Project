package com.group2.katching;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group2.katching.entity.User;
import com.group2.katching.ui.UserViewModel;

import java.util.Locale;

public class TransferActivity extends AppCompatActivity {

    FirebaseDatabase mFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        mFirebase = FirebaseDatabase.getInstance();
        DatabaseReference firebase = mFirebase.getReference("users");
        final User[] receivingUser = new User[1];

        Button btnIncAmount = findViewById(R.id.increase_btn_transfer);
        Button btnDecAmount = findViewById(R.id.decrease_btn_transfer);
        ImageButton btnTransfer = findViewById(R.id.transfer_btn);
        TextView valueTransfer = findViewById(R.id.tv_value_transfer);
        EditText receivingUserEmail = findViewById(R.id.et_userTransfer);

        //Retrieve data from transfer fragment intent
        Bundle intentData = getIntent().getExtras();
        String userSendingKey = intentData.getString("key");
        double[] userSendingBalance = new double[]{intentData.getDouble("balance")};

        final Double[] transferValue = {0.00};

        btnDecAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (transferValue[0] <= 0) {
                    Toast.makeText(TransferActivity.this, "Negative values not accepted", Toast.LENGTH_SHORT).show();
                }
                if (transferValue[0] == 0) {
                    Toast.makeText(TransferActivity.this, "The value for transference should be bigger than zero", Toast.LENGTH_SHORT).show();
                } else {
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
                //only validate if key has been properly sent in intent.

                // Checking user receiving email with database children
                mFirebase = FirebaseDatabase.getInstance();
                DatabaseReference data = mFirebase.getReference("users");
                data.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Log.v("check", "changecheck");
                        boolean matchFound = false;
                        for (DataSnapshot child : snapshot.getChildren()) {
                            String dataBaseId = String.valueOf(child.getKey());
                            String email = String.valueOf(child.child("email").getValue()).toLowerCase();
                            Log.v("email", email);
                            String balance = String.valueOf(child.child("balance").getValue()).toLowerCase();
                            if (receivingUserEmail.getText().toString().toLowerCase().equals(email)) {
                                matchFound = true;
                                receivingUser[0] = new User(dataBaseId, email, false, Double.valueOf(balance));
                            }
                        }
                        if (matchFound) {
                            Log.v("found", "match for " + receivingUser[0].getEmail() + "(id: " + receivingUser[0].getDataBaseId() + ")" + " found.");
                            if (userSendingKey != null && receivingUser[0].getDataBaseId() != null) {
                                // Get user sender amount
                                Double amount = Double.valueOf(valueTransfer.getText().toString().replace("$ ", ""));
                                Double userSendingBalanceValue = userSendingBalance[0];

                                boolean ableToTransfer = userSendingBalance[0] >= 0 &&  userSendingBalance[0] >= amount;
                                if (ableToTransfer) {
                                    // Start the transferring process
                                    Double newReceivingUserBalance = (receivingUser[0].getBalance() + amount);
                                    Double newSendingUserBalance = userSendingBalance[0] - amount;
                                    firebase.child(userSendingKey).child("balance").setValue(newSendingUserBalance);
                                    firebase.child(receivingUser[0].getDataBaseId()).child("balance").setValue(newReceivingUserBalance);
                                    //Show user successful deposit
                                    Toast.makeText(TransferActivity.this, "transfer of " + amount + "to" + receivingUser[0].getEmail()+ " successful!"
                                            , Toast.LENGTH_SHORT).show();

                                    Log.v("Transfer:", "transfer of " + amount + "to" + receivingUser[0].getEmail()+ " successful!"); //log success to console
                                    return;
                                } else {
                                    Log.v("Transfer:", "insuficient amount to transfer");
                                }
                            } else
                                Log.v("not found", "no match found");
                        }


                    };
                    @Override
                    public void onCancelled (@NonNull DatabaseError error){
                    }
                });
            };
        });
    }
}