package com.android.easymanager.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.easymanager.MainActivity;
import com.android.easymanager.R;
import com.android.easymanager.ui.bean.CommunitityBean;
import com.android.easymanager.ui.fragment.HomeMainFragment;
import com.android.easymanager.ui.widget.CommunityGridLayout;
import java.util.Arrays;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCreateCommAdapter extends BaseRecyclerAdapter<CommunitityBean>{

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_pengyouquan_layout, parent, false);
        return new MyHolder(layout);
    }

    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, CommunitityBean item) {
        if(viewHolder instanceof MyHolder) {
            MyHolder holder = (MyHolder)viewHolder;
//            holder.tv_name.setText(item.getContactName().substring(0,1));
//            holder.tv_title.setText(item.getTitle());
//            holder.tv_body.setText(item.getBody());
//            holder.tv_time.setText(item.getTime());

            holder.home_pengyou_nine_grid.setIsShowAll(true);
            holder.home_pengyou_nine_grid.setUrlList(Arrays.asList(HomeMainFragment.mUrls));

        }
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_pengyou_nine_grid)
        CommunityGridLayout home_pengyou_nine_grid;

        MyHolder(View view) {
            super(view);
            ButterKnife.bind(this,itemView);
        }
    }


}
