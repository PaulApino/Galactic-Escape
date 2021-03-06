package com.gscape.sdp.galacticescape;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    private ConstraintLayout menuContainer;
    private Button start_new_game;
    private Button quit_game;
    private Button con;
    private Button controls;
    private Button scorebutton;



    //my method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_menu);

        start_new_game = findViewById(R.id.new_gameID);

        menuContainer = findViewById(R.id.main_menu_container);
        controls = findViewById(R.id.controlsBT);

        scorebutton = findViewById(R.id.scoresBt);

        con = findViewById(R.id.continueID);

        start_new_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**
                 *intent is currently starting fullscreenactivity class temporary,
                 as introduction to game is not yet built.
                 */

                Intent intro_to_Game = new Intent(MainMenu.this,
                        Introduction.class);
                /**
                 * intro_to_Game.putExtra("",""); <-- if you need to carry any
                 data into the next intent class.
                 */
                startActivity(intro_to_Game); //starts the new activity

            }
        });


        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intro_to_Game = new Intent(MainMenu.this,
                        CurrentPlayer.class);
                startActivity(intro_to_Game);
            }
        });

        controls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent controls = new Intent(MainMenu.this,
                        ControlPopup.class);
                startActivity(controls);
            }
        });


        scorebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent scores = new Intent(MainMenu.this,
                        ScoreBoard.class);
                startActivity(scores);
            }
        });


        quit_game = findViewById(R.id.quitID);
        quit_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }


            //quiting game
        });
    }
}
