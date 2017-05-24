package com.z.roomshop.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.transition.AutoTransition;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TabHost;

import com.z.roomshop.R;
import com.z.roomshop.app.AppConstant;
import com.z.roomshop.beans.Buyer;
import com.z.roomshop.utils.HttpThread;
import com.z.roomshop.utils.JsonAnalysis;
import com.z.roomshop.utils.ToolFile;

/**
 * Created by ZJer on 2017/4/26.
 */

public class LoginActivity extends FragmentActivity {

    private final static String TAG = "LoginActivity";

    private Buyer buyer = null;
    private TabHost tabhost;
    private EditText buyerPhoneEdt;
    private EditText buyerPwdEdt;
    private EditText sellerPhoneEdt;
    private EditText sellerPwdEdt;
    private CheckBox buyerRememberChk;
    private CheckBox buyerAutoLoginChk;
    private CheckBox sellerRememberChk;
    private CheckBox sellerAutoLoginChk;
    private Button button;
    private Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.i(TAG, "------------------>onCreate");
        initView();

    }

    private void initView() {

        tabhost = (TabHost) findViewById(android.R.id.tabhost);
        tabhost.setup();

        tabhost.addTab(tabhost.newTabSpec("tab_buyer").setIndicator("买家登录").setContent(R.id.fragment_buyer));
        tabhost.addTab(tabhost.newTabSpec("tab_seller").setIndicator("卖家登录").setContent(R.id.fragment_seller));

    }

    @Override
    protected void onStart() {
        setupWindowAnimations();
        super.onStart();
        Log.i(TAG, "------------------>onStart");

        //买家
        buyerPhoneEdt = (EditText) findViewById(R.id.edt_buyerLogin_phone);
        buyerPwdEdt = (EditText) findViewById(R.id.edt_buyerLogin_pwd);
        buyerRememberChk = (CheckBox) findViewById(R.id.chk_buyer_remember);
        buyerAutoLoginChk = (CheckBox) findViewById(R.id.chk_buyer_autoLogin);
        btn = (Button) findViewById(R.id.btn_buyerLogin_toLogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String http = AppConstant.BUYER_LOGIN_URL + "phone=" + buyerPhoneEdt.getText().toString()
                        + "&pwd=" + buyerPwdEdt.getText().toString();
                HttpThread httpThread = new HttpThread(http, handler);
                new Thread(httpThread).start();
            }
        });

        //卖家
        sellerPhoneEdt = (EditText) findViewById(R.id.edt_sellerLogin_phone);
        sellerPwdEdt = (EditText) findViewById(R.id.edt_sellerLogin_pwd);
        sellerRememberChk = (CheckBox) findViewById(R.id.chk_seller_remember);
        sellerAutoLoginChk = (CheckBox) findViewById(R.id.chk_seller_autoLogin);
        button = (Button) findViewById(R.id.btn_sellerLogin_toLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String http = AppConstant.SELLER_LOGIN_URL + "phone=" + sellerPhoneEdt.getText().toString()
                        + "&pwd=" + sellerPwdEdt.getText().toString();
                HttpThread httpThread = new HttpThread(http, handler);
                new Thread(httpThread).start();
            }
        });
    }

    //用于接收网络传入的数据
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == AppConstant.FINISHED) {
                Bundle bundle = msg.getData();
                String result = bundle.getString("result", "");
                Log.i(TAG, "----------返回数据：>>>" + result);
                int i = new JsonAnalysis().getLoginState(result);
                checkLoginState(i);
                Log.i(TAG, "----------返回数据：>>>" + i);
            } else if (msg.what == AppConstant.UNFINISHED) {
                checkLoginState(4000);
            }
        }
    };

    private void checkLoginState(int i) {
        switch (i) {
            case 1010:
                String buyer = "buyer";
                saveLoginState(buyer);
                gotoMainActivity();
                break;
            case 1011:
                break;
            case 1012:
                AlertDialog dialog1 = new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("提示")
                        .setMessage("手机号不存在")
                        .setCancelable(false)
                        .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create();
                dialog1.show();
                break;
            case 2010:
                String seller = "seller";
                saveLoginState(seller);
                break;
            case 2011:
                break;
            case 2012:

                break;
            case 4000:
                AlertDialog dialog = new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("提示")
                        .setCancelable(false)
                        .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create();
                dialog.show();
                break;
            default:
                break;
        }
    }

    private void saveLoginState(String who) {
        Context context = getApplicationContext();
        if (who.equals("buyer")) {
            ToolFile.putString(context, "who", "buyer");
            ToolFile.putString(context, "account", buyerPhoneEdt.getText().toString());
            ToolFile.putString(context, "password", buyerPwdEdt.getText().toString());
            ToolFile.putBoolean(context, "remember", buyerRememberChk.isChecked());
            ToolFile.putBoolean(context, "auto", buyerAutoLoginChk.isChecked());
        } else if (who.equals("seller")) {
            ToolFile.putString(context, "who", "seller");
            ToolFile.putString(context, "account", sellerPhoneEdt.getText().toString());
            ToolFile.putString(context, "password", sellerPwdEdt.getText().toString());
            ToolFile.putBoolean(context, "remember", sellerRememberChk.isChecked());
            ToolFile.putBoolean(context, "auto", sellerAutoLoginChk.isChecked());
        }


    }

    /**
     * 页面跳转，从登录界面跳转至主界面，并将用户账号信息发送
     */
    private void gotoMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("BuyerPhone", buyerPhoneEdt.getText().toString());
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, "------------------>onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "------------------>onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "------------------>onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "------------------>onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "------------------>onDestroy");
        super.onDestroy();
    }

    /**
     * 设置窗口动画
     */
    private void setupWindowAnimations() {
        AutoTransition transition = new AutoTransition();
        transition.setDuration(2000);
        getWindow().setExitTransition(transition);
    }
}
