package com.android.easymanager.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.easymanager.R;
import com.android.easymanager.presenter.HomePresenter;
import com.android.easymanager.ui.activity.FriendInfoActivity;
import com.android.easymanager.ui.activity.SchoolAnnouncementActivity;
import com.android.easymanager.ui.activity.SchoolSocietyActivity;
import com.android.easymanager.ui.widget.CommunityGridLayout;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.shaohui.shareutil.ShareUtil;
import me.shaohui.shareutil.share.ShareListener;
import me.shaohui.shareutil.share.SharePlatform;

/**
 * Created by PC-xiaoming on 2019/4/23.
 */

public class CommunityGirdAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<String> mDatas = new ArrayList<>();
    private Context mContext;
    public final static int HEADER_TYPE = 1;
    public final static int LIST_TYP = 2;
    private HomeMainAdapter.OnRvItemListener mOnRvItemListener;
    private HomePresenter mHomePresenter;

    public CommunityGirdAdapter(List<String> datas, Context context) {
        this.mDatas = datas;
        this.mContext = context;
    }

    public void setOnRvItemListener(HomeMainAdapter.OnRvItemListener onRvItemListener){
        this.mOnRvItemListener = onRvItemListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        if (viewType == HEADER_TYPE) {
            viewHolder = new HeaderHolder(LayoutInflater.from(mContext).inflate(R.layout.item_community_layout, null));
        } else {
            viewHolder = new ListViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_community_status_layout, null));
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderHolder) {

        } else if (holder instanceof ListViewHolder) {
            ((ListViewHolder) holder).layout.setIsShowAll(true);
            ((ListViewHolder) holder).layout.setUrlList(mDatas);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER_TYPE;
        } else {
            return LIST_TYP;
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setPresenter(HomePresenter homePresenter) {
        this.mHomePresenter = homePresenter;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.layout_nine_grid)
        CommunityGridLayout layout;
        @BindView(R.id.comment_dianzhan_count)
        TextView comment_dianzhan_count;

        private int dianzhanCount;

        public ListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mOnRvItemListener!=null){
                        mOnRvItemListener.onItemClick(getAdapterPosition(),getItemViewType());
                    }
                }
            });
        }

        @OnClick({R.id.comment_dianzhan_count, R.id.comment_count, R.id.comment_share,R.id.home_pengyou_img})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.comment_dianzhan_count:
                    dianzhanCount++;
                    comment_dianzhan_count.setText("" + dianzhanCount);
                    break;
                case R.id.comment_count:
                    if (mHomePresenter != null) {
                        mHomePresenter.showEdit();
                    }
                    break;
                case R.id.comment_share:
                    ShareUtil.shareText(mContext, SharePlatform.WX, mContext.getResources().getString(R.string.share_text), new ShareListener() {
                        @Override
                        public void shareSuccess() {

                        }

                        @Override
                        public void shareFailure(Exception e) {

                        }

                        @Override
                        public void shareCancel() {

                        }
                    });
                    break;
                case R.id.home_pengyou_img:
                    Intent intent = new Intent(mContext, FriendInfoActivity.class);
                    intent.putExtra("fromContact", true);
                    mContext.startActivity(intent);
                    break;
            }
        }
    }

    public class HeaderHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.school_announcement)
        LinearLayout school_announcement;
        @BindView(R.id.school_activity)
        LinearLayout school_activity;
        @BindView(R.id.school_society)
        LinearLayout school_society;
        @BindView(R.id.school_learn_help)
        LinearLayout school_learn_help;

        public HeaderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick({R.id.school_announcement,
                R.id.school_activity,
                R.id.school_society,
                R.id.school_learn_help})
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.school_announcement:
                    SchoolAnnouncementActivity.launchActivity(mContext);
                    break;
                case  R.id.school_activity:
                    SchoolAnnouncementActivity.launchActivity(mContext);
                    break;
                case  R.id.school_society:
                    SchoolSocietyActivity.launchActivity(mContext);
                    break;
                case R.id.school_learn_help:
                    break;
            }
        }
    }

    public interface OnRvItemListener {
        void onItemClick(int position, int type);
    }
}
