package com.android.easymanager.ui.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.easymanager.R;
import com.android.easymanager.ui.widget.CommunityGridLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC-xiaoming on 2019/4/27.
 */

public class HomeMainAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<String> mDatas;
    private Context mContext;
    private static final int TYPE_TUIJIAN = 1;
    private static final int TYPE_GONGGAO = 2;
    private static final int TYPE_XINGCHENG = 3;
    private static final int TYPE_DONGTAI = 4;
    private static final int TYPE_PENGYOU = 5;

    public HomeMainAdapter(Context context, List<String> list) {
        mDatas = list;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = null;
        switch (viewType) {
            case TYPE_TUIJIAN:
                holder = new TuiJianViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_tuijian_layout, parent,false));
                break;
            case TYPE_GONGGAO:
                holder = new GongGaoViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_gonggao_layout, parent,false));
                break;
            case TYPE_XINGCHENG:
                holder = new XingChengViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_xinghceng_layout, parent,false));
                break;
            case TYPE_DONGTAI:
                holder = new DontaiViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_dongtai_layout, parent,false));
                break;
            case TYPE_PENGYOU:
                holder = new PengYouHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_pengyouquan_layout, parent,false));
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TuiJianViewHolder) {

        } else if (holder instanceof GongGaoViewHolder) {

        } else if (holder instanceof XingChengViewHolder) {

        } else if (holder instanceof DontaiViewHolder) {

        } else if (holder instanceof PengYouHolder) {
            ((PengYouHolder)holder) .home_pengyou_nine_grid.setIsShowAll(true);
            ((PengYouHolder)holder).home_pengyou_nine_grid.setUrlList(mDatas);
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
        return mDatas.size();
    }

    //推荐
    public class TuiJianViewHolder extends RecyclerView.ViewHolder {

        public TuiJianViewHolder(View itemView) {
            super(itemView);
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

        @BindView(R.id.home_pengyou_img)
        ImageView home_pengyou_img;
        @BindView(R.id.home_pengyou_name)
        TextView home_pengyou_name;
        @BindView(R.id.home_pengyou_state)
        TextView home_pengyou_state;
        @BindView(R.id.home_pengyou_time)
        TextView home_pengyou_time;
        @BindView(R.id.home_pengyou_content)
        TextView home_pengyou_content;
        @BindView(R.id.home_pengyou_content_more)
        TextView home_pengyou_content_more;
        @BindView(R.id.home_pengyou_nine_grid)
        CommunityGridLayout home_pengyou_nine_grid;

        public PengYouHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
