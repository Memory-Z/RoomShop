package com.z.roomshop.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ZJer on 2017/5/22.
 */

public class GsonTools {

    public GsonTools() {

    }

    public static <T> T getString(String gsonString, Class<T> cls) {
        T t = null;
        Gson gson = new Gson();
        try {
            t = gson.fromJson(gsonString, cls);
        } catch (JsonSyntaxException e){
            e.printStackTrace();
        }
        return t;
    }

    public static <T> List<T> getList(String gsonString, Class<T> cls) {
        List<T> list = new ArrayList<>();
        Gson gson = new Gson();
        try {
            list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
            }.getType());
        } catch (JsonSyntaxException e){
            e.printStackTrace();
        }
        return list;
    }

    public static List<String> getListString(String gsonString) {
        List<String> list = new ArrayList<>();
        Gson gson = new Gson();
        try {
            list = gson.fromJson(gsonString, new TypeToken<List<String>>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Map<String, Object>> getListMap(String gsonString) {
        List<Map<String, Object>> list = new ArrayList<>();
        Gson gson = new Gson();
        try {
            list = gson.fromJson(gsonString, new TypeToken<List<Map<String, Object>>>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return list;
    }

}
