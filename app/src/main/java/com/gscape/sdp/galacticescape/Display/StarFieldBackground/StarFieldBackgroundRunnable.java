package com.gscape.sdp.galacticescape.Display.StarFieldBackground;

import android.content.Context;
import android.widget.RelativeLayout;

import com.gscape.sdp.galacticescape.Display.ActualGame.ScreenValues;
import com.gscape.sdp.galacticescape.Display.ActualGame.SimulationState;

public class StarFieldBackgroundRunnable implements Runnable {

    private final static int NO_DIRECTION = -1;
    private final static int GREATER_POSITIVE = 0;
    private final static int GREATER_NEGATIVE = 1;

    private final Context context;
    private final RelativeLayout container;
    private final StarFieldChunkView[][] starFieldChunkViews;
    private final StarFieldBackground starFieldBackground;
    private final ScreenValues screenValues;
    private final SimulationState simulationState;

    public StarFieldBackgroundRunnable(Context context, RelativeLayout container, StarForge starForge, ScreenValues screenValues, SimulationState simulationState) {
        this.context = context;
        this.container = container;
        this.screenValues = screenValues;
        this.simulationState = simulationState;
        this.starFieldBackground = new StarFieldBackground(starForge, screenValues);
        this.starFieldChunkViews = initViews();
    }

    @Override
    public void run() {
        while(simulationState.isRunning() | simulationState.isResumed()) {
            int distChunkCentX = (int)Math.ceil(screenValues.getScreenCentreLocation().getX()) - (starFieldBackground.getCentreChunkX() + 500);
            int distChunkCentY = (int)Math.ceil(screenValues.getScreenCentreLocation().getY()) - (starFieldBackground.getCentreChunkY() + 500);

            int containerDistX = (1000 * -(starFieldBackground.getSideChunkCountX() + 1)) + (int)(screenValues.getScreenSize().getX() / 2) - distChunkCentX;
            int containerDistY = (1000 * -(starFieldBackground.getSideChunkCountY() + 1)) + (int)(screenValues.getScreenSize().getY() / 2) - distChunkCentY;

            final RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)container.getLayoutParams();
            layoutParams.leftMargin = containerDistX;
            layoutParams.bottomMargin = containerDistY;
            container.post(new Runnable() {
                @Override
                public void run() {
                    container.setLayoutParams(layoutParams);
                    container.invalidate();
                }
            });

            int newCentreDirection = getNewChunkDirection(distChunkCentX, distChunkCentY);

            if (newCentreDirection != NO_DIRECTION) {
                starFieldBackground.backgroundUpdate(newCentreDirection);
                setChunkViews();
            }

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {}
        }
    }

    public StarFieldBackground getStarFieldBackground() {
        return starFieldBackground;
    }

    private StarFieldChunkView[][] initViews () {
        StarFieldChunkView[][] initViews = new StarFieldChunkView[starFieldBackground.getMaxMatrixRow()][starFieldBackground.getMaxMatrixColumn()];
        StarFieldChunk[][] initChunks = starFieldBackground.getStarFieldChunks();
        for (int i = 0; i < starFieldBackground.getMaxMatrixRow(); i++) {
            for (int j = 0; j < starFieldBackground.getMaxMatrixColumn(); j++) {
                initViews[i][j] = new StarFieldChunkView(context, initChunks[i][j]);
            }
        }
        return initViews;
    }

    private void setChunkViews () {
        StarFieldChunk[][] starFieldChunks = starFieldBackground.getStarFieldChunks();
        for (int i = 0; i < starFieldChunkViews.length; i++) {
            for (int j = 0; j < starFieldChunkViews[i].length; j++) {
                starFieldChunkViews[i][j].setNewChunk(starFieldChunks[i][j]);
            }
        }
    }

    private int checkPlaneDisplacement (int displacement) {
        if (displacement > 1500) return GREATER_POSITIVE;
        else if (displacement < -1500) return GREATER_NEGATIVE;
        else return NO_DIRECTION;
    }

    private int getNewChunkDirection (int distChunkCentX, int distChunkCentY) {
        int newCentreDirectionX = NO_DIRECTION;
        int newCentreDirectionY = NO_DIRECTION;

        switch (checkPlaneDisplacement(distChunkCentX)) {
            case GREATER_POSITIVE :
                newCentreDirectionX = StarFieldBackground.EAST;
                break;

            case GREATER_NEGATIVE :
                newCentreDirectionX = StarFieldBackground.WEST;
                break;
        }

        switch (checkPlaneDisplacement(distChunkCentY)) {
            case GREATER_POSITIVE :
                newCentreDirectionY = StarFieldBackground.NORTH;
                break;

            case GREATER_NEGATIVE :
                newCentreDirectionY = StarFieldBackground.SOUTH;
                break;
        }

        switch (newCentreDirectionX) {
            case StarFieldBackground.EAST :
                switch (newCentreDirectionY) {
                    case StarFieldBackground.NORTH :
                        return StarFieldBackground.NORTH_EAST;

                    case StarFieldBackground.SOUTH :
                        return StarFieldBackground.SOUTH_EAST;

                    default : return StarFieldBackground.EAST;
                }

            case StarFieldBackground.WEST :
                switch (newCentreDirectionY) {
                    case StarFieldBackground.NORTH :
                        return StarFieldBackground.NORTH_WEST;

                    case StarFieldBackground.SOUTH :
                        return StarFieldBackground.SOUTH_WEST;

                    default : return StarFieldBackground.WEST;
                }

            default :
                switch (newCentreDirectionY) {
                    case StarFieldBackground.NORTH :
                        return StarFieldBackground.NORTH;

                    case StarFieldBackground.SOUTH :
                        return StarFieldBackground.SOUTH;

                    default : return NO_DIRECTION;
                }
        }
    }
}
