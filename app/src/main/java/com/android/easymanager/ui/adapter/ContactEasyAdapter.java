package com.android.easymanager.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.easymanager.R;
import com.android.easymanager.ui.bean.Contact;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactEasyAdapter extends RecyclerView.Adapter<ContactEasyAdapter.ContactHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<Contact> mList;
    private RvOnItemListener mRvOnItemListener;

    public ContactEasyAdapter(Context context, List<Contact> list,RvOnItemListener listener) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.mRvOnItemListener=listener;
        mList = list;
    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactHolder(mLayoutInflater.inflate(R.layout.contact_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ContactHolder holder, int position) {
        Contact item = mList.get(position);
            holder.tv_name.setText(item.getmName());
            holder.tv_class.setText(item.getmClassName());
            if(item.isFrident()){
                holder.tv_add.setText("已添加");
                holder.tv_add.setTextColor(Color.parseColor("#101010"));
                holder.tv_add.setBackgroundColor(Color.parseColor("#00000000"));
            }else{
                holder.tv_add.setText("添加");
                holder.tv_add.setTextColor(Color.parseColor("#FFFFFF"));
                holder.tv_add.setBackgroundColor(Color.parseColor("#68C85D"));
            }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mRvOnItemListener != null) {
                        mRvOnItemListener.onItemClick(getAdapterPosition(), mList.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    public interface RvOnItemListener{
        void onItemClick(int position,Contact entry);
    }

}

