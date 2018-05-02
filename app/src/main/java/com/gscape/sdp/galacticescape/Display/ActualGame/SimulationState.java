package com.gscape.sdp.galacticescape.Display.ActualGame;

import com.gscape.sdp.galacticescape.Engine.Physics.SimulationStateEnum;

public class SimulationState {

    private SimulationStateEnum generalState;
    private SimulationStateEnum backgroundState;
    private SimulationStateEnum displayState;

    public SimulationState() {
        generalState = SimulationStateEnum.SIMULATION_INITIALIZING;
        backgroundState = SimulationStateEnum.SIMULATION_BACKGROUND_STOPPED;
        displayState = SimulationStateEnum.SIMULATION_DISPLAY_STOPPED;
    }

    public boolean isInitializing() {
        return generalState == SimulationStateEnum.SIMULATION_INITIALIZING;
    }

    public boolean isRunning() {
        return generalState == SimulationStateEnum.SIMULATION_RUNNING;
    }

    public boolean isPaused() {
        return generalState == SimulationStateEnum.SIMULATION_PAUSED;
    }

    public boolean isResumed() {
        return generalState == SimulationStateEnum.SIMULATION_RESUMED;
    }

    public boolean isStopped() {
        return generalState == SimulationStateEnum.SIMULATION_STOPPED;
    }

    public boolean isBackgroundRunning() {
        return backgroundState == SimulationStateEnum.SIMULATION_BACKGROUND_RUNNING;
    }

    public boolean isBackgroundFinished() {
        return backgroundState == SimulationStateEnum.SIMULATION_BACKGROUND_FINISHED;
    }

    public boolean isBackgroundStoppped() {
        return backgroundState == SimulationStateEnum.SIMULATION_BACKGROUND_STOPPED;
    }

    public boolean isBackgroundDeleting() {
        return backgroundState == SimulationStateEnum.SIMULATION_BACKGROUND_DELETING;
    }

    public boolean isDisplayRunning() {
        return displayState == SimulationStateEnum.SIMULATION_DISPLAY_RUNNING;
    }

    public boolean isDisplayFinished() {
        return displayState == SimulationStateEnum.SIMULATION_DISPLAY_FINISHED;
    }

    public boolean isDisplayStopped() {
        return displayState == SimulationStateEnum.SIMULATION_DISPLAY_STOPPED;
    }

    public boolean isDisplayDeleting() {
        return displayState == SimulationStateEnum.SIMULATION_DISPLAY_DELETING;
    }

    public boolean isSafeDisplay() {
        return
    }
}
