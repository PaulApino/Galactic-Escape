package com.gscape.sdp.galacticescape;

import android.content.Intent;
import android.sax.StartElementListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.gscape.sdp.galacticescape.Display.ActualGame.GamePlayScreen;

public class Introduction_togame extends AppCompatActivity {

    Button skip_intro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_introduction_togame);

        skip_intro = findViewById(R.id.skip_intro_bt);

        skip_intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent skipped = new Intent(Introduction_togame.this, User_name_selection.class );
                startActivity(skipped);
            }
        });

    }



}
