package com.example.bottomsecnew;

/**
 * Created by hyovin on 2018-09-21.
 */
public class PolicyInfo {

    public String pCategory;
    public String pTitle;
    public String pContent;
    public String pLikeCount;
    public String pCommentCount;


    public PolicyInfo( String pCategory, String pTitle, String pContent, String pLikeCount, String pCommentCount ){

        this.pCategory = pCategory;
        this.pTitle = pTitle;
        this.pContent = pContent;
        this.pLikeCount = pLikeCount;
        this.pCommentCount = pCommentCount;
    }
}
