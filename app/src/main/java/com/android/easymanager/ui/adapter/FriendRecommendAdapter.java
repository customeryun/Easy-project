package com.android.easymanager.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.easymanager.IxiaApplication;
import com.android.easymanager.R;
import com.android.easymanager.database.FriendEntry;
import com.android.easymanager.database.FriendInvitation;
import com.android.easymanager.database.FriendRecommendEntry;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.UserInfo;

public class FriendRecommendAdapter extends BaseRecyclerAdapter<FriendRecommendEntry> {
    private Context mContext;

    public FriendRecommendAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend_recomend, parent, false);
        return new ContactHolder(layout);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, final FriendRecommendEntry item) {
        if(viewHolder instanceof ContactHolder) {
            ContactHolder holder = (ContactHolder) viewHolder;
            holder.tv_name.setText(item.username);
            holder.tv_reason.setText(item.reason);
            JMessageClient.getUserInfo(item.username, new GetUserInfoCallback() {
                @Override
                public void gotResult(int i, String s, UserInfo userInfo) {
                    if (i == 0) {
                        if (userInfo.isFriend()) {
                            item.state = FriendInvitation.ACCEPTED.getValue();
                            item.save();
                            FriendEntry entry = FriendEntry.getFriend(IxiaApplication.getUserEntry(), item.username, item.appKey);
//                            if (entry == null) {
//                                EventBus.getDefault().post(new Event.Builder().setType(EventType.addFriend)
//                                        .setFriendId(item.getId()).build());
//                            }
                        }
                    }
                }
            });

            if (item.state.equals(FriendInvitation.INVITED.getValue())) {
                holder.addBtn.setVisibility(View.VISIBLE);
                holder.tv_state.setVisibility(View.GONE);
                holder.addBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
            } else if (item.state.equals(FriendInvitation.ACCEPTED.getValue())) {
                holder.addBtn.setVisibility(View.GONE);
                holder.tv_state.setVisibility(View.VISIBLE);
                holder.tv_state.setText(mContext.getString(R.string.added));
            } else if (item.state.equals(FriendInvitation.INVITING.getValue())) {
                holder.addBtn.setVisibility(View.GONE);
                holder.tv_state.setVisibility(View.VISIBLE);
                holder.tv_state.setText(mContext.getString(R.string.friend_inviting));
            } else if (item.state.equals(FriendInvitation.BE_REFUSED.getValue())) {
                holder.addBtn.setVisibility(View.GONE);
                holder.tv_state.setVisibility(View.VISIBLE);
                holder.tv_state.setText(mContext.getString(R.string.decline_friend_invitation));
            } else {
                holder.addBtn.setVisibility(View.GONE);
                holder.tv_state.setVisibility(View.VISIBLE);
                holder.tv_state.setText(mContext.getString(R.string.refused));
            }
            //控件事件监听：待处理.......
        }
    }

    public class ContactHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_name)
        TextView tv_name;
        @BindView(R.id.item_reason)
        TextView tv_reason;
        @BindView(R.id.item_add_btn)
        TextView addBtn;
        @BindView(R.id.item_state)
        TextView tv_state;
        @BindView(R.id.txt_del)
        TextView tv_del;

        ContactHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }
    }

}

