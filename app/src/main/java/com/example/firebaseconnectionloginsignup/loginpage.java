package com.example.firebaseconnectionloginsignup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginpage extends AppCompatActivity {
    EditText gmail,pass;

    Button login;

    TextView signup;

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loginpage);

        gmail=findViewById(R.id.gmail);
        pass=findViewById(R.id.pass);
        login=findViewById(R.id.login);
        signup=findViewById(R.id.signup);

        fAuth=FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pass.getText().toString().length()<6){
                    Toast.makeText(loginpage.this, "Password Must Be 6 Character", Toast.LENGTH_SHORT).show();
                }
                else {
                    fAuth.signInWithEmailAndPassword(gmail.getText().toString(),pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Intent d=new Intent(loginpage.this, dashboard.class);
                                startActivity(d);
                                Toast.makeText(loginpage.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(loginpage.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s=new Intent(loginpage.this,MainActivity.class);
                startActivity(s);
            }
        });

    }
}