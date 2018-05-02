package com.gscape.sdp.galacticescape.Display.ActualGame;

import android.content.Context;
import android.widget.RelativeLayout;

import com.gscape.sdp.galacticescape.Engine.Physics.SimulationStateEnum;

import java.util.ArrayList;

public class SimulationViewRunnable implements Runnable {

    private Context context;
    private RelativeLayout container;

    private SimulationContents contents;
    private SimulationState simulationState;

    public SimulationViewRunnable(Context context, SimulationContents contents, SimulationState simulationState) {
        this.context = context;
        this.contents = contents;
        this.simulationState = simulationState;
        init();
    }

    private void init() {
        container = new RelativeLayout(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        container.setLayoutParams(layoutParams);

        ArrayList<SpaceObject> spaceObjects = contents.getSpaceObjects();

        if (simulationState.isSafeDisplay()) {
            for (SpaceObject currentObject : spaceObjects) {
                currentObject.setScreenLocation(contents.getScreenLocation());
                container.addView(currentObject.getSpaceView());
            }
        }
    }

    @Override
    public void run() {

    }
}
