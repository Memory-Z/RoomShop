package com.z.roomshop.utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.z.roomshop.app.AppConstant;

import java.io.IOException;

/**
 * Created by ZJer on 2017/4/30.
 */

public class HttpThread extends Thread {

    private String mHttp = "";
    private String mResult = "";
    private Handler mHandler = null;

    /**
     * HttpThread 构造方法
     * @param http 获取值得http路径，如：“http://127.0.0.1:8080/test/serlvet”
     * @param handler Handler 类，用于接收和发送Message消息
     */
    public HttpThread(String http, Handler handler) {
        this.mHttp = http;
        this.mHandler = handler;
    }

    @Override
    public void run() {
        super.run();
        HTTPConnection httpConnection = new HTTPConnection();
        try {
            mResult = httpConnection.getConnectionResult(mHttp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bundle bundle = new Bundle();
        bundle.putString("result", mResult);

        Message msg = new Message();
        msg.setData(bundle);
        if (mResult.equals(AppConstant.UNCONNECT)) {
            msg.what = AppConstant.UNFINISHED;
        } else {
            msg.what = AppConstant.FINISHED;
        }
        mHandler.sendMessage(msg);
    }
}
