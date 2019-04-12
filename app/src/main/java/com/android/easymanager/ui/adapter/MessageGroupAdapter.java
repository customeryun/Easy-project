package com.android.easymanager.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.easymanager.R;
import com.android.easymanager.ui.bean.MessageGroupEntry;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageGroupAdapter extends BaseRecyclerAdapter<MessageGroupEntry> {
    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_gropu_item_layout, parent, false);
        return new MyHolder(layout);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, MessageGroupEntry item) {
        if(viewHolder instanceof MyHolder) {
            MyHolder holder = (MyHolder)viewHolder;
            holder.tv_name.setText(item.getContactName().substring(0,1));
            holder.tv_title.setText(item.getTitle());
            holder.tv_body.setText(item.getBody());
            holder.tv_time.setText(item.getTime());
        }
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.contact_name)
        TextView tv_name;
        @BindView(R.id.message_title)
        TextView tv_title;
        @BindView(R.id.message_time)
        TextView tv_time;
        @BindView(R.id.message_body)
        TextView tv_body;

        MyHolder(View view) {
            super(view);
            ButterKnife.bind(this,itemView);
        }
    }

}

