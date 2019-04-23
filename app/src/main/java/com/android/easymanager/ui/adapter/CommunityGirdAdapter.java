package com.android.easymanager.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView.ViewHolder;
import com.android.easymanager.R;
import com.android.easymanager.ui.widget.CommunityGridLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC-xiaoming on 2019/4/23.
 */

public class CommunityGirdAdapter extends RecyclerView.Adapter<ViewHolder>{

    private List<String> mDatas = new ArrayList<>();
    private Context mContext;
    private final static int HEADER_TYPE = 1;
    private final static int LIST_TYP = 2;

    public CommunityGirdAdapter(List<String> datas, Context context){
        this.mDatas = datas;
        this.mContext = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        if(viewType == HEADER_TYPE){
            viewHolder = new HeaderHolder(LayoutInflater.from(mContext).inflate(R.layout.item_community_layout,null));
        }else{
            viewHolder = new ListViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_community_status_layout,null));
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof HeaderHolder){

        }else if(holder instanceof ListViewHolder){
            ((ListViewHolder)holder).layout.setIsShowAll(true);
            ((ListViewHolder)holder).layout.setUrlList(mDatas);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return HEADER_TYPE;
        }else{
            return LIST_TYP;
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        CommunityGridLayout layout;
        public ListViewHolder(View itemView) {
            super(itemView);
            layout = (CommunityGridLayout) itemView.findViewById(R.id.layout_nine_grid);
        }
    }

    public class HeaderHolder  extends RecyclerView.ViewHolder{

        public HeaderHolder(View itemView) {
            super(itemView);
        }
    }
}
