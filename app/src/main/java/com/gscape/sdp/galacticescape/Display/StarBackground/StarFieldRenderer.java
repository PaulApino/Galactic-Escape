package com.gscape.sdp.galacticescape.Display.StarBackground;

import com.gscape.sdp.galacticescape.Display.ActualGame.SimulationState;

public class StarFieldRenderer implements Runnable {

    private final StarField starField;
    private final SimulationState simulationState;

    public StarFieldRenderer(StarField starField, SimulationState simulationState) {
        this.starField = starField;
        this.simulationState = simulationState;
    }

    @Override
    public void run() {
        while (simulationState.isRunning() || simulationState.isResumed()) {
            starField.updateBackground();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {}
        }
    }
}
