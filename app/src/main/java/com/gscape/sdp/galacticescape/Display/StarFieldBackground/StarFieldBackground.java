package com.gscape.sdp.galacticescape.Display.StarFieldBackground;

import android.content.Context;

import com.gscape.sdp.galacticescape.Display.ActualGame.ScreenValues;
import com.gscape.sdp.galacticescape.Engine.Physics.Vector;

public class StarFieldBackground {

    private StarFieldChunkView[][] starFieldChunks;
    private final Context context;
    private final ScreenValues screenValues;
    private final int maxColumnCount;
    private final int maxRowCount;

    private final StarForge starForge;

    public StarFieldBackground (Context context, ScreenValues screenValues, StarForge starForge) {
        this.context = context;
        this.screenValues = screenValues;
        this.starForge = starForge;

        maxColumnCount = getMaxMatrixCount(screenValues.getScreenSize().getX());
        maxRowCount = getMaxMatrixCount(screenValues.getScreenSize().getY());

//        starFieldChunks = new StarFieldChunkView[maxRowCount][maxColumnCount];
    }

    private int getMaxMatrixCount (double size) {
        return (int)Math.round(size * 2) + 1;
    }

//    private StarFieldChunkView[][] generateViews () {
//        int xMinLocation = ((int)screenValues.getScreenLocation().getX() / 1000) - (maxColumnCount / 2);
//        int yMaxLocation = (int)screenValues.getScreenLocation().getY() / 1000 + (maxRowCount / 2);
//
//        for (int i = 0; i < maxColumnCount; i++) {
//            for (int j = 0; j < maxRowCount; j++) {
//
//            }
//        }
//    }
}
