package com.android.easymanager.utils.keyboard.interfaces;

import android.view.View;
import android.view.ViewGroup;

import com.android.easymanager.utils.keyboard.data.PageEntity;

public interface PageViewInstantiateListener<T extends PageEntity> {

    View instantiateItem(ViewGroup container, int position, T pageEntity);
}
