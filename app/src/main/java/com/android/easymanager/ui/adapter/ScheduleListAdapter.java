package com.android.easymanager.ui.adapter;

import com.android.easymanager.R;
import android.content.Context;
import com.android.easymanager.ui.bean.ScheduleGroupEntry;
import com.android.easymanager.ui.bean.ScheduleItem;
import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import java.util.ArrayList;

/**
 * Created by PC-xiaoming on 2019/5/13.
 */

public class ScheduleListAdapter extends GroupedRecyclerViewAdapter{

    private ArrayList<ScheduleGroupEntry> mGroups;

    public ScheduleListAdapter(Context context, ArrayList<ScheduleGroupEntry> groups) {
        super(context);
        mGroups = groups;
    }

    public void addItem(ScheduleItem item){
        ArrayList<ScheduleItem> children = mGroups.get(0).getChildren();
        children.add(item);
        this.notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return mGroups == null ? 0 : mGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<ScheduleItem> children = mGroups.get(groupPosition).getChildren();
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
        return R.layout.schedule_item_header;
    }

    @Override
    public int getFooterLayout(int viewType) {
        return R.layout.schedule_item_footer;
    }

    @Override
    public int getChildLayout(int viewType) {
        return R.layout.schedule_item_layout;
    }

    @Override
    public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {
        ScheduleGroupEntry entity = mGroups.get(groupPosition);
        holder.setText(R.id.tv_header, entity.getHeader());
    }

    @Override
    public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {
        ScheduleGroupEntry entity = mGroups.get(groupPosition);
        holder.setText(R.id.tv_footer, entity.getFooter());
    }

    @Override
    public void onBindChildViewHolder(BaseViewHolder holder, int groupPosition, int childPosition) {
        ScheduleItem item = mGroups.get(groupPosition).getChildren().get(childPosition);
        //holder.setText(R.id.tv_child, entity.getChild());
        holder.setImageResource(R.id.rv_left,item.getRes());
        holder.setText(R.id.rv_time,item.getTime());
        holder.setText(R.id.rv_title,item.getTitle());
    }
}
