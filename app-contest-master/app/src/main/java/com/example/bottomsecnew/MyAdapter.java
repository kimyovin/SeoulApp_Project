package com.example.bottomsecnew;

/**
 * Created by hyovin on 2018-09-21.
 */

        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.TextView;

        import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView text_category;
        TextView text_title;
        TextView text_content;
        TextView text_like_count;
        TextView text_comment_count;


        MyViewHolder(View view){
            super(view);

            text_category = view.findViewById(R.id.tv_policycategory);
            text_title = view.findViewById(R.id.tv_policyname);
            text_content = view.findViewById(R.id.tv_policycontent);
            text_like_count = view.findViewById(R.id.tv_likecount);
            text_comment_count = view.findViewById(R.id.tv_comentcount);

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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyViewHolder myViewHolder = (MyViewHolder) holder;



        myViewHolder.text_category.setText(policyInfoArrayList.get(position).pCategory);
        myViewHolder.text_title.setText(policyInfoArrayList.get(position).pTitle);
        myViewHolder.text_content.setText(policyInfoArrayList.get(position).pContent);
        myViewHolder.text_like_count.setText(policyInfoArrayList.get(position).pLikeCount);
        myViewHolder.text_comment_count.setText(policyInfoArrayList.get(position).pCommentCount);

    }

    @Override
    public int getItemCount() {
        return policyInfoArrayList.size();
    }



}
