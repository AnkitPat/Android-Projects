package com.example.bbi_w1003.fragmentdatapassing;


import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Fragment1.OnFragmentInteractionListener, Fragment2.OnFragmentDecreaseListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.frame1) != null) {
            if (savedInstanceState != null) {
                return;
            }
        }

        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        Fragment1  f1 = new Fragment1();
        ft.add(R.id.frame1, f1);


        Fragment2 f2 = new Fragment2();
        ft.add(R.id.frame2,f2);
        ft.commit();



    }



    @Override
    public void dataIncrease(int a) {
        Fragment2 f2 = new Fragment2();
        Fragment1 f1 = new Fragment1();
        Bundle args = new Bundle();
        a = a+1;
        args.putString("key", String.valueOf(a));
        f2.setArguments(args);
        f1.setArguments(args);
        FragmentManager ff = getSupportFragmentManager();
        FragmentTransaction ft = ff.beginTransaction();

        ft.replace(R.id.frame1, f1);
        ft.replace(R.id.frame2,f2);
        ft.commit();
    }

    @Override
    public void dataDecrease(int b) {
        Fragment2 f2 = new Fragment2();
        Fragment1 f1 = new Fragment1();
        Bundle args = new Bundle();
        b = b-1;
        args.putString("key", String.valueOf(b));
        f2.setArguments(args);
        f1.setArguments(args);
        FragmentManager ff = getSupportFragmentManager();
        FragmentTransaction ft = ff.beginTransaction();

        ft.replace(R.id.frame1, f1);
        ft.replace(R.id.frame2,f2);
        ft.commit();
    }
}
