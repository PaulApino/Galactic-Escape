package com.gscape.sdp.galacticescape.Display.TestDisplay;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.gscape.sdp.galacticescape.Display.ActualGame.ScreenValues;
import com.gscape.sdp.galacticescape.Display.StarFieldBackground.StarFieldBackground;
import com.gscape.sdp.galacticescape.Display.StarFieldBackground.StarFieldChunk;
import com.gscape.sdp.galacticescape.Display.StarFieldBackground.StarFieldChunkView;
import com.gscape.sdp.galacticescape.Display.StarFieldBackground.StarForge;
import com.gscape.sdp.galacticescape.Engine.Physics.Vector;
import com.gscape.sdp.galacticescape.R;

public class StarFieldTest extends AppCompatActivity {

    private RelativeLayout mainContainer;
    private RelativeLayout starFieldContainer;
    private TableLayout tableLayout;

    private ScreenValues screenValues;
    private StarForge starForge;
    private StarFieldBackground starFieldBackground;

    private StarFieldChunkView aStarFieldChunkView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_star_field_test);

        mainContainer = findViewById(R.id.test_starfield_layout);
//        mainContainer.setBackgroundColor(Color.BLACK);

        starFieldContainer = new RelativeLayout(getApplicationContext());
        RelativeLayout.LayoutParams starParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        starParams.addRule(RelativeLayout.CENTER_VERTICAL);
        starParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        starFieldContainer.setLayoutParams(starParams);
        mainContainer.addView(starFieldContainer);

        tableLayout = new TableLayout(getApplicationContext());
        TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
        tableLayout.setLayoutParams(tableParams);
        starFieldContainer.addView(tableLayout);

        init();

//        mainContainer.addView(aStarFieldChunkView);
    }

    private void init () {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenValues = new ScreenValues(Vector.make2D(displayMetrics.widthPixels, displayMetrics.heightPixels), Vector.make2D(0,0), Vector.make2D(0,0));

        starForge = new StarForge(5983);

        starFieldBackground = new StarFieldBackground(starForge, screenValues);

        StarFieldChunkView[][] chunkViews = starFieldBackground.getStarFieldChunkViews(getApplicationContext());
        StarFieldChunk[][] chunks = starFieldBackground.getStarFieldChunks();

        for (int i = 0; i < chunkViews.length; i++) {
            TableRow aRow = new TableRow(getApplicationContext());
//            aRow.setBackgroundColor(Color.BLACK);
            for (int j = 0; j < chunkViews[i].length; j++) {
                Log.i("Adding View", "asd");
//                chunkViews[i][j] = new StarFieldChunkView(getApplicationContext(), chunks[i][j]);
                aRow.addView(chunkViews[i][j]);
            }
            tableLayout.addView(aRow, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }

//        aStarFieldChunkView = chunkViews[1][0];
//        aStarFieldChunkView.setBackgroundColor(Color.BLACK);
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
