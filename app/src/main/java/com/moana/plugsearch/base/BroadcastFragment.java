package com.moana.plugsearch.base;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

abstract public class BroadcastFragment extends ContentFragment {

    protected BroadcastReceiver receiver;
    protected IntentFilter filter;

    @Override
    public void onStart() {
        super.onStart();

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (null != intent) {
                    try {
                        onReceiveBroadcast(intent.getAction(), intent);
                    } catch (Exception e) {

                    }
                }
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        addIntentFilter(intentFilter);
        getContext().registerReceiver(receiver, intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        getContext().unregisterReceiver(receiver);
    }

    public abstract void addIntentFilter(IntentFilter filter);
    public abstract void onReceiveBroadcast(String action, Intent intent);
}