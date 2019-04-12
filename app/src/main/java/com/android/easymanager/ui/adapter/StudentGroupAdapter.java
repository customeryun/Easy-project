package com.android.easymanager.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.easymanager.R;
import com.android.easymanager.ui.bean.ContactGroupEntry;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentGroupAdapter extends BaseRecyclerAdapter<ContactGroupEntry> {
    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_group_item_layout, parent, false);
        return new ContactHolder(layout);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, ContactGroupEntry item) {
        if(viewHolder instanceof ContactHolder){
            ContactHolder holder = (ContactHolder)viewHolder;
            holder.tv_name.setText(item.getName());
            holder.tv_child_count.setText("("+item.getChildSize()+")");
        }
    }

    public class ContactHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.group_name)
        TextView tv_name;
        @BindView(R.id.group_child_count)
        TextView tv_child_count;

        ContactHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }
    }

}

