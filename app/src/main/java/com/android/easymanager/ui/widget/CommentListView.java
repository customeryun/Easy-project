package com.android.easymanager.ui.widget;

import com.android.easymanager.IxiaApplication;
import com.android.easymanager.R;
import com.android.easymanager.ui.spannable.SpannableClickable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

/**
 * Created by PC-xiaoming on 2019/5/4.
 */

public class CommentListView extends LinearLayout {

    private int mItemColor;
    private int mItemSelectorColor;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private List<String> mDatas;
    private LayoutInflater layoutInflater ;

    private String[] testData = new String[]{"a","a","a"};

    public CommentListView(Context context) {
        this(context, null);
    }

    public CommentListView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public CommentListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(attrs);
        setDatas(Arrays.asList(testData));
    }

    public void initAttr(AttributeSet attrs) {
        final TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.CommentListView, 0, 0);
        try {
            if (typedArray != null) {
                mItemColor = typedArray.getColor(R.styleable.CommentListView_item_color, Color.BLACK);
                mItemSelectorColor = typedArray.getColor(R.styleable.CommentListView_item_selector_color, Color.WHITE);
            }
        } finally {
            typedArray.recycle();
        }
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public OnItemLongClickListener getOnItemLongClickListener() {
        return onItemLongClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public void setDatas(List<String> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    public List<String> getDatas() {
        return mDatas;
    }

    public void notifyDataSetChanged(){
        removeAllViews();
        if(mDatas==null||mDatas.size()==0){
            return;
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i=0;i<mDatas.size();i++){
            final int index = i;
            View view =getView(index);
            if(view == null){
                throw new NullPointerException("listview item layout is null, please check getView()...");
            }
            Log.d("dataTest", "notifyDataSetChanged: " +index);
            addView(view,index,params);
        }
    }

    public View getView(int position){
        if(layoutInflater == null){
            layoutInflater = LayoutInflater.from(getContext());
        }
        View convertView = layoutInflater.inflate(R.layout.item_comment_layout, null, false);
        TextView commentName =  convertView.findViewById(R.id.comment_item_userName);
        if(position == 1){
            String name = commentName.getText().toString();
            String toReplyName = "李华";
            SpannableStringBuilder builder = new SpannableStringBuilder();
            builder.append(setClickableSpan(name, "1"));
            builder.append(" 回复 ");
            builder.append(setClickableSpan(toReplyName,"1"));
            commentName.setText(builder.toString());
        }
       return convertView;
    }

    private SpannableString setClickableSpan(final String textStr, final String id) {
        SpannableString subjectSpanText = new SpannableString(textStr);
        subjectSpanText.setSpan(new SpannableClickable(mItemColor){
                                    @Override
                                    public void onClick(View widget) {
                                        Toast.makeText(IxiaApplication.context, textStr + " &id = " + id, Toast.LENGTH_SHORT).show();
                                    }
                                }, 0, subjectSpanText.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return subjectSpanText;
    }

    public static interface OnItemClickListener{
        public void onItemClick(int position);
    }

    public static interface OnItemLongClickListener{
        public void onItemLongClick(int position);
    }

}
