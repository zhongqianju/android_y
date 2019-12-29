package com.example.androidjava.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.androidjava.activitys.LoginActivity;
import com.example.androidjava.R;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

public class InputView extends FrameLayout {
    private Context tabBarContext;
    private View mView;
    private ImageView leftImg;
    private ImageView rightImg;
    private ImageView passImg;
    private EditText input_editText;
    private String input_editText_hint;
    private LinearLayout bg_main;
    private String jumpUrl;
    private int iconImgId;
    private int flag=0;//输入后的删除图标是否已显示标志
    private int flag_leftimg=0;//左侧图标标识
    private int i;
    /*
     *自定义控件的构造函数
     * https://blog.csdn.net/zq2114522/article/details/53312530
     */
    public InputView(@NonNull Context context) {
        this(context, null);
    }
    public InputView(Context context, AttributeSet attrs){
        this(context, attrs, 0);
    }
    public InputView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        mView = inflater.inflate(R.layout.view_input, this, true);
        leftImg = (ImageView)mView.findViewById(R.id.input_left_image);
        rightImg = (ImageView)mView.findViewById(R.id.input_right_image);
        passImg = (ImageView)mView.findViewById(R.id.input_left_pass);
        input_editText = (EditText)mView.findViewById(R.id.input_editText);

        @SuppressLint("Recycle") TypedArray a = tabBarContext.obtainStyledAttributes(attrs, R.styleable.InputView);
        setInputLeftImgId(a.getResourceId(R.styleable.InputView_input_left_reference, 10000));
        setInputEditTextHint(a.getNonResourceString(R.styleable.InputView_input_hint));
        setLeftImgBtn(a.getInt(R.styleable.InputView_input_left_btn,0));


        /*
         *点击事件
         */
        initClick();
    }
    /*
    *input 左侧图标
     */
    public void setInputLeftImgId(int iconImgId){
        if(iconImgId!=10000){
            this.iconImgId= iconImgId;
            leftImg.setImageResource(iconImgId);
        }
    }
    public int getLeftImgId(){
        return iconImgId;
    }

    /*
    * editText 的hint
     */
    public void setInputEditTextHint(String textHint){
        if (textHint!=null){
            this.input_editText_hint = textHint;
            SpannableString string = new SpannableString(textHint);//这里输入自己想要的提示文字
            // 新建一个属性对象,设置文字的大小;
            AbsoluteSizeSpan ass = new AbsoluteSizeSpan(16,true);
            // 附加属性到文本
            string.setSpan(ass, 0, string.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            // 设置hint
            input_editText.setHint(new SpannedString(string));
        }

    }
    public String getInputEditTextHint(){
        return input_editText_hint;
    }

    /*
     * 输入框中内容
     */
    public String getInputEditText(){
        return input_editText.getText().toString();
    }

    /*
    * 左侧图标点击事件
    * */
    public void setLeftImgBtn(int flag){
        if(flag==1){
            flag_leftimg = 1;
            leftImg.setVisibility(View.GONE);
            passImg.setImageResource(R.drawable.ic_pass_gone);
            input_editText.setInputType(InputType. TYPE_TEXT_VARIATION_PASSWORD);
            input_editText.setInputType(129);//设置为隐藏密码
            //1是左侧为密码的显示与隐藏
            passImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(input_editText.getInputType() == 128){//如果现在是显示密码模式
                        input_editText.setInputType(129);//设置为隐藏密码
                        passImg.setImageResource(R.drawable.ic_pass_gone);
                    }else {
                        input_editText.setInputType(128);//设置为显示密码
                        passImg.setImageResource(R.drawable.ic_pass_visible);
                    }
                    input_editText.setSelection(input_editText.getText().length());//设置光标的位置到末尾
                }
            });
        }
        else{
            passImg.setVisibility(View.GONE);
            leftImg.setVisibility(View.VISIBLE);
        }
    }
    /********/
    private TextWatcher textWatche = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String text = input_editText.getText().toString();
            if(text.length()>0 && flag==0){
                flag = 1;
                rightImg.setVisibility(View.VISIBLE);
                if(flag_leftimg==1){
                    passImg.setVisibility(View.VISIBLE);
                }
            }else if(text.length()==0){
                flag = 0;
                rightImg.setVisibility(View.GONE);
                if(flag_leftimg==1){
                    passImg.setVisibility(View.GONE);
                }
            }
        }
    };
    private void initClick() {
        //显示删除图片
        input_editText.addTextChangedListener((TextWatcher) textWatche);

        //删除输入内容
        rightImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input_editText.setText("");
            }
        });
    }
}
