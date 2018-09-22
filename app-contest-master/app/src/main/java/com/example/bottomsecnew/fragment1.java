package com.example.bottomsecnew;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class fragment1 extends Fragment {

    public static fragment1 newInstance(){
        fragment1 fragment = new fragment1();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        android.support.v7.widget.RecyclerView RecyclerView1;
        RecyclerView.LayoutManager LayoutManager1;

        View view = inflater.inflate(R.layout.fragment1,container,false);
        LayoutManager1 = new LinearLayoutManager(view.getContext());
        RecyclerView1 = view.findViewById(R.id.recycle_view1);
        RecyclerView1.setHasFixedSize(true);
        RecyclerView1.setLayoutManager(LayoutManager1);

        ArrayList<PolicyInfo> policyInfoArrayList = new ArrayList<>();
        policyInfoArrayList.add(new PolicyInfo("분류", "정책 이름", "정책 설명 간단히", "30", "25"));
        MyAdapter myAdapter = new MyAdapter(policyInfoArrayList);
        RecyclerView1.setAdapter(myAdapter);
        return view;
    }
}