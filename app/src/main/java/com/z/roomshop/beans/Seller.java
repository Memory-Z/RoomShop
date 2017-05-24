package com.z.roomshop.beans;

/**
 * Created by ZJer on 2017/4/30.
 */

public class Seller {
    private String SellerID = "";
    private String SellerName = "";
    private String SellerPassword = "";
    private String SellerShopName = "";
    private String SellerPhone = "";
    private String SellerAddress = "";

    public String getSellerID() {
        return SellerID;
    }
    public void setSellerID(String sellerID) {
        SellerID = sellerID;
    }
    public String getSellerName() {
        return SellerName;
    }
    public void setSellerName(String sellerName) {
        SellerName = sellerName;
    }
    public String getSellerPassword() {
        return SellerPassword;
    }
    public void setSellerPassword(String sellerPassword) {
        SellerPassword = sellerPassword;
    }
    public String getSellerShopName() {
        return SellerShopName;
    }
    public void setSellerShopName(String sellerShopName) {
        SellerShopName = sellerShopName;
    }
    public String getSellerPhone() {
        return SellerPhone;
    }
    public void setSellerPhone(String sellerPhone) {
        SellerPhone = sellerPhone;
    }
    public String getSellerAddress() {
        return SellerAddress;
    }
    public void setSellerAddress(String sellerAddress) {
        SellerAddress = sellerAddress;
    }
    @Override
    public String toString() {
        return "Seller [SellerID=" + SellerID + ", SellerName=" + SellerName
                + ", SellerPassword=" + SellerPassword + ", SellerShopName="
                + SellerShopName + ", SellerPhone=" + SellerPhone
                + ", SellerAddress=" + SellerAddress + "]";
    }

}
