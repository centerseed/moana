package com.moana.carsharing.station.car;

import android.content.Context;
import android.net.Uri;

import com.moana.carsharing.R;
import com.moana.carsharing.station.StationProvider;
import com.moana.carsharing.station.plug.PlugInfoParser;

import okhttp3.OkHttpClient;

public class CarSyncer {
    Context mContext;
    Uri mUri;
    OkHttpClient mClient;

    public CarSyncer(Context context) {
        mContext = context;
        mUri = StationProvider.getProviderUri(context.getString(R.string.auth_provider_plug), StationProvider.TABLE_CAR);
    }

    public static CarSyncer with(Context context) {
        return new CarSyncer(context);
    }

    public void getCarInfos() {
        // TODO: okhttp, we use fake data
        CarInfoParser.with(mContext, mUri).parseDummy();
    }
}
