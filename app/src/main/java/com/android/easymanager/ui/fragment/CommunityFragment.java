package com.android.easymanager.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.android.easymanager.R;
import com.android.easymanager.contract.HomeContract;
import com.android.easymanager.presenter.HomePresenter;
import com.android.easymanager.ui.activity.CommentActivity;
import com.android.easymanager.ui.adapter.CommunityGirdAdapter;
import com.android.easymanager.ui.adapter.HomeMainAdapter;
import com.android.easymanager.ui.bean.CommentDetailBean;
import com.android.easymanager.ui.widget.CommentEditTextDialog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import butterknife.BindView;

/**
 * Created by PC-xiaoming on 2019/4/23.
 */

public class CommunityFragment extends BaseFragment implements HomeContract.View,HomeMainAdapter.OnRvItemListener{

    //test data
    private String[] mUrls = new String[]{"http://d.hiphotos.baidu.com/image/h%3D200/sign=201258cbcd80653864eaa313a7dca115/ca1349540923dd54e54f7aedd609b3de9c824873.jpg",
            "http://img3.fengniao.com/forum/attachpics/537/165/21472986.jpg",
//            "http://d.hiphotos.baidu.com/image/h%3D200/sign=ea218b2c5566d01661199928a729d498/a08b87d6277f9e2fd4f215e91830e924b999f308.jpg",
//            "http://img4.imgtn.bdimg.com/it/u=3445377427,2645691367&fm=21&gp=0.jpg",
//            "http://img4.imgtn.bdimg.com/it/u=2644422079,4250545639&fm=21&gp=0.jpg",
//            "http://img5.imgtn.bdimg.com/it/u=1444023808,3753293381&fm=21&gp=0.jpg",
//            "http://img4.imgtn.bdimg.com/it/u=882039601,2636712663&fm=21&gp=0.jpg",
//            "http://img4.imgtn.bdimg.com/it/u=4119861953,350096499&fm=21&gp=0.jpg",
//            "http://img5.imgtn.bdimg.com/it/u=2437456944,1135705439&fm=21&gp=0.jpg",
//            "http://img2.imgtn.bdimg.com/it/u=3251359643,4211266111&fm=21&gp=0.jpg",
//            "http://img4.duitang.com/uploads/item/201506/11/20150611000809_yFe5Z.jpeg",
            "http://img5.imgtn.bdimg.com/it/u=1717647885,4193212272&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=2024625579,507531332&fm=21&gp=0.jpg"};


    @BindView(R.id.community_list)
    RecyclerView community_list;

    CommunityGirdAdapter mAdapter;

    public static CommunityFragment getInstance() {
        CommunityFragment fragment = new CommunityFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int initLayout() {
        return R.layout.activity_community_layout;
    }

    @Override
    public Object crateView() {
        return null;
    }

//    @Override
//    public Object createPresenter() {
//        return null;
//    }
    @Override
    public HomeContract.Presenter createPresenter() {
        return new HomePresenter(this,mContext);
    }


    @Override
    public void showLoad() {

    }

    @Override
    public void hideLoad() {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void networkError() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void updateEditLayout(int visibility) {
        //showPopWindow();
        showDialog();
    }

    public void showDialog(){
        new CommentEditTextDialog(mActivity).initView().setSendClickListener(new CommentEditTextDialog.SendClickListener() {
            @Override
            public void sendClick(CommentDetailBean detailBean) {
                //adapter.addTheCommentData(detailBean);
            }
        }).showDialog();
    }

    @Override
    public void init() {
        initRecycleView();
    }


    public void initRecycleView(){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        community_list.setLayoutManager(linearLayoutManager);

        //ArrayList<String> paths = (ArrayList<String>) Arrays.asList(mUrls);
        List<String> paths = Arrays.asList(mUrls);
        mAdapter = new CommunityGirdAdapter(paths,mContext);
        mAdapter.setPresenter((HomePresenter) mPresenter);
        mAdapter.setOnRvItemListener(this);
        community_list.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(int position, int type) {
        if(type == CommunityGirdAdapter.LIST_TYP){
            CommentActivity.launchActivity(mContext);
        }
    }
}
