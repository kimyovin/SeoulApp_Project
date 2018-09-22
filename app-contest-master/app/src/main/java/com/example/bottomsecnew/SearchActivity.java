package com.example.bottomsecnew;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by hyovin on 2018-09-21.
 * 두번째 메뉴 - 맞춤복지
 */

public class SearchActivity extends AppCompatActivity implements MenuItem.OnMenuItemClickListener{


    @Override   //하단바 메뉴에 대한 intent 설정
    public boolean onMenuItemClick(MenuItem menuItem) {
        if(menuItem.getItemId() == R.id.navigation_home){   //1번째 하단바 메뉴
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
//        if(menuItem.getItemId() == R.id.navigation_dashboard){  //2번째 하단바 메뉴
//            Intent intent = new Intent(this, SearchActivity.class);
//            startActivity(intent);
//        }
        if(menuItem.getItemId() == R.id.navigation_notifications){  //3번째 하단바 메뉴
            Intent intent = new Intent(this, CategoryActivity.class);
            startActivity(intent);
        }
        if (menuItem.getItemId() == R.id.navigation_search) {   //4번째 하단바 메뉴
            Intent intent = new Intent(this, signin.class);
            startActivity(intent);
            //    finish();
            return true;
        }
        return false;
    }

    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener  //하단바 리스너 생성
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            onMenuItemClick(item);
//            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    setContentView(R.layout.activity_main);
//                    return true;
//                case R.id.navigation_dashboard:
//                    container.setBackgroundColor(Color.YELLOW);
//                    return true;
//                case R.id.navigation_notifications:
//                    container.setBackgroundColor(Color.BLUE);
//                    return true;
//                case R.id.navigation_search:
//                    mTextMessage.setText("Login");
//                    return true;
//            }
            return false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //하단바 액션 불구설정
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);  //하단바 리스너 설정
        BottomNavigationViewHelper.disableShiftMode(navigation);    //하단바 viewHelper 설정
    }
}
