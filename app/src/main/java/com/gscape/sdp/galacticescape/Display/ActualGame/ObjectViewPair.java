package com.gscape.sdp.galacticescape.Display.ActualGame;

import android.widget.ImageView;

import com.gscape.sdp.galacticescape.Engine.Objects.PhysicsObject;

public class ObjectViewPair {

    private final PhysicsObject physicsObject;
    private final ImageView imageView;

    public ObjectViewPair(PhysicsObject physicsObject, ImageView imageView) {
        this.physicsObject = physicsObject;
        this.imageView = imageView;
    }

    public PhysicsObject getPhysicsObject() {
        return physicsObject;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public static ObjectViewPair getObjectValuePair (PhysicsObject physicsObject) {
        if (physicsObject == null) throw new NullPointerException("physicsObject cannot be null.");

        switch (physicsObject.getObjectType()) {
            case PLAYER:

        }
    }
}
