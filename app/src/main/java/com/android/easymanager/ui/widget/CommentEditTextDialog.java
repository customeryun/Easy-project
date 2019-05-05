package com.android.easymanager.ui.widget;

import com.android.easymanager.R;
import com.android.easymanager.ui.activity.CommentActivity;
import com.android.easymanager.ui.bean.CommentDetailBean;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by xz on 2017/6/14 0014.
 * 用户输入dialog
 */

public class CommentEditTextDialog {
    private Context mContext;
    private AlertDialog mAlertDialog;
    private View mView;
    private EditText mEditText;
    private ImageView mFaceIco;
    private TextView mSubmit;
    private RecyclerView mRecyclerView;
    private ArrayMap<String, Integer> mEmotionMap;

    public CommentEditTextDialog(Context context) {
        this.mContext = context;
    }

    public CommentEditTextDialog initView() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.item_home_comment_edit, null);
        mEditText = (EditText) mView.findViewById(R.id.tv_eyou_edit);
        mEditText.setFocusable(true);
        mEditText.setFocusableInTouchMode(true);
        mEditText.requestFocus();
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        mEditText.addTextChangedListener(textWatcher);
        mEditText.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                     /*隐藏软键盘*/
                    InputMethodManager imm = (InputMethodManager) textView
                            .getContext().getSystemService(
                                    Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(
                                textView.getApplicationWindowToken(), 0);
                    }
                    String commentContent = mEditText.getText().toString().trim();
                    if (!TextUtils.isEmpty(commentContent)) {
                        CommentDetailBean detailBean = new CommentDetailBean("小明", commentContent, "刚刚");
                        //adapter.addTheCommentData(detailBean);
                       if(sendClickListener!=null){
                           sendClickListener.sendClick(detailBean);
                           mAlertDialog.dismiss();
                       }
                        Toast.makeText(mContext, "评论成功", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(mContext, "评论内容不能为空", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return false;
            }
        });
        return this;
    }

    public void showDialog() {
        // 创建对话
        mAlertDialog = new AlertDialog.Builder(mContext, R.style.EdittextAlertDialog).create();
        // 设置返回键失
//        dialog.setCancelable(true);
//        dialog.setCanceledOnTouchOutside(true);
        mAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (editTextDissmissListener != null) {
                    editTextDissmissListener.editTextContent(mEditText.getText().toString());
                }
                mContext = null;
                mAlertDialog = null;
                mEditText = null;
                mView = null;
            }
        });
        mAlertDialog.show();
        mAlertDialog.getWindow().setContentView(mView);
        mAlertDialog.getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        mAlertDialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        InputMethodManager imm = (InputMethodManager) mEditText.getContext()// 弹出软键盘
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        // 必须放到显示对话框下面，否则显示不出效果
        Window window = mAlertDialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.BOTTOM;
        window.setAttributes(params);
        // 加载布局组件
    }


    private void hideKeyBord() {
//        ((InputMethodManager)mContext.getSystemService(Context.INPUT_METHOD_SERVICE)).
//                hideSoftInputFromWindow(((Activity)mContext).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(mView.getWindowToken(), 0);
    }

    private void showKeyBord() {
//        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.showSoftInput(mView,InputMethodManager.SHOW_FORCED);
        InputMethodManager im = ((InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE));
        im.showSoftInput(mEditText, 0);
//        InputMethodManager imm = (InputMethodManager) mEditText.getContext()// 弹出软键盘
//                .getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }



    private SendClickListener sendClickListener;

    public CommentEditTextDialog setSendClickListener(SendClickListener sendClickListener) {
        this.sendClickListener = sendClickListener;
        return this;
    }

    private EditTextDissmissListener editTextDissmissListener;

    public CommentEditTextDialog setEditTextDissmissListener(EditTextDissmissListener editTextDissmissListener) {
        this.editTextDissmissListener = editTextDissmissListener;
        return this;
    }

    /**
     * 确认按钮监听
     */
    public interface SendClickListener {
        void sendClick(CommentDetailBean detailBean);
    }

    /**
     * Dialog关闭时，会返回当前dittText的文字
     */
    public interface EditTextDissmissListener {
        void editTextContent(String contentStr);
    }

}
