package com.gscape.sdp.galacticescape;

import android.content.Intent;
import android.sax.StartElementListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gscape.sdp.galacticescape.Display.ActualGame.GamePlayScreen;

public class Introduction_togame extends AppCompatActivity {

    Button skip_intro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction_togame);
        skip_intro = findViewById(R.id.skip_intro_bt);

        skip_intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent skipped = new Intent(Introduction_togame.this, GamePlayScreen.class );
                startActivity(skipped);
            }
        });

    }



}
