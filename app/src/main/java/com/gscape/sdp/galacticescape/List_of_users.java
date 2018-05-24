package com.gscape.sdp.galacticescape;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.gscape.sdp.galacticescape.Display.ActualGame.GamePlayScreen;

import java.util.ArrayList;

public class List_of_users extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRootReference = firebaseDatabase.getReference();
    ListView currentUsers;
    private  ArrayList<String> users = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_users);

        currentUsers = (ListView) findViewById(R.id.list_of_usersListView);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(List_of_users.this, android.R.layout.simple_list_item_1, users);
        currentUsers.setAdapter(arrayAdapter);


        mRootReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    User u = child.getValue(User.class);
                    users.add(child.getKey());
                    currentUsers.setAdapter(arrayAdapter);

                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });







        // Create an ArrayAdapter from List
        final ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>
                (List_of_users.this, android.R.layout.simple_list_item_1, users){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                /// Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                // Set the text size 25 dip for ListView each item
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,10);

                // Return the view
                return view;
            }
        };



        currentUsers.setAdapter(arrayAdapter1);


        currentUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                currentUsers.getSelectedItem();
                String selItem = adapterView.getItemAtPosition(i).toString();
                String[] selectedUser = selItem.split(", ");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent i=new Intent(List_of_users.this, GamePlayScreen.class);
                        startActivity(i);
                    }
                }, 5000); Toast.makeText(List_of_users.this, "Resuming "+  selectedUser[0] + " Game Play please wait..",
                        Toast.LENGTH_LONG).show();
            }
        });

    }



}
