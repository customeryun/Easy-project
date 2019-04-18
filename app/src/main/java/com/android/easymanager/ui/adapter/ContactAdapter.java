package com.android.easymanager.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.easymanager.R;
import com.android.easymanager.ui.activity.ContactDetailActivity;
import com.android.easymanager.ui.activity.NewContactActivity;
import com.android.easymanager.ui.activity.StudentContactGroupActivity;
import com.android.easymanager.ui.activity.TeacherGroupActivity;
import com.android.easymanager.ui.bean.Contact;
import com.android.easymanager.ui.widget.contact.ContactComparator;
import com.android.easymanager.ui.widget.contact.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private String[] mContactNames; // 联系人名称字符串数组
    private List<String> mContactList; // 联系人名称List（转换成拼音）
    private List<Contact> resultList; // 最终结果（包含分组的字母）
    private List<String> characterList; // 字母List

    public enum ITEM_TYPE {
        ITEM_TYPE_HEAD,
        ITEM_TYPE_CHARACTER,
        ITEM_TYPE_CONTACT
    }

    public ContactAdapter(Context context, String[] contactNames) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mContactNames = contactNames;

        handleContact();
    }

    private void handleContact() {
        mContactList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < mContactNames.length; i++) {
            String pinyin = Utils.getPingYin(mContactNames[i]);
            map.put(pinyin, mContactNames[i]);
            mContactList.add(pinyin);
        }
//        Collections.sort(mContactList, new ContactComparator());

        resultList = new ArrayList<>();
        characterList = new ArrayList<>();

        resultList.add(new Contact("", ITEM_TYPE.ITEM_TYPE_HEAD.ordinal()));

        for (int i = 0; i < mContactList.size(); i++) {
            String name = mContactList.get(i);
            String character = (name.charAt(0) + "").toUpperCase(Locale.ENGLISH);
            if (!characterList.contains(character)) {
                if (character.hashCode() >= "A".hashCode() && character.hashCode() <= "Z".hashCode()) { // 是字母
                    characterList.add(character);
                    resultList.add(new Contact(character, ITEM_TYPE.ITEM_TYPE_CHARACTER.ordinal()));
                } else {
                    if (!characterList.contains("#")) {
                        characterList.add("#");
                        resultList.add(new Contact("#", ITEM_TYPE.ITEM_TYPE_CHARACTER.ordinal()));
                    }
                }
            }
            resultList.add(new Contact(map.get(name), ITEM_TYPE.ITEM_TYPE_CONTACT.ordinal()));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == ITEM_TYPE.ITEM_TYPE_HEAD.ordinal()){
            return new HeadHolder(mLayoutInflater.inflate(R.layout.contact_manager_head_layout, parent, false));
        }else
        if (viewType == ITEM_TYPE.ITEM_TYPE_CHARACTER.ordinal()) {
            return new CharacterHolder(mLayoutInflater.inflate(R.layout.contact_character_item, parent, false));
        } else {
            return new ContactHolder(mLayoutInflater.inflate(R.layout.contact_item_layout, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CharacterHolder) {
            ((CharacterHolder) holder).mTextView.setText(resultList.get(position).getmName());
        } else if (holder instanceof ContactHolder) {
            ((ContactHolder) holder).mTextView.setText(resultList.get(position).getmName());
            ((ContactHolder) holder).mTextView_class.setText(resultList.get(position).getmClassName());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return resultList.get(position).getmType();
    }

    @Override
    public int getItemCount() {
        return resultList == null ? 0 : resultList.size();
    }

    public class HeadHolder extends RecyclerView.ViewHolder {
        @OnClick({R.id.contact_teacher,R.id.contact_new,R.id.contact_student})
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.contact_new:
                    NewContactActivity.launchActivity(mContext);
                    break;
                case R.id.contact_teacher:
                    TeacherGroupActivity.launchActivity(mContext);
                    break;
                case R.id.contact_student:
                    StudentContactGroupActivity.launchActivity(mContext);
                    break;
            }
        }

        HeadHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }
    }

    public class CharacterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.character)
        TextView mTextView;

        CharacterHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ContactDetailActivity.launchActivity(mContext);
                }
            });
        }
    }

    public int getScrollPosition(String character) {
        if (characterList.contains(character)) {
            for (int i = 0; i < resultList.size(); i++) {
                if (resultList.get(i).getmName().equals(character)) {
                    return i;
                }
            }
        }
        return -1; // -1不会滑动
    }
}

