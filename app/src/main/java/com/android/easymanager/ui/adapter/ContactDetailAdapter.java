package com.android.easymanager.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.easymanager.R;
import com.android.easymanager.ui.bean.ListItemEntry;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactDetailAdapter extends RecyclerView.Adapter<ContactDetailAdapter.mViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<ListItemEntry> mList;
    private RvOnItemListener mRvOnItemListener;

    public ContactDetailAdapter(Context context, List<ListItemEntry> list, RvOnItemListener listener) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.mRvOnItemListener=listener;
        mList = list;
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new mViewHolder(mLayoutInflater.inflate(R.layout.user_settings_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        ListItemEntry item = mList.get(position);
        holder.tv_title.setText(item.getTitle());
        holder.tv_name.setVisibility(View.VISIBLE);
        holder.tv_name.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class mViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.left_text)
        TextView tv_title;
        @BindView(R.id.right_text)
        TextView tv_name;

        mViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,48*2);
            itemView.setLayoutParams(layoutParams);
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
        void onItemClick(int position, ListItemEntry entry);
    }

}

