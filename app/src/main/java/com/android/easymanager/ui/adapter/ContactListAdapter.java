package com.android.easymanager.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.easymanager.IxiaApplication;
import com.android.easymanager.R;
import com.android.easymanager.database.FriendEntry;
import com.android.easymanager.ui.activity.FriendInfoActivity;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactListAdapter extends BaseRecyclerAdapter<FriendEntry> {
    private Context mContext;
    private int[] mSectionIndices;
    private String[] mSectionLetters;
    List<FriendEntry> mData;

    public ContactListAdapter(Context context){
        mContext = context;

    }

    @Override
    public void addDatas(List<FriendEntry> datas) {
        super.addDatas(datas);
        mData = datas;
        mSectionIndices = getSectionIndices();
        mSectionLetters = getSectionLetters();
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item_layout, parent, false);
        return new ContactHolder(layout);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, final FriendEntry data) {
        if (viewHolder instanceof ContactHolder) {
            ContactHolder holder = (ContactHolder) viewHolder;
            holder.mTextView.setText(data.username);
            holder.mTextView_class.setText("汉语言01级");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, FriendInfoActivity.class);
                    intent.putExtra("fromContact", true);
                    intent.putExtra(IxiaApplication.TARGET_ID, data.username);
                    intent.putExtra(IxiaApplication.TARGET_APP_KEY, data.appKey);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    public class ContactHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.contact_name)
        TextView mTextView;
        @BindView(R.id.contact_class)
        TextView mTextView_class;

        ContactHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }
    }

    private int[] getSectionIndices() {
        ArrayList<Integer> sectionIndices = new ArrayList<Integer>();
        if (mData.size() > 0) {
            char lastFirstChar = mData.get(0).letter.charAt(0);
            sectionIndices.add(0);
            for (int i = 1; i < mData.size(); i++) {
                if (mData.get(i).letter.charAt(0) != lastFirstChar) {
                    lastFirstChar = mData.get(i).letter.charAt(0);
                    sectionIndices.add(i);
                }
            }
            int[] sections = new int[sectionIndices.size()];
            for (int i = 0; i < sectionIndices.size(); i++) {
                sections[i] = sectionIndices.get(i);
            }
            return sections;
        }
        return null;
    }

    public int getSectionForLetter(String letter) {
        if (null != mSectionIndices) {
            for (int i = 0; i < mSectionIndices.length; i++) {
                if (mSectionLetters[i].equals(letter)) {
                    return mSectionIndices[i] + 1;
                }
            }
        }
        return -1;
    }

    private String[] getSectionLetters() {
        if (null != mSectionIndices) {
            String[] letters = new String[mSectionIndices.length];
            for (int i = 0; i < mSectionIndices.length; i++) {
                letters[i] = mData.get(mSectionIndices[i]).letter;
            }
            return letters;
        }
        return null;
    }

    public int getScrollPosition(String letter) {
        if(getSectionForLetter(letter)!=-1){
            for (int i = 0; i < mData.size(); i++) {
                if (mData.get(i).letter.equals(letter)) {
                    return i;
                }
            }
        }
        return -1; // -1不会滑动
    }
}

