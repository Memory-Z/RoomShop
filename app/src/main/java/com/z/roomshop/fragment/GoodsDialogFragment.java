package com.z.roomshop.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.z.roomshop.R;
import com.z.roomshop.beans.Goods;

import java.util.List;

/**
 * Created by ZJer on 2017/5/19.
 */

public class GoodsDialogFragment extends DialogFragment {
    private static final String TAG = "GoodsDialogFragment";

    private Context mContext;
    private List<Bitmap> mGoodsImageList;
    private List<Goods> mGoodsList;
    private View view = null;
    private ImageView mImageView_0, mImageView_1, mImageView_2, mImageView_3, mImageView_4;
    private TextView mTextViewGoodsName, mTextViewGoodsPriceY, mTextViewGoodsPriceF, mTextViewGoodsNum,
            mTextViewGoodsSellerName, mTextViewGoodsTetail, mTextViewGoodsShowMore;
    private ListView listView;

    public interface OnGoodsDialogListener{
        void getGoodsNum();
    }

    public GoodsDialogFragment(Context context, List<Bitmap> goodsImageList, List<Goods> goodsList) {
        super();
        this.mContext = context;
        this.mGoodsImageList = goodsImageList;
        this.mGoodsList = goodsList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "=============>>onCreate" + "");
        initView();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.i(TAG, "=============>>onCreateDialog");

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.FullScreen);
        builder.setView(view);
        return builder.create();

    }

    private void initView() {
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.dialog_goods, null);
        }
        mImageView_0 = (ImageView) view.findViewById(R.id.img_dialog_goods_1);
    }

    @Override
    public void onResume() {
        Log.i(TAG, "=============>>onResume");
        super.onResume();
    }

    public void setImage(int position) {
//        mImageView_0.setImageBitmap();
        if (mImageView_0 == null) {
            initView();
        }
        Log.i(TAG, "=============>>" + mGoodsImageList.get(position));
        mImageView_0.setImageBitmap(mGoodsImageList.get(position));
    }

}
