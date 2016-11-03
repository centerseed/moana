package com.moana.carsharing.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import okhttp3.OkHttpClient;

public abstract class BroadcastActivity extends ContentActivity {
    protected BroadcastReceiver receiver;
    protected OkHttpClient mClient = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (null != intent) {
                    try {
                        if (intent.getAction().equals(ConstantDef.NETWORK_FAIL)) {
                            onNetworkFail(intent.getStringExtra(ConstantDef.ARG_STRING));
                        }
                        onReceiveBroadcast(intent.getAction(), intent);
                    } catch (Exception e) {

                    }
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();

        intentFilter.addAction(ConstantDef.NETWORK_FAIL);
        addIntentFilter(intentFilter);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    protected abstract void addIntentFilter(IntentFilter filter);
    protected abstract void onReceiveBroadcast(String action, Intent intent);
    protected abstract void onNetworkFail(String fail);
}
