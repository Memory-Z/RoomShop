package com.z.roomshop.beans;

/**
 * Created by ZJer on 2017/5/19.
 */

public class Goods {

    private String GoodsID = "";
    private String GoodsName = "";
    private float GoodsPrice = 0;
    private int GoodsNum = 0;
    private String SellerID = "";

    public String getGoodsID() {
        return GoodsID;
    }
    public void setGoodsID(String goodsID) {
        GoodsID = goodsID;
    }
    public String getGoodsName() {
        return GoodsName;
    }
    public void setGoodsName(String goodsName) {
        GoodsName = goodsName;
    }
    public float getGoodsPrice() {
        return GoodsPrice;
    }
    public void setGoodsPrice(float goodsPrice) {
        GoodsPrice = goodsPrice;
    }
    public int getGoodsNum() {
        return GoodsNum;
    }
    public void setGoodsNum(int goodsNum) {
        GoodsNum = goodsNum;
    }
    public String getSellerID() {
        return SellerID;
    }
    public void setSellerID(String sellerID) {
        SellerID = sellerID;
    }
    @Override
    public String toString() {
        return "Goods [GoodsID=" + GoodsID + ", GoodsName=" + GoodsName
                + ", GoodsPrice=" + GoodsPrice + ", GoodsNum=" + GoodsNum
                + ", SellerID=" + SellerID + "]";
    }

}
