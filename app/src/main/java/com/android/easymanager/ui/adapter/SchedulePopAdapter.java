package com.android.easymanager.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.easymanager.R;

import java.util.ArrayList;

/**
 * Created by PC-xiaoming on 2019/4/7.
 */

public class SchedulePopAdapter extends RecyclerView.Adapter<SchedulePopAdapter.PopHolder>{

    private ArrayList<String> mData;
    private Context mContext;

    public SchedulePopAdapter(Context context,ArrayList<String> data){
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public PopHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.schedule_pop_item_layout,parent,false);
        return new PopHolder(view);
    }

    @Override
    public void onBindViewHolder(PopHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mData!=null?mData.size():0;
    }

    class PopHolder extends RecyclerView.ViewHolder{

        public PopHolder(View itemView) {
            super(itemView);
        }
    }
}
