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

        if (xIntensity > -3 && xIntensity < 3) {
            xIntensity = xIntensity / 3;
        } else if (xIntensity <= -3) {
            xIntensity = -1;
        } else xIntensity = 1;
        
        if (yIntensity > -3 && yIntensity < 3) {
            yIntensity = yIntensity / 3;
        } else if (yIntensity <= -3) {
            yIntensity = -1;
        } else yIntensity = 1;

        Vector objectAccel = Vector.make2D(0.2 * xIntensity, 0.2 * yIntensity);

        synchronized (physicsObject) {
            physicsObject.setVelocity(physicsObject.getVelocity().add(objectAccel));
        }
    }
}
