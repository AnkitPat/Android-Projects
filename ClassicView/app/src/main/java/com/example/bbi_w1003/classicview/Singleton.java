package com.example.bbi_w1003.classicview;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by BBI-W 1003 on 7/26/2016.
 */
public class Singleton {

    private static Singleton mInstance = null;
    private static Context ctx;

    /**
     * Gets ctx.
     *
     * @return the ctx
     */
    public static Context getCtx() {
        return ctx;
    }

    /**
     * Sets ctx.
     *
     * @param ctx the ctx
     */
    public static void setCtx(Context ctx) {
        Singleton.ctx = ctx;
    }

    private static ArrayList<ClassicDataHolder> classicDataHolderArrayList =new ArrayList<>();

    private Singleton(){
            MainActivity mainActivity = new MainActivity();
            mainActivity.callAsyncTask();
    }

    /**
     * Instantiates a new Singleton.
     *
     * @param classicDataHolderArrayList the classic array list
     */
    public Singleton(ArrayList<ClassicDataHolder> classicDataHolderArrayList) {
        this.classicDataHolderArrayList = classicDataHolderArrayList;
    }

    /**
     * Gets classic array list.
     *
     * @return the classic array list
     */
    public ArrayList<ClassicDataHolder> getClassicDataHolderArrayList() {
        return classicDataHolderArrayList;
    }

    /**
     * Sets classic array list.
     *
     *
     */
    public static void setClassicDataHolderArrayList(ArrayList<ClassicDataHolder> classicDataHolderArrayList1) {
        classicDataHolderArrayList = classicDataHolderArrayList1;
    }

    /**
     * Get instance singleton.
     *
     * @return the singleton
     */
    public static Singleton getInstance(){
        if(mInstance == null)
        {

            mInstance = new Singleton();

        }
        return mInstance;
    }
}
