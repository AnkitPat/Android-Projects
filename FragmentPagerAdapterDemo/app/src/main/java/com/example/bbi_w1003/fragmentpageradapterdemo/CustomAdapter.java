package com.example.bbi_w1003.fragmentpageradapterdemo;

import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Toast;

import java.io.File;

/**
 * Created by BBI-W 1003 on 7/8/2016.
 */
public class CustomAdapter extends FragmentPagerAdapter {

    int[] resources = {
            R.drawable.first,
            R.drawable.second,
            R.drawable.third,
            R.drawable.fourth,
            R.drawable.fifth
    };
    public CustomAdapter(FragmentManager fm,String filePath) {

        super(fm);
        try {
            File file = new File(filePath);
            PdfRenderer renderer = new PdfRenderer(ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY));
        } catch (Exception e)
        {
          //  Toast.makeText(,e.toString(),Toast.LENGTH_LONG).show();
    }


    }

    @Override
    public Fragment getItem(int position) {



        MyPage myPage = new MyPage();
        Bundle b = new Bundle();
        int id = resources[position];
        b.putInt("key",id);
        b.putInt("positon",position);
        b.putInt("Count",resources.length);
        myPage.setArguments(b);
        return myPage;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return "Page no."+resources[position];
    }
}
