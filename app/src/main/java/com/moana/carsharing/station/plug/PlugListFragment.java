package com.moana.carsharing.station.plug;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.moana.carsharing.R;
import com.moana.carsharing.base.AbstractRecyclerCursorAdapter;
import com.moana.carsharing.base.RecyclerFragment;
import com.moana.carsharing.station.StationProvider;

public class PlugListFragment extends RecyclerFragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
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
}
