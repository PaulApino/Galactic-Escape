package com.gscape.sdp.galacticescape.Display.StarFieldBackground;

import android.widget.TableLayout;

import com.gscape.sdp.galacticescape.Display.ActualGame.ScreenValues;
import com.gscape.sdp.galacticescape.Display.ActualGame.SimulationState;

public class StarFieldBackgroundRunnable implements Runnable {

    private final TableLayout container;
    private final StarFieldChunkView[][] starFieldChunkViews;
    private final StarFieldBackground starFieldBackground;
    private final ScreenValues screenValues;
    private final SimulationState simulationState;

    public StarFieldBackgroundRunnable(TableLayout container, StarFieldChunkView[][] starFieldChunkViews, StarFieldBackground starFieldBackground, ScreenValues screenValues, SimulationState simulationState) {
        this.container = container;
        this.starFieldChunkViews = starFieldChunkViews;
        this.starFieldBackground = starFieldBackground;
        this.screenValues = screenValues;
        this.simulationState = simulationState;
    }

    @Override
    public void run() {
        while(simulationState.isRunning() | simulationState.isResumed()) {
            int screenLocX = (int)Math.ceil(screenValues.getScreenCentreLocation().getX());
            int screenLocY = (int)Math.ceil(screenValues.getScreenCentreLocation().getY());
        }
    }

    private void setChunkViews () {
        StarFieldChunk[][] starFieldChunks = starFieldBackground.getStarFieldChunks();
        for (int i = 0; i < starFieldChunkViews.length; i++) {
            for (int j = 0; j < starFieldChunkViews[i].length; j++) {
                starFieldChunkViews[i][j].setNewChunk(starFieldChunks[i][j]);
            }
        }
    }
}
