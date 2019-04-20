package com.android.easymanager.ui.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.easymanager.IxiaApplication;
import com.android.easymanager.R;
import com.android.easymanager.database.FriendEntry;
import com.android.easymanager.database.FriendInvitation;
import com.android.easymanager.database.FriendRecommendEntry;
import com.android.easymanager.ui.activity.FriendInfoActivity;
import com.android.easymanager.utils.DialogCreator;
import com.android.easymanager.view.SwipeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.im.android.api.ContactManager;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.android.eventbus.EventBus;
import cn.jpush.im.api.BasicCallback;

public class FriendRecommendAdapter extends BaseRecyclerAdapter<FriendRecommendEntry> {
    private Activity mContext;

    public FriendRecommendAdapter(Activity context) {
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
            final ContactHolder holder = (ContactHolder) viewHolder;
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

            Log.i("linmei","****item.state=="+item.state);

            if (item.state.equals(FriendInvitation.INVITED.getValue())) {
                holder.addBtn.setVisibility(View.VISIBLE);
                holder.tv_state.setVisibility(View.GONE);
                holder.addBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Dialog dialog = DialogCreator.createLoadingDialog(mContext, "正在加载");
                        ContactManager.acceptInvitation(item.username, item.appKey, new BasicCallback() {
                            @Override
                            public void gotResult(int responseCode, String responseMessage) {
                                dialog.dismiss();
                                if (responseCode == 0) {
                                    item.state = FriendInvitation.ACCEPTED.getValue();
                                    item.save();
                                    holder.addBtn.setVisibility(View.GONE);
                                    holder.tv_state.setVisibility(View.VISIBLE);
//                                    holder.tv_state.setTextColor(mContext.getResources().getColor(R.color.contacts_pinner_txt));
                                    holder.tv_state.setText("已添加");
//                                    EventBus.getDefault().post(new Event.Builder().setType(EventType.addFriend)
//                                            .setFriendId(item.getId()).build());

                                    //添加好友成功创建个会话
                                    Conversation conversation = JMessageClient.getSingleConversation(item.username, item.appKey);
                                    if (conversation == null) {
                                        conversation = Conversation.createSingleConversation(item.username, item.appKey);
//                                        EventBus.getDefault().post(new Event.Builder()
//                                                .setType(EventType.createConversation)
//                                                .setConversation(conversation)
//                                                .build());
                                    }
                                }
                            }
                        });
                    }
                });
            } else if (item.state.equals(FriendInvitation.ACCEPTED.getValue())) {
                holder.addBtn.setVisibility(View.GONE);
                holder.tv_state.setVisibility(View.VISIBLE);
                holder.tv_state.setText(mContext.getString(R.string.added));
                holder.tv_state.setTextColor(mContext.getResources().getColor(R.color.contacts_pinner_txt));
            } else if (item.state.equals(FriendInvitation.INVITING.getValue())) {
                holder.addBtn.setVisibility(View.GONE);
                holder.tv_state.setVisibility(View.VISIBLE);
                holder.tv_state.setText(mContext.getString(R.string.friend_inviting));
                holder.tv_state.setTextColor(mContext.getResources().getColor(R.color.wait_inviting));
            } else if (item.state.equals(FriendInvitation.BE_REFUSED.getValue())) {
                holder.addBtn.setVisibility(View.GONE);
                holder.tv_state.setVisibility(View.VISIBLE);
                holder.tv_state.setText(mContext.getString(R.string.decline_friend_invitation));
                holder.tv_state.setTextColor(mContext.getResources().getColor(R.color.contacts_pinner_txt));
            } else {
                holder.addBtn.setVisibility(View.GONE);
                holder.tv_state.setVisibility(View.VISIBLE);
                holder.tv_state.setText(mContext.getString(R.string.refused));
                holder.tv_state.setTextColor(mContext.getResources().getColor(R.color.contacts_pinner_txt));
            }

            holder.itemLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final FriendRecommendEntry entry = item;
                    final Intent intent;
                    if (entry.state.equals(FriendInvitation.INVITED.getValue())) {
                        //1.没同意也没拒绝时--> 是否同意界面
//                        intent = new Intent(mContext, SearchFriendDetailActivity.class);
//                        intent.putExtra("reason", item.reason);
//                        intent.putExtra("position", position);
//                        intent.putExtra(JGApplication.TARGET_ID, entry.username);
//                        intent.putExtra(JGApplication.TARGET_APP_KEY, entry.appKey);
//                        mContext.startActivityForResult(intent, 0);
                    } else if (entry.state.equals(FriendInvitation.ACCEPTED.getValue())) {//2.已经添加的 --> 好友详情
                        JMessageClient.getUserInfo(item.username, new GetUserInfoCallback() {
                            @Override
                            public void gotResult(int i, String s, UserInfo userInfo) {
                                if (i == 0) {
                                    Intent intent1 = new Intent();
                                    if (userInfo.isFriend()) {
                                        intent1.setClass(mContext, FriendInfoActivity.class);
                                        intent1.putExtra("fromContact", true);
                                    } else {
//                                        intent1.setClass(mContext, GroupNotFriendActivity.class);
                                    }
                                    intent1.putExtra(IxiaApplication.TARGET_ID, entry.username);
                                    intent1.putExtra(IxiaApplication.TARGET_APP_KEY, entry.appKey);
                                    mContext.startActivityForResult(intent1, 0);
                                }
                            }
                        });
                    } else {//3.自己拒绝、被对方拒绝、等待对方验证 --> 用户资料界面
//                        intent = new Intent(mContext, GroupNotFriendActivity.class);
//                        intent.putExtra("reason", item.reason);
//                        intent.putExtra(IxiaApplication.TARGET_ID, entry.username);
//                        intent.putExtra(IxiaApplication.TARGET_APP_KEY, entry.appKey);
//                        mContext.startActivityForResult(intent, 0);
                    }

                }
            });


            holder.swp_layout.addSwipeListener(new SwipeLayout.SwipeListener() {
                @Override
                public void onStartOpen(SwipeLayout layout) {

                }

                @Override
                public void onOpen(SwipeLayout layout) {
                    //侧滑删除拉出来后,点击删除,删除此条目
                    holder.txt_del.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //final FriendRecommendEntry entry = item;
                            FriendRecommendEntry.deleteEntry(item);
                            //mList.remove(position);
                            getDatas().remove(getRealPosition(holder));
                            notifyDataSetChanged();

                        }
                    });
                    //侧滑删除拉出来后,点击整个条目的话,删除回退回去
                    holder.itemLl.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            holder.swp_layout.cancelPull();

                        }
                    });
                }

                @Override
                public void onStartClose(SwipeLayout layout) {

                }

                @Override
                public void onClose(SwipeLayout layout) {
                    /**
                     * 这里分三种情况
                     * 1.没同意也没拒绝时--> 是否同意界面
                     * 2.已经添加的 --> 好友详情
                     * 3.自己拒绝、被对方拒绝、等待对方验证 --> 用户资料界面
                     */
                    holder.itemLl.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            FriendRecommendEntry entry = item;
                            Intent intent = null;
                            if (entry.state.equals(FriendInvitation.INVITED.getValue())) {
                                //1.没同意也没拒绝时--> 是否同意界面
//                                intent = new Intent(mContext, SearchFriendDetailActivity.class);
//                                intent.putExtra("reason", item.reason);
//                                intent.putExtra("position", position);
                            } else if (entry.state.equals(FriendInvitation.ACCEPTED.getValue())) {
                                //2.已经添加的 --> 好友详情
                                intent = new Intent(mContext, FriendInfoActivity.class);
                                intent.putExtra("fromContact", true);
                            } else {//3.自己拒绝、被对方拒绝、等待对方验证 --> 用户资料界面
//                                intent = new Intent(mContext, GroupNotFriendActivity.class);
//                                intent.putExtra("reason", item.reason);
                            }
                            intent.putExtra(IxiaApplication.TARGET_ID, entry.username);
                            intent.putExtra(IxiaApplication.TARGET_APP_KEY, entry.appKey);
                            mContext.startActivityForResult(intent, 0);
                        }
                    });
                }

                @Override
                public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

                }

                @Override
                public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

                }
            });
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
        TextView txt_del;
        @BindView(R.id.friend_verify_item_ll)
        LinearLayout itemLl;
        @BindView(R.id.swp_layout)
        SwipeLayout swp_layout;

        ContactHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }
    }

}

