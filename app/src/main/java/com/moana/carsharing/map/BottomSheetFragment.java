package com.moana.carsharing.map;


import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.moana.carsharing.R;
import com.moana.carsharing.base.BroadcastActivity;
import com.moana.carsharing.base.BroadcastFragment;
import com.moana.carsharing.base.ConstantDef;
import com.moana.carsharing.base.ContentFragment;
import com.moana.carsharing.plug.PlugProvider;

public class BottomSheetFragment extends BroadcastFragment{
    String mSnippet = "";
    TextView mName;
    TextView mAddress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottomsheet, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mName = (TextView) view.findViewById(R.id.name);
        mAddress = (TextView) view.findViewById(R.id.address);
    }

    @Override
    protected Uri getProviderUri() {
        return PlugProvider.getProviderUri(getString(R.string.auth_provider_plug), PlugProvider.TABLE_STATION);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cl = (CursorLoader) super.onCreateLoader(id, args);
        cl.setSelection(PlugProvider.FIELD_STATION_ADDRESS + "=?");
        cl.setSelectionArgs(new String[]{mSnippet});
        return cl;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst()) {
            mName.setText(data.getString(data.getColumnIndex(PlugProvider.FIELD_STATION_NAME)));
            mAddress.setText(data.getString(data.getColumnIndex(PlugProvider.FIELD_STATION_ADDRESS)));
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void addIntentFilter(IntentFilter filter) {
        filter.addAction(ConstantDef.ACTION_CHOOSE_POSIITON);
    }

    @Override
    public void onReceiveBroadcast(String action, Intent intent) {
        if (action.equals(ConstantDef.ACTION_CHOOSE_POSIITON)) {
            mSnippet = intent.getStringExtra(ConstantDef.ARG_STRING);
            getLoaderManager().restartLoader(0, null, this);
        }
    }

    @Override
    public void onNetworkFail(String fail) {

    }
}
