package com.android.easymanager.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.easymanager.R;
import com.android.easymanager.utils.ThreadUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConversationListView {

    private Context mContext;
    private View mView;
    /**
     * conversation listview
     */
    private ListView mConvListView;
    /**
     *search
     */
    private LinearLayout mSearchHead;
    /**
     *header
     */
    private LinearLayout mHeader;
    /**
     *loading header
     */
    private RelativeLayout mLoadingHeader;
    /**
     * loading image
     */
    private ImageView mLoadingIv;
    /**
     * loading tv
     */
    private LinearLayout mLoadingTv;
    /**
     * null conversation
     */
    private TextView mNull_conversation;
    /**
     * search
     */
    private LinearLayout mSearch;
    /**
     * all unread message count
     */
    private TextView mAllUnReadMsg;


    public ConversationListView(Activity context, View view){
        this.mContext = context;
        this.mView = view;
    }

    public void initModule(){
        mConvListView = (ListView) mView.findViewById(R.id.conv_list_view);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mHeader = (LinearLayout) inflater.inflate(R.layout.conv_list_head_view, mConvListView, false);
        mSearchHead = (LinearLayout) inflater.inflate(R.layout.conversation_head_view, mConvListView, false);
        mLoadingHeader = (RelativeLayout) inflater.inflate(R.layout.jmui_drop_down_list_header, mConvListView, false);
        mLoadingIv = (ImageView) mLoadingHeader.findViewById(R.id.jmui_loading_img);
        mLoadingTv = (LinearLayout) mLoadingHeader.findViewById(R.id.loading_view);
        mSearch = (LinearLayout) mSearchHead.findViewById(R.id.search_title);
        mConvListView.addHeaderView(mLoadingHeader);
        mConvListView.addHeaderView(mSearchHead);
        mConvListView.addHeaderView(mHeader);
        mNull_conversation = mView.findViewById(R.id.null_conversation);

    }

    public void setConListAdapter(ListAdapter adapter){
        mConvListView.setAdapter(adapter);
    }

    public void setListener(View.OnClickListener onClickListener) {
        mSearch.setOnClickListener(onClickListener);
    }

    public void setItemListeners(AdapterView.OnItemClickListener onClickListener) {
        mConvListView.setOnItemClickListener(onClickListener);
    }

    public void setLongClickListener(AdapterView.OnItemLongClickListener listener) {
        mConvListView.setOnItemLongClickListener(listener);
    }

    public void showHeaderView() {
        mHeader.findViewById(R.id.network_disconnected_iv).setVisibility(View.VISIBLE);
        mHeader.findViewById(R.id.check_network_hit).setVisibility(View.VISIBLE);
    }

    public void dismissHeaderView() {
        mHeader.findViewById(R.id.network_disconnected_iv).setVisibility(View.GONE);
        mHeader.findViewById(R.id.check_network_hit).setVisibility(View.GONE);
    }


    public void showLoadingHeader() {
        mLoadingIv.setVisibility(View.VISIBLE);
        mLoadingTv.setVisibility(View.VISIBLE);
        AnimationDrawable drawable = (AnimationDrawable) mLoadingIv.getDrawable();
        drawable.start();
    }

    public void dismissLoadingHeader() {
        mLoadingIv.setVisibility(View.GONE);
        mLoadingTv.setVisibility(View.GONE);
    }

    public void setNullConversation(boolean isHaveConv) {
        if (isHaveConv) {
            mNull_conversation.setVisibility(View.GONE);
        } else {
            mNull_conversation.setVisibility(View.VISIBLE);
        }
    }

    public void setUnReadMsg(final int count) {
        ThreadUtil.runInUiThread(new Runnable() {
            @Override
            public void run() {
                if (mAllUnReadMsg != null) {
                    if (count > 0) {
                        mAllUnReadMsg.setVisibility(View.VISIBLE);
                        if (count < 100) {
                            mAllUnReadMsg.setText(count + "");
                        } else {
                            mAllUnReadMsg.setText("99+");
                        }
                    } else {
                        mAllUnReadMsg.setVisibility(View.GONE);
                    }
                }
            }
        });
    }
}
