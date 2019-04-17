package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.HandlerThread;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.easymanager.R;
import com.android.easymanager.ui.controller.ConversationController;
import com.android.easymanager.ui.widget.ConversationListView;

import butterknife.BindView;

public class ConversationListActivity extends BaseActivity{

    @BindView(R.id.conv_fragment_view)
    LinearLayout conv_fragment_view;

    private ConversationListView mConvListView;
    private ConversationController mConvListController;
    private HandlerThread mThread;
    private static final int REFRESH_CONVERSATION_LIST = 0x3000;
    private static final int DISMISS_REFRESH_HEADER = 0x3001;
    private static final int ROAM_COMPLETED = 0x3002;

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,ConversationListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_conv_list;
    }

    @Override
    public void init() {

        setTitle("对话列表");
        mConvListView = new ConversationListView(this,conv_fragment_view);
        mConvListView.initModule();
        mConvListController = new ConversationController(mConvListView,this,0);
        mThread = new HandlerThread("MainActivity");
        mThread.start();
        mConvListView.setListener(mConvListController);
        mConvListView.setItemListeners(mConvListController);
        mConvListView.setLongClickListener(mConvListController);

    }


}
