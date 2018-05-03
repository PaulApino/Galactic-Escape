package com.gscape.sdp.galacticescape.Engine.Physics;

import android.util.Log;

import com.gscape.sdp.galacticescape.Display.ActualGame.SimulationState;
import com.gscape.sdp.galacticescape.Display.ActualGame.SpaceObject;
import com.gscape.sdp.galacticescape.Engine.Objects.PhysicsObject;

import java.util.ArrayList;

public class SimulationRunnable implements Runnable {

    private ArrayList<SpaceObject> simulationContents;
    private GravitationCalculator calculator;
    private SimulationState simulationState;

    public SimulationRunnable(ArrayList<SpaceObject> simulationContents, GravitationCalculator calculator, SimulationState simulationState) {
        this.simulationContents = simulationContents;
        this.calculator = calculator;
        this.simulationState = simulationState;
    }

    @Override
    public void run() {
        try {
            while (simulationState.isRunning() || simulationState.isResumed()) {
                if (simulationState.isSafeBackgroundSimulate()) {

                    PhysicsObject[] arrObjects = new PhysicsObject[simulationContents.size()];
                    for (int i = 0; i < arrObjects.length; i++) {
                        arrObjects[i] = simulationContents.get(i).getPhysicsObject();
                    }

                    calculator.simulate(arrObjects);
                    calculator.update(arrObjects);

                    simulationState.setBackgroundFinished();
                    notifyAll();

                    Thread.sleep(50);
                }
            }
        } catch (InterruptedException e) {
            Log.e("BackgroundSimulation", e.getMessage());
        }
    }
}
