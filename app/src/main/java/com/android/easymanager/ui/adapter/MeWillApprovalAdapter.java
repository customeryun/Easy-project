package com.android.easymanager.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.easymanager.R;
import com.android.easymanager.ui.bean.MeApprovalChildEntry;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC-xiaoming on 2019/4/6.
 */

public class MeWillApprovalAdapter extends RecyclerView.Adapter<MeWillApprovalAdapter.WillApprovalHolder>{

    private Context mContext;
    private ArrayList<MeApprovalChildEntry> childEntries;

    public MeWillApprovalAdapter(Context context, ArrayList<MeApprovalChildEntry> childEntries){
        this.mContext = context;
        this.childEntries = childEntries;
    }

    @Override
    public WillApprovalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.me_will_approval_item_layout,parent,false);
        return new WillApprovalHolder(view);
    }

    @Override
    public void onBindViewHolder(WillApprovalHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return childEntries!=null?childEntries.size():0;
    }

    public  class WillApprovalHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.will_approval_photo)
        ImageView will_approval_photo;
        @BindView(R.id.will_approval_name)
        TextView will_approval_name;
        @BindView(R.id.will_approval_start)
        TextView will_approval_start;
        @BindView(R.id.will_approval_end)
        TextView will_approval_end;
        @BindView(R.id.will_time)
        TextView will_time;

        public WillApprovalHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
