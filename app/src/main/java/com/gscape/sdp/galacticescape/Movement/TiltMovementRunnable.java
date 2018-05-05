package com.gscape.sdp.galacticescape.Movement;

import com.gscape.sdp.galacticescape.Display.ActualGame.SimulationState;
import com.gscape.sdp.galacticescape.Engine.Objects.PhysicsObject;

public class TiltMovementRunnable implements Runnable {

    private final TiltAcceleration tiltAcceleration;
    private final PhysicsObject physicsObject;
    private final SimulationState simulationState;

    public TiltMovementRunnable(TiltAcceleration tiltAcceleration, PhysicsObject physicsObject, SimulationState simulationState) {
        this.tiltAcceleration = tiltAcceleration;
        this.physicsObject = physicsObject;
        this.simulationState = simulationState;
    }

    @Override
    public void run() {
        while (simulationState.isRunning() || simulationState.isResumed()) {
            try {
                tiltAcceleration.setTiltAcceleration();
                Thread.sleep(50);
            } catch (InterruptedException e) {}
        }
    }
}
