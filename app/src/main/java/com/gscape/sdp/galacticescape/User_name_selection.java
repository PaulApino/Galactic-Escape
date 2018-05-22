package com.gscape.sdp.galacticescape;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.gscape.sdp.galacticescape.Display.ActualGame.GamePlayScreen;

import java.util.ArrayList;
import java.util.List;


public class User_name_selection extends AppCompatActivity {
    
    Button create_userBT;
    EditText username;


    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRootReference = firebaseDatabase.getReference();

    private ArrayList<User> Useradd = new ArrayList<>();
    
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_name_selection);

        username =  findViewById(R.id.user_edit);
        create_userBT = findViewById(R.id.save_user);
        

        mRootReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    User user = child.getValue(User.class);
                    Useradd.add(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        create_userBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User u = new User();
                u.setUsername(username.getText().toString());
                u.setAchievements("0");
                u.setCurrentPos("0");
                u.setLives("1");
                u.setScore("0");

                mRootReference.child(u.getUsername()).setValue(u);

                Toast.makeText(User_name_selection.this, "User Name created successfully!",
                        Toast.LENGTH_SHORT).show();




                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent i=new Intent(User_name_selection.this, GamePlayScreen.class);
                        startActivity(i);
                    }
                }, 5000); Toast.makeText(User_name_selection.this, "Loading game... please wait",
                        Toast.LENGTH_LONG).show();
            }

        });





    }
}
