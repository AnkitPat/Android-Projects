package com.example.bbi_w1003.classicview;



import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity{

    /**
     * The constant context.
     */
    public static Context context;
    public String jsonString=null;
    private Button showButton;
    /**
     * The In.
     */
    InputStream in=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        try {
            in = getAssets().open("jsonSample.json");
        } catch (IOException e) {
            Log.d("Movies",e.getMessage()+" ");
        }


        Singleton.getInstance();

        showButton = (Button)findViewById(R.id.btn);
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(android.R.id.content,new FragmentWebView());
                ft.commit();
            }
        });


    }

    /**
     * this methd is copying data from assets to internal storage.
     */
    public  void copyFile()
    {
        try {
            File f = new File(new File(Environment.getExternalStorageDirectory(),
                    "ClassicView"), "jsonSample.json");
            FileOutputStream out = new FileOutputStream(f);
            InputStream is = MainActivity.context.getAssets().open("jsonSample.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            out.write(buffer);
            is.close();
        }
        catch (Exception e)
        {
            Log.d("Project",e.toString());
        }
    }

    public  class GetJsonData extends AsyncTask<Void,Void,Boolean>{
        ProgressDialog dialog=new ProgressDialog(MainActivity.context);
        @Override
        protected Boolean doInBackground(Void... voids) {

            File file = new File("/storage/emulated/0/ClassicView/jsonSample.json");
            if(!file.exists())
            {
                File file1 = new File("/storage/emulated/0/","ClassicView");
                file1.mkdir();
                copyFile();
            }
            jsonString =  loadFromFile(file);
            parsing(jsonString);
            return true;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Coping....");
            dialog.show();
        }
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean)
            {
                dialog.dismiss();
            }
        }
    }

    public String loadFromFile(File file)
    {
        String json=null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStream is = fileInputStream;
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            Log.d("Project",json.toString());
        }
        catch (Exception e)
        {
            Log.d("Error",e.getMessage());
        }
        return json;
    }

    /**
     * Parsing.
     *
     * @param s the s
     */
    public void parsing(String s)
    {
        try {
            ArrayList<ClassicDataHolder> classicDataHolderArrayList = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray("Classic");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                String mName = jsonObject1.getString("name");
                String mHtml = jsonObject1.getString("html");
                ClassicDataHolder ClassicDataHolder = new ClassicDataHolder(mName, mHtml);

                classicDataHolderArrayList.add(ClassicDataHolder);

            }
            Singleton.setClassicDataHolderArrayList(classicDataHolderArrayList);
        } catch (Exception e) {
            Log.d("Error", e.getMessage()+" " +
                    "");
        }
    }

    public void callAsyncTask()
    {
        new GetJsonData().execute();
    }


}
