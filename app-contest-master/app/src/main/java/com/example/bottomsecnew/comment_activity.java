package com.example.bottomsecnew;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by hyovin on 2018-09-23.
 * 댓글 창에 대한 activity
 */

public class comment_activity extends AppCompatActivity{

    private Button btn_push;
    private EditText et_comment;
    private EditText et_nickname;
    public int count=0;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_layout);

        RecyclerView.LayoutManager layoutManager;

        recyclerView = findViewById(R.id.recycle_view2);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        /*servID 가져오기*/
        final String servID = getIntent().getExtras().getString("servID");//정책의 servID 받아오기

        String queryUrl="http://www.bokjiro.go.kr/openapi/rest/gvmtWelSvc?crtiKey=uBDRiqRLBoeq9U0S3l2DfIA11tHI2iHGlBuLi722hUi0T8WZ9IUpYwxrE0VFf78meUlyC8ymW9ROB%2FDXmlokrQ%3D%3D&callTp=L&pageNum=1&numOfRows=100";
        String a="a";
        String b="b";

        List<policyInput> policyInputArrayList= new ArrayList<>();
        try {
            policyInputArrayList=new xmlhelper2().execute(queryUrl,a,b).get();  //이 방식으로 파싱실행해서 폴리시인풋 배열 얻어옴
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        final ArrayList<comment_arraylist> commentArraylist1 = new ArrayList<>();
        getCommentCount(servID);    //servID에 대한 댓글 수 가져오기

        String servID2 =servID;
        FirebaseDatabase.getInstance().getReference().child("comment").child(servID2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                commentArraylist1.clear();
                for (DataSnapshot item : dataSnapshot.getChildren()){
                    Log.v("확인", "comment데이터 접근 1 #"+item.getKey());
                    commentArraylist1.add(item.getValue(comment_arraylist.class));
                    Log.v("확인", "comment데이터 접근 2 #"+item.getKey());

                    comment_cardview_adapter comment_cardview_adapter = new comment_cardview_adapter(commentArraylist1);
                    recyclerView.setAdapter(comment_cardview_adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        /*댓글 달기 event*/
        btn_push = findViewById(R.id.btn_push);
        et_comment = findViewById(R.id.et_comment);
        et_nickname = findViewById(R.id.et_nickname);



        btn_push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_comment.getText().toString() == null){    //내용 입력안했을 때의 예외 처리
                    return;
                }

                comment_arraylist comment1 = new comment_arraylist();

                comment1.cContent = et_comment.getText().toString();
                Log.v("확인", "setText 하기 전"+et_comment.getText().toString());
                et_comment.setText("");
                Log.v("확인", "setText 하기 후"+et_comment.getText().toString());
                comment1.uid = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
                comment1.cLikeCount = 0;    //공감 수 초기화 ; 0 으로
                comment1.servid = servID;
                comment1.nickname = et_nickname.getText().toString();

                    FirebaseDatabase.getInstance().getReference().child("comment").child(servID).push().setValue(comment1); //데이터베이스에 객체 user 추가

            }
        });
    }

    /*댓글 개수 가져오는 함수*/
    private void getCommentCount(String servID) {
        FirebaseDatabase.getInstance().getReference().child("comment").orderByChild(servID).equalTo(true).addListenerForSingleValueEvent(new ValueEventListener() {
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

    /*comment 객체 가져오기*/

}
