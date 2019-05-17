package com.android.easymanager.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.easymanager.R;
import com.android.easymanager.ui.bean.SocietyBean;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MySchoolSocietyAdapter extends BaseRecyclerAdapter<SocietyBean>{
    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_my_society_item_layout, parent, false);
        return new SocietyHolder(layout);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, SocietyBean data) {
        if(viewHolder instanceof SocietyHolder){
            SocietyHolder holder = (SocietyHolder)viewHolder;
            holder.tv_name.setText(data.getName());
            holder.tv_type.setText(data.getType());
            holder.tv_status.setVisibility(data.getStatus()==0 ? View.GONE : View.VISIBLE);
        }
    }

    public class SocietyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView tv_name;
        @BindView(R.id.type)
        TextView tv_type;
        @BindView(R.id.status)
        TextView tv_status;
        public SocietyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
