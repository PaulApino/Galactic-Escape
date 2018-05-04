package com.gscape.sdp.galacticescape.Display.ActualGame;

import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.gscape.sdp.galacticescape.Engine.Objects.PhysicsObject;
import com.gscape.sdp.galacticescape.Engine.Physics.SimulationStateEnum;
import com.gscape.sdp.galacticescape.Engine.Physics.Vector;

import java.util.ArrayList;

public class SimulationDisplayRunnable implements Runnable {

    private final RelativeLayout container;
    private final Vector screenSize;
    private final SimulationContents contents;
    private final SimulationState simulationState;

    public SimulationDisplayRunnable(RelativeLayout container, Vector screenSize, SimulationContents contents, SimulationState simulationState) {
        this.container = container;
        this.screenSize = screenSize;
        this.contents = contents;
        this.simulationState = simulationState;
    }

    @Override
    public void run() {
        try {
            while (simulationState.isRunning() | simulationState.isResumed()) {
                if (simulationState.isSafeDisplay()) {

                    ArrayList<ImageView> imageObjects = contents.getImageObjects();
                    ArrayList<RelativeLayout.LayoutParams> imageParameters = new ArrayList<>(imageObjects.size());

                    synchronized (contents) {
                        ArrayList<PhysicsObject> physicsObjects = contents.getPhysicsObjects();

                        PhysicsObject player = physicsObjects.get(0);
                        Vector screenLocation = Vector.make2D(
                                player.getLocation().getX() - (screenSize.getX() / 2),
                                player.getLocation().getY() - (screenSize.getY() / 2));

                        for (int i = 1; i < imageObjects.size(); i++) {
                            Vector currentPhysObjectLocation = physicsObjects.get(i).getLocation();
                            double imageOffset = physicsObjects.get(i).getCollisionRadius();
                            ImageView currentImgObject = imageObjects.get(i);

                            Vector deltaV = currentPhysObjectLocation.minus(screenLocation);

                            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)currentImgObject.getLayoutParams();
                            layoutParams.leftMargin = (int)(deltaV.getX() - imageOffset);
                            layoutParams.bottomMargin = (int)(deltaV.getY() - imageOffset);
                            imageParameters.add(layoutParams);
                        }
                    }

                    simulationState.setDisplayFinished();

                    container.post(new LayoutParameterUpdate(imageObjects, imageParameters));

                    container.post(new Runnable() {
                        @Override
                        public void run() {
                            container.invalidate();
                        }
                    });

                    Thread.sleep(10);
                }
            }
        } catch (InterruptedException e) {
            Log.i("DisplayRefresher", e.getMessage());
        }
    }

    private class LayoutParameterUpdate implements Runnable {

        private final ArrayList<ImageView> images;
        private final ArrayList<RelativeLayout.LayoutParams> layoutParams;

        public LayoutParameterUpdate(ArrayList<ImageView> images, ArrayList<RelativeLayout.LayoutParams> layoutParams) {
            this.images = images;
            this.layoutParams = layoutParams;
        }

        @Override
        public void run() {
            for (int i = 1; i < images.size(); i++) {
                images.get(i).setLayoutParams(layoutParams.get(i - 1));
            }
        }
    }
}
