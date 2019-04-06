package com.android.easymanager.ui.adapter;

import android.content.Context;

import com.android.easymanager.R;
import com.android.easymanager.ui.bean.MeApprovalChildEntry;
import com.android.easymanager.ui.bean.MeApprovalGroupEntry;
import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;

import java.util.ArrayList;

/**
 * Created by PC-xiaoming on 2019/4/6.
 */

public class MeApprovalAdapter extends GroupedRecyclerViewAdapter {

    private ArrayList<MeApprovalGroupEntry> entries;
    private Context mContext;

    public MeApprovalAdapter(Context context, ArrayList<MeApprovalGroupEntry> entries) {
        super(context);
        this.mContext = context;
        this.entries = entries;
    }

    @Override
    public int getGroupCount() {
        return entries!=null?entries.size():0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<MeApprovalChildEntry> childEntries = entries.get(groupPosition).getEntries();
        return childEntries!=null?childEntries.size():0;
    }

    @Override
    public boolean hasHeader(int groupPosition) {
        return true;
    }

    @Override
    public boolean hasFooter(int groupPosition) {
        return false;
    }

    @Override
    public int getHeaderLayout(int viewType) {
        return R.layout.me_approval_header_layout;
    }

    @Override
    public int getFooterLayout(int viewType) {
        return 0;
    }

    @Override
    public int getChildLayout(int viewType) {
        return R.layout.me_approval_item_layout;
    }

    @Override
    public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {
        MeApprovalGroupEntry groupEntry = entries.get(groupPosition);
        holder.setText(R.id.approval_time,groupEntry.getHeader());
    }

    @Override
    public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {

    }

    @Override
    public void onBindChildViewHolder(BaseViewHolder holder, int groupPosition, int childPosition) {

    }


    /**
     * 判断当前组是否展开
     *
     * @param groupPosition
     * @return
     */
    public boolean isExpand(int groupPosition){
        MeApprovalGroupEntry groupEntry = entries.get(groupPosition);
        return groupEntry.isExpand();
    }
}
