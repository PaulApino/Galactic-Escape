package com.gscape.sdp.galacticescape.Engine.Physics;

import android.util.Log;

import com.gscape.sdp.galacticescape.Display.ActualGame.SimulationContents;
import com.gscape.sdp.galacticescape.Display.ActualGame.SpaceObject;
import com.gscape.sdp.galacticescape.Engine.Objects.PhysicsObject;

import java.util.ArrayList;
import java.util.Iterator;

public class SimulationRunnable implements Runnable {

    public static final int SIMULATION_STOPPED = -1;
    public static final int SIMULATION_CYCLE_FINISHED = 0;
    public static final int SIMULATION_CYCLE_RUNNING = 1;

    private SimulationContents simulationContents;
    private GravitationCalculator calculator;
    private int state;

    public SimulationRunnable(SimulationContents simulationContents, GravitationCalculator calculator) {
        this.simulationContents = simulationContents;
        this.calculator = calculator;
        state = SIMULATION_STOPPED;
    }

    public int getState () {
        return state;
    }

    @Override
    public void run() {
        try {
            while (state == SIMULATION_STOPPED || state == SIMULATION_CYCLE_FINISHED) {
                state = SIMULATION_CYCLE_RUNNING;
                ArrayList<SpaceObject> spaceObjects = simulationContents.getSpaceObjects();

                PhysicsObject[] arrObjects = new PhysicsObject[spaceObjects.size()];

                for (int i = 0; i < arrObjects.length; i++) {
                    arrObjects[i] = spaceObjects.get(i).getPhysicsObject();
                }

                calculator.simulate(arrObjects);
                calculator.update(arrObjects);

                state = SIMULATION_CYCLE_FINISHED;

                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            Log.e("BackgroundSimulation", e.getMessage());
        }
    }
}
