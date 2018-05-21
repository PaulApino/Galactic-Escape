package com.gscape.sdp.galacticescape.Display.TestDisplay;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gscape.sdp.galacticescape.Display.StarFieldBackground.StarFieldChunk;
import com.gscape.sdp.galacticescape.Display.StarFieldBackground.StarFieldChunkView;
import com.gscape.sdp.galacticescape.Display.StarFieldBackground.StarForge;
import com.gscape.sdp.galacticescape.Engine.Physics.Vector;
import com.gscape.sdp.galacticescape.R;

public class StarFieldTest extends AppCompatActivity {

    private ConstraintLayout contentLayout;

    private StarForge starForge;
    private StarFieldChunk chunk;
    private StarFieldChunkView chunkView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_field_test);

        contentLayout = findViewById(R.id.test_starfield_layout);

        starForge = new StarForge(2269);
//        chunk = new StarFieldChunk(Vector.make2D(0,0));
        long start = System.nanoTime();
        starForge.generateStars(chunk, 200);
        long end = System.nanoTime();
        end -= start;


    }

    private class GeneratorRunnable implements Runnable {

        private final StarForge starForge;
        private final StarFieldChunk chunk;
        private final int starCount;

        public GeneratorRunnable(StarForge starForge, StarFieldChunk chunk, int starCount) {
            this.starForge = starForge;
            this.chunk = chunk;
            this.starCount = starCount;
        }

        @Override
        public void run() {
            starForge.generateStars(chunk, starCount);
        }
    }
}
