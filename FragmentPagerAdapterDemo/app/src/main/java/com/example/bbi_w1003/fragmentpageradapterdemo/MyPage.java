package com.example.bbi_w1003.fragmentpageradapterdemo;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by BBI-W 1003 on 7/8/2016.
 */
public class MyPage extends Fragment {

    int currentValue, position, page_count;
    byte[] bytes;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        Bundle b = getArguments();
         bytes = b.getByteArray("image");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View itemView = inflater.inflate(R.layout.pager_item, container, false);

        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);


        return itemView;
    }
}
