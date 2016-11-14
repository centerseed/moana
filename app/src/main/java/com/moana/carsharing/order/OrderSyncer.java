package com.moana.carsharing.order;

import android.content.Context;
import android.net.Uri;

import com.moana.carsharing.R;
import com.moana.carsharing.station.StationProvider;
import com.moana.carsharing.station.plug.PlugInfoParser;
import com.moana.carsharing.station.plug.PlugSyncer;

import okhttp3.OkHttpClient;

/**
 * Created by barry.wu on 2016/11/14.
 */

public class OrderSyncer {
    Context mContext;
    OkHttpClient mClient;

    public OrderSyncer(Context context) {
        mContext = context;
    }

    public static OrderSyncer with(Context context) {
        return new OrderSyncer(context);
    }

    public void getOrderList() {
        final Uri carUri = StationProvider.getProviderUri(mContext.getString(R.string.auth_provider_plug), StationProvider.TABLE_CAR_ORDER);
        Uri plugUri = StationProvider.getProviderUri(mContext.getString(R.string.auth_provider_plug), StationProvider.TABLE_PLUG_ORDER);
        CarOrderParser.with(mContext, carUri).parseDummy();
        PlugOrderParser.with(mContext, plugUri).parseDummy();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    mContext.getContentResolver().notifyChange(carUri, null);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
