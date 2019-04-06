package com.android.easymanager.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.easymanager.R;
/**
 * 提示对话框(可以自定义布局)
 * 1，两个选项，顶部是问号图标
 * 2，单个选项
 */
public class AlertDialog extends Dialog {

    public AlertDialog(Context context) {
        super(context);
    }

    public AlertDialog(Context context, int theme) {
        super(context, theme);
    }

    /**
     * 建造者类
     *
     * @author Administrator
     */
    public static class Builder {
        private Context context;
        private int topImageId;
        private String title;
        private String message;
        private Drawable drawable = null;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * 设置顶部图标
         */
        public Builder setTopImage(int id) {
            this.topImageId = id;
            return this;
        }

        /**
         * 设置消息内容
         *
         * @param title
         * @return
         */
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * 设置消息内容
         *
         * @param message
         * @return
         */
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setDrawable(Drawable drawable_id) {
            this.drawable = drawable_id;
            return this;
        }


        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        /**
         * 设置积极按钮
         *
         * @param positiveButtonText
         * @param listener
         * @return
         */
        public Builder setPositiveButton(String positiveButtonText,
                                         OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        /**
         * 设置消极按钮
         *
         * @param negativeButtonText
         * @param listener
         * @return
         */
        public Builder setNegativeButton(String negativeButtonText,
                                         OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        /**
         * 创建一个AlertDialog
         *
         * @return
         */
        public AlertDialog create() {
            LayoutInflater inflater = LayoutInflater.from(context);

            final AlertDialog dialog = new AlertDialog(context);
            View    layout = inflater.inflate(R.layout.student_manager_dialog_layout, null);
            // 设置对话框的视图
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT);
            dialog.setContentView(layout, params);
            return dialog;
        }
    }
}
