package com.gscape.sdp.galacticescape.Engine.Physics;

import android.util.Log;

import com.gscape.sdp.galacticescape.Display.ActualGame.SpaceObject;
import com.gscape.sdp.galacticescape.Engine.Objects.PhysicsObject;

import java.util.ArrayList;

public class SimulationRunnable implements Runnable {

    private ArrayList<SpaceObject> simulationContents;
    private GravitationCalculator calculator;
    private SimulationStateEnum simulationState;

    public SimulationRunnable(ArrayList<SpaceObject> simulationContents, GravitationCalculator calculator, SimulationStateEnum simulationState) {
        this.simulationContents = simulationContents;
        this.calculator = calculator;
        this.simulationState = simulationState;
    }

    @Override
    public void run() {
        try {
            while (simulationState == SimulationStateEnum.SIMULATION_BACKGROUND_FINISHED ||
                    simulationState == SimulationStateEnum.SIMULATION_RUNNING) {
                synchronized (simulationState) {
                    simulationState = SimulationStateEnum.SIMULATION_BACKGROUND_RUNNING;
                }

                PhysicsObject[] arrObjects = new PhysicsObject[simulationContents.size()];

                for (int i = 0; i < arrObjects.length; i++) {
                    arrObjects[i] = simulationContents.get(i).getPhysicsObject();
                }

                calculator.simulate(arrObjects);
                calculator.update(arrObjects);

                synchronized (simulationState) {
                    simulationState = SimulationStateEnum.SIMULATION_BACKGROUND_FINISHED;
                }

                notifyAll();

                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            Log.e("BackgroundSimulation", e.getMessage());
        }
    }
}
