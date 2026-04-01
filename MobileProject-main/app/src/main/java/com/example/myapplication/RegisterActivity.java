package com.example.myapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore fAuth;
    private EditText email, password,repassword,PhoneNumber;
    private Button btnRegister;
    private String userID;
    private CheckBox showPass;
    private TextView textLogin,fName,lName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        fAuth = FirebaseFirestore.getInstance();
        fName = findViewById(R.id.fName);
        lName = findViewById(R.id.lName);
        showPass = findViewById(R.id.CheckPass);
        email = findViewById(R.id.register_email);
        PhoneNumber = findViewById(R.id.PhoneNumber);
        password = findViewById(R.id.register_password);
        repassword = findViewById(R.id.register_password2);
        btnRegister  = findViewById(R.id.sign_up);
        textLogin = findViewById(R.id.existing_account);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
        showPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    repassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    repassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }
            }
        });
        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finishAffinity();
            }
        });

    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterActivity.this);
        alertDialog.setTitle("Close App");
        alertDialog.setMessage("Do you want to exit?");
        alertDialog.setCancelable(true);
        alertDialog.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Home.this.finish();
                        finishAffinity();
                    }
                });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }
    private void Register() {
        String FirstName = fName.getText().toString().trim();
        String LastName = lName.getText().toString().trim();
        String Email = email.getText().toString().trim();
        String phoneNumber = PhoneNumber.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String repass = repassword.getText().toString().trim();
        if(Email.isEmpty())
        {
            email.setError("Email can not be empty");
        }
        if(pass.isEmpty())
        {
            password.setError("Password can not be empty");
        }
        if(FirstName.isEmpty())
        {
            fName.setError("First name can not be empty");
        }
        if(LastName.isEmpty())
        {
            lName.setError("Last Name can not be empty");
        }
        if(phoneNumber.isEmpty())
        {
            PhoneNumber.setError("Phone Number can not be empty");
        }
        if(repass.isEmpty())
        {
            repassword.setError("Re password can not be empty");
        }
        if(!repass.equals(pass)){
            repassword.setError("Password not matched");
            password.setError("Password not matched");
        }
        else
        {
            mAuth.createUserWithEmailAndPassword(Email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                userID = mAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = fAuth.collection("users").document(userID);
                                Map<String,Object> user = new HashMap<>();
                                user.put("FirstName",FirstName);
                                user.put("LastName",LastName);
                                user.put("Email",Email);
                                user.put("Phone",phoneNumber);
                                user.put("Password",pass);
                                documentReference.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Log.d("App", "Profile Created" + userID);
                                    }
                                });
                                FirebaseUser User = mAuth.getCurrentUser();
                                updateUI(User);
                                Toast.makeText(RegisterActivity.this, "Please open your email and verify it", Toast.LENGTH_SHORT).show();
                                fName.setText("");
                                lName.setText("");
                                email.setText("");
                                PhoneNumber.setText("");
                                password.setText("");
                                repassword.setText("");
                            }
                        });
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }

                }
            });

        }
    }
    public void updateUI(FirebaseUser account) {
        if (account != null) {
            Log.d("Android", "Register is done = [" + account + "]");
        } else {
            Log.d("Android", "Register failed = [" + null + "]");

        }
    }
}