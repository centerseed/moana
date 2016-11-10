package com.moana.carsharing.station.car;

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
import com.moana.carsharing.utils.TimeUtils;

public class CarReserveConfirmFragment extends BaseSettingFragment {

    static CarReserveConfirmFragment mInstance;
    TextView mName;
    TextView mTimeStart;
    TextView mTimeEnd;
    CarReserveInfo mInfo;

    public static CarReserveConfirmFragment newInstance(Bundle bundle) {
        mInstance = new CarReserveConfirmFragment();
        mInstance.setArguments(bundle);
        return mInstance;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_car_reserve_confirm, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mName = (TextView) view.findViewById(R.id.name);
        mName.setText(getArguments().getString(StationProvider.FIELD_STATION_NAME));

        mTimeStart = (TextView) view.findViewById(R.id.time_start);
        mTimeEnd = (TextView) view.findViewById(R.id.time_end);
    }

    @Override
    protected Uri getProviderUri() {
        return StationProvider.getProviderUri(getString(R.string.auth_provider_plug), StationProvider.TABLE_CAR_ORDER);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cl = (CursorLoader) super.onCreateLoader(id, args);
        cl.setSelection(StationProvider.FIELD_CAR_ORDER_SERIAL + "=?");
        cl.setSelectionArgs(new String[]{getArguments().getString(ConstantDef.ARG_ORDER_TEMP_SERIAL)});
        return cl;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst()) {
            mInfo = new CarReserveInfo(data);
            mTimeStart.setText(TimeUtils.getYYYYMMDDStr(getContext(), mInfo.startTime));
            mTimeEnd.setText(TimeUtils.getYYYYMMDDStr(getContext(), mInfo.endTime));
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
