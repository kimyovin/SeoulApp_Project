package com.example.bottomsecnew;

/**
 * Created by hyovin on 2018-09-21.
 */

        import android.content.Context;
        import android.content.Intent;
        import android.support.annotation.NonNull;
        import android.support.v4.content.ContextCompat;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.concurrent.ExecutionException;


public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    static Context context;
    //공공openAPI 인증키
    private String key="uBDRiqRLBoeq9U0S3l2DfIA11tHI2iHGlBuLi722hUi0T8WZ9IUpYwxrE0VFf78meUlyC8ymW9ROB%2FDXmlokrQ%3D%3D";


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView text_category;
        TextView text_title;
        TextView text_content;
        TextView text_like_count;
        TextView text_comment_count;
        ImageView iv_comment;
        ImageView like_button;
        ImageView scrap_button;


        MyViewHolder(View view){
            super(view);


            context = view.getContext();
            iv_comment = view.findViewById(R.id.iv_comment);
            text_category = view.findViewById(R.id.tv_policycategory);
            text_title = view.findViewById(R.id.tv_policyname);
            text_content = view.findViewById(R.id.tv_policycontent);
            text_like_count = view.findViewById(R.id.tv_likecount);
            text_comment_count = view.findViewById(R.id.tv_comentcount);
            like_button = view.findViewById(R.id.ib_likebutton);
            scrap_button = view.findViewById(R.id.ib_scrapbutton);

        }
    }

    private ArrayList<PolicyInfo> policyInfoArrayList;
    MyAdapter(ArrayList<PolicyInfo> policyInfoArrayList){
        this.policyInfoArrayList = policyInfoArrayList;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        int Position = position;
        final MyViewHolder myViewHolder = (MyViewHolder) holder;

        myViewHolder.text_category.setText(policyInfoArrayList.get(position).pCategory);
        myViewHolder.text_title.setText(policyInfoArrayList.get(position).pTitle);
        myViewHolder.text_content.setText(policyInfoArrayList.get(position).pContent);
        myViewHolder.text_like_count.setText(policyInfoArrayList.get(position).pLikeCount);
        myViewHolder.text_comment_count.setText(policyInfoArrayList.get(position).pCommentCount);

        /*comment action*/


        myViewHolder.iv_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String address = getdetailnum(position);
                Intent intent = new Intent(context, comment_activity.class);
                intent.putExtra("servID", address);
                intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                context.startActivity(intent);

            }

        });

        myViewHolder.like_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                v.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.like_button_on));
                String selected=String.valueOf(Integer.parseInt(policyInfoArrayList.get(position).pLikeCount)+1);
                ((MyViewHolder) myViewHolder).text_like_count.setText(selected);
            }
        });


        myViewHolder.scrap_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                v.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.scrap_star));
            }
        });
    }



    @Override
    public int getItemCount() {
        return policyInfoArrayList.size();
    }

    public String getdetailnum(int position) {
        String queryUrl = "http://www.bokjiro.go.kr/openapi/rest/gvmtWelSvc?crtiKey=uBDRiqRLBoeq9U0S3l2DfIA11tHI2iHGlBuLi722hUi0T8WZ9IUpYwxrE0VFf78meUlyC8ymW9ROB%2FDXmlokrQ%3D%3D&callTp=L&pageNum=1&numOfRows=500";
        String a = "a";
        String b = "b";

        List<policyInput> policyInputs = new ArrayList<policyInput>();
        try {
            policyInputs = new xmlhelper2().execute(queryUrl, a, b).get();   //이 방식으로 파싱실행해서 폴리시인풋 배열 얻어옴
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        String address=policyInputs.get(position).getServId();
        return address;
    }

}
