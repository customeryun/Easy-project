package com.android.easymanager.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.easymanager.R;
import java.util.ArrayList;

public class ApprovalCommitAdapter extends Adapter<ApprovalCommitAdapter.CommitHolder> {
    private Context mContext;
    private ArrayList<String> mData;

    class CommitHolder extends ViewHolder {
        public CommitHolder(View itemView) {
            super(itemView);
        }
    }

    public ApprovalCommitAdapter(Context context, ArrayList<String> data) {
        this.mContext = context;
        this.mData = data;
    }

    public CommitHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommitHolder(LayoutInflater.from(this.mContext).inflate(R.layout.me_approval_commited_item_layout, parent, false));
    }

    public void onBindViewHolder(CommitHolder holder, int position) {
    }

    public int getItemCount() {
        return this.mData != null ? this.mData.size() : 0;
    }
}