package com.gscape.sdp.galacticescape.Display.TestDisplay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.RelativeLayout;

import com.gscape.sdp.galacticescape.Display.ActualGame.ScreenValues;
import com.gscape.sdp.galacticescape.Display.StarFieldBackground.StarFieldBackground;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(findViewById(R.id.test_starfield_layout));

        allContainer = findViewById(R.id.test_starfield_layout);

        gridContainer = new RelativeLayout(getApplicationContext());
        RelativeLayout.LayoutParams containerParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        containerParams.addRule(RelativeLayout.ALIGN_PARENT_START);
        containerParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        gridContainer.setLayoutParams(containerParams);
        allContainer.addView(gridContainer);

        starGrid = new GridLayout(getApplicationContext());
        starGrid.setRowCount(MAX_ROW);
        starGrid.setColumnCount(MAX_COLUMN);
        ViewGroup.LayoutParams gridParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        starGrid.setLayoutParams(gridParams);
        gridContainer.addView(starGrid);
    }

    private void init() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenValues = new ScreenValues(Vector.make2D(displayMetrics.widthPixels, displayMetrics.heightPixels), Vector.make2D(0,0), Vector.make2D(0,0));

        starForge = new StarForge(4566);

        starFieldBackground = new StarFieldBackground(starForge, screenValues);

        StarFieldChunkView[][] chunks = starFieldBackground.getStarFieldChunkViews(getApplicationContext());

        for (int i = 0;  i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COLUMN; j++) {

            }
        }
    }
}
