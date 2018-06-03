package com.gscape.sdp.galacticescape;

import android.content.Intent;
import android.os.Handler;
import android.sax.StartElementListener;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.gscape.sdp.galacticescape.Display.ActualGame.GamePlayScreen;

public class Introduction_togame extends AppCompatActivity {

    public Button skip_intro;

    private ViewPager viewPager;
    private RelativeLayout page1;
    private RelativeLayout page2;
    private  RelativeLayout page3;
    private  RelativeLayout page4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_introduction_togame);

        viewPager = findViewById(R.id.viewpager_layout);
        viewPager.setAdapter(new MainPageAdapter());
        viewPager.addOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position)
            {
                //Toast.makeText(Introduction_togame.this, "page " + (position + 1), Toast.LENGTH_SHORT).show();





            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });


        skip_intro = findViewById(R.id.skip_intro_bt);

        skip_intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent skipped = new Intent(Introduction_togame.this, User_name_selection.class );
                startActivity(skipped);
            }
        });

    }


    class MainPageAdapter extends PagerAdapter
    {

        @Override
        public int getCount()
        {
            return 4;
        }

        @Override
        public Object instantiateItem(ViewGroup collection, int position)
        {
            View page = null;
            switch (position)
            {
                case 0:
                    if (page1 == null)
                    {
                        page1 = (RelativeLayout) LayoutInflater.from(Introduction_togame.this).inflate(R.layout.page1_intro, null);
                    }
                    page = page1;
                    break;
                case 1:
                    if (page2 == null)
                    {
                        page2 = (RelativeLayout) LayoutInflater.from(Introduction_togame.this).inflate(R.layout.page2_intro, null);
                    }
                    page = page2;
                    break;
                case 2:
                    if (page3 == null)
                    {
                        page3 = (RelativeLayout) LayoutInflater.from(Introduction_togame.this).inflate(R.layout.page3_intro, null);

                    }
                    page = page3;
                    break;
                default:
                    if (page4 == null)
                    {
                        page4 = (RelativeLayout) LayoutInflater.from(Introduction_togame.this).inflate(R.layout.page4_intro, null);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                                Intent i=new Intent(Introduction_togame.this, User_name_selection.class);
                                startActivity(i);
                            }
                        }, 9000);

                    }
                    page = page4;
                    break;
            }

            ((ViewPager) collection).addView(page, 0);

            return page;
        }

        @Override
        public boolean isViewFromObject(View view, Object object)
        {
            return view == object;
        }

        @Override
        public void destroyItem(View collection, int position, Object view)
        {
            ((ViewPager) collection).removeView((View) view);
        }



    }





}




