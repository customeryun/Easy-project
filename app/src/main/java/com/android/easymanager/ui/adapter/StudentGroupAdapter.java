package com.android.easymanager.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.easymanager.R;
import com.android.easymanager.ui.bean.ContactGroupEntry;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentGroupAdapter extends RecyclerView.Adapter<StudentGroupAdapter.ContactHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<ContactGroupEntry> mList;
    private RvOnItemListener mRvOnItemListener;

    public StudentGroupAdapter(Context context, List<ContactGroupEntry> list, RvOnItemListener listener) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.mRvOnItemListener=listener;
        mList = list;
    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactHolder(mLayoutInflater.inflate(R.layout.contact_group_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ContactHolder holder, int position) {
        ContactGroupEntry item = mList.get(position);
            holder.tv_name.setText(item.getName());
            holder.tv_child_count.setText("("+item.getChildSize()+")");
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    public class ContactHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.group_name)
        TextView tv_name;
        @BindView(R.id.group_child_count)
        TextView tv_child_count;

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
        void onItemClick(int position, ContactGroupEntry entry);
    }

}

