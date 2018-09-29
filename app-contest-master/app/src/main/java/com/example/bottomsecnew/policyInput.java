package com.example.bottomsecnew;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by 또리또리 on 2018-09-22.
 */

public class policyInput {   //정책에 들어가는 내용들 그 요청하면 xml에 나오는 태그들
    //태그들이랑 이름 똑같이 만들어서 헷갈리지 않을거예요!
    private String inqNum;
    private String jurMnofNm;
    private String jurOrgNm;
    private String servDgst;
    private String servDtlLink;
    private String servId;
    private String servNm;
    private String svcfrstRegTs;


    public String getInqNum() {
        return inqNum;
    }    //변수에 있는 내용 가져오기

    public void setInqNum(String inqNum) {
        this.inqNum = inqNum;
    }  //변수에 있는 내용 넣기

    public String getJurMnofNm() {
        return jurMnofNm;
    }

    public void setJurMnofNm(String jurMnofNm) {
        this.jurMnofNm = jurMnofNm;
    }

    public String getJurOrgNm() {
        return jurOrgNm;
    }

    public void setJurOrgNm(String jurOrgNm) {
        this.jurOrgNm = jurOrgNm;
    }

    public String getServDgst() {
        return servDgst;
    }

    public void setServDgst(String servDgst) {
        this.servDgst = servDgst;
    }

    public String getServDtlLink() {
        return servDtlLink;
    }

    public void setServDtlLink(String servDtlLink) {
        this.servDtlLink = servDtlLink;
    }

    public String getServId() {
        return servId;
    }

    public void setServId(String servId) {
        this.servId = servId;
    }

    public String getServNm() {
        return servNm;
    }

    public void setServNm(String servNm) {
        this.servNm = servNm;
    }

    public String getSvcfrstRegTs() {
        return svcfrstRegTs;
    }

    public void setSvcfrstRegTs(String svcfrstRegTs) {
        this.svcfrstRegTs = svcfrstRegTs;
    }

    public  String toString(){
        return ("복지이름: " + servNm + "복지 상세정보: " + servDgst + "복지 등록일:" + svcfrstRegTs + "복지 링크: " + servDtlLink);
    }
}