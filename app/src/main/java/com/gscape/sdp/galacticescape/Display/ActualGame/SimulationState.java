package com.gscape.sdp.galacticescape.Display.ActualGame;

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

    public boolean isStopped() {
        return generalState == SimulationStateEnum.SIMULATION_STOPPED;
    }

    public boolean isBackgroundRunning() {
        return backgroundState == SimulationStateEnum.SIMULATION_BACKGROUND_RUNNING;
    }

    public boolean isBackgroundFinished() {
        return backgroundState == SimulationStateEnum.SIMULATION_BACKGROUND_FINISHED;
    }

    public boolean isBackgroundStopped() {
        return backgroundState == SimulationStateEnum.SIMULATION_BACKGROUND_STOPPED;
    }

    public boolean isBackgroundAdding() {
        return backgroundState == SimulationStateEnum.SIMULATION_BACKGROUND_ADDING;
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

    public boolean isDisplayAdding() {
        return displayState == SimulationStateEnum.SIMULATION_DISPLAY_ADDING;
    }

    public boolean isDisplayDeleting() {
        return displayState == SimulationStateEnum.SIMULATION_DISPLAY_DELETING;
    }

    public void setRunning() {
        generalState = SimulationStateEnum.SIMULATION_RUNNING;
    }

    public void setStopped() {
        generalState = SimulationStateEnum.SIMULATION_STOPPED;
    }

    public void setBackgroundRunning() {
        backgroundState = SimulationStateEnum.SIMULATION_BACKGROUND_RUNNING;
    }

    public void setBackgroundFinished() {
        backgroundState = SimulationStateEnum.SIMULATION_BACKGROUND_FINISHED;
    }

    public void setBackgroundStopped() {
        backgroundState = SimulationStateEnum.SIMULATION_BACKGROUND_STOPPED;
    }

    public void setBackgroundAdding() {
        backgroundState = SimulationStateEnum.SIMULATION_BACKGROUND_ADDING;
    }

    public void setBackgroundDeleting() {
        backgroundState = SimulationStateEnum.SIMULATION_BACKGROUND_DELETING;
    }

    public void setDisplayRunning() {
        displayState = SimulationStateEnum.SIMULATION_DISPLAY_RUNNING;
    }

    public void setDisplayFinished() {
        displayState = SimulationStateEnum.SIMULATION_DISPLAY_FINISHED;
    }

    public void setDisplayStopped() {
        displayState = SimulationStateEnum.SIMULATION_DISPLAY_STOPPED;
    }

    public void setDisplayAdding() {
        displayState = SimulationStateEnum.SIMULATION_DISPLAY_ADDING;
    }

    public void setDisplayDeleting() {
        displayState = SimulationStateEnum.SIMULATION_DISPLAY_DELETING;
    }

    public boolean isSafeBackgroundSimulate() {
        synchronized (this) {
            if ((isBackgroundFinished() | isBackgroundStopped())
                    && (isDisplayFinished() | isDisplayStopped())) {
                setBackgroundRunning();
                return true;
            } else return false;
        }
    }

    public boolean isSafeDisplay() {
        synchronized (this) {
            if ((!isBackgroundAdding() | !isBackgroundDeleting())
                    && (!isDisplayAdding() | !isBackgroundDeleting())) {
                setDisplayRunning();
                return true;
            } else return false;
        }
    }
}
