package com.moana.carsharing.station.plug;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.moana.carsharing.R;
import com.moana.carsharing.base.AbstractRecyclerCursorAdapter;
import com.moana.carsharing.base.ConstantDef;
import com.moana.carsharing.base.RecyclerFragment;
import com.moana.carsharing.station.StationProvider;
import com.moana.carsharing.station.car.CarListFragment;

import static com.moana.carsharing.station.plug.PlugReserveActivity.*;

public class PlugListFragment extends RecyclerFragment implements PlugAdapter.ResultAdapterListener {

    public static PlugListFragment newInstance(String id, String name, String address) {
        Bundle bundle = new Bundle();
        bundle.putString(StationProvider.FIELD_ID, id);
        bundle.putString(StationProvider.FIELD_STATION_ADDRESS, name);
        bundle.putString(StationProvider.FIELD_STATION_NAME, address);

        PlugListFragment f = new PlugListFragment();
        f.setArguments(bundle);
        return f;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));

        ((PlugAdapter) mAdapter).setPlugAdapterListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        PlugSyncer.with(getContext()).getPlugInfos();
    }

    @Override
    protected AbstractRecyclerCursorAdapter getAdapter() {
        return new PlugAdapter(getContext(), null);
    }

    @Override
    protected Uri getProviderUri() {
        return StationProvider.getProviderUri(getResources().getString(R.string.auth_provider_plug), StationProvider.TABLE_PLUG);
    }

    @Override
    public void onPlugClick(Cursor cursor) {
        PlugReserveInfo info = new PlugReserveInfo(cursor);
        info.address = getArguments().getString(StationProvider.FIELD_STATION_ADDRESS);
        Intent intent = new Intent(getActivity(), PlugReserveActivity.class);
        intent.putExtra(ConstantDef.ARG_RESERVE_PLUG_INFO, info);
        startActivity(intent);
    }
}
