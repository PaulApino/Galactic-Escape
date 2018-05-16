package com.gscape.sdp.galacticescape.Display.TestDisplay;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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
        chunk = new StarFieldChunk(Vector.make2D(0,0));
        long start = System.nanoTime();
        starForge.generateChunk(chunk, 200);
        long end = System.nanoTime();
        end -= start;

        Log.i("Generation Time", end + "");

        chunkView = chunk.getChunkView(getApplicationContext());

        contentLayout.addView(chunkView);

//        StarFieldChunk chunk1 = new StarFieldChunk(Vector.make2D(-1000,1000));
//        StarFieldChunk chunk2 = new StarFieldChunk(Vector.make2D(0,1000));
//        StarFieldChunk chunk3 = new StarFieldChunk(Vector.make2D(1000,1000));
//        StarFieldChunk chunk4 = new StarFieldChunk(Vector.make2D(-1000,0));
//        StarFieldChunk chunk5 = new StarFieldChunk(Vector.make2D(0,0));
//        StarFieldChunk chunk6 = new StarFieldChunk(Vector.make2D(1000,0));
//        StarFieldChunk chunk7 = new StarFieldChunk(Vector.make2D(-1000,-1000));
//        StarFieldChunk chunk8 = new StarFieldChunk(Vector.make2D(0,-1000));
//        StarFieldChunk chunk9 = new StarFieldChunk(Vector.make2D(1000,-1000));
//
//        long start = System.nanoTime();
//        Thread t1 = new Thread(new GeneratorRunnable(starForge, chunk1, 200));
//        Thread t2 = new Thread(new GeneratorRunnable(starForge, chunk2, 200));
//        Thread t3 = new Thread(new GeneratorRunnable(starForge, chunk3, 200));
//        Thread t4 = new Thread(new GeneratorRunnable(starForge, chunk4, 200));
//        Thread t5 = new Thread(new GeneratorRunnable(starForge, chunk5, 200));
//        Thread t6 = new Thread(new GeneratorRunnable(starForge, chunk6, 200));
//        Thread t7 = new Thread(new GeneratorRunnable(starForge, chunk7, 200));
//        Thread t8 = new Thread(new GeneratorRunnable(starForge, chunk8, 200));
//        Thread t9 = new Thread(new GeneratorRunnable(starForge, chunk9, 200));
//        t1.start();
//        t2.start();
//        t3.start();
//        t4.start();
//        t5.start();
//        t6.start();
//        t7.start();
//        t8.start();
//        t9.start();
//        try {
//            t1.join();
//            t2.join();
//            t3.join();
//            t4.join();
//            t5.join();
//            t6.join();
//            t7.join();
//            t8.join();
//            t9.join();
//        } catch (InterruptedException e) {}
//        long end = System.nanoTime();
//        end -= start;
//
//        Log.i("Generation Time", end + "");

//        long start = System.nanoTime();
//        starForge.generateChunk(chunk1, 200);
//        starForge.generateChunk(chunk2, 200);
//        starForge.generateChunk(chunk3, 200);
//        starForge.generateChunk(chunk4, 200);
//        starForge.generateChunk(chunk5, 200);
//        starForge.generateChunk(chunk6, 200);
//        starForge.generateChunk(chunk7, 200);
//        starForge.generateChunk(chunk8, 200);
//        starForge.generateChunk(chunk9, 200);
//        long end = System.nanoTime();
//        end -= start;
//
//        Log.i("Generation Time", end + "");
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
            starForge.generateChunk(chunk, starCount);
        }
    }
}
