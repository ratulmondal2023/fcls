package com.example.firebaseconnectionloginsignup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.firebaseconnectionloginsignup.model.users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText name,gmail,pass;

    Button submit;
    FirebaseAuth fAuth;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

       name=findViewById(R.id.name);
        gmail=findViewById(R.id.gmail);
        pass=findViewById(R.id.pass);
        submit=findViewById(R.id.submit);

        fAuth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("users");

          submit.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if(pass.getText().toString().length()<6){
                      Toast.makeText(MainActivity.this, "Password Must Be 6 Character", Toast.LENGTH_SHORT).show();
                  }
                  else {
                      fAuth.createUserWithEmailAndPassword(gmail.getText().toString(),pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                          @Override
                          public void onComplete(@NonNull Task<AuthResult> task) {
                              if (task.isSuccessful()){
                                  databaseReference.child(databaseReference.push().getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
                                      @Override
                                      public void onDataChange(@NonNull DataSnapshot snapshot) {
                                          snapshot.getRef().setValue(new users(name.getText().toString(),gmail.getText().toString()));
                                          Intent d=new Intent(MainActivity.this, dashboard.class);
                                          startActivity(d);
                                          Toast.makeText(MainActivity.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                                      }

                                      @Override
                                      public void onCancelled(@NonNull DatabaseError error) {
                                          Toast.makeText(MainActivity.this, "Signup Failed", Toast.LENGTH_SHORT).show();

                                      }
                                  });
                              }
                              else {
                                  Toast.makeText(MainActivity.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                              }
                          }
                      });
                  }
              }
          });



    }
}