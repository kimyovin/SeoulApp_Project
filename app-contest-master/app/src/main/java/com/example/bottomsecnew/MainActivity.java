package com.example.bottomsecnew;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MenuItem.OnMenuItemClickListener {
    MenuItem item1, item2, item3, item4;
    private TextView mTextMessage;

   private ViewPager tabViewPager; //tab이 들어갈 view pager
    ActionBar actionBar;    //swipe할 수 있게 해주는 액션바
    private FragmentManager fm;
    private ArrayList<Fragment> fList;  //각 탭에 들어갈 fragment list


    @Override   //하단바 메뉴에 대한 intent 설정
    public boolean onMenuItemClick(MenuItem menuItem) {
//        if(menuItem.getItemId() == R.id.navigation_home){   //1번째 하단바 메뉴
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//        }
        if(menuItem.getItemId() == R.id.navigation_dashboard){  //2번째 하단바 메뉴
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        }
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

    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        tabViewPager = findViewById(R.id.pager);    //스와이프할 뷰페이지를 정의
        fm = getSupportFragmentManager();  //fragment Manager 객체 정의
        actionBar = getSupportActionBar();  //액션바 객체 정의

        //액션바 속성 정의
        actionBar.setDisplayShowTitleEnabled(true); //액션바 노출 유무
        actionBar.setTitle("actionbar title");  //액션바 타이틀 라벨

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);    //액션바에 모드 설정; navigation_mode_tabs로 tab모드 설정

        //액션바에 추가될 탭 생성
        ActionBar.Tab tab1 = actionBar.newTab().setText("인기순").setTabListener(tabListener);
        ActionBar.Tab tab2 = actionBar.newTab().setText("최신순").setTabListener(tabListener);

        //액션바에 탭 추가
        actionBar.addTab(tab1);
        actionBar.addTab(tab2);

        //각 탭에 들어갈 fragment 생성 및 추가
        fList = new ArrayList<Fragment>();
        fList.add(fragment1.newInstance());
        fList.add(fragment2.newInstance());

        //swipe로 tab을 이동할 viewpager의 리스너 설정
        tabViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //뷰페이져의 adapter 생성, 연결
        CustomFragmentPagerAdapter adapter = new CustomFragmentPagerAdapter(fm, fList);

        tabViewPager.setAdapter(adapter);

        //하단바 액션 불구설정
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);  //하단바 리스너 설정
        BottomNavigationViewHelper.disableShiftMode(navigation);    //하단바 viewHelper 설정

    }
    //onCreate 끝


    ActionBar.TabListener tabListener = new ActionBar.TabListener() {
        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            //액션바에서 선택된 탭에 대응되는 페이지를 뷰페이지에서 현재 보여지는 페이지로 변경
            tabViewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            //해당 탭을 선택시 처리
            //해당 탭을 선택시 해당 뷰페이져로 이동
//            actionBar.setSelectedNavigationItem(tab.getPosition());
//            tabViewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
            //해당 탭이 다시 선택됐을때 처리
            //그냥 다시 냅두니 아무코드 X
        }
    };

}