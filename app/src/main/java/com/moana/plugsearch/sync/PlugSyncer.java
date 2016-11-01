package com.moana.plugsearch.sync;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.moana.plugsearch.R;
import com.moana.plugsearch.plug.PlugInfoParser;
import com.moana.plugsearch.plug.PlugProvider;

public class PlugSyncer {
    Context mContext;
    Uri mUri;

    private PlugSyncer(Context context) {
        mContext = context;
        mUri = PlugProvider.getProviderUri(context.getString(R.string.auth_provider_plug), PlugProvider.TABLE_PLUG);
    }

    public static PlugSyncer with(Context context) {
        return new PlugSyncer(context);
    }

    public void getPlugInfos() {
        // TODO: okhttp, we use fake data
        PlugInfoParser.with(mContext, mUri).parseDummy();
    }
}
