package com.moana.carsharing.station;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.moana.carsharing.R;
import com.moana.carsharing.base.AsyncCallback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StationCarSyncer {
    static final String TAG = "StationPlugSyncer";

    Context mContext;
    Uri mUri;
    OkHttpClient mClient;

    private StationCarSyncer(Context context) {
        mContext = context;
        mUri = StationProvider.getProviderUri(context.getString(R.string.auth_provider_plug), StationProvider.TABLE_STATION);
    }

    public static StationCarSyncer with(Context context) {
        return new StationCarSyncer(context);
    }

    public void getRentInfos(LatLng latLng) {
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

    public void getRentInfos() {
        // TODO: okhttp, we use fake data
        StationCarParser.with(mContext, mUri).parseDummy();
    }
}
