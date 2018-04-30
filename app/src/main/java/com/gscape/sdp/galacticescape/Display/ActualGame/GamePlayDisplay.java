package com.gscape.sdp.galacticescape.Display.ActualGame;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.gscape.sdp.galacticescape.Engine.Objects.PhysicsObject;
import com.gscape.sdp.galacticescape.Engine.Physics.Vector;
import com.gscape.sdp.galacticescape.R;

import java.util.ArrayList;

public class GamePlayDisplay extends AppCompatActivity {

    private RelativeLayout contentDisplay;
    private ArrayList<SpaceObject> spaceObjects;
    private SpaceObject referenceObject;
    private Switch spawnMoveSwitch;
    private Vector screenLocation;

    private int contentDisplayHeight;
    private float originX, originY;

    private View.OnTouchListener contentDisplayListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            final float touchX = motionEvent.getRawX();
            final float touchY = motionEvent.getRawY();

            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    if (spawnMoveSwitch.isChecked()) {
                        originX = (float)screenLocation.getX() + touchX;
                        originY = (float)screenLocation.getY() + (contentDisplayHeight - touchY);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (spawnMoveSwitch.isChecked()) {
                        double newScreenX = originX - touchX;
                        double newScreenY = originY - (contentDisplayHeight - touchY);
                        screenLocation = Vector.make2D(newScreenX, newScreenY);
                        referenceObject.setScreenLocation(screenLocation);
                    }
                    break;
            }
            contentDisplay.invalidate();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play_display);

        contentDisplay = findViewById(R.id.space_objects_display);
        contentDisplay.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        contentDisplay.setOnTouchListener(contentDisplayListener);

        contentDisplayHeight = 0;
        final ViewTreeObserver observer = contentDisplay.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (contentDisplayHeight == 0)
                    contentDisplayHeight = contentDisplay.getHeight();
                else contentDisplay.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        spawnMoveSwitch = findViewById(R.id.spawn_move_switch);

        screenLocation = Vector.make2D(0, 0);

        PhysicsObject physA = new PhysicsObject(100, 125,
                Vector.make2D(960, 540),
                Vector.make2D(0, 0),
                Vector.make2D(0, 0));

        referenceObject = new SpaceObject(getApplicationContext(), physA);
        referenceObject.setScreenLocation(screenLocation);
        contentDisplay.addView(referenceObject.getSpaceView());
    }
}
