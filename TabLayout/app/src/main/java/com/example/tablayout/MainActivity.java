package com.example.tablayout;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    Halaman adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout=findViewById(R.id.tab);
        viewPager=findViewById(R.id.viewpager);

        adapter=new Halaman(getApplicationContext(),getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
class Halaman extends FragmentStatePagerAdapter{
    Context context;
    int jumlah_tab;
    Halaman(Context context, FragmentManager fm,int jumlah_tab){
        super(fm);
        this.context=context;
        this.jumlah_tab=jumlah_tab;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case  0:return new Fragment1();
            case  1:return new Fragment2();
            case  2:return new Fragment3();
        }
        return null;
    }

    @Override
    public int getCount() {
        return jumlah_tab;
    }
}