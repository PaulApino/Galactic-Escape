package com.gscape.sdp.galacticescape.Display.ActualGame;

import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.gscape.sdp.galacticescape.Engine.Objects.PhysicsObject;
import com.gscape.sdp.galacticescape.Engine.Physics.Vector;

import java.util.ArrayList;

public class SimulationDisplayRunnable implements Runnable {

    private final RelativeLayout container;
    private final ScreenValues screenValues;
    private final SimulationContents contents;
    private final SimulationState simulationState;

    public SimulationDisplayRunnable(RelativeLayout container, ScreenValues screenValues, SimulationContents contents, SimulationState simulationState) {
        this.container = container;
        this.screenValues = screenValues;
        this.contents = contents;
        this.simulationState = simulationState;
    }

    @Override
    public void run() {
        try {
            while (simulationState.isRunning()) {
                if (simulationState.isSafeDisplay()) {

                    synchronized (contents) {
                        ArrayList<ObjectViewPair> objectViewPairs = contents.getObjectViewPairs();

                        PhysicsObject player = contents.getPlayer().getPhysicsObject();
                        screenValues.setScreenCentreLocation(Vector.make2D(
                                player.getLocation().getX(),
                                player.getLocation().getY()));
                        screenValues.setScreenLocation(Vector.make2D(
                                player.getLocation().getX() - (screenValues.getScreenSize().getX() / 2),
                                player.getLocation().getY() - (screenValues.getScreenSize().getY() / 2)));

                        for (int i = 1; i < objectViewPairs.size(); i++) {
                            Vector currentPhysObjectLocation = objectViewPairs.get(i).getPhysicsObject().getLocation();
                            double imageOffset = objectViewPairs.get(i).getPhysicsObject().getCollisionRadius();
                            ImageView currentImgObject = objectViewPairs.get(i).getImageView();

                            Vector deltaV = currentPhysObjectLocation.minus(screenValues.getScreenLocation());

                            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)currentImgObject.getLayoutParams();
                            layoutParams.leftMargin = (int)(deltaV.getX() - imageOffset);
                            layoutParams.bottomMargin = (int)(deltaV.getY() - imageOffset);
                            container.post(new LayoutParameterUpdate(currentImgObject, layoutParams));
                        }
                    }

                    container.post(new Runnable() {
                        @Override
                        public void run() {
                            container.invalidate();
                        }
                    });

                    simulationState.setDisplayFinished();

                    Thread.sleep(15);
                }
            }
        } catch (InterruptedException e) {
            Log.i("DisplayRefresher", e.getMessage());
        }
    }

    private class LayoutParameterUpdate implements Runnable {

        private final ImageView images;
        private final RelativeLayout.LayoutParams layoutParams;

        public LayoutParameterUpdate(ImageView images, RelativeLayout.LayoutParams layoutParams) {
            this.images = images;
            this.layoutParams = layoutParams;
        }

        @Override
        public void run() {
            images.setLayoutParams(layoutParams);
        }
    }
}
