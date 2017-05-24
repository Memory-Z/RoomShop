package com.z.roomshop.utils;

import com.z.roomshop.beans.Buyer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * JSON 解析
 * Created by ZJer on 2017/4/30.
 */

public class JsonAnalysis {

    private JSONObject mJson = null;

    /**
     * 将JSON数据解析为int类型
     * @param json JSON数据，获取登录状态
     * @return 返回登录状态码：1010：登录成功；1012：用户名或手机号不存在；1011：密码不匹配；4000：网络异常
     *                       2010：登陆成功；2012：商铺名或手机号不存在；2011：密码不匹配；
     */
    public int getLoginState(String json) {
        int loginState = 0;
        try {
            mJson = new JSONObject(json);
            loginState = mJson.optInt("LoginState", 4000);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return loginState;
    }

    /**
     * 将JSON数据解析为Boolean 类型
     * @param json  JSON数据，获取是否修改密码成功
     * @return  返回修改状态：true：密码修改成功；false：秘密修改失败
     */
    public boolean getModifyPWD(String json) {
        boolean isModifyPwd = false;
        try {
            mJson = new JSONObject(json);
            isModifyPwd = mJson.optBoolean("ModifyPWD", false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return isModifyPwd;
    }

    /**
     * 将JSON数据解析为Boolean
     * @param json  JSON数据，获取是否修改信息成功
     * @return      返回修改状态：true：修改成功；false：修改失败
     */
    public boolean getModifyState(String json) {
        boolean isModifyState = false;
        try {
            mJson = new JSONObject(json);
            isModifyState = mJson.optBoolean("ModifyState", false);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return isModifyState;
    }

    /**
     * 将JSON数据解析为int类型数据
     * @param json  JSON 数据，买家注册是否成功
     * @return      返回注册状态：1000：注册成功；1001：用户名已被占用；1002：手机号已被占用；
     *                          1003：用户名、手机号都已被占用；1004：未知错误
     */
    public int getBuyerRegisterState(String json) {
        int BuyerRegisterState = 1004;
        try {
            mJson = new JSONObject(json);
            BuyerRegisterState = mJson.optInt("BuyerRegisterState", 1004);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return BuyerRegisterState;
    }

    /**
     * 将JSON数据解析为Buyer类型数据
     * @param json  查询的买家信息
     * @return      买家类数据
     */
    public Buyer getBuyer(String json) {
        Buyer buyer = new Buyer();
        String buyerId = "";
        String buyerName = "";
        String buyerPhone = "";
        String buyerPassword = "";
        String buyerAddress = "";
        try {
            mJson = new JSONObject(json);
            buyerId = mJson.getString("BuyerId");
            buyerName = mJson.getString("BuyerName");
            buyerPhone = mJson.getString("BuyerPhone");
            buyerPassword = mJson.getString("BuyerPassword");
            buyerAddress = mJson.getString("BuyerAddress");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        buyer.setBuyerID(buyerId);
        buyer.setBuyerName(buyerName);
        buyer.setBuyerPhone(buyerPhone);
        buyer.setBuyerPassword(buyerPassword);
        buyer.setBuyerAddress(buyerAddress);

        return buyer;
    }

    public List<String> getList(String json) throws JSONException {
        List<String> list = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(json);
//        list = (List<String>) jsonArray;
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.getString(i));
        }
        return list;
    }
}
