package com.moana.carsharing.station.car;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.moana.carsharing.R;
import com.moana.carsharing.base.AbstractRecyclerCursorAdapter;
import com.moana.carsharing.base.ConstantDef;
import com.moana.carsharing.base.RecyclerFragment;
import com.moana.carsharing.station.StationProvider;
import com.moana.carsharing.station.plug.PlugAdapter;
import com.moana.carsharing.station.plug.PlugReserveActivity;
import com.moana.carsharing.station.plug.PlugSyncer;

public class CarListFragment extends RecyclerFragment implements CarAdapter.ResultAdapterListener {

    public static CarListFragment newInstance(String id, String name, String address) {
        Bundle bundle = new Bundle();
        bundle.putString(StationProvider.FIELD_ID, id);
        bundle.putString(StationProvider.FIELD_STATION_NAME, name);
        bundle.putString(StationProvider.FIELD_STATION_ADDRESS, address);

        CarListFragment f = new CarListFragment();
        f.setArguments(bundle);
        return f;
    }

    @Override
    public void onResume() {
        super.onResume();

        ((CarAdapter)mAdapter).setCarAdapterListener(this);
    }

    @Override
    protected AbstractRecyclerCursorAdapter getAdapter() {
        return new CarAdapter(getContext(), null);
    }

    @Override
    protected void onSync() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    CarSyncer.with(getContext()).getCarInfos();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected Uri getProviderUri() {
        return StationProvider.getProviderUri(getResources().getString(R.string.auth_provider_plug), StationProvider.TABLE_CAR);
    }

    @Override
    public void onCarClick(Cursor cursor) {
        Intent intent = new Intent(getActivity(), CarReserveActivity.class);
        intent.putExtra(ConstantDef.ARG_SITE_NAME, getArguments().getString(ConstantDef.ARG_SITE_NAME));
        intent.putExtra(ConstantDef.ARG_SITE_ADDRESS, getArguments().getString(ConstantDef.ARG_SITE_ADDRESS));
        intent.putExtra(ConstantDef.ARG_CHARGE, cursor.getInt(cursor.getColumnIndex(StationProvider.FIELD_CAR_CHARGE)));
        startActivity(intent);
    }
}
