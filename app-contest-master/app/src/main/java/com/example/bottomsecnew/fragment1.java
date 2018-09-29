package com.example.bottomsecnew;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class fragment1 extends Fragment {

    int count=0;
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

        String queryUrl="http://www.bokjiro.go.kr/openapi/rest/gvmtWelSvc?crtiKey=uBDRiqRLBoeq9U0S3l2DfIA11tHI2iHGlBuLi722hUi0T8WZ9IUpYwxrE0VFf78meUlyC8ymW9ROB%2FDXmlokrQ%3D%3D&callTp=L&pageNum=1&numOfRows=100";
        String a="a";
        String b="b";
        //a,b 스트링은 그냥 세개 스트링을 넣어줘서 선언한 아무의미 없는 애

        List<policyInput> policyInputArrayList= new ArrayList<>();
        try {
            policyInputArrayList=new xmlhelper2().execute(queryUrl,a,b).get();   //이 방식으로 파싱실행해서 폴리시인풋 배열 얻어옴
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ArrayList<PolicyInfo> policyInfoArrayList = new ArrayList<>();
        for(int i=0;i<policyInputArrayList.size();i++){

            getCommentCount(policyInputArrayList.get(i).getServId());

            policyInfoArrayList.add(new PolicyInfo("분류",policyInputArrayList.get(i).getServNm(),policyInputArrayList.get(i).getServDgst(),
                 "0", String.valueOf(count)));
            //policyInputArrayList.get(i).getServNm() 이런건 그 배열 객체의 변수 값 받기 코드
            MyAdapter myAdapter = new MyAdapter(policyInfoArrayList);
            RecyclerView1.setAdapter(myAdapter);
        }




        /*ArrayList<policyInput> policyInputArrayList = new ArrayList<>();
        policyInputArrayList.add(new policyInput("분류", "정책 이름", "정책 설명 간단히", "30", "25"));
        MyAdapter myAdapter = new MyAdapter(policyInputArrayList);
        RecyclerView1.setAdapter(myAdapter);

*/
        //comment 처리

        return view;
    }

    private void getCommentCount(String servID) {
        FirebaseDatabase.getInstance().getReference().child("comment").orderByChild(servID+"/").equalTo(true).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    count++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



}