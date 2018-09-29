package com.example.bottomsecnew;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by hyovin on 2018-09-26.
 */

public class comment_cardview_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    public static class comment_ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_comment;
        TextView tv_comment_name;
        TextView tv_comment_LikeCount;

        comment_ViewHolder(View view){
            super(view);

            tv_comment = view.findViewById(R.id.tv_comment);
            tv_comment_name = view.findViewById(R.id.tv_comment_name);
            tv_comment_LikeCount = view.findViewById(R.id.tv_comment_LikeCount);

        }
    }


    private ArrayList<comment_arraylist> commentArraylists;
    comment_cardview_adapter(ArrayList<comment_arraylist> commentArraylists){
        this.commentArraylists = commentArraylists;

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_cardview, parent, false);

        return new comment_ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);

        int Position = position;
        comment_ViewHolder cm_ViewHolder = (comment_ViewHolder) holder;

        cm_ViewHolder.tv_comment.setText(commentArraylists.get(position).getcContent());
        cm_ViewHolder.tv_comment_name.setText(commentArraylists.get(position).getNickname());
        cm_ViewHolder.tv_comment_LikeCount.setText(String.valueOf(commentArraylists.get(position).getcLikeCount()));


    }

    @Override
    public int getItemCount() {
        return commentArraylists.size();
    }
}
