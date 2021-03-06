package com.moana.carsharing.map;


import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moana.carsharing.R;
import com.moana.carsharing.base.BroadcastFragment;
import com.moana.carsharing.base.ConstantDef;
import com.moana.carsharing.station.StationProvider;

import java.text.DecimalFormat;

public class BottomSheetFragment extends BroadcastFragment{
    String mSnippet = "";
    TextView mName;
    TextView mAddress;
    TextView mDistance;

    DecimalFormat mDecimalFormat = new DecimalFormat("#.##");

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
        mDistance = (TextView) view.findViewById(R.id.distance);
    }

    @Override
    protected Uri getProviderUri() {
        return StationProvider.getProviderUri(getString(R.string.auth_provider_plug), StationProvider.TABLE_STATION);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cl = (CursorLoader) super.onCreateLoader(id, args);
        cl.setSelection(StationProvider.FIELD_STATION_ADDRESS + "=?");
        cl.setSelectionArgs(new String[]{mSnippet});
        return cl;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst()) {
            mName.setText(data.getString(data.getColumnIndex(StationProvider.FIELD_STATION_NAME)));
            mAddress.setText(data.getString(data.getColumnIndex(StationProvider.FIELD_STATION_ADDRESS)));
            mDistance.setText(mDecimalFormat.format(data.getFloat(data.getColumnIndex(StationProvider.FIELD_DISTANCE))) + " km");
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
