package com.android.easymanager.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.easymanager.R;
import com.android.easymanager.ui.bean.ContactGroupEntry;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IconAdapter extends BaseRecyclerAdapter<String> {
    Activity mContext;
    public IconAdapter(Activity context){
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.icon_item_layout, parent, false);
        return new MyHolder(layout);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, String item) {
        if(getRealPosition(viewHolder) == getDatas().size()-1){
            RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) ((MyHolder)viewHolder).lineView.getLayoutParams();
            DisplayMetrics dm = new DisplayMetrics();
            dm = new DisplayMetrics();
            mContext.getWindowManager().getDefaultDisplay().getMetrics(dm);
            float density  = dm.density;
            params.height = (int)(22.5 * density);

            ((MyHolder)viewHolder).lineView.setLayoutParams(params);

        }
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.line1)
        View lineView;

        MyHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }
    }

}

