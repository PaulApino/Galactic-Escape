package com.gscape.sdp.galacticescape.Engine.Objects;

import com.gscape.sdp.galacticescape.Engine.Physics.Vector;

public class Player extends PhysicsObject {

    public Player(double mass, double collisionRadius, Vector location, Vector velocity, Vector acceleration) {
        super(mass, collisionRadius, location, velocity, acceleration);
    }


}
