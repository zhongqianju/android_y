package com.example.androidjava.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidjava.R;

/**
 * 我的
 */

public class MineTabFragment extends BaseTabFragment {

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_tab_mine,container,false);
        return view;
    }

    @Override
    public void init() {

    }
}