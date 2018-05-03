package com.gscape.sdp.galacticescape.Display.ActualGame;

import android.util.Log;
import android.widget.RelativeLayout;

import com.gscape.sdp.galacticescape.Engine.Physics.SimulationStateEnum;

import java.util.ArrayList;

public class SimulationDisplayRunnable implements Runnable {

    private RelativeLayout container;

    private SimulationContents contents;
    private SimulationState simulationState;

    public SimulationDisplayRunnable(RelativeLayout container, SimulationContents contents, SimulationState simulationState) {
        this.container = container;
        this.contents = contents;
        this.simulationState = simulationState;
    }

    @Override
    public void run() {
        try {
            while (simulationState.isRunning() | simulationState.isResumed()) {
                Log.i("DisplayRefresher", "StartDisplay");
                if (simulationState.isSafeDisplay()) {

                    contents.updateLocations();

                    simulationState.setDisplayFinished();

                    container.requestLayout();

                    Thread.sleep(200);
                } else Log.i("DisplayRefresher", "FailedDisplay");
            }
        } catch (InterruptedException e) {
            Log.i("DisplayRefresher", e.getMessage());
        }
    }
}
