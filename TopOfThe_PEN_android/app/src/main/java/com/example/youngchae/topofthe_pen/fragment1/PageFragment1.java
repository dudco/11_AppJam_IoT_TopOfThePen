package com.example.youngchae.topofthe_pen.fragment1;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.youngchae.topofthe_pen.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youngchae on 2016-07-23.
 */
public class PageFragment1 extends Fragment {
    private static RecyclerView recyclerView;
    public static List<RecyclerItem> items = new ArrayList<>();
    public static RecyclerAdapter adapter;
    CutomDialog mCustomDialog;
    View view;
    public PageFragment1() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.flagment1, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        items.add(new RecyclerItem("국어","능률 이찬승",90,100,90));
        items.add(new RecyclerItem("수학","신사고 심훈",0,100,00));
        items.add(new RecyclerItem("생명과학","천제 이경실",0,100,0));
        adapter = new RecyclerAdapter(items);
        final int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400.0f, view.getResources().getDisplayMetrics());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int totalWidth = parent.getWidth();
                int maxCardWidth = px;
                int sidePadding = (totalWidth - maxCardWidth)/2;
                sidePadding = Math.max(0, sidePadding);
                outRect.set(sidePadding, 0, sidePadding,10);
            }
        });
        recyclerView.setAdapter(adapter);

        view.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCustomDialog = new CutomDialog(view.getContext(), leftListener,RightListener );
                mCustomDialog.show();
            }
        });
        return view;
    }

    private View.OnClickListener leftListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "취소",Toast.LENGTH_SHORT).show();
            mCustomDialog.dismiss();
        }
    };
    private View.OnClickListener RightListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//            Toast.makeText(view.getContext(), "확인",Toast.LENGTH_SHORT).show();
//            Toast.makeText(view.getContext(),
//                    "Subject :"+mCustomDialog.getSubject()+"\nBook :"+mCustomDialog.getBook()+"\nTime :"+mCustomDialog.getTime(),
//                    Toast.LENGTH_SHORT).show();
            if(mCustomDialog.getSubject()!=null&&mCustomDialog.getBook()!=null)
                items.add(new RecyclerItem(mCustomDialog.getSubject(), mCustomDialog.getBook(), 0,Integer.valueOf(mCustomDialog.getTime()),0));

            recyclerView.setAdapter(adapter);
            mCustomDialog.dismiss();
        }
    };
}
