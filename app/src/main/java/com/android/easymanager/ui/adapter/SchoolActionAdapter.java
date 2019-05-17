package com.android.easymanager.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.easymanager.R;
import com.android.easymanager.ui.activity.FriendInfoActivity;
import com.android.easymanager.ui.bean.SocietyBean;
import com.android.easymanager.ui.fragment.HomeMainFragment;
import com.android.easymanager.ui.widget.CommunityGridLayout;

import android.support.v7.widget.RecyclerView.ViewHolder;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.shaohui.shareutil.ShareUtil;
import me.shaohui.shareutil.share.ShareListener;
import me.shaohui.shareutil.share.SharePlatform;

public class SchoolActionAdapter extends RecyclerView.Adapter<ViewHolder>{

    public static final int TYPE_HEAD = 1;
    public static final int TYPE_SOCIETY_ACTION = 2;
    public static final int TYPE_NEW_DONGTAI = 3;

    private Context mContext;
    private List<String> mDatas;
    private OnRvItemListener mOnRvItemListener;

    public interface OnRvItemListener {
        void onItemClick(int position, int type);
    }

    public void setOnRvItemListener(OnRvItemListener onRvItemListener){
        this.mOnRvItemListener = onRvItemListener;
    }
    public SchoolActionAdapter(Context context, List<String> list) {
        mDatas = list;
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEAD;
        } else if (position == 1) {
            return TYPE_SOCIETY_ACTION;
        }
        return TYPE_NEW_DONGTAI;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case TYPE_HEAD:
                holder = new HeadHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_society_action_head_layout, parent, false));
                break;
            case TYPE_SOCIETY_ACTION:
                holder = new SocietyActionHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_society_action_huodong_layout, parent, false));
                break;
            case TYPE_NEW_DONGTAI:
                holder = new SocietyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_society_action_dongtai_layout, parent, false));
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof SocietyHolder){
            SocietyHolder holder = (SocietyHolder)viewHolder;
            holder.home_pengyou_nine_grid.setIsShowAll(true);
            holder.home_pengyou_nine_grid.setUrlList(Arrays.asList(HomeMainFragment.mUrls));
        }else if(viewHolder instanceof SocietyActionHolder){

        }
    }

    public class SocietyHolder extends RecyclerView.ViewHolder {
        private int dianzhanCount;
        @BindView(R.id.home_pengyou_img)
        ImageView home_pengyou_img;
        @BindView(R.id.home_pengyou_name)
        TextView home_pengyou_name;
        @BindView(R.id.home_pengyou_state)
        TextView home_pengyou_state;
        @BindView(R.id.home_pengyou_time)
        TextView home_pengyou_time;
        @BindView(R.id.home_pengyou_nine_grid)
        CommunityGridLayout home_pengyou_nine_grid;
        @BindView(R.id.home_dianzhan_count)
        TextView home_dianzhan_count;
        @BindView(R.id.home_comment_count)
        TextView home_comment_count;
        @BindView(R.id.home_share)
        TextView home_share;

        public SocietyHolder(View itemView) {
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

        @OnClick({R.id.home_dianzhan_count, R.id.home_comment_count, R.id.home_share,R.id.home_pengyou_img})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.home_dianzhan_count:
                    dianzhanCount++;
                    home_dianzhan_count.setText("" + dianzhanCount);
                    break;
                case R.id.home_comment_count:
//                    if (mHomePresenter != null) {
//                        mHomePresenter.showEdit();
//                    }
                    break;
                case R.id.home_share:
                    ShareUtil.shareText(mContext, SharePlatform.WX, "分享文字", new ShareListener() {
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

    public class HeadHolder extends RecyclerView.ViewHolder {
        public HeadHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class SocietyActionHolder extends RecyclerView.ViewHolder {
        public SocietyActionHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
