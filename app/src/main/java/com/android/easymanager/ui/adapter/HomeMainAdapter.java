package com.android.easymanager.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.easymanager.R;
import com.android.easymanager.presenter.HomePresenter;
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
 * Created by PC-xiaoming on 2019/4/27.
 */

public class HomeMainAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<String> mDatas;
    private ArrayList<String> mTuiJianDatas;
    private Context mContext;
    public static final int TYPE_TUIJIAN = 1;
    public static final int TYPE_GONGGAO = 2;
    public static final int TYPE_XINGCHENG = 3;
    public static final int TYPE_DONGTAI = 4;
    public static final int TYPE_PENGYOU = 5;
    private int mItemCount;
    private HomePresenter mHomePresenter;
    private OnRvItemListener mOnRvItemListener;

    public HomeMainAdapter(Context context, List<String> list) {
        mDatas = list;
        mItemCount = mDatas.size();
        mContext = context;
    }

    public void setOnRvItemListener(OnRvItemListener onRvItemListener){
        this.mOnRvItemListener = onRvItemListener;
    }

    public void addTuiJinaData(ArrayList<String> tuijianDatas) {
        this.mTuiJianDatas = tuijianDatas;
        if (mTuiJianDatas.size() > 0) {
            mItemCount += 1;
            this.notifyDataSetChanged();
        }
    }

    public void setPresenter(HomePresenter homePresenter) {
        this.mHomePresenter = homePresenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = null;
        switch (viewType) {
            case TYPE_TUIJIAN:
                holder = new TuiJianViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_tuijian_layout, parent, false));
                break;
            case TYPE_GONGGAO:
                holder = new GongGaoViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_gonggao_layout, parent, false));
                break;
            case TYPE_XINGCHENG:
                holder = new XingChengViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_xinghceng__layout, parent, false));
                break;
            case TYPE_DONGTAI:
                holder = new DontaiViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_dongtai_layout, parent, false));
                break;
            case TYPE_PENGYOU:
                holder = new PengYouHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_pengyouquan_layout, parent, false));
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TuiJianViewHolder) {
            if (mTuiJianDatas != null && mTuiJianDatas.size() > 0) {
                Log.d("tuijian", "onBindViewHolder TuiJianViewHolder: ");
                RecyclerView recyclerView = ((TuiJianViewHolder) holder).tuijian_list;
                LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(layoutManager);
                TuiJianAdapter adapter = new TuiJianAdapter(mContext, mTuiJianDatas);
                recyclerView.setAdapter(adapter);
                //监听拖拽
                TuiJianItemTouchCallback callback = new TuiJianItemTouchCallback(adapter);
                ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
                itemTouchHelper.attachToRecyclerView(recyclerView);

            }
        } else if (holder instanceof GongGaoViewHolder) {

        } else if (holder instanceof XingChengViewHolder) {

        } else if (holder instanceof DontaiViewHolder) {

        } else if (holder instanceof PengYouHolder) {
            ((PengYouHolder) holder).home_pengyou_nine_grid.setIsShowAll(true);
            ((PengYouHolder) holder).home_pengyou_nine_grid.setUrlList(mDatas);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_TUIJIAN;
        } else if (position == 1) {
            return TYPE_GONGGAO;
        } else if (position == 2) {
            return TYPE_XINGCHENG;
        } else if (position == 3) {
            return TYPE_DONGTAI;
        }
        return TYPE_PENGYOU;
    }

    @Override
    public int getItemCount() {
        return mItemCount;
    }

    //推荐
    public class TuiJianViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tuijian_list)
        RecyclerView tuijian_list;

        public TuiJianViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //公告
    public class GongGaoViewHolder extends RecyclerView.ViewHolder {

        public GongGaoViewHolder(View itemView) {
            super(itemView);
        }
    }

    //行程
    public class XingChengViewHolder extends RecyclerView.ViewHolder {

        public XingChengViewHolder(View itemView) {
            super(itemView);
        }
    }

    //动态
    public class DontaiViewHolder extends RecyclerView.ViewHolder {

        public DontaiViewHolder(View itemView) {
            super(itemView);
        }
    }

    //朋友
    public class PengYouHolder extends RecyclerView.ViewHolder {
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

        public PengYouHolder(View itemView) {
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

        @OnClick({R.id.home_dianzhan_count, R.id.home_comment_count, R.id.home_share})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.home_dianzhan_count:
                    dianzhanCount++;
                    home_dianzhan_count.setText("" + dianzhanCount);
                    break;
                case R.id.home_comment_count:
                    if (mHomePresenter != null) {
                        mHomePresenter.showEdit();
                    }
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
            }
        }
    }

    public interface OnRvItemListener {
        void onItemClick(int position, int type);
    }
}
