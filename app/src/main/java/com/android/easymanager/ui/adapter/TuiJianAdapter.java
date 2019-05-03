package com.android.easymanager.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.easymanager.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC-xiaoming on 2019/5/3.
 */

public class TuiJianAdapter extends RecyclerView.Adapter<TuiJianAdapter.TuijianHolder> implements CallbackItemTouch {

    private ArrayList<String> mData;
    private Context mContext;

    public TuiJianAdapter(Context context, ArrayList<String> data) {
        this.mData = data;
        this.mContext = context;
    }

    @Override
    public TuijianHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TuijianHolder holder = new TuijianHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_sub_tuijian_layout, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(TuijianHolder holder, int position) {
        if (mData != null && mData.size() > 0) {
            switch (position) {
                case 0:
                    holder.tuijian_img.setImageResource(R.drawable.ic_home_shuse);
                    holder.tuijian_title.setText(R.string.tuijian_shuse);
                    break;
                case 1:
                    holder.tuijian_img.setImageResource(R.drawable.ic_home_baodao);
                    holder.tuijian_title.setText(R.string.tuijian_baodao);
                    break;
                case 2:
                    holder.tuijian_img.setImageResource(R.drawable.ic_home_mobile);
                    holder.tuijian_title.setText(R.string.tuijian_dingweidaka);
                    break;
                case 3:
                    holder.tuijian_img.setImageResource(R.drawable.ic_home_cursor);
                    holder.tuijian_title.setText(R.string.tuijian_xuanke);
                    break;
                case 4:
                    holder.tuijian_img.setImageResource(R.drawable.ic_home_shuse);
                    holder.tuijian_title.setText(R.string.tuijian_kechengbiao);
                    break;
                case 5:
                    holder.tuijian_img.setImageResource(R.drawable.ic_home_tuijian_add);
                    holder.tuijian_title.setText(R.string.tuijian_add);
            }
        }
    }

    @Override
    public int getItemCount() {
        Log.d("tuijian", "getItemCount: " + mData.size());
        return mData.size();
    }

    @Override
    public void itemTouchOnMove(int oldPosition, int newPosition) {
        mData.add(newPosition, mData.remove(oldPosition));// change position
        this.notifyItemMoved(oldPosition, newPosition); //not
    }

    class TuijianHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tuijian_img)
        ImageView tuijian_img;
        @BindView(R.id.tuijian_title)
        TextView tuijian_title;

        public TuijianHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
