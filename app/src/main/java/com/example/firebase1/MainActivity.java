package com.example.firebase1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText etEmail, etPassword;
    Button btnRegister, btnLogin;

    String email, password;
    private ProgressBar loginProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.email_id);
        etPassword = findViewById(R.id.password_id);
        btnRegister = findViewById(R.id.registerbtn_id);
        btnLogin = findViewById(R.id.btnLogin_id);
//        loginProgress = findViewById(R.id.progressBar);

          btnRegister.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                   register();
              }
          });

          btnLogin.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {


                  login();
//                  loginProgress.setVisibility(View.VISIBLE);
              }
          });

    }

    private void register(){
        
        validation();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "createUserWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
                            Toast.makeText(getApplicationContext(), "User Registration succesfully", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                            Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);

                            Toast.makeText(getApplicationContext(), "User Registration failed", Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }


    private void login(){

        validation();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                           Intent intent = new Intent(getApplicationContext(),Login.class);
                           startActivity(intent);
                        } else {


                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();


                        }

                        // ...
                    }
                });
    }

    private void validation() {

        email = etEmail.getText().toString();
        password = etPassword.getText().toString();

        //validation: check for presence
        if (TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(),"Please enter email address",Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(),"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }
    }





//    addd our button icons to main activity


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu );
        return  true;
    }

//    wht happend when items is selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.logout_id:
                logout();
                return true;
                default:
                    return false;
        }

    }

    private void logout() {
    }
}
