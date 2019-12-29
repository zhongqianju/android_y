package com.example.androidjava.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.androidjava.R;
import com.example.androidjava.fragments.HomeTabFragment;
import com.example.androidjava.fragments.MineTabFragment;
import com.example.androidjava.fragments.CategoryTabFragment;

import com.heima.tabview.library.TabView;
import com.heima.tabview.library.TabViewChild;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity{
    //定义对象


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test();
        init();
    }

    //测试代码
    private void test(){
        Intent intent=new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void init(){
        List<TabViewChild> tabViewChildList=new ArrayList<>();
        TabViewChild tabViewChild01=new TabViewChild(R.drawable.ic_home,R.drawable.ic_home,"首页",new HomeTabFragment());
        TabViewChild tabViewChild02=new TabViewChild(R.drawable.ic_home,R.drawable.ic_home,"分类",new CategoryTabFragment());
        TabViewChild tabViewChild03=new TabViewChild(R.drawable.ic_home,R.drawable.ic_home,"好友",new HomeTabFragment());
        TabViewChild tabViewChild04=new TabViewChild(R.drawable.ic_home,R.drawable.ic_home,"社区",new HomeTabFragment());
        TabViewChild tabViewChild05=new TabViewChild(R.drawable.ic_home,R.drawable.ic_home,"我的",new MineTabFragment());
        tabViewChildList.add(tabViewChild01);
        tabViewChildList.add(tabViewChild02);
        tabViewChildList.add(tabViewChild03);
        tabViewChildList.add(tabViewChild04);
        tabViewChildList.add(tabViewChild05);


        TabView tabView= findViewById(R.id.tabView);
        tabView.setTabViewChild(tabViewChildList,getSupportFragmentManager());
        /*tabView.setOnTabChildClickListener(new TabView.OnTabChildClickListener() {
            @Override
            public void onTabChildClick(int  position, ImageView currentImageIcon, TextView currentTextView) {
                Toast.makeText(getApplicationContext(),"position:"+position,Toast.LENGTH_SHORT).show();
            }
        });*/
    }


}
