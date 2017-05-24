package com.z.roomshop.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.z.roomshop.R;
import com.z.roomshop.app.AppConstant;
import com.z.roomshop.utils.GsonTools;
import com.z.roomshop.utils.HTTPConnection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZJer on 2017/5/20.
 */

public class GoodsDetailActivity extends Activity {

    private static final String TAG = "GoodsDetailActivity";

    private ImageView mImageView_0, mImageView_1, mImageView_2, mImageView_3, mImageView_4;
    private TextView mTextViewGoodsName, mTextViewGoodsPriceY, mTextViewGoodsPriceF, mTextViewGoodsNum,
            mTextViewGoodsSellerName, mTextViewGoodsDetail, mTextViewGoodsShowMore;
    private ScrollView scrollView;
    private HorizontalScrollView horizontalScrollView;
    private Button btnShowGoodsDetail;

    private Handler handler;
    private List<Bitmap> mGoodsImageList;
    private List<String> goodsImageNameList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_goods);

        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }


    /**
     * 初始化数据
     */
    private void initData() {
        handler = new GoodsDetailHandler();

        mGoodsImageList = new ArrayList<>();
        goodsImageNameList = new ArrayList<>();
        getImageName();

    }

    /**
     * 初始化视图
     */
    private void initView() {
        mImageView_0 = (ImageView) findViewById(R.id.img_dialog_goods_1);
        mImageView_1 = (ImageView) findViewById(R.id.img_dialog_goods_2);
        mImageView_2 = (ImageView) findViewById(R.id.img_dialog_goods_3);
        mImageView_3 = (ImageView) findViewById(R.id.img_dialog_goods_4);
        mImageView_4 = (ImageView) findViewById(R.id.img_dialog_goods_5);

        mTextViewGoodsName = (TextView) findViewById(R.id.txt_dialog_goods_name);
        mTextViewGoodsNum = (TextView) findViewById(R.id.txt_dialog_goods_num);
        mTextViewGoodsPriceY = (TextView) findViewById(R.id.txt_dialog_goods_priceY);
        mTextViewGoodsPriceF = (TextView) findViewById(R.id.txt_list_goods_price_F);
        mTextViewGoodsSellerName = (TextView) findViewById(R.id.txt_dialog_goods_seller_name);
        mTextViewGoodsDetail = (TextView) findViewById(R.id.txt_dialog_goods_detail);
        mTextViewGoodsShowMore = (TextView) findViewById(R.id.txt_dialog_goods_show_detail);

        btnShowGoodsDetail = (Button) findViewById(R.id.btn_goods_detail_show_comment);
        btnShowGoodsDetail.setOnClickListener(new OnButtonOnClick());

        horizontalScrollView = (HorizontalScrollView) findViewById(R.id.hor_scroll_dialog_goods_image);

        scrollView = (ScrollView) findViewById(R.id.scroll_dialog_goods_info);

        scrollView.setOnTouchListener(new OnScrollViewOnTouch());           //  滚动视图监听事件
        mTextViewGoodsShowMore.setOnClickListener(new OnGoodsDetailShowOnClick());  // 显示更多监听事件
    }

    private boolean isShowMore = false;                     //  是否显示了全部商品信息， 默认为否

    /**
     * 获取图片名称
     */
    private void getImageName() {
        Thread getImageNameThread = new Thread(){
            String http = "http://112.74.95.137:8010/dormitoryshops/servlet/GoodsImageSelectByGoodsIdServlet?goodsid=1";
//            String http = "http://10.0.2.2:8080/dormitoryshops/servlet/GoodsImageSelectByGoodsIdServlet?goodsid=1";

            @Override
            public void run() {
                super.run();
                String result = null;
                try {
                    result = new HTTPConnection().getConnectionResult(http);
                    Message msg = Message.obtain();
                    msg.what = AppConstant.GETIMAGENAME;
                    msg.obj = result;
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        getImageNameThread.start();
    }

    /**
     * 执行子线程，获取图片Bitmap信息
     */
    private void getImage() {
        Thread getImageBitMapThread = new Thread() {
            @Override
            public void run() {
                List<String> list = goodsImageNameList;
                HTTPConnection httpConnection = new HTTPConnection();

                for (int i = 0; i < 5; i++) {
//                    String http = "http://111.51.197.127:8080/dormitoryshops/file/";
                    String http = "http://112.74.95.137:8010/dormitoryshops/file/";
//                    String http = "http://10.0.2.2:8080/dormitoryshops/file/";
                    http += list.get(i);
                    Log.i(TAG, "----->>>>>HTTP:" + http);
                    Bitmap bitmap = null;
                    try {
                        bitmap = httpConnection.getConnectionImage(http);
                        Message msg = Message.obtain();
                        msg.obj = bitmap;
                        msg.what = AppConstant.LOADINGBITMAP;
                        handler.sendMessage(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Message msg = Message.obtain();
                msg.what = AppConstant.LOADEDBITMAP;
                handler.sendMessage(msg);
            }
        };
        getImageBitMapThread.start();
    }

    /**
     * 设置水平滚动视图的图片
     */
    private void setImage() {

        getViewData();
        Log.i(TAG, "---->" + mGoodsImageList.size());

        mImageView_0.setImageBitmap(mGoodsImageList.get(0));
        mImageView_1.setImageBitmap(mGoodsImageList.get(1));
        mImageView_2.setImageBitmap(mGoodsImageList.get(2));
        mImageView_3.setImageBitmap(mGoodsImageList.get(3));
        mImageView_4.setImageBitmap(mGoodsImageList.get(4));

        ViewGroup.LayoutParams imageParams_0 = mImageView_0.getLayoutParams();
        imageParams_0.height = imageHeight_0;
        imageParams_0.width = imageHeight_0;
        mImageView_0.setLayoutParams(imageParams_0);

        ViewGroup.LayoutParams imageParams_1 = mImageView_1.getLayoutParams();
        imageParams_1.height = imageHeight_1;
        imageParams_1.width = imageHeight_1;
        mImageView_1.setLayoutParams(imageParams_1);

        ViewGroup.LayoutParams imageParams_2 = mImageView_2.getLayoutParams();
        imageParams_2.height = imageHeight_2;
        imageParams_2.width = imageHeight_2;
        mImageView_2.setLayoutParams(imageParams_2);

        ViewGroup.LayoutParams imageParams_3 = mImageView_3.getLayoutParams();
        imageParams_3.height = imageHeight_3;
        imageParams_3.width = imageHeight_3;
        mImageView_3.setLayoutParams(imageParams_3);

        ViewGroup.LayoutParams imageParams_4 = mImageView_4.getLayoutParams();
        imageParams_4.height = imageHeight_4;
        imageParams_4.width = imageHeight_4;
        mImageView_4.setLayoutParams(imageParams_4);

    }

    private int horScrollHeight;                       //  水平滚动视图的高度；单位px
    private int imageHeight_0, imageHeight_1, imageHeight_2, imageHeight_3, imageHeight_4;

    /**
     * 获取视图的相关数据；
     */
    public void getViewData() {

        horScrollHeight = horizontalScrollView.getLayoutParams().height;

        imageHeight_0 = mImageView_0.getHeight();
        imageHeight_1 = mImageView_1.getHeight();
        imageHeight_2 = mImageView_2.getHeight();
        imageHeight_3 = mImageView_3.getHeight();
        imageHeight_4 = mImageView_4.getHeight();
    }

    /**
     * 更改图片的大小
     */
    private void changeImageSize() {
        ViewGroup.LayoutParams layoutParams = horizontalScrollView.getLayoutParams();
        layoutParams.height = (int) (horScrollHeight * 0.5);
        Log.i(TAG, "-->>layoutParams.height:>>" + layoutParams.height);

        ViewGroup.LayoutParams imageParams_0 = mImageView_0.getLayoutParams();
        imageParams_0.height = (int) (imageHeight_0 * 0.5);
        imageParams_0.width = (int) (imageHeight_0 * 0.5);
        mImageView_0.setLayoutParams(imageParams_0);

        ViewGroup.LayoutParams imageParams_1 = mImageView_1.getLayoutParams();
        imageParams_1.height = (int) (imageHeight_1 * 0.5);
        imageParams_1.width = (int) (imageHeight_1 * 0.5);
        mImageView_0.setLayoutParams(imageParams_1);

        ViewGroup.LayoutParams imageParams_2 = mImageView_2.getLayoutParams();
        imageParams_2.height = (int) (imageHeight_2 * 0.5);
        imageParams_2.width = (int) (imageHeight_2 * 0.5);
        mImageView_0.setLayoutParams(imageParams_2);

        ViewGroup.LayoutParams imageParams_3 = mImageView_3.getLayoutParams();
        imageParams_3.height = (int) (imageHeight_3 * 0.5);
        imageParams_3.width = (int) (imageHeight_3 * 0.5);
        mImageView_0.setLayoutParams(imageParams_3);

        ViewGroup.LayoutParams imageParams_4 = mImageView_4.getLayoutParams();
        imageParams_4.height = (int) (imageHeight_4 * 0.5);
        imageParams_4.width = (int) (imageHeight_4 * 0.5);
        mImageView_0.setLayoutParams(imageParams_4);
    }

    /**
     * 还原图片大小
     */
    private void backImageSize() {
        ViewGroup.LayoutParams layoutParams = horizontalScrollView.getLayoutParams();
        layoutParams.height = horScrollHeight;
        horizontalScrollView.setLayoutParams(layoutParams);

        ViewGroup.LayoutParams imageParams_0 = mImageView_0.getLayoutParams();
        imageParams_0.height = imageHeight_0;
        imageParams_0.width = imageHeight_0;
        mImageView_0.setLayoutParams(imageParams_0);

        ViewGroup.LayoutParams imageParams_1 = mImageView_1.getLayoutParams();
        imageParams_1.height = imageHeight_1;
        imageParams_1.width = imageHeight_1;
        mImageView_1.setLayoutParams(imageParams_1);

        ViewGroup.LayoutParams imageParams_2 = mImageView_2.getLayoutParams();
        imageParams_2.height = imageHeight_2;
        imageParams_2.width = imageHeight_2;
        mImageView_2.setLayoutParams(imageParams_2);

        ViewGroup.LayoutParams imageParams_3 = mImageView_3.getLayoutParams();
        imageParams_3.height = imageHeight_3;
        imageParams_3.width = imageHeight_3;
        mImageView_3.setLayoutParams(imageParams_3);

        ViewGroup.LayoutParams imageParams_4 = mImageView_4.getLayoutParams();
        imageParams_4.height = imageHeight_4;
        imageParams_4.width = imageHeight_4;
        mImageView_4.setLayoutParams(imageParams_4);
    }


    private int lastY;                                //  点击事件时点下时的Y坐标

    /**
     * 视图触摸事件
     */
    class OnScrollViewOnTouch implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int y = (int) event.getY();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    lastY = y;
                    Log.i(TAG, "==========>>lastY:" + lastY);
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP:
                    int offsetY = y - lastY;
                    Log.i(TAG, "+++++++++++++>>offertY:" + offsetY);
                    if (offsetY < 0) {
                        changeImageSize();

                    } else if (offsetY > 0) {
                        backImageSize();
                    }
                    break;
            }
            return true;
        }
    }

    /**
     * 商品详情点击事件
     */
    class OnGoodsDetailShowOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (isShowMore == false) {
                mTextViewGoodsDetail.setMaxLines(40);
                mTextViewGoodsShowMore.setText("收起详情");
                isShowMore = true;
            } else {
                isShowMore = false;
                mTextViewGoodsShowMore.setText("显示详情");
                mTextViewGoodsDetail.setMaxLines(5);
            }
        }
    }

    /**
     * 按钮点击事件
     */
    class OnButtonOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_goods_detail_show_comment:
                    Intent intent = new Intent(GoodsDetailActivity.this, GoodsCommentListActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }

    class GoodsDetailHandler extends Handler {
        @Override
        public synchronized void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case AppConstant.GETIMAGENAME:
                    String result_0 = (String) msg.obj;
                    goodsImageNameList = GsonTools.getListString(result_0);
                    Log.i(TAG, "----->>>" + goodsImageNameList.toString());
                    getImage();
                    break;
                case AppConstant.LOADINGBITMAP:
                    Bitmap bitmap = (Bitmap) msg.obj;
                    Log.i(TAG, "------->>>>>" + bitmap.toString());
                    mGoodsImageList.add(bitmap);
                    break;
                case AppConstant.LOADEDBITMAP:
                    Log.i(TAG, "------->>>>>000000000000000000000000000");
                    setImage();
                    break;
            }
        }
    }
}
