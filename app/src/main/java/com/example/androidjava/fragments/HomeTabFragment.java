package com.example.androidjava.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.androidjava.R;
import com.example.androidjava.views.TabBarView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 首页
 */

public class HomeTabFragment extends BaseTabFragment {
    private TabBarView tabBarView;
    private ImageView imageView;
    private Bitmap bitmap = null;


    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_tab_home,container,false);
        imageView = (ImageView)view.findViewById(R.id.net_image_test);
        tabBarView = (TabBarView) view.findViewById(R.id.view_tabbar);
        return view;
    }

    @Override
    public void init() {
        /*
        * 在Android4.0以后，会发现，只要是写在主线程（就是Activity）中的HTTP请求，运行时都会报错，
        * 这是因为Android在4.0以后为了防止应用的ANR（Aplication Not Response）异常，
        * Android这个设计是为了防止网络请求时间过长而导致界面假死的情况发生。
        * */
        new Thread(new Runnable(){
            @Override
            public void run() {
                bitmap = getImageBitmap("http://120.78.220.194/test/test.png");
                //imageView.setImageResource(R.drawable.ic_home);
                //imageView.setImageBitmap(bitmap);
            }
        }).start();
            imageView.setImageBitmap(bitmap);
        tabBarView.setIconImgId(R.drawable.test);
    }

    private Bitmap getImageBitmap(String url) {
        URL imgUrl = null;
        Bitmap bitmap = null;
        try {
            imgUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) imgUrl.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}