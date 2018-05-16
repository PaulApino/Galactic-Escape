package com.gscape.sdp.galacticescape.Display.TestDisplay;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.gscape.sdp.galacticescape.Display.StarFieldBackground.BackgroundStar;
import com.gscape.sdp.galacticescape.Display.StarFieldBackground.StarFieldChunk;
import com.gscape.sdp.galacticescape.Display.StarFieldBackground.StarFieldChunkView;
import com.gscape.sdp.galacticescape.Display.StarFieldBackground.StarForge;
import com.gscape.sdp.galacticescape.Engine.Physics.Vector;
import com.gscape.sdp.galacticescape.R;

import java.util.ArrayList;

public class StarFieldTest extends AppCompatActivity {

    private ConstraintLayout contentLayout;

    private StarForge starForge;
    private StarFieldChunk chunk;
    private StarFieldChunkView chunkView;
    private ArrayList<BackgroundStar> stars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_field_test);

        contentLayout = findViewById(R.id.test_starfield_layout);

        starForge = new StarForge(5913);
        chunk = new StarFieldChunk(Vector.make2D(0,0));
        starForge.generateChunk(chunk, 50);
        chunkView = chunk.getChunkView(getApplicationContext());

        contentLayout.addView(chunkView);
    }
}
