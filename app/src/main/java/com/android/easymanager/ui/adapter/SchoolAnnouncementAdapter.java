package com.android.easymanager.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.easymanager.R;

import java.util.ArrayList;

/**
 * Created by 肖明 on 2019/4/24.
 */

public class SchoolAnnouncementAdapter extends RecyclerView.Adapter<SchoolAnnouncementAdapter.ContentHodler>{

    private ArrayList<String> mDatas = new ArrayList<>();
    private Context mContext;

    public SchoolAnnouncementAdapter(ArrayList<String> data,Context context){
        this.mDatas = data;
        this.mContext = context;
        Log.d("", "SchoolAnnouncementAdapter: "+mDatas.size());
    }

    @Override
    public ContentHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_school_announcement,null);
        return new ContentHodler(itemView);
    }

    @Override
    public void onBindViewHolder(ContentHodler holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ContentHodler extends RecyclerView.ViewHolder{

        public ContentHodler(View itemView) {
            super(itemView);
        }
    }
}
