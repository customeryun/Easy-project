package com.android.easymanager.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.LinearLayout;

import com.android.easymanager.R;
import com.android.easymanager.ui.bean.Event;
import com.android.easymanager.ui.controller.ConversationController;
import com.android.easymanager.ui.widget.ConversationListView;

import butterknife.BindView;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.enums.ConversationType;
import cn.jpush.im.android.api.event.ConversationRefreshEvent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.MessageReceiptStatusChangeEvent;
import cn.jpush.im.android.api.event.OfflineMessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.android.eventbus.EventBus;

public class ConversationListActivity extends BaseActivity{


    @BindView(R.id.conv_fragment_view)
    LinearLayout conv_fragment_view;

    private static final String TAG = "ConversationList_update";

    private ConversationListView mConvListView;
    private ConversationController mConvListController;
    private static final int REFRESH_CONVERSATION_LIST = 0x3000;
    private static final int DISMISS_REFRESH_HEADER = 0x3001;
    private static final int ROAM_COMPLETED = 0x3002;

    private BackgroundHandler mBackgroundHandler;
    private HandlerThread mThread;

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
        //init handler
        initHandler();
        initReceiver();
        //订阅接收消息,只要重写onEvent就能收到消息
        JMessageClient.registerEventReceiver(this);
        initConversationView();
        updateHeadView();
    }

    public void initConversationView(){
        mConvListView = new ConversationListView(this,conv_fragment_view);
        mConvListView.initModule();
        mConvListController = new ConversationController(mConvListView,this,0);
        mThread = new HandlerThread("MainActivity");
        mThread.start();
        mConvListView.setListener(mConvListController);
        mConvListView.setItemListeners(mConvListController);
        mConvListView.setLongClickListener(mConvListController);
    }

    private void initReceiver(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(mNetworkBroadCast,intentFilter);
    }

    public void updateHeadView(){
        ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = manager.getActiveNetworkInfo();
        if(activeInfo == null){
            mConvListView.showHeaderView();
        }else{
            mConvListView.dismissHeaderView();
            mConvListView.showLoadingHeader();
            mBackgroundHandler.sendEmptyMessageDelayed(DISMISS_REFRESH_HEADER, 1000);
        }
    }

    public void initHandler(){
        mThread = new HandlerThread("MainActivity");
        mThread.start();
        mBackgroundHandler = new BackgroundHandler(mThread.getLooper());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销消息接收
        JMessageClient.unRegisterEventReceiver(this);
        unregisterReceiver(mNetworkBroadCast);
        EventBus.getDefault().unregister(this);
        mBackgroundHandler.removeCallbacksAndMessages(null);
        mThread.getLooper().quit();

    }

    /**
     *监听网络广播变化
     */
    private BroadcastReceiver mNetworkBroadCast = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")){
                ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeInfo = manager.getActiveNetworkInfo();
                if(activeInfo == null){
                    mConvListView.showHeaderView();
                }else{
                    mConvListView.dismissHeaderView();
                }
            }
        }
    };

    private class BackgroundHandler extends Handler{

        public BackgroundHandler(Looper looper){
            super(looper);
        }

        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case REFRESH_CONVERSATION_LIST:
                    Conversation conversation = (Conversation) msg.obj;
                    mConvListController.getAdapter().setToTop(conversation);
                    break;
                case DISMISS_REFRESH_HEADER:
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mConvListView.dismissLoadingHeader();
                        }
                    });
                    break;
                case ROAM_COMPLETED:
                    conversation = (Conversation) msg.obj;
                    mConvListController.getAdapter().addAndSort(conversation);
                    break;
            }
        }
    }


    /*************以下是收到消息的处理*******************/

    /**
     * 收到消息
     */
    public void onEvent(MessageEvent event){
        Log.d(TAG, "onEvent : the message is line************* ");
        mConvListView.setUnReadMsg(JMessageClient.getAllUnReadMsgCount());
        Message message = event.getMessage();
        if(message.getTargetType().equals(ConversationType.group)){//群组消息

        }else{
            final UserInfo userInfo = (UserInfo) message.getTargetInfo();
            String targetId = userInfo.getUserName();
            Conversation conversation = JMessageClient.getSingleConversation(targetId,userInfo.getAppKey());
            if(conversation!=null&&mConvListController!=null){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(!TextUtils.isEmpty(userInfo.getAvatar())){
                            userInfo.getAvatarBitmap(new GetAvatarBitmapCallback() {
                                @Override
                                public void gotResult(int responseCode, String responseMessage, Bitmap bitmap) {
                                    if(responseCode == 0){
                                        mConvListController.getAdapter().notifyDataSetChanged();
                                    }
                                }
                            });
                        }
                    }
                });
            }
            mBackgroundHandler.sendMessage(mBackgroundHandler.obtainMessage(REFRESH_CONVERSATION_LIST,conversation));
        }
    }

    /**
     * 接收离线消息
     *
     * @param event 离线消息事件
     */
    public void onEvent(OfflineMessageEvent event){
        Log.d(TAG, "onEvent: the message is offline************");
        Conversation conv = event.getConversation();
        if(!conv.getTargetId().equals("feedback_Android")){
            mBackgroundHandler.sendMessage(mBackgroundHandler.obtainMessage(REFRESH_CONVERSATION_LIST, conv));
        }
    }

    /**
     * 消息已读事件
     */
    public void onEventMainThread(MessageReceiptStatusChangeEvent event){
        Log.d(TAG, "onEventMainThread: the message have read");
        mConvListController.getAdapter().notifyDataSetChanged();
    }

    /**
     * 消息漫游完成事件
     *
     * @param event 漫游完成后， 刷新会话事件
     */
    public void onEvent(ConversationRefreshEvent event){
        Log.d(TAG, "onEvent: the message is remote message");
        Conversation conv = event.getConversation();
        if(!conv.getTargetId().equals("feedback_Android")){
            mBackgroundHandler.sendMessage(mBackgroundHandler.obtainMessage(REFRESH_CONVERSATION_LIST,conv));
            //多端在线未读数改变时刷新
            if(event.getReason().equals(ConversationRefreshEvent.Reason.UNREAD_CNT_UPDATED)){
                mBackgroundHandler.sendMessage(mBackgroundHandler.obtainMessage(REFRESH_CONVERSATION_LIST, conv));
            }
        }
    }

    public void onEventMainThread(Event event){
        switch (event.getType()){
            case createConversation:
                Conversation conv = event.getConversation();
                if(conv!=null){
                    mConvListController.getAdapter().addNewConversation(conv);
                }
                break;
            case deleteConversation:
                conv = event.getConversation();
                if(conv!=null){
                    mConvListController.getAdapter().deleteConversation(conv);
                }
                break;
            case draft:
                //收到保存为草稿事件
                conv = event.getConversation();
                String draft = event.getDraft();
                if(!TextUtils.isEmpty(draft)){
                    mConvListController.getAdapter().putDraftToMap(conv, draft);
                    mConvListController.getAdapter().setToTop(conv);
                    //否则删除
                }else {
                    mConvListController.getAdapter().delDraftFromMap(conv);
                }
                break;
            case addFriend:
                break;
        }
    }


}
