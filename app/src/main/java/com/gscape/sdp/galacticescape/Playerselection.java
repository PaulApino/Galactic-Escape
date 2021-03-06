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


public class Playerselection extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRootReference = firebaseDatabase.getReference();
    private ArrayList<Player> Useradd = new ArrayList<>();
    Button create_userBT;
    EditText username;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playerselection);
        username =  findViewById(R.id.user_edit);
        create_userBT = findViewById(R.id.save_user);
        

        mRootReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    Player player = child.getValue(Player.class);
                   Useradd.add(player);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        create_userBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!username.getText().toString().trim().isEmpty()){
                    Player u = new Player();
                    u.setUsername(username.getText().toString());
                    u.setAchievements("0");
                    u.setCurrentPos("0");
                    u.setLives("1");
                    u.setScore("0");
                    mRootReference.child(u.getUsername()).setValue(u);
                    Toast.makeText(Playerselection.this, "player Name created successfully!",
                            Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                            Intent i=new Intent(Playerselection.this, GamePlayScreen.class);
                            startActivity(i);
                        }
                    }, 9000);
                    Toast.makeText(Playerselection.this, "Taking off in Three...\n\n\n",
                            Toast.LENGTH_SHORT).show();
                    Toast.makeText(Playerselection.this, "Two...\n\n\n",
                            Toast.LENGTH_SHORT).show();
                    Toast.makeText(Playerselection.this, "One...\n\n\n",
                            Toast.LENGTH_SHORT).show();
                    Toast.makeText(Playerselection.this, "BLAST OFF!!!!\n\n\n",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Playerselection.this, "The field Cannot be empty, please enter a new username",
                            Toast.LENGTH_LONG).show();
                }

            }

        });





    }
}
