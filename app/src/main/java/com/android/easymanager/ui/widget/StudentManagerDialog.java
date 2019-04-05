package com.android.easymanager.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by PC-xiaoming on 2019/4/5.
 */

public class StudentManagerDialog extends Dialog{

    public StudentManagerDialog(@NonNull Context context) {
        super(context);
    }

    public StudentManagerDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected StudentManagerDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
