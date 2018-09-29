package com.example.bottomsecnew;


import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 또리또리 on 2018-09-26.
 */

@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
public class xmlhelper2 extends AsyncTask<String, Void, List<policyInput>> {

    List<policyInput> policyInputs;

    policyInput policyInput;
    String text;

    public xmlhelper2() {
        policyInputs = new ArrayList<policyInput>();
    }

    public List<policyInput> getpolicyInputs() {
        return policyInputs;
    }   //배열 만들기

    @Override
    protected List<policyInput> doInBackground(String... strings) {    //스레드 위해 생성
        try {
            InputStream ins = new URL(strings[0]).openStream();      //주소 유알엘 받아 오픈스트림

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpars = factory.newPullParser();

            xpars.setInput(new InputStreamReader(ins, "UTF-8"));    //인풋리더로 파싱에 내용 넣기

            int eventType = xpars.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {   //가져온 내용이 끝이 아닐때까지 수행
                String tag = xpars.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG://태그가 시작됨
                        tag = xpars.getName();//태그 이름 얻어오기
                        if (tag.equals("servList")) {// 서브리스트 태그가 한 서비스마다 시작 알려주는 거
                            policyInput = new policyInput();
                        }
                        break;

                    case XmlPullParser.TEXT:     //텍스받으면 각각에 내용에 맞게 함수 실행시켜 넣어줌
                        text = xpars.getText();
                        break;

                    case XmlPullParser.END_TAG:       //</servList>이런 식으 태그 만나면 끝이므로 실행
                        if (tag.equals("servList")) {       //서브리스트라는 태그를 만나면 생성한 배열 원소 객체 하나 더해주기
                            policyInputs.add(policyInput);
                        } else if (tag.equals("inqNum")) {    //텍스트에 들어온 값 넣어줌
                            policyInput.setInqNum(text);
                        } else if (tag.equals("jurMnofNm")) {
                            policyInput.setJurMnofNm(text);
                        } else if (tag.equals("jurOrgNm")) {
                            policyInput.setJurOrgNm(text);
                        } else if (tag.equals("servDgst")) {
                            policyInput.setServDgst(text);
                        } else if (tag.equals("servDtlLink")) {
                            policyInput.setServDtlLink(text);
                        } else if (tag.equals("servId")) {
                            policyInput.setServId(text);
                        } else if (tag.equals("servNm")) {
                            policyInput.setServNm(text);
                            Log.e("ㅎㅎ", text);
                        } else if (tag.equals("svcfrstRegTs")) {
                            policyInput.setSvcfrstRegTs(text);
                        }
                        break;
                    default:
                        break;
                }
                eventType = xpars.next();    //다음 이벤트 타입으로 넘겨줌
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //   return policyInputs;
        catch (Exception e) {

        }
        return  policyInputs;     //만든 객체 배열 반환
    }

}