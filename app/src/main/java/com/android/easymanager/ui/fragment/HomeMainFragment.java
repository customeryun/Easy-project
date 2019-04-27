package com.android.easymanager.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.easymanager.R;
import com.android.easymanager.ui.adapter.HomeMainAdapter;
import com.android.easymanager.ui.adapter.HomeMainDividerItemDecoration;

import java.util.Arrays;

import butterknife.BindView;

/**
 * Created by PC-xiaoming on 2019/4/27.
 */

public class HomeMainFragment extends BaseFragment{

    //test data
    private String[] mUrls = new String[]{"http://d.hiphotos.baidu.com/image/h%3D200/sign=201258cbcd80653864eaa313a7dca115/ca1349540923dd54e54f7aedd609b3de9c824873.jpg",
            "http://img3.fengniao.com/forum/attachpics/537/165/21472986.jpg",
            "http://d.hiphotos.baidu.com/image/h%3D200/sign=ea218b2c5566d01661199928a729d498/a08b87d6277f9e2fd4f215e91830e924b999f308.jpg",
            "http://img4.imgtn.bdimg.com/it/u=3445377427,2645691367&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=2644422079,4250545639&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=1444023808,3753293381&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=882039601,2636712663&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=4119861953,350096499&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=2437456944,1135705439&fm=21&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=3251359643,4211266111&fm=21&gp=0.jpg",
            "http://img4.duitang.com/uploads/item/201506/11/20150611000809_yFe5Z.jpeg",
            "http://img5.imgtn.bdimg.com/it/u=1717647885,4193212272&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=2024625579,507531332&fm=21&gp=0.jpg"};


    @BindView(R.id.home_list)
    RecyclerView home_list;
    private HomeMainAdapter mainAdapter;

    @Override
    public int initLayout() {
        return R.layout.home_main_layout;
    }

    @Override
    public Object crateView() {
        return null;
    }

    @Override
    public Object createPresenter() {
        return null;
    }

    @Override
    public void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        home_list.setLayoutManager(linearLayoutManager);
        mainAdapter = new HomeMainAdapter(mContext, Arrays.asList(mUrls));
        home_list.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.HORIZONTAL));
        home_list.setAdapter(mainAdapter);
    }

    public static HomeMainFragment getInstance(){
        HomeMainFragment fragment = new HomeMainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
}
