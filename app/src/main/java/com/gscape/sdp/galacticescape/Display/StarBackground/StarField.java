package com.gscape.sdp.galacticescape.Display.StarBackground;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.gscape.sdp.galacticescape.Display.ActualGame.ScreenValues;
import com.gscape.sdp.galacticescape.Engine.Physics.Vector;
import com.gscape.sdp.galacticescape.R;

public class StarField {

    private final ImageView[][] stars;
    private final TableLayout tableLayout;
    private final TableRow[] tableRow;
    private final ScreenValues screenValues;

    public StarField(Context context, ScreenValues screenValues) {
        this.screenValues = screenValues;
        tableLayout = new TableLayout(context);
        tableRow = new TableRow[4];
        for (int i = 0; i  < 4; i++) {
            tableRow[i] = new TableRow(context);
            tableLayout.addView(tableRow[i]);
        }

        stars = new ImageView[4][4];

        int halfScreen = (int)(screenValues.getScreenSize().getX() / 2);

        Bitmap starField1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.star_field_1);
        Bitmap starField1Rescaled = Bitmap.createScaledBitmap(starField1, halfScreen, halfScreen, true);

        Bitmap starField2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.star_field_1);
        Bitmap starField2Rescaled = Bitmap.createScaledBitmap(starField2, halfScreen, halfScreen, true);

        Bitmap starField3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.star_field_1);
        Bitmap starField3Rescaled = Bitmap.createScaledBitmap(starField3, halfScreen, halfScreen, true);

        Bitmap starField4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.star_field_1);
        Bitmap starField4Rescaled = Bitmap.createScaledBitmap(starField4, halfScreen, halfScreen, true);

        for (int i = 0; i < 4; i++) {
            for (int j =0; j < 4; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        stars[i][j] = new ImageView(context);
                        stars[i][j].setImageBitmap(starField1Rescaled);
                    } else {
                        stars[i][j] = new ImageView(context);
                        stars[i][j].setImageBitmap(starField2Rescaled);
                    }
                } else {
                    if (j % 2 == 0) {
                        stars[i][j] = new ImageView(context);
                        stars[i][j].setImageBitmap(starField3Rescaled);
                    } else {
                        stars[i][j] = new ImageView(context);
                        stars[i][j].setImageBitmap(starField4Rescaled);
                    }
                }
                tableRow[i].addView(stars[i][j]);
            }
        }

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layoutParams.leftMargin = Integer.MIN_VALUE;
        layoutParams.bottomMargin = Integer.MIN_VALUE;
        Vector screenOffset = screenValues.getScreenLocation().add(screenValues.getScreenSize());
        layoutParams.topMargin = (int)(screenOffset.getY() % (halfScreen * 2));
        layoutParams.rightMargin = (int)(screenOffset.getX() % (halfScreen * 2));
        tableLayout.setLayoutParams(layoutParams);
    }

    public TableLayout getTableLayout() {
        return tableLayout;
    }

    public void updateBackground () {
        final RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)tableLayout.getLayoutParams();
        Vector screenOffset = screenValues.getScreenLocation().add(screenValues.getScreenSize());
        double imageViewSize = screenValues.getScreenSize().getX();
        layoutParams.topMargin = (int)(screenOffset.getY() % imageViewSize);
        layoutParams.rightMargin = (int)(screenOffset.getX() % imageViewSize);
        tableLayout.post(new Runnable() {
            @Override
            public void run() {
                tableLayout.setLayoutParams(layoutParams);
            }
        });
    }
}
