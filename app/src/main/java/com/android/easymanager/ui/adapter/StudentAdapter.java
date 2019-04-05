package com.android.easymanager.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.easymanager.R;
import com.android.easymanager.ui.bean.StudentManagerEntry;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC-xiaoming on 2019/4/5.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentHolder>{

    private Context mContext;
    private ArrayList<StudentManagerEntry> entries;

    public StudentAdapter(Context context,ArrayList<StudentManagerEntry> entries){
        this.mContext = context;
        this.entries =entries;
    }

    @Override
    public StudentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.student_manager_item_layout,parent,false);
        return new StudentHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return entries!=null ? entries.size():0;
    }

    class StudentHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.student_name)
        TextView student_name;
        @BindView(R.id.student_photo)
        ImageView student_photo;
        @BindView(R.id.status1)
        TextView status1;
        @BindView(R.id.status2)
        TextView status2;

        public StudentHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
