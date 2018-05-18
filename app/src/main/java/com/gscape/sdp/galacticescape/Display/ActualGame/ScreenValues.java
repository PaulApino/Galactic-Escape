package com.gscape.sdp.galacticescape.Display.ActualGame;

import com.gscape.sdp.galacticescape.Engine.Physics.Vector;

public class ScreenValues {

    private Vector screenSize;
    private Vector screenLocation;
    private Vector screenCentreLocation;

    public ScreenValues(Vector screenSize, Vector screenLocation, Vector screenCentreLocation) {
        this.screenSize = screenSize;
        this.screenLocation = screenLocation;
        this.screenCentreLocation = screenCentreLocation;
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

    public Vector getScreenCentreLocation() {
        return screenCentreLocation;
    }

    public void setScreenCentreLocation(Vector screenCentreLocation) {
        this.screenCentreLocation = screenCentreLocation;
    }
}
