package com.group2.katching;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group2.katching.entity.User;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    private EditText signupInputEmail;
    private EditText signupInputPassword;
    private Button btnSignUp;
    private Button btnLinkToLogIn;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private double balance = 0;
    private boolean status = false;
    private String userID;

    private static final String TAG = "FIREBASE AUTHENTICATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        View toolbar_id = findViewById(R.id.toolbar_id);
        ImageView toolbar_logo = findViewById(R.id.toolbar_logo);
        ImageView arrow_back = findViewById(R.id.toolbar_backArrow);

        arrow_back.setVisibility(View.VISIBLE);
        arrow_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        // change to admin page toolbar color and icon
        toolbar_id.setBackgroundColor(ContextCompat.getColor(this, R.color.PrimaryPurple));
        toolbar_logo.setImageResource(R.drawable.logo_green_app);

        // Reference member variables
        signupInputEmail = (EditText) findViewById(R.id.txtUserEmail);
        signupInputPassword = (EditText) findViewById(R.id.password);
        btnSignUp = (Button) findViewById(R.id.btnSignup);

        // Firebase instances
        auth = FirebaseAuth.getInstance();
        // Get the reference to the database on firebase
        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("users");

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                return;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user to the other app", error.toException());
            }
        });

        // Set up the sign up button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
                Toast.makeText(SignupActivity.this, "signed up", Toast.LENGTH_SHORT).show();
                SignupActivity.super.onBackPressed();
            }
        });

    }

    private void submitForm() {
        String email = signupInputEmail.getText().toString().trim();
        String password = signupInputPassword.getText().toString().trim();

        if (!checkEmail()) {
            Toast.makeText(getApplicationContext(), "This user already exists!", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (!checkPassword()) {
            return;
        }
        else {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignupActivity.this,
                    new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(Task<AuthResult> task) {
                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                            if (task.isSuccessful()) {
                                startActivity(new Intent(SignupActivity.this, HomeActivity.class));
                                finish();
                            } else {
                                Log.d(TAG, "Authentication failed." + task.getException());
                            }
                        }
                    });

            if (!TextUtils.isEmpty(email)) {
                createUser(email, balance, status);
            }
            Toast.makeText(getApplicationContext(), "You are successfully registered!", Toast.LENGTH_SHORT).show();
        }
    }

    private void createUser(String email, double balance, boolean status) {
        if (TextUtils.isEmpty(userID)) {
            // In real apps this userId should be fetched
            // if you don't have an id, create one
            userID = mFirebaseDatabase.push().getKey();
            Log.e("Created a new USER ID", userID);
        }

        // add the user object values
        User user = new User(String.valueOf(userID), email, status, balance);
        mFirebaseDatabase.child(userID).setValue(user);
    }

    private boolean checkEmail() {
        final boolean[] status = new boolean[1];
        status[0] = true;
        String email = signupInputEmail.getText().toString().trim();

        if (email.isEmpty() || !isEmailValid(email)) {
            Toast.makeText(getApplicationContext(), "Please insert a valid email!", Toast.LENGTH_SHORT).show();
            requestFocus(signupInputEmail);
            status[0] = false;
        }

        return status[0];
    }

    private boolean checkPassword() {
        String password = signupInputPassword.getText().toString().trim();
        if (password.isEmpty() || !isPasswordValid(password)) {
            Toast.makeText(getApplicationContext(), "Invalid password!", Toast.LENGTH_SHORT).show();
            requestFocus(signupInputPassword);
            return false;
        }
        return true;
    }
    private static boolean isEmailValid(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private static boolean isPasswordValid(String password) {
        return (password.length() >= 6);
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}