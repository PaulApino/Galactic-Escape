package com.gscape.sdp.galacticescape.Display.TestDisplay;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.RelativeLayout;

import com.gscape.sdp.galacticescape.Display.ActualGame.ScreenValues;
import com.gscape.sdp.galacticescape.Display.StarFieldBackground.StarFieldBackground;
import com.gscape.sdp.galacticescape.Display.StarFieldBackground.StarFieldChunk;
import com.gscape.sdp.galacticescape.Display.StarFieldBackground.StarFieldChunkView;
import com.gscape.sdp.galacticescape.Display.StarFieldBackground.StarForge;
import com.gscape.sdp.galacticescape.Engine.Physics.Vector;
import com.gscape.sdp.galacticescape.R;

public class StarFieldTest extends AppCompatActivity {

    private final int MAX_ROW = 3;
    private final int MAX_COLUMN = 5;

    private ScreenValues screenValues;

    private RelativeLayout allContainer;
    private RelativeLayout gridContainer;
    private GridLayout starGrid;

    private StarForge starForge;
    private StarFieldBackground starFieldBackground;
    private StarFieldChunkView[][] chunkViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_field_test);

        allContainer = findViewById(R.id.test_starfield_layout);

        gridContainer = new RelativeLayout(getApplicationContext());
        RelativeLayout.LayoutParams containerParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        containerParams.addRule(RelativeLayout.ALIGN_PARENT_START);
        containerParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        gridContainer.setLayoutParams(containerParams);
        gridContainer.setBackgroundColor(Color.BLACK);
        allContainer.addView(gridContainer);

        starGrid = new GridLayout(getApplicationContext());
        ViewGroup.LayoutParams gridParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        starGrid.setLayoutParams(gridParams);
        starGrid.setRowCount(MAX_ROW);
        starGrid.setColumnCount(MAX_COLUMN);
        gridContainer.addView(starGrid);

        init();

        allContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starFieldBackground.backgroundUpdate(StarFieldBackground.WEST);

                StarFieldChunk[][] chunks = starFieldBackground.getStarFieldChunks();

                for (int i = 0; i <  MAX_ROW; i++) {
                    for (int j = 0; j < MAX_COLUMN; j++) {
                        chunkViews[i][j].setNewChunk(chunks[i][j]);
                        chunkViews[i][j].invalidate();
                    }
                }
            }
        });
    }

    private void init() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenValues = new ScreenValues(Vector.make2D(displayMetrics.widthPixels, displayMetrics.heightPixels), Vector.make2D(0,0), Vector.make2D(0,0));

        starForge = new StarForge(-384675768);

        starFieldBackground = new StarFieldBackground(getApplicationContext(), starForge, screenValues);

        chunkViews = starFieldBackground.getStarFieldChunkViews();

        setChunks();
    }

    private void setChunks () {
        starGrid.removeAllViews();
        for (int i = 0;  i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COLUMN; j++) {
                GridLayout.LayoutParams aChunkParam = new GridLayout.LayoutParams(GridLayout.spec(i), GridLayout.spec(j));
                aChunkParam.height = 1000;
                aChunkParam.width = 1000;
                starGrid.addView(chunkViews[i][j], aChunkParam);
            }
        }
    }
}
