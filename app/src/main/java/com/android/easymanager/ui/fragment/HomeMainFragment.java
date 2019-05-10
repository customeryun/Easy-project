package com.android.easymanager.ui.fragment;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.android.easymanager.presenter.HomePresenter;
import com.android.easymanager.R;
import com.android.easymanager.contract.HomeContract;
import com.android.easymanager.ui.activity.CommentActivity;
import com.android.easymanager.ui.activity.CommunityActivity;
import com.android.easymanager.ui.activity.ConversationListActivity;
import com.android.easymanager.ui.adapter.HomeItemDecoration;
import com.android.easymanager.ui.adapter.HomeMainAdapter;
import com.android.easymanager.ui.adapter.HomeMainDividerItemDecoration;
import com.android.easymanager.ui.bean.CommentDetailBean;
import com.android.easymanager.ui.widget.CommentEditTextDialog;
import com.android.easymanager.utils.CommonUtils;
import com.example.zhouwei.library.CustomPopWindow;
//import com.android.easymanager.ui.adapter.HomeMainDividerItemDecoration;
import java.util.ArrayList;
import java.util.Arrays;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by PC-xiaoming on 2019/4/27.
 */

public class HomeMainFragment extends BaseFragment<HomeContract.View,HomeContract.Presenter> implements HomeContract.View,HomeMainAdapter.OnRvItemListener{
    private static final String TAG ="HomeMainFragment_debug";
    //test data
    public static String[] mUrls = new String[]{"http://d.hiphotos.baidu.com/image/h%3D200/sign=201258cbcd80653864eaa313a7dca115/ca1349540923dd54e54f7aedd609b3de9c824873.jpg",
            "http://img3.fengniao.com/forum/attachpics/537/165/21472986.jpg",
            "http://d.hiphotos.baidu.com/image/h%3D200/sign=ea218b2c5566d01661199928a729d498/a08b87d6277f9e2fd4f215e91830e924b999f308.jpg",
            "http://img4.imgtn.bdimg.com/it/u=3445377427,2645691367&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=2644422079,4250545639&fm=21&gp=0.jpg",
//            "http://img5.imgtn.bdimg.com/it/u=1444023808,3753293381&fm=21&gp=0.jpg",
//            "http://img4.imgtn.bdimg.com/it/u=882039601,2636712663&fm=21&gp=0.jpg",
//            "http://img4.imgtn.bdimg.com/it/u=4119861953,350096499&fm=21&gp=0.jpg",
//            "http://img5.imgtn.bdimg.com/it/u=2437456944,1135705439&fm=21&gp=0.jpg",
//            "http://img2.imgtn.bdimg.com/it/u=3251359643,4211266111&fm=21&gp=0.jpg",
//            "http://img4.duitang.com/uploads/item/201506/11/20150611000809_yFe5Z.jpeg",
//            "http://img5.imgtn.bdimg.com/it/u=1717647885,4193212272&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=2024625579,507531332&fm=21&gp=0.jpg"};

    @BindView(R.id.home_list)
    RecyclerView home_list;
    private HomeMainAdapter mainAdapter;

    @Override
    public int initLayout() {
        return R.layout.home_main_layout;
    }

    @Override
    public HomeContract.View crateView() {
        return this;
    }

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

    public void showPopWindow(){
        View contentView = LayoutInflater.from(mActivity).inflate(R.layout.item_home_comment_edit,null);
        //处理popWindow 显示内容
        final EditText input_content= contentView.findViewById(R.id.tv_eyou_edit);
        contentView.setVisibility(View.VISIBLE);
        input_content.requestFocus();
        CommonUtils.showSoftInput(mContext,input_content);
        //创建并显示popWindow
        new CustomPopWindow.PopupWindowBuilder(mActivity)
                .setView(contentView)
                .size(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                .create()
                .showAtLocation(contentView, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);

    }


    @Override
    public void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        home_list.setLayoutManager(linearLayoutManager);
        mainAdapter = new HomeMainAdapter(mActivity, Arrays.asList(mUrls));
        home_list.addItemDecoration(new HomeItemDecoration(mContext));
        home_list.setAdapter(mainAdapter);
        mainAdapter.setPresenter((HomePresenter) mPresenter);
        mainAdapter.setOnRvItemListener(this);
        mainAdapter.addTuiJinaData(getTuiJianData());
    }

    public static HomeMainFragment getInstance(){
        HomeMainFragment fragment = new HomeMainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public ArrayList<String> getTuiJianData(){
        ArrayList<String> temp = new ArrayList<>();
        temp.add("s");
        temp.add("s");
        temp.add("s");
        temp.add("s");
        temp.add("s");
        temp.add("s");
        return temp;
    }

    @Override
    public void onItemClick(int position, int type) {
        Log.d(TAG, "onItemClick: "+type);
        if(type == HomeMainAdapter.TYPE_PENGYOU){
            CommentActivity.launchActivity(mContext);
//        }else if(type == HomeMainAdapter.TYPE_XINGCHENG){
//            View view = LayoutInflater.from(mActivity).inflate(R.layout.schedule_dialog_s1_layout,null,false);
//            final AlertDialog dialog = new AlertDialog.Builder(mActivity).setView(view).create();
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            dialog.show();
        }
    }

    @OnClick({R.id.iv_home_message})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_home_message:
                ConversationListActivity.launchActivity(mContext);
                break;
        }
    }
}
