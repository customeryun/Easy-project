package com.android.easymanager.ui.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.easymanager.R;
import com.android.easymanager.ui.bean.Contact;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactEasyAdapter extends BaseRecyclerAdapter<Contact> {
    private boolean mIsShowRightIcon;

    public ContactEasyAdapter(boolean isShowRightIcon) {
        mIsShowRightIcon = isShowRightIcon;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item_layout, parent, false);
        return new ContactHolder(layout);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, Contact item) {
        if(viewHolder instanceof ContactHolder) {
            ContactHolder holder = (ContactHolder) viewHolder;
            holder.tv_name.setText(item.getmName());
            holder.tv_class.setText(item.getmClassName());
            if(item.isFrident()){
                holder.tv_add.setText("已添加");
                holder.tv_add.setTextColor(Color.parseColor("#101010"));
                holder.tv_add.setBackgroundColor(Color.parseColor("#00000000"));
            }else{
                holder.tv_add.setText("接受");
                holder.tv_add.setTextColor(Color.parseColor("#FFFFFF"));
                holder.tv_add.setBackgroundColor(Color.parseColor("#68C85D"));
            }
            holder.tv_add.setVisibility(mIsShowRightIcon?View.VISIBLE:View.GONE);
        }
    }

    public class ContactHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.contact_name)
        TextView tv_name;
        @BindView(R.id.contact_class)
        TextView tv_class;
        @BindView(R.id.add)
        TextView tv_add;

        ContactHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }
    }

}

