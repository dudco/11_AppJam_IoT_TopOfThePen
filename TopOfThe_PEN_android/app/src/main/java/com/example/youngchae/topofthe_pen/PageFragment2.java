package com.example.youngchae.topofthe_pen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.lzyzsd.circleprogress.CircleProgress;

/**
 * Created by youngchae on 2016-07-23.
 */
public class PageFragment2 extends Fragment{
    public static CircleProgress circleProgress;
    public PageFragment2() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.flagment2,container,false);
        circleProgress = (CircleProgress) view.findViewById(R.id.circle_progress);

        return view;
    }
}
