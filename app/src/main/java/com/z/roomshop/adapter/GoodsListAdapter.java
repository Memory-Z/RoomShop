package com.z.roomshop.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.z.roomshop.R;
import com.z.roomshop.beans.Goods;

import java.util.List;

/**
 * Created by ZJer on 2017/5/19.
 */

public class GoodsListAdapter extends BaseAdapter {
    private Context mContext;
    private List<Goods> mGoodsList;
    private List<Bitmap> mBitmapList;

    /**
     * 初始化商品适配器，
     * @param context           上下文环境
     * @param goodsList         商品列表
     * @param bitmapList        商品图片列表
     */
    public GoodsListAdapter(Context context, List<Goods> goodsList, List<Bitmap> bitmapList) {
        mContext = context;
        mGoodsList = goodsList;
        mBitmapList = bitmapList;
    }

    @Override
    public int getCount() {
        return mGoodsList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_goods, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.img_list_goods_image);
            holder.txtName = (TextView) convertView.findViewById(R.id.txt_list_gooods_name);
            holder.txtNum = (TextView) convertView.findViewById(R.id.txt_list_goods_num);
            holder.txtPriceY = (TextView) convertView.findViewById(R.id.txt_list_goods_price_Y);
            holder.txtPriceF = (TextView) convertView.findViewById(R.id.txt_list_goods_price_F);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imageView.setImageBitmap(mBitmapList.get(position));
        holder.txtName.setText(mGoodsList.get(position).getGoodsName());
        holder.txtNum.setText(mGoodsList.get(position).getGoodsNum() + "");
        Float price = mGoodsList.get(position).getGoodsPrice();
        int priceY = (int) (price / 1);
        int priceF = (int) (price % 1);
        holder.txtPriceY.setText(priceY + "");
        holder.txtPriceF.setText(priceF + "");
        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView txtName, txtNum, txtPriceY, txtPriceF;
    }
}
