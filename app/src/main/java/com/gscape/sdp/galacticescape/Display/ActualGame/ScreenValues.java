package com.gscape.sdp.galacticescape.Display.ActualGame;

import com.gscape.sdp.galacticescape.Engine.Physics.Vector;

public class ScreenValues {

    private Vector screenSize;
    private Vector screenLocation;

    public ScreenValues(Vector screenSize, Vector screenLocation) {
        this.screenSize = screenSize;
        this.screenLocation = screenLocation;
    }

    public Vector getScreenSize() {
        synchronized (this) {
            return screenSize;
        }
    }

    public void setScreenSize(Vector screenSize) {
        synchronized (this) {
            this.screenSize = screenSize;
        }
    }

    public Vector getScreenLocation() {
        synchronized (this) {
            return screenLocation;
        }
    }

    public void setScreenLocation(Vector screenLocation) {
        synchronized (this) {
            this.screenLocation = screenLocation;
        }
    }
}
