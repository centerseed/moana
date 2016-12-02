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
import android.widget.Button;
import android.widget.TextView;

import com.moana.carsharing.R;
import com.moana.carsharing.base.ConstantDef;
import com.moana.carsharing.base.ContentFragment;
import com.moana.carsharing.station.StationProvider;
import com.moana.carsharing.utils.TimeUtils;

public class CarReserveDetailFragment extends ContentFragment {

    static CarReserveDetailFragment mInstance;
    TextView mName;
    TextView mTimeStart;
    TextView mTimeEnd;
    TextView mOrderSerial;
    TextView mOrderTime;
    TextView mOrderStatus;
    CarReserveInfo mInfo;

    Button mDone;
    Button mCancel;

    public static CarReserveDetailFragment newInstance(Bundle bundle) {
        mInstance = new CarReserveDetailFragment();
        mInstance.setArguments(bundle);
        return mInstance;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_car_reserve_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mName = (TextView) view.findViewById(R.id.name);

        mTimeStart = (TextView) view.findViewById(R.id.time_start);
        mTimeEnd = (TextView) view.findViewById(R.id.time_end);
        mOrderSerial = (TextView) view.findViewById(R.id.order_serial);
        mOrderStatus = (TextView) view.findViewById(R.id.order_status);
        mOrderTime = (TextView) view.findViewById(R.id.order_time);

        mDone = (Button) view.findViewById(R.id.next);
        mDone.setText(R.string.title_fee_confirm);
        mDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        mCancel = (Button) view.findViewById(R.id.back);
        mCancel.setText(R.string.title_cancel_order);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContext().getContentResolver().delete(mUri,
                        StationProvider.FIELD_CAR_ORDER_SERIAL + "=?", new String[]{getArguments().getString(ConstantDef.ARG_ORDER_TEMP_SERIAL)});
                getActivity().finish();
            }
        });
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
            mOrderSerial.setText(mInfo.orderSerial);
            mOrderTime.setText(TimeUtils.getYYYYMMDDStr(getContext(), mInfo.orderTime));
            mOrderStatus.setText(mInfo.status);
            mTimeStart.setText(TimeUtils.getYYYYMMDDStr(getContext(), mInfo.startTime));
            mTimeEnd.setText(TimeUtils.getYYYYMMDDStr(getContext(), mInfo.endTime));
            mName.setText(mInfo.rentSite);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
