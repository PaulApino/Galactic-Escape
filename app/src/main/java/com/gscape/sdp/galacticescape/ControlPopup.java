package com.gscape.sdp.galacticescape;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class ControlPopup extends AppCompatActivity {


    private Button mainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_popup);

        mainMenu = findViewById(R.id.returnMainmenu);


        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent skipped = new Intent(ControlPopup.this, MainMenu.class );
                startActivity(skipped);
            }
        });
    }


}
