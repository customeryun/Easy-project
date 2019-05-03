package com.android.easymanager.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by PC-xiaoming on 2019/5/3.
 */

public class TuiJianItemTouchCallback extends ItemTouchHelper.Callback{

    private CallbackItemTouch mCallbackItemTouch;
    public TuiJianItemTouchCallback(CallbackItemTouch callbackItemTouch){
        this.mCallbackItemTouch = callbackItemTouch;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeFlag(ItemTouchHelper.ACTION_STATE_DRAG,dragFlag);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if(mCallbackItemTouch!=null){
            mCallbackItemTouch.itemTouchOnMove(viewHolder.getAdapterPosition(),target.getAdapterPosition()); // information to the interface
        }
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }
}
