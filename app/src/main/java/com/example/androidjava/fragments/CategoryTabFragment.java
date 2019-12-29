package com.example.androidjava.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.androidjava.R;

public class CategoryTabFragment extends BaseTabFragment {
    private Button btn;
    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_tab_category,container,false);
        btn=(Button) view.findViewById(R.id.btn);
        return view;
    }

    @Override
    public void init() {
        test();
    }

    private void test(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.setText("button1 is clicked");
            }
        });
    }
}
