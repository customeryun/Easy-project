package com.android.easymanager.ui.adapter;

import android.content.Context;

import com.android.easymanager.R;
import com.android.easymanager.ui.bean.ApplyChildEntry;
import com.android.easymanager.ui.bean.ExpandGroupEntry;
import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;

import java.util.ArrayList;

/**
 * Created by PC-xiaoming on 2019/4/5.
 */

public class ApplyAdapter extends GroupedRecyclerViewAdapter {

    private ArrayList<ExpandGroupEntry> mGroups;
    private Context mContext;

    public ApplyAdapter(Context context, ArrayList<ExpandGroupEntry> groups) {
        super(context);
        this.mGroups = groups;
        this.mContext = context;
    }

    @Override
    public int getGroupCount() {
        return mGroups!=null?mGroups.size():0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(!isExpand(groupPosition)){
            return 0;
        }
        ArrayList<ApplyChildEntry> children = mGroups.get(groupPosition).getChildEntries();
        return children == null ? 0 : children.size();
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
        return R.layout.adapter_expandable_header;
    }

    @Override
    public int getFooterLayout(int viewType) {
        return 0;
    }

    @Override
    public int getChildLayout(int viewType) {
        return R.layout.apply_child;
    }

    @Override
    public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {
        ExpandGroupEntry groupEntry = mGroups.get(groupPosition);
        holder.setText(R.id.tv_expandable_header, groupEntry.getHeader());
        if(isExpand(groupPosition)){
            holder.setText(R.id.iv_state, "收起");
        }else {
            holder.setText(R.id.iv_state, "展开");

        }
    }

    @Override
    public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {

    }

    @Override
    public void onBindChildViewHolder(BaseViewHolder holder, int groupPosition, int childPosition) {
        ApplyChildEntry entity = mGroups.get(groupPosition).getChildEntries().get(childPosition);
        holder.setText(R.id.apply_name, entity.getName());
    }

    /**
     * 判断当前组是否展开
     *
     * @param groupPosition
     * @return
     */
    public boolean isExpand(int groupPosition) {
        ExpandGroupEntry entity = mGroups.get(groupPosition);
        return entity.isExpand();
    }

    /**
     * 展开一个组
     *
     * @param groupPosition
     */
    public void expandGroup(int groupPosition) {
        expandGroup(groupPosition, false);
    }

    /**
     * 展开一个组
     *
     * @param groupPosition
     * @param animate
     */
    public void expandGroup(int groupPosition, boolean animate) {
        ExpandGroupEntry entity = mGroups.get(groupPosition);
        entity.setExpand(true);
        if (animate) {
            notifyChildrenInserted(groupPosition);
        } else {
            notifyDataChanged();
        }
    }

    /**
     * 收起一个组
     *
     * @param groupPosition
     */
    public void collapseGroup(int groupPosition) {
        collapseGroup(groupPosition, false);
    }

    /**
     * 收起一个组
     *
     * @param groupPosition
     * @param animate
     */
    public void collapseGroup(int groupPosition, boolean animate) {
        ExpandGroupEntry entity = mGroups.get(groupPosition);
        entity.setExpand(false);
        if (animate) {
            notifyChildrenRemoved(groupPosition);
        } else {
            notifyDataChanged();
        }
    }
}
