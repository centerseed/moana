package com.moana.carsharing.sync;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.moana.carsharing.R;
import com.moana.carsharing.base.AsyncCallback;
import com.moana.carsharing.station.plug.PlugInfoParser;
import com.moana.carsharing.station.StationProvider;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PlugSyncer {
    static final String TAG = "PlugSyncer";

    Context mContext;
    Uri mUri;
    OkHttpClient mClient;

    private PlugSyncer(Context context) {
        mContext = context;
        mUri = StationProvider.getProviderUri(context.getString(R.string.auth_provider_plug), StationProvider.TABLE_STATION);
    }

    public static PlugSyncer with(Context context) {
        return new PlugSyncer(context);
    }

    public void getPlugInfos(LatLng latLng) {
        String url = "";

        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new AsyncCallback(mContext) {

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.d(TAG, json);
            }
        });
    }

    public void getPlugInfos() {
        // TODO: okhttp, we use fake data
        PlugInfoParser.with(mContext, mUri).parseDummy();
    }
}
