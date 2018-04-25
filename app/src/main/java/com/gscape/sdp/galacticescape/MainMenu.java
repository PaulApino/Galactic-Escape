package com.gscape.sdp.galacticescape;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    Button start_new_game;
    Button quit_game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        start_new_game = findViewById(R.id.new_gameID);

        start_new_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**
                 *intent is currently starting fullscreenactivity class temporary,
                 as introduction to game is not yet built.
                 */

                Intent intro_to_Game = new Intent(MainMenu.this,
                        FullscreenActivity.class);
                /**
                 * intro_to_Game.putExtra("",""); <-- if you need to carry any
                 data into the next intent class.
                 */
                startActivity(intro_to_Game); //starts the new activity

            }
        });

        quit_game = findViewById(R.id.quitID);
        quit_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });


    }
}
