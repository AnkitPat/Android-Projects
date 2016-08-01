package com.example.bbi_w1003.fragmentpageradapterdemo;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    public  int dotsCount=5;
    ImageView[] dots;
    File f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AssetManager assetManager = getAssets();

        if(new File(getFilesDir()+"/sample.pdf")==null) {
            InputStream in = null;
            OutputStream out = null;
            File file = new File(getFilesDir(), "sample.pdf");
            try {
                in = assetManager.open("sample.pdf");
                out = openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);

                byte[] buffer = new byte[1024];
                int read;
                while ((read = in.read(buffer)) != -1) {
                    out.write(buffer, 0, read);
                }

                in.close();
                in = null;
                out.flush();
                out.close();
                out = null;
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
        else {
           f = new File(getFilesDir()+"/sample.pdf");
            Toast.makeText(MainActivity.this,f.getPath()+" ",Toast.LENGTH_LONG).show();
        }
        ViewPager viewPager = (ViewPager)findViewById(R.id.pager);

        linearLayout = (LinearLayout)findViewById(R.id.viewPagerCountDots);

        CustomAdapter customAdapter = new CustomAdapter(getSupportFragmentManager(),f.getPath());
        viewPager.setAdapter(customAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                drawPageSelectionIndicators(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public void drawPageSelectionIndicators(int mPosition){
        if(linearLayout!=null) {
            linearLayout.removeAllViews();
        }
        linearLayout=(LinearLayout)findViewById(R.id.viewPagerCountDots);
        dots = new ImageView[dotsCount];
        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(getApplicationContext());
            if(i==mPosition)
                dots[i].setImageDrawable(getResources().getDrawable(R.drawable.item_selected));
            else
                dots[i].setImageDrawable(getResources().getDrawable(R.drawable.item_unselected));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);
            linearLayout.addView(dots[i], params);
        }
    }
}
