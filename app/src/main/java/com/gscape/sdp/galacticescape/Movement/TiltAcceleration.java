package com.gscape.sdp.galacticescape.Movement;

import com.gscape.sdp.galacticescape.Engine.Objects.PhysicsObject;
import com.gscape.sdp.galacticescape.Engine.Physics.Vector;

public class TiltAcceleration {

    private final PhysicsObject physicsObject;
    private final Accelerometer accelValues;

    public TiltAcceleration(PhysicsObject physicsObject, Accelerometer accelValues) {
        this.physicsObject = physicsObject;
        this.accelValues = accelValues;
    }

    public void setTiltAcceleration() {
        Vector vectorFromAccelmeter = accelValues.getVectorFromSensor();

        double xIntensity = vectorFromAccelmeter.getX();
        double yIntensity = vectorFromAccelmeter.getY();

        if (xIntensity > -4 && xIntensity < 4) {
            xIntensity = xIntensity / 4;
            if (xIntensity > -0.3 && xIntensity < 0.3) {
                xIntensity = 0;
            }
        } else if (xIntensity <= -4) {
            xIntensity = -1;
        } else xIntensity = 1;

        if (yIntensity > -4 && yIntensity < 4) {
            yIntensity = yIntensity / 4;
            if (yIntensity > -0.3 && yIntensity < 0.3) {
                yIntensity = 0;
            }
        } else if (yIntensity <= -4) {
            yIntensity = -1;
        } else yIntensity = 1;

        Vector objectAccel = Vector.make2D(0.15 * xIntensity, 0.15 * yIntensity);

        synchronized (physicsObject) {
            physicsObject.setVelocity(physicsObject.getVelocity().add(objectAccel));
        }
    }
}
