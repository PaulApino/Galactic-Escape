package com.gscape.sdp.galacticescape;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gscape.sdp.galacticescape.Display.ActualGame.GamePlayScreen;

public class Submenu extends AppCompatActivity {

    Button resumeGame;
    Button mainMenu;
    Button save_and_quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submenu);

        resumeGame = findViewById(R.id.resumeButton);
        mainMenu = findViewById(R.id.mainMenuButton);
        save_and_quit = findViewById(R.id.SaveButton);


        resumeGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resumingGame = new Intent(Submenu.this, GamePlayScreen.class);
                startActivity(resumingGame);
            }
        });

        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(Submenu.this, MainMenu.class);
                startActivity(main);
            }
        });


        save_and_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);


            }
        });

    }
}
