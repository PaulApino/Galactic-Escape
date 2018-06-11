package com.gscape.sdp.galacticescape.Engine.Physics;

import android.util.Log;

import com.gscape.sdp.galacticescape.Display.ActualGame.ObjectViewPair;
import com.gscape.sdp.galacticescape.Display.ActualGame.SimulationContents;
import com.gscape.sdp.galacticescape.Display.ActualGame.SimulationState;
import com.gscape.sdp.galacticescape.Engine.Objects.PhysicsObject;

import java.util.ArrayList;

public class SimulationRunnable implements Runnable {

    private SimulationContents simulationContents;
    private GravitationCalculator calculator;
    private SimulationState simulationState;

    public SimulationRunnable(SimulationContents simulationContents, GravitationCalculator calculator, SimulationState simulationState) {
        this.simulationContents = simulationContents;
        this.calculator = calculator;
        this.simulationState = simulationState;
    }

    @Override
    public void run() {
        try {
            while (simulationState.isRunning()) {
                if (simulationState.isSafeBackgroundSimulate()) {

                    synchronized (simulationContents) {
                        ArrayList<ObjectViewPair> physicsObjects = simulationContents.getObjectViewPairs();
                        PhysicsObject[] arrObjects = new PhysicsObject[physicsObjects.size()];
                        for (int i = 0; i < arrObjects.length; i++) {
                            arrObjects[i] = physicsObjects.get(i).getPhysicsObject();
                        }
                        calculator.simulate(arrObjects);
                        calculator.update(arrObjects);
                    }

                    simulationState.setBackgroundFinished();

                    Thread.sleep(20);
                }
            }
        } catch (InterruptedException e) {
            Log.i("BackgroundSimulation", e.getMessage());
        }
    }
}
