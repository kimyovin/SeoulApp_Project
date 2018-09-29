package com.example.bottomsecnew;

/**
 * Created by hyovin on 2018-09-27.
 */

public class comment_arraylist {

    public String servid; //정책의 id
    public String uid;    //작성자의 uid
    public String cContent; //댓글 내용
    public int cLikeCount;   //공감수
    public String nickname;

    public comment_arraylist(String servid, String uid, String cContent, int cLikeCount, String nickname) {
        this.servid = servid;
        this.uid = uid;
        this.cContent = cContent;
        this.cLikeCount = cLikeCount;
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public comment_arraylist() {
    }

    public void setServid(String servid) {
        this.servid = servid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setcContent(String cContent) {
        this.cContent = cContent;
    }

    public void setcLikeCount(int cLikeCount) {
        this.cLikeCount = cLikeCount;
    }

    public String getServid() {
        return servid;
    }

    public String getUid() {
        return uid;
    }

    public String getcContent() {
        return cContent;
    }

    public int getcLikeCount() {
        return cLikeCount;
    }
}
