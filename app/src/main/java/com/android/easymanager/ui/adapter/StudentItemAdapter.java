package com.android.easymanager.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.easymanager.R;
import com.android.easymanager.ui.bean.StudentItem;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentItemAdapter extends BaseRecyclerAdapter<StudentItem> {
    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item_layout, parent, false);
        return new MyHolder(layout);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, StudentItem item) {
        if(viewHolder instanceof MyHolder) {
            MyHolder holder = (MyHolder)viewHolder;
            holder.tv_name.setText(item.getName());
            holder.tv_sex.setText("性别："+item.getSex());
            holder.tv_country.setText("国籍："+item.getCountry());
            holder.tv_number.setText("学号："+item.getStudengNumber());
        }
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.student_name)
        TextView tv_name;
        @BindView(R.id.student_sex)
        TextView tv_sex;
        @BindView(R.id.student_country)
        TextView tv_country;
        @BindView(R.id.student_number)
        TextView tv_number;

        MyHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }
    }

}

