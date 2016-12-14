package com.moana.carsharing.station.plug;

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

public class PlugListFragment extends RecyclerFragment implements PlugAdapter.ResultAdapterListener {

    public static PlugListFragment newInstance(String id, String name, String address) {
        Bundle bundle = new Bundle();
        bundle.putString(StationProvider.FIELD_ID, id);
        bundle.putString(ConstantDef.ARG_SITE_NAME, name);
        bundle.putString(ConstantDef.ARG_SITE_ADDRESS, address);

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
        Intent intent = new Intent(getActivity(), PlugReserveActivity.class);
        intent.putExtra(ConstantDef.ARG_SITE_NAME, getArguments().getString(ConstantDef.ARG_SITE_NAME));
        intent.putExtra(ConstantDef.ARG_SITE_ADDRESS, getArguments().getString(ConstantDef.ARG_SITE_ADDRESS));
        intent.putExtra(ConstantDef.ARG_INT, cursor.getInt(cursor.getColumnIndex(StationProvider.FIELD_ID)));
        startActivity(intent);
    }

    @Override
    protected void onSync() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    PlugSyncer.with(getContext()).getPlugInfos();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
