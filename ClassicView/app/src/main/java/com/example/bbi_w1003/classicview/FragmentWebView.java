package com.example.bbi_w1003.classicview;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by BBI-test on 7/26/2016.
 */
public class FragmentWebView extends Fragment {
    private Button btnPrev;
    private Button btnNext;
    private TextView txtChapter;
    private WebView webView;
    private ArrayList<ClassicDataHolder>list;
    private static int currentPage=0;
    /**
     * The Gdt.
     */
    final GestureDetector gdt = new GestureDetector(new CustomeGestureDetector());
    /**
     * The Animation.
     */
    Animation animation=null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.classic_web_fragment,container,false);
        btnPrev=(Button)v.findViewById(R.id.btnPrev);
        btnNext=(Button)v.findViewById(R.id.btnNext);
        txtChapter=(TextView)v.findViewById(R.id.txtChapter);
        webView=(WebView)v.findViewById(R.id.webView);

        list=Singleton.getInstance().getClassicDataHolderArrayList();
        showCurrentPage(list.get(currentPage).getName(),list.get(currentPage).getHtml());
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation = AnimationUtils.loadAnimation(getActivity(),R.anim.transition_right_left);
                txtChapter.setAnimation(animation);
                animation = AnimationUtils.loadAnimation(getActivity(),R.anim.transition_right_left);
                webView.setAnimation(animation);
                currentPage++;
                showCurrentPage(list.get(currentPage).getName(),list.get(currentPage).getHtml());
            }
        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation = AnimationUtils.loadAnimation(getActivity(),R.anim.transition_left_right);
                txtChapter.setAnimation(animation);
                animation = AnimationUtils.loadAnimation(getActivity(),R.anim.transition_left_right);
                webView.setAnimation(animation);
                currentPage--;
                showCurrentPage(list.get(currentPage).getName(),list.get(currentPage).getHtml());
            }
        });

        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gdt.onTouchEvent(motionEvent);
                return true;
            }
        });
        return v;
    }

    /**
     * Show current page.
     *
     * @param chapter the chapter
     * @param url     the url
     */
    public void showCurrentPage(String chapter,String url){
        txtChapter.setText(chapter);

        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            Log.d("SD card", "No SDCARD");
        } else {
            String filePath=Environment.getExternalStorageDirectory()
                    +File.separator+ "data_classic"+File.separator + url;
            File f = new File(filePath);
            if(f.exists())
            {
                webView.loadUrl("file://"+Environment.getExternalStorageDirectory()
                        +File.separator+ "data_classic"+File.separator + url);

            }
        }

        if(currentPage>0&&currentPage<list.size()-1){
            btnNext.setVisibility(View.VISIBLE);
            btnPrev.setVisibility(View.VISIBLE);
        }else if(currentPage==0){
            btnPrev.setVisibility(View.GONE);
        }else {
            btnNext.setVisibility(View.GONE);
        }
    }


    private class CustomeGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(e1 == null || e2 == null) return false;
            if(e1.getPointerCount() > 1 || e2.getPointerCount() > 1) return false;
            else {
                try {
                    if(e1.getX() - e2.getX() > 100 && Math.abs(velocityX) > 800) {
                        currentPage++;
                        animation = AnimationUtils.loadAnimation(getActivity(),R.anim.transition_right_left);
                        txtChapter.setAnimation(animation);
                        animation = AnimationUtils.loadAnimation(getActivity(),R.anim.transition_right_left);
                        webView.setAnimation(animation);
                        showCurrentPage(list.get(currentPage).getName(),list.get(currentPage).getHtml());
                        return true;
                    }
                    else if (e2.getX() - e1.getX() > 100 && Math.abs(velocityX) > 800) {

                        currentPage--;
                        animation = AnimationUtils.loadAnimation(getActivity(),R.anim.transition_left_right);
                        txtChapter.setAnimation(animation);
                        animation = AnimationUtils.loadAnimation(getActivity(),R.anim.transition_left_right);
                        webView.setAnimation(animation);
                        showCurrentPage(list.get(currentPage).getName(),list.get(currentPage).getHtml());
                        return true;
                    }
                    else if(e1.getY() - e2.getY() > 100 && Math.abs(velocityY) > 800
                            && webView.getScrollY() >= webView.getScale() * (webView.getContentHeight() - webView.getHeight())) {
                        return true;
                    }
                    else if (e2.getY() - e1.getY() > 100 && Math.abs(velocityY) > 800 ) {

                        return true;
                    }
                } catch (Exception e) {                 }
                return false;
            }
        }
    }
}
