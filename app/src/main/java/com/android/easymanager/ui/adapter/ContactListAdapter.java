package com.android.easymanager.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.easymanager.IxiaApplication;
import com.android.easymanager.R;
import com.android.easymanager.database.FriendEntry;
import com.android.easymanager.ui.activity.FriendInfoActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactListAdapter extends BaseRecyclerAdapter<FriendEntry> {
    private Context mContext;
    public ContactListAdapter(Context context){
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item_layout, parent, false);
        return new ContactHolder(layout);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, final FriendEntry data) {
        if (viewHolder instanceof ContactHolder) {
            ContactHolder holder = (ContactHolder) viewHolder;
            holder.mTextView.setText(data.username);
            holder.mTextView_class.setText("***班级");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, FriendInfoActivity.class);
                    intent.putExtra("fromContact", true);
                    intent.putExtra(IxiaApplication.TARGET_ID, data.username);
                    intent.putExtra(IxiaApplication.TARGET_APP_KEY, data.appKey);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    public class ContactHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.contact_name)
        TextView mTextView;
        @BindView(R.id.contact_class)
        TextView mTextView_class;

        ContactHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }
    }
}

