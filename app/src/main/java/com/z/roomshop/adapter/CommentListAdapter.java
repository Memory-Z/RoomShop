package com.z.roomshop.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.z.roomshop.R;

import java.util.List;
import java.util.Map;

/**
 * Created by ZJer on 2017/5/23.
 */

public class CommentListAdapter extends BaseAdapter {
    private static final String TAG = "CommentListAdapter";

    private Context mContext;
    private List<Bitmap> mPhotoBitmapList;
    private List<Map<String, Object>> mCommentList;
    private List<String> mCommentBuyerName;

    public CommentListAdapter(Context context, List<Bitmap> photoBitmapList,
                              List<Map<String, Object>> commentList, List<String> commentBuyerName) {
        mContext = context;
        mPhotoBitmapList = photoBitmapList;
        mCommentList = commentList;
        mCommentBuyerName = commentBuyerName;
        Log.i(TAG, "--" + mCommentList.toString() + "---" + mCommentBuyerName.toString());
    }

    @Override
    public int getCount() {
        return mCommentList.size();
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_comment, null);
            viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) convertView.findViewById(R.id.img_list_comment_photo);
            viewHolder.commentName = (TextView) convertView.findViewById(R.id.txt_list_comment_buyer_name);
            viewHolder.commentTime = (TextView) convertView.findViewById(R.id.txt_list_comment_time);
            viewHolder.commentContent = (TextView) convertView.findViewById(R.id.txt_list_comment_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        viewHolder.image.setImageBitmap(mPhotoBitmapList.get(position));
        viewHolder.commentName.setText(mCommentBuyerName.get(position));
        viewHolder.commentTime.setText((CharSequence) mCommentList.get(position).get("CommentTime"));
        viewHolder.commentContent.setText((CharSequence) mCommentList.get(position).get("CommentContent"));
        Log.i(TAG, "=============getView==============" + mCommentBuyerName.get(position));
        return convertView;
    }

    class ViewHolder {
        ImageView image;
        TextView commentName, commentTime, commentContent;
    }
}
