package com.android.easymanager.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.easymanager.R;
import com.android.easymanager.ui.bean.ListItemEntry;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactDetailAdapter extends BaseRecyclerAdapter<ListItemEntry> {

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_settings_item_layout, parent, false);
        return new MyHolder(layout);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int position, ListItemEntry data) {
        if(viewHolder instanceof MyHolder) {
            ((MyHolder) viewHolder).tv_title.setText(data.getTitle());
            ((MyHolder) viewHolder).tv_name.setVisibility(View.VISIBLE);
            ((MyHolder) viewHolder).tv_name.setText(data.getName());
            ((MyHolder) viewHolder).tv_name.setText(data.getName());
        }
    }

    class MyHolder extends BaseRecyclerAdapter.Holder {
        @BindView(R.id.left_text)
        TextView tv_title;
        @BindView(R.id.right_text)
        TextView tv_name;
        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,48*2);
            itemView.setLayoutParams(layoutParams);
        }
    }
}
