package com.android.easymanager.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.easymanager.R;
import com.android.easymanager.ui.bean.MessageGroupEntry;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageGroupAdapter extends RecyclerView.Adapter<MessageGroupAdapter.MessageHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<MessageGroupEntry> mList;
    private RvOnItemListener mRvOnItemListener;

    public MessageGroupAdapter(Context context, List<MessageGroupEntry> list,RvOnItemListener listener) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.mRvOnItemListener=listener;
        mList = list;
    }

    @Override
    public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MessageHolder(mLayoutInflater.inflate(R.layout.message_gropu_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(MessageHolder holder, int position) {
        MessageGroupEntry item = mList.get(position);
        holder.tv_name.setText(item.getContactName().substring(0,1));
        holder.tv_title.setText(item.getTitle());
        holder.tv_body.setText(item.getBody());
        holder.tv_time.setText(item.getTime());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class MessageHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.contact_name)
        TextView tv_name;
        @BindView(R.id.message_title)
        TextView tv_title;
        @BindView(R.id.message_time)
        TextView tv_time;
        @BindView(R.id.message_body)
        TextView tv_body;

        MessageHolder(View view) {
            super(view);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mRvOnItemListener!=null){
                        mRvOnItemListener.onItemClick(getAdapterPosition(),mList.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    public interface RvOnItemListener{
        void onItemClick(int position,MessageGroupEntry entry);
    }
}

