package com.android.easymanager.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.easymanager.R;
import com.android.easymanager.ui.widget.contact.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LanguageAdapter extends BaseRecyclerAdapter<String> {

    public static final int TYPE_CHARACTER = 0;
    public static final int TYPE_LANGUAGE = 1;

    private Context mContext;
    private List<String> mDatas;
    private List<String> mLanguageList; // 语言列表（拼音）
    private List<String> characterList; // 字母列表
    private List<Language> resultList; // 最终结果（包含分组的字母）

    private OnRvItemListener mOnRvItemListener;

    public void setOnRvItemListener(OnRvItemListener onRvItemListener){
        this.mOnRvItemListener = onRvItemListener;
    }

    public interface OnRvItemListener {
        void onItemClick(int position, int type);
    }


    public LanguageAdapter(Context context,List<String> datas){
        mContext = context;
        mDatas = datas;
        handleDatas();
    }

    public List<Language> getResultDatas(){
        return resultList;
    }

    private void handleDatas(){
        mLanguageList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < mDatas.size(); i++) {
            String pinyin = Utils.getPingYin(mDatas.get(i));
            map.put(pinyin, mDatas.get(i));
            mLanguageList.add(pinyin);
        }
        Collections.sort(mLanguageList, new ContactComparator());

        resultList = new ArrayList<>();
        characterList = new ArrayList<>();

        for (int i = 0; i < mLanguageList.size(); i++) {
            String name = mLanguageList.get(i);
            String character = (name.charAt(0) + "").toUpperCase(Locale.ENGLISH);
            if (!characterList.contains(character)) {
                if (character.hashCode() >= "A".hashCode() && character.hashCode() <= "Z".hashCode()) { // 是字母
                    characterList.add(character);
                    resultList.add(new Language(character, TYPE_CHARACTER));
                } else {
                    if (!characterList.contains("#")) {
                        characterList.add("#");
                        resultList.add(new Language("#", TYPE_CHARACTER));
                    }
                }
            }
            resultList.add(new Language(map.get(name), TYPE_LANGUAGE));
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case TYPE_CHARACTER:
                holder = new CharacterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_language_character_layout, parent, false));
                break;
            case TYPE_LANGUAGE:
        holder = new LanguageHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_language_item_layout, parent, false));
                break;

        }
        return holder;
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, String data) {
        if(viewHolder instanceof LanguageHolder){
            ((LanguageHolder) viewHolder).mTextView.setText(resultList.get(RealPosition).getmName());
        }else if(viewHolder instanceof CharacterHolder){
            ((CharacterHolder) viewHolder).mTextView.setText(resultList.get(RealPosition).getmName());
        }
    }

    public class LanguageHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView mTextView;

        LanguageHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mOnRvItemListener!=null){
                        mOnRvItemListener.onItemClick(getAdapterPosition(),getItemViewType());
                    }
                }
            });
        }
    }

    public class CharacterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView mTextView;

        CharacterHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }
    }

    public class Language {
        private String mName;
        private int mType;

        public Language(String name, int type) {
            mName = name;
            mType = type;
        }

        public String getmName() {
            return mName;
        }

        public int getmType() {
            return mType;
        }
    }

    class ContactComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            int c1 = (o1.charAt(0) + "").toUpperCase().hashCode();
            int c2 = (o2.charAt(0) + "").toUpperCase().hashCode();

            boolean c1Flag = (c1 < "A".hashCode() || c1 > "Z".hashCode()); // 不是字母
            boolean c2Flag = (c2 < "A".hashCode() || c2 > "Z".hashCode()); // 不是字母
            if (c1Flag && !c2Flag) {
                return 1;
            } else if (!c1Flag && c2Flag) {
                return -1;
            }
            return c1 - c2;
        }

    }

}