package com.z.roomshop.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.z.roomshop.R;
import com.z.roomshop.adapter.CommentListAdapter;
import com.z.roomshop.app.AppConstant;
import com.z.roomshop.utils.GsonTools;
import com.z.roomshop.utils.HTTPConnection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ZJer on 2017/5/24.
 */

public class GoodsCommentListActivity extends Activity {

    private static final String TAG = "GoodsCommentList";

    private TextView txtGoodsName;
    private ListView listView;

    private List<Map<String, Object>> commentDetailList;
    private List<String> buyerIdList;
    private List<String> buyerNameList;
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_comment_list);

        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {
        handler = new commentHandler();
        getCommentDetail();
    }

    private void initView() {
        txtGoodsName = (TextView) findViewById(R.id.txt_comment_list_name);

        listView = (ListView) findViewById(R.id.list_comment_detail);
    }

    /**
     * 获取商品评论详情
     */
    private void getCommentDetail() {
        commentDetailList = new ArrayList<>();
        Thread getCommentDetailThread = new Thread() {
            @Override
            public void run() {
                super.run();
                String http = "http://112.74.95.137:8010/dormitoryshops/servlet/CommentSelectServlet?goodsid=1";
//                String http = "http://10.0.2.2:8080/dormitoryshops/servlet/CommentSelectServlet?goodsid=1";
                String result = "";
                try {
                    result = new HTTPConnection().getConnectionResult(http);
                    Message msg = Message.obtain();
                    msg.what = AppConstant.GETCOMMENTDATILEMAP;
                    msg.obj = result;
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        getCommentDetailThread.start();
    }

    /**
     * 获取评论的买家昵称
     */
    private void getCommentBuyer() {
        buyerIdList = new ArrayList<>();
        for (int i = 0; i < commentDetailList.size(); i ++) {
            buyerIdList.add((String) commentDetailList.get(i).get("BuyerId"));
        }
        buyerNameList = new ArrayList<>();
        new Thread() {
            @Override
            public void run() {
                super.run();
                String http = "http://112.74.95.137:8010/dormitoryshops/servlet/BuyerNameByBuyerIdServlet?buyerid=";
//                String http = "http://10.0.2.2:8080/dormitoryshops/servlet/BuyerNameByBuyerIdServlet?buyerid=";
                for (int i = 0; i < buyerIdList.size(); i ++) {
                    String http_0 = http + buyerIdList.get(i);
                    String result = null;
                    try {
                        result = new HTTPConnection().getConnectionResult(http_0);
                        Message msg = Message.obtain();
                        msg.what = AppConstant.GETBUYERNICKNAMEING;
                        msg.obj = result;
                        handler.sendMessage(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Message msg = Message.obtain();
                msg.what = AppConstant.GETBUYERNICKNAMED;
                handler.sendMessage(msg);
            }
        }.start();
    }

    private void setCommentListAdapter() {
        Log.i(TAG, "--" + commentDetailList.toString() + "-----" + buyerNameList.toString() + "---");
        CommentListAdapter adapter = new CommentListAdapter(getApplicationContext(), null,
                commentDetailList, buyerNameList);
        listView.setAdapter(adapter);
    }


    class commentHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case AppConstant.GETCOMMENTDATILEMAP:       // 获取评论列表，返回List<Map<String,Object>>类型
                    String result_1  = (String) msg.obj;
                    Log.i(TAG, "----->获取评论list:>>>" + result_1);
                    commentDetailList = GsonTools.getListMap(result_1);
                    getCommentBuyer();
                    break;
                case AppConstant.GETBUYERNICKNAMEING:       // 获取评论者昵称中，返回String类型
                    String result_2 = (String) msg.obj;
                    Log.i(TAG, "----->获取买家昵称:>>>" + result_2);
                    buyerNameList.add(GsonTools.getString(result_2, new String().getClass()));
                    break;
                case AppConstant.GETBUYERNICKNAMED:         // 评论者昵称获取完毕
                    setCommentListAdapter();
                    break;
            }
        }
    }

}
