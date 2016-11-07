package com.moana.carsharing.sync;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.moana.carsharing.R;
import com.moana.carsharing.base.AsyncCallback;
import com.moana.carsharing.plug.PlugInfoParser;
import com.moana.carsharing.plug.PlugProvider;
import com.moana.carsharing.rent.RentInfoParser;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RentSyncer {
    static final String TAG = "PlugSyncer";

    Context mContext;
    Uri mUri;
    OkHttpClient mClient;

    private RentSyncer(Context context) {
        mContext = context;
        mUri = PlugProvider.getProviderUri(context.getString(R.string.auth_provider_plug), PlugProvider.TABLE_PLUG);
    }

    public static RentSyncer with(Context context) {
        return new RentSyncer(context);
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
        RentInfoParser.with(mContext, mUri).parseDummy();
    }
}
