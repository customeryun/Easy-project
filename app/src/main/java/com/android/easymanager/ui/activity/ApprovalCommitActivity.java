package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.OnClick;
import com.android.easymanager.R;
import com.android.easymanager.ui.adapter.ApprovalCommitAdapter;
import java.util.ArrayList;

public class ApprovalCommitActivity extends BaseActivity {

    @BindView(R.id.recycle_view)
    RecyclerView recycle_view;
    @BindView(R.id.approval_no)
    Button approval_no;
    @BindView(R.id.approval_yes)
    Button approval_yes;
    @BindView(R.id.view_history)
    ImageView view_history;

    public static void launchActivity(Context context) {
        context.startActivity(new Intent(context, ApprovalCommitActivity.class));
    }

    public int getLayout() {
        return R.layout.me_approval_commited_layout;
    }

    public void init() {
        setTitle("提交的申请假单");
        initRecycleView();
    }

    @OnClick({R.id.view_history, R.id.approval_yes, R.id.approval_no})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.approval_no:
                finish();
                return;
            case R.id.approval_yes:
                ApprovalLeaveActivity.launchActivity(this.mContext);
                return;
            case R.id.view_history:
                ApprovalHistoryActivity.launchActivity(this.mContext);
                return;
            default:
                return;
        }
    }

    public void initRecycleView() {
        ApprovalCommitAdapter adapter = new ApprovalCommitAdapter(this.mContext, buildItems());
        this.recycle_view.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.recycle_view.setAdapter(adapter);
    }

    public ArrayList<String> buildItems() {
        ArrayList<String> items = new ArrayList();
        items.add("s");
        items.add("s");
        items.add("s");
        return items;
    }
}
