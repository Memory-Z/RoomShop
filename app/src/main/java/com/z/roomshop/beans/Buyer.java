package com.z.roomshop.beans;

/**
 * Created by ZJer on 2017/4/30.
 */

public class Buyer {
    private String BuyerID = "";
    private String BuyerName = "";
    private String BuyerNickName = "";
    private String BuyerPassword = "";
    private String BuyerPhone = "";
    private String BuyerAddress = "";

    public String getBuyerID() {
        return BuyerID;
    }

    public String getBuyerName() {
        return BuyerName;
    }

    public String getBuyerNickName() {
        return BuyerNickName;
    }

    public void setBuyerID(String buyerID) {
        BuyerID = buyerID;
    }

    public void setBuyerName(String buyerName) {
        BuyerName = buyerName;
    }

    public void setBuyerNickName(String buyerNickName) {
        BuyerNickName = buyerNickName;
    }

    public String getBuyerAddress() {
        return BuyerAddress;
    }

    public String getBuyerPassword() {
        return BuyerPassword;
    }

    public String getBuyerPhone() {
        return BuyerPhone;
    }

    public void setBuyerAddress(String buyerAddress) {
        BuyerAddress = buyerAddress;
    }

    public void setBuyerPassword(String buyerPassword) {
        BuyerPassword = buyerPassword;
    }

    public void setBuyerPhone(String buyerPhone) {
        BuyerPhone = buyerPhone;
    }

    @Override
    public String toString() {
        return "Buyer [BuyerID=" + BuyerID + ", BuyerName=" + BuyerName
                + ", BuyerNickName=" + BuyerNickName + ", BuyerPassword="
                + BuyerPassword + ", BuyerPhone=" + BuyerPhone
                + ", BuyerAddress=" + BuyerAddress + "]";
    }
}
