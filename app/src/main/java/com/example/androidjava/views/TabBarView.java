package com.example.androidjava.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.androidjava.activitys.LoginActivity;
import com.example.androidjava.R;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

public class TabBarView extends FrameLayout {
    private Context tabBarContext;
    private View mView;
    private ImageView iconImg;
    private LinearLayout bg_main;
    private String jumpUrl;
    private int iconImgId;
    /*
    *自定义控件的构造函数
    * https://blog.csdn.net/zq2114522/article/details/53312530
     */
    public TabBarView(@NonNull Context context) {
        this(context, null);
    }
    public TabBarView(Context context, AttributeSet attrs){
        this(context, attrs, 0);
    }
    public TabBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        tabBarContext = context;
        /*
        * LayoutInflater的作用类似于 findViewById(),不同点是LayoutInflater是用来找layout文件夹下的xml布局文件，并且实例化！而 findViewById()是找具体某一个xml下的具体 widget控件(如:Button,TextView等)。
        * https://blog.csdn.net/notonlyforshe/article/details/7954149
        * */
        LayoutInflater inflater = (LayoutInflater) tabBarContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.view_tabbar, this, true);
        iconImg = (ImageView) mView.findViewById(R.id.view_tabBer_leftImage);
        bg_main = (LinearLayout) mView.findViewById(R.id.bg_main);

        @SuppressLint("Recycle") TypedArray a = tabBarContext.obtainStyledAttributes(attrs, R.styleable.TabBarView);
        setIconImgId(a.getResourceId(R.styleable.TabBarView_reference, 10000));
        setBg_main(a.getDrawable(R.styleable.TabBarView_bg_reference));

        /*
        *点击事件
         */
        initClick();
    }

    /*
    * 设置左侧图标
    * */
    public void setIconImgId(int iconImgId) {
        if (iconImgId != 10000) {
            this.iconImgId = iconImgId;
            iconImg.setImageResource(iconImgId);
        }
    }

    /*
     *设置背景
     */
    public void setBg_main(Drawable background){
        if(background !=null){
            bg_main.setBackground(background);
        }else {
            /* mView.setBackgroundColor(Color.parseColor("#FFFFFF"));*/
            /*
             *动态设置背景
             * */
            Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.bg_shadow_tabbar, null);
            bg_main.setBackground(drawable);
        }

    }


    private void initClick() {
        iconImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                /*new AlertDialog.Builder(tabBarContext)
                        .setTitle("标题")
                        .setMessage("简单的消息提示框")
                        .setPositiveButton("确定", null)
                        .show();*/
                Intent intent=new Intent(tabBarContext, LoginActivity.class);
                tabBarContext.startActivity(intent);
            }
        });
    }
}
