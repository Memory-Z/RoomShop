package com.z.roomshop.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.z.roomshop.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZJer on 2017/5/5.
 */

public class MainCartFragment extends Fragment {
    private ListView list_cart;
    private ListAdapter adapter;
    private String[] name = {"photo", "name", "num", "total"};
    private int[] nameId = {R.id.img_cart_item_photo, R.id.txt_cart_item_name,
            R.id.txt_cart_item_num, R.id.txt_cart_item_total};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_cart_fragment, null);
        initData(v);
        return v;
    }
    private void initData(View v) {


        list_cart = (ListView) v.findViewById(R.id.list_main_cart);
        adapter = new SimpleAdapter(v.getContext(), getCartData(), R.layout.main_cart_item, name, nameId);
        list_cart.setAdapter(adapter);
    }

    private List<Map<String, Object>> getCartData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "西瓜");
        map.put("num", "9");
        map.put("total", "180.76");
        list.add(map);
        map.put("name", "西瓜");
        map.put("num", "9");
        map.put("total", "180.76");
        list.add(map);
        return list;
    }
}
