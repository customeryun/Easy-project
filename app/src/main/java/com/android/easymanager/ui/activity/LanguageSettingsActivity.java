package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.android.easymanager.R;
import com.android.easymanager.ui.adapter.LanguageAdapter;
import com.android.easymanager.utils.ToastUtil;
import java.util.Arrays;
import java.util.List;
import butterknife.BindView;

public class LanguageSettingsActivity extends BaseActivity implements LanguageAdapter.OnRvItemListener {

    @BindView(R.id.recyclerView)
    RecyclerView recycle_view;

    LanguageAdapter adapter;

    @Override
    public int getLayout() {
        return R.layout.activity_language_layout;
    }

    @Override
    public void init() {
        setTitle("请选择语种");
        initRecycleView();
    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,LanguageSettingsActivity.class);
        context.startActivity(intent);
    }

    public void initRecycleView() {
        /*LanguageAdapter*/ adapter = new LanguageAdapter(this,buildItems());
        adapter.addDatas(buildItems());
        adapter.setOnRvItemListener(this);

        recycle_view.setLayoutManager(new LinearLayoutManager(mContext));
        recycle_view.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recycle_view.setAdapter(adapter);
    }

    public List<String> buildItems() {
        String[] mDatas = {"ZH-CN","ZH-TW","Algeria","Algeria","Bahrain","Canada","Canada","Canada","English"};
        return Arrays.asList(mDatas);
    }

    @Override
    public void onItemClick(int position, int type) {
        if(type == LanguageAdapter.TYPE_LANGUAGE){
            String name = adapter.getResultDatas().get(position).getmName();
            ToastUtil.shortToast(mContext,"你选择了语言："+name);
            //切换语言，返回结果
            Intent intent = new Intent();
            intent.putExtra("selected_language", name);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
