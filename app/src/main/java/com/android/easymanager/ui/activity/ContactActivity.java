package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import com.android.easymanager.R;
import com.android.easymanager.ui.adapter.ContactAdapter;
import com.android.easymanager.ui.widget.contact.DividerItemDecoration;
import com.android.easymanager.ui.widget.contact.LetterView;
import com.android.easymanager.ui.widget.settings.ItemView;
import butterknife.BindView;
import butterknife.OnClick;

public class ContactActivity extends BaseActivity {

    @BindView(R.id.contact_list)
    RecyclerView contactList;
    @BindView(R.id.letter_view)
    LetterView letterView;
//    @BindView(R.id.contact_new)
//    ItemView contact_item_new;
//    @BindView(R.id.contact_teacher)
//    ItemView contact_item_teacher;
//    @BindView(R.id.contact_student)
//    ItemView contact_item_student;

    private String[] contactNames;
    private LinearLayoutManager layoutManager;
    private ContactAdapter adapter;

    @Override
    public int getLayout() {
        return R.layout.contact_manager_layout;
    }

    @Override
    public void init() {
        setTitle("校园通讯录");
        setAddIconVisible(true);
        setAddIconRes(android.R.drawable.ic_menu_add);
        setAddIconListener(mAddOnClickListener);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        contactNames = new String[] {"张三丰", "郭靖", "黄蓉", "黄老邪", "赵敏", "123", "天山童姥", "任我行", "于万亭", "陈家洛", "韦小宝", "$6", "穆人清", "陈圆圆", "郭芙", "郭襄", "穆念慈", "东方不败", "梅超风", "林平之", "林远图", "灭绝师太", "段誉", "鸠摩智"};
        layoutManager = new LinearLayoutManager(this);
        adapter = new ContactAdapter(this, contactNames);

        contactList.setLayoutManager(layoutManager);
        contactList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        contactList.setAdapter(adapter);

        letterView.setCharacterListener(new LetterView.CharacterClickListener() {
            @Override
            public void clickCharacter(String character) {

                layoutManager.scrollToPositionWithOffset(adapter.getScrollPosition(character), 0);
            }

            @Override
            public void clickArrow() {
                layoutManager.scrollToPositionWithOffset(0, 0);
            }
        });

    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,ContactActivity.class);
        context.startActivity(intent);
    }

    View.OnClickListener mAddOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            ContactAddActivity.launchActivity(mContext);
        }
    };

//    @OnClick({R.id.contact_teacher,R.id.contact_new,R.id.contact_student})
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.contact_new:
//                NewContactActivity.launchActivity(mContext);
//                break;
//            case R.id.contact_teacher:
//                TeacherGroupActivity.launchActivity(mContext);
//                break;
//            case R.id.contact_student:
//                StudentContactGroupActivity.launchActivity(mContext);
//                break;
//        }
//    }
}
