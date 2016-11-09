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
        bundle.putString(StationProvider.FIELD_STATION_ADDRESS, name);
        bundle.putString(StationProvider.FIELD_STATION_NAME, address);

        CarListFragment f = new CarListFragment();
        f.setArguments(bundle);
        return f;
    }

    @Override
    public void onResume() {
        super.onResume();
        CarSyncer.with(getContext()).getCarInfos();

        ((CarAdapter)mAdapter).setCarAdapterListener(this);
    }

    @Override
    protected AbstractRecyclerCursorAdapter getAdapter() {
        return new CarAdapter(getContext(), null);
    }

    @Override
    protected Uri getProviderUri() {
        return StationProvider.getProviderUri(getResources().getString(R.string.auth_provider_plug), StationProvider.TABLE_CAR);
    }

    @Override
    public void onCarClick(Cursor cursor) {
        CarReserveInfo info = new CarReserveInfo(cursor);
        info.address = getArguments().getString(StationProvider.FIELD_STATION_ADDRESS);
        Intent intent = new Intent(getActivity(), CarReserveActivity.class);
        intent.putExtra(ConstantDef.ARG_RESERVE_CAR_INFO, info);
        startActivity(intent);
    }
}
