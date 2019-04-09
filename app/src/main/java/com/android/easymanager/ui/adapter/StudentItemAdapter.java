package com.android.easymanager.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.easymanager.R;
import com.android.easymanager.ui.bean.StudentItem;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentItemAdapter extends RecyclerView.Adapter<StudentItemAdapter.StudentItemHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<StudentItem> mList;
    private RvOnItemListener mRvOnItemListener;

    public StudentItemAdapter(Context context, List<StudentItem> list, RvOnItemListener listener) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.mRvOnItemListener=listener;
        mList = list;
    }

    @Override
    public StudentItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StudentItemHolder(mLayoutInflater.inflate(R.layout.student_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(StudentItemHolder holder, int position) {
        StudentItem item = mList.get(position);
        holder.tv_name.setText(item.getName());
        holder.tv_sex.setText("性别："+item.getSex());
        holder.tv_country.setText("国籍："+item.getCountry());
        holder.tv_number.setText("学号："+item.getStudengNumber());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    public class StudentItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.student_name)
        TextView tv_name;
        @BindView(R.id.student_sex)
        TextView tv_sex;
        @BindView(R.id.student_country)
        TextView tv_country;
        @BindView(R.id.student_number)
        TextView tv_number;


        StudentItemHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mRvOnItemListener != null) {
                        mRvOnItemListener.onItemClick(getAdapterPosition(), mList.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    public interface RvOnItemListener{
        void onItemClick(int position, StudentItem entry);
    }

}

