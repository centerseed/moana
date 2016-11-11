package com.moana.carsharing.station.plug;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moana.carsharing.R;
import com.moana.carsharing.base.BaseSettingFragment;
import com.moana.carsharing.base.ConstantDef;
import com.moana.carsharing.station.StationProvider;
import com.moana.carsharing.station.car.CarReserveInfo;
import com.moana.carsharing.utils.TimeUtils;

public class PlugReserveDetailFragment extends BaseSettingFragment {

    static PlugReserveDetailFragment mInstance;
    TextView mName;
    TextView mTimeStart;
    TextView mPlugId;
    PlugReserveInfo mInfo;

    public static PlugReserveDetailFragment newInstance(Bundle bundle) {
        mInstance = new PlugReserveDetailFragment();
        mInstance.setArguments(bundle);
        return mInstance;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_plug_reserve_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNext.setText(getString(R.string.title_fee_confirm));

        mName = (TextView) view.findViewById(R.id.name);
        mName.setText(getArguments().getString(ConstantDef.ARG_SITE_NAME));

        mTimeStart = (TextView) view.findViewById(R.id.time_start);
        mPlugId = (TextView) view.findViewById(R.id.plug_id);

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mBack.setText(getString(R.string.title_cancel_order));
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected Uri getProviderUri() {
        return StationProvider.getProviderUri(getString(R.string.auth_provider_plug), StationProvider.TABLE_PLUG_ORDER);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cl = (CursorLoader) super.onCreateLoader(id, args);
        cl.setSelection(StationProvider.FIELD_PLUG_ORDER_SERIAL + "=?");
        cl.setSelectionArgs(new String[]{getArguments().getString(ConstantDef.ARG_ORDER_TEMP_SERIAL)});
        return cl;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst()) {
            mInfo = new PlugReserveInfo(data);
            mTimeStart.setText(TimeUtils.getYYYYMMDDStr(getContext(), mInfo.time));
            mPlugId.setText(mInfo.serial + "");
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
