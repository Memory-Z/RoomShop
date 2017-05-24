package com.z.roomshop.app;

/**
 * 存储应用程序应用的常量
 * Created by ZJer on 2017/4/30.
 */

public class AppConstant {

    public static final String UNCONNECT = "****####*****";
    //未完成
    public static final int UNFINISHED = 0x000001;
    //完成
    public static final int FINISHED = 0x000000;
    // 加载图片
    public static final int LOADINGBITMAP = 0x000002;

    public static final int LOADEDBITMAP = 0x000003;
    // 获取评论详情Map
    public static final int GETCOMMENTDATILEMAP = 0x000004;

    public static final int GETBUYERNICKNAMEING = 0x000005;

    public static final int GETBUYERNICKNAMED = 0x000006;

    public static final int GETIMAGENAME = 0x000007;
    //连接地址  112.74.95.137
//    public static final String URL = "http://111.51.198.94:8080/dormitoryshops/servlet/";
    public static final String URL = "http://112.74.95.137:8010/dormitoryshops/servlet/";
    //买家登录地址
    public static final String BUYER_LOGIN_URL = URL + "BuyerLoginServlet?";
    //卖家登录地址
    public static final String SELLER_LOGIN_URL = URL + "SellerLoginServlet?";

}
