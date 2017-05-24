package com.z.roomshop.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.z.roomshop.R;
import com.z.roomshop.adapter.GoodsListAdapter;
import com.z.roomshop.beans.Goods;
import com.z.roomshop.utils.HTTPConnection;
import com.z.roomshop.fragment.GoodsDialogFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZJer on 2017/5/19.
 */

public class GoodsListActivity extends Activity {
    private static final String TAG = "GoodsListActivity";
    private ListView listView;
    private List<Goods> goodsList;
    private List<Bitmap> goodsImageList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        goodsList = new ArrayList<>();
        goodsImageList = new ArrayList<>();
        initData();


    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.i(TAG, "------->>" + msg.arg1);
            if (msg.obj != null) {
                Bitmap bitmap = (Bitmap) msg.obj;
                goodsImageList.add(bitmap);
                initView();
            }

            super.handleMessage(msg);
        }
    };
    private void initData() {

        final String[] imageName = {"aquarius", "aries", "cancer", "capricorn", "gemini", "leo", "libra"};
        String http = "http://111.51.195.109:8080/dormitoryshops/file/";
        final String finalHttp = http;
        Thread thread = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < imageName.length; i ++) {
                    String http = finalHttp + imageName[i] + ".jpg";

                    HTTPConnection httpConnect = new HTTPConnection();
                    Bitmap bitmap = null;
                    try {
                        bitmap = httpConnect.getConnectionImage(http);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Message msg = Message.obtain();
                    msg.arg1 = i;
                    msg.obj = bitmap;
                    handler.sendMessage(msg);
                }
            }
        };
        thread.start();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.list_goods);
        goodsList = new ArrayList<>();
        for (int i = 0; i < goodsImageList.size(); i ++) {
            Goods goods = new Goods();
            goods.setGoodsName("1223");
            goods.setGoodsNum(11);
            goods.setGoodsPrice(15.78F);
            goodsList.add(goods);
        }
        Log.i(TAG, "---->>goodsList：" + goodsList.size() + "----->goodsImageList：" + goodsImageList.size());
        Log.i(TAG, "---->>>>>>>>>>>>>>>>>>>>>>>>>>");
        GoodsListAdapter adapter = new GoodsListAdapter(getApplicationContext(), goodsList, goodsImageList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GoodsDialogFragment dialogFragment = new GoodsDialogFragment(GoodsListActivity.this, goodsImageList, goodsList);
//                dialogFragment.setImage(goodsImageList.get(position));
                Log.i(TAG, ">>>>>>>>>>>>>>>>>" + goodsImageList.get(position));
                dialogFragment.setImage(position);
                dialogFragment.show(getFragmentManager(), "goodsDialog");
            }
        });
    }

}
