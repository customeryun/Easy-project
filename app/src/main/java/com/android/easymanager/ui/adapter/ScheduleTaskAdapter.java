package com.android.easymanager.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.LinearLayout;
import com.android.easymanager.R;
import com.android.easymanager.ui.bean.ScheduleItem;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC-xiaoming on 2019/4/4.
 */

public class ScheduleTaskAdapter extends RecyclerView.Adapter<ScheduleTaskAdapter.ScheduleHolder>{

    private Context mContext;
    private ArrayList<ScheduleItem> scheduleItems;
    private RvOnItemListener rvOnItemListener;

    public ScheduleTaskAdapter(Context context, ArrayList<ScheduleItem> items,RvOnItemListener rvOnItemListener){
        this.mContext = context;
        this.scheduleItems = items;
        this.rvOnItemListener = rvOnItemListener;
    }

    public void addData(ScheduleItem item){
        this.scheduleItems.add(item);
        notifyDataSetChanged();
    }

    @Override
    public ScheduleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.schedule_item_layout,parent,false);
        return new ScheduleHolder(view);
    }

    @Override
    public void onBindViewHolder(ScheduleHolder holder, int position) {
        ScheduleItem item = scheduleItems.get(position);
        holder.rv_left.setImageResource(item.getRes());
        holder.rv_time.setText(item.getTime());
        holder.rv_title.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return scheduleItems.size();
    }

    class ScheduleHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.rv_left)
        ImageView rv_left;
        @BindView(R.id.rv_right)
        LinearLayout rv_right;
        @BindView(R.id.rv_title)
        TextView rv_title;
        @BindView(R.id.rv_time)
        TextView rv_time;

        public ScheduleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            /*itemView*/rv_right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(rvOnItemListener!=null){
                        rvOnItemListener.onItemClick(getAdapterPosition(),scheduleItems.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    public interface RvOnItemListener{
        void onItemClick(int position,ScheduleItem item);
    }
}
