package com.z.roomshop.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.transition.AutoTransition;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.z.roomshop.R;
import com.z.roomshop.fragment.MainCartFragment;
import com.z.roomshop.fragment.MainHomeFragment;
import com.z.roomshop.fragment.MainMineFragment;
import com.z.roomshop.utils.NoScrollViewPager;
import com.z.roomshop.weight.StepperView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemLongClickListener {

    private NoScrollViewPager viewPager;
    private FragmentStatePagerAdapter mAdapter;
    private ArrayList<Fragment> array;
    private RadioButton rbtn_home;
    private RadioButton rbtn_cart;
    private RadioButton rbtn_mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupWindowAnimations();

        initData();
        initView();

    }

    /**
     * 初始化数据，将fragment 实例化，添加至ArrayList 内
     */
    private void initData() {
        Fragment fragment_home = new MainHomeFragment();
        Fragment fragment_cart = new MainCartFragment();
        Fragment fragment_mine = new MainMineFragment();
        array = new ArrayList<Fragment>();
        array.add(fragment_home);
        array.add(fragment_cart);
        array.add(fragment_mine);
    }

    /**
     * 初始化视图
     */
    private void initView() {

        rbtn_home = (RadioButton) findViewById(R.id.rbtn_main_home);
        rbtn_home.setOnClickListener(this);
        rbtn_home.setChecked(true);
        rbtn_cart = (RadioButton) findViewById(R.id.rbtn_main_cart);
        rbtn_cart.setOnClickListener(this);
        rbtn_mine = (RadioButton) findViewById(R.id.rbtn_main_mine);
        rbtn_mine.setOnClickListener(this);

        viewPager = (NoScrollViewPager) findViewById(R.id.viewpager_main);
        viewPager.setNoScroll(true);

        mAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return array.get(position);
            }

            @Override
            public int getCount() {
                return array.size();
            }
        };

        viewPager.setAdapter(mAdapter);

        //设置页面切换监听事件
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rbtn_home.setChecked(true);
                        break;
                    case 1:
                        rbtn_cart.setChecked(true);
                        break;
                    case 2:
                        rbtn_mine.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.requestDisallowInterceptTouchEvent(true);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rbtn_main_home:
                viewPager.setCurrentItem(0);
                break;
            case R.id.rbtn_main_cart:
                viewPager.setCurrentItem(1);
                fragmentCartActivity();
                break;
            case R.id.rbtn_main_mine:
                viewPager.setCurrentItem(2);
                break;
            default:
                break;
        }
    }

    private void fragmentCartActivity() {

        ListView listView = (ListView) findViewById(R.id.list_main_cart);
        listView.setOnItemLongClickListener(this);
    }

    /**
     * 界面跳转
     */
    private void setupWindowAnimations() {
        AutoTransition transition = new AutoTransition();
        transition.setDuration(2000);
        getWindow().setEnterTransition(transition);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        final LinearLayout layout = (LinearLayout) view.findViewById(R.id.llayout_cart_item_seek);
        final StepperView seekBar = (StepperView) view.findViewById(R.id.seek_cart_item_num);
        final TextView total = (TextView) view.findViewById(R.id.txt_cart_item_total);
        final TextView num = (TextView) view.findViewById(R.id.txt_cart_item_num);
        seekBar.setSaveOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setVisibility(View.GONE);
                num.setText(seekBar.getNum());
            }
        });
        if (layout.getVisibility() == View.GONE) {
            layout.setVisibility(View.VISIBLE);
            seekBar.setNum(num.getText().toString());
        } else {
            layout.setVisibility(View.GONE);
        }
        return true;
    }
}
