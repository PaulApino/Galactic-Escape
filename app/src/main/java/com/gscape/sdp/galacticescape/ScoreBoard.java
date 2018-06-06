package com.gscape.sdp.galacticescape;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
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
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class ScoreBoard extends AppCompatActivity {


    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRootReference = firebaseDatabase.getReference();
    ListView currentUsersScore;
    private ArrayList<String> usersScores = new ArrayList<>();

    private EditText etSearch;
    private Button backtoMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);


        currentUsersScore = (ListView) findViewById(R.id.scoreslistview);
        backtoMainMenu = findViewById(R.id.mainmenubtsearch);
        etSearch = findViewById(R.id.editText_search);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(ScoreBoard.this, android.R.layout.simple_list_item_1, usersScores);
        currentUsersScore.setAdapter(arrayAdapter);
        currentUsersScore.setTextFilterEnabled(true);



        mRootReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    User u = child.getValue(User.class);
                    usersScores.add(u.getScore() + "                      " + u.getUsername());
                    currentUsersScore.setAdapter(arrayAdapter);
                }
                sorting(usersScores);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        // Create an ArrayAdapter from List
        final ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>
                (ScoreBoard.this, android.R.layout.simple_list_item_1, usersScores) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                /// Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                // Set the text size 25 dip for ListView each item
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);

                // Return the view
                return view;
            }
        };


        currentUsersScore.setAdapter(arrayAdapter1);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                arrayAdapter.getFilter().filter(editable);

            }
        });

        backtoMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent scores = new Intent(ScoreBoard.this,
                        MainMenu.class);
                startActivity(scores);
            }
        });


        currentUsersScore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                currentUsersScore.getSelectedItem();
                String selItem = adapterView.getItemAtPosition(i).toString();
                String[] selectedUser = selItem.split(", ");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 5000);
            }
        });

    }


    public void sorting(ArrayList array){
        Collections.sort(array, Collections.<String>reverseOrder());
    };





}
