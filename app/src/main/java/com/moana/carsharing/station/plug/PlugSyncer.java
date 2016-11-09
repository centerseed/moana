package com.moana.carsharing.station.plug;

import android.content.Context;
import android.net.Uri;

import com.moana.carsharing.R;
import com.moana.carsharing.station.StationCarParser;
import com.moana.carsharing.station.StationProvider;

import okhttp3.OkHttpClient;

public class PlugSyncer {
    Context mContext;
    Uri mUri;
    OkHttpClient mClient;

    public PlugSyncer(Context context) {
        mContext = context;
        mUri = StationProvider.getProviderUri(context.getString(R.string.auth_provider_plug), StationProvider.TABLE_PLUG);
    }

    public static PlugSyncer with(Context context) {
        return new PlugSyncer(context);
    }

    public void getPlugInfos() {
        // TODO: okhttp, we use fake data
        PlugInfoParser.with(mContext, mUri).parseDummy();
    }
}
