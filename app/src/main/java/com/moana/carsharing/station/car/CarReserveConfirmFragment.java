package com.moana.carsharing.station.car;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.moana.carsharing.CarIntroduceActivity;
import com.moana.carsharing.R;
import com.moana.carsharing.base.BaseSettingFragment;
import com.moana.carsharing.base.ConstantDef;
import com.moana.carsharing.order.OrderDetailActivity;
import com.moana.carsharing.station.StationProvider;
import com.moana.carsharing.utils.TimeUtils;

public class CarReserveConfirmFragment extends BaseSettingFragment {

    static CarReserveConfirmFragment mInstance;
    TextView mName;
    TextView mTimeStart;
    TextView mTimeEnd;
    ImageView mMore;
    CarReserveInfo mInfo;
    Spinner mFee;

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

        mNext.setText(getString(R.string.title_fee_confirm));

        mName = (TextView) view.findViewById(R.id.name);
        mName.setText(getArguments().getString(ConstantDef.ARG_SITE_NAME));

        mTimeStart = (TextView) view.findViewById(R.id.time_start);
        mTimeEnd = (TextView) view.findViewById(R.id.time_end);

        mFee = (Spinner) view.findViewById(R.id.fee_type);
        String fee[] = getResources().getStringArray(R.array.fee_type);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, fee);
        mFee.setAdapter(adapter);

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dummySendOrder();
            }
        });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = StationProvider.getProviderUri(getString(R.string.auth_provider_plug), StationProvider.TABLE_CAR_ORDER);
                getContext().getContentResolver().delete(uri, StationProvider.FIELD_CAR_ORDER_SERIAL + "=?", new String[]{getArguments().getString(ConstantDef.ARG_ORDER_TEMP_SERIAL)});
                if (mListener != null) mListener.toBackFragment();
            }
        });

        mMore = (ImageView) view.findViewById(R.id.more);
        mMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CarIntroduceActivity.class);
                getActivity().startActivity(intent);
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
            mTimeStart.setText(TimeUtils.getYYYYMMDDStr(getContext(), mInfo.startTime));
            mTimeEnd.setText(TimeUtils.getYYYYMMDDStr(getContext(), mInfo.endTime));
            mInfo.rentSite = getArguments().getString(ConstantDef.ARG_SITE_NAME);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private void dummySendOrder() {
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", getString(R.string.action_sending_order), true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    // TODO: place tmp order to real order
                    mInfo.orderTime = System.currentTimeMillis() + 8000000;
                    mInfo.status = "處理中";
                    mInfo.orderSerial = "Car " + mInfo.orderTime /1000000;
                    mInfo.id = mInfo.orderSerial.hashCode();
                    getContext().getContentResolver().insert(mUri, mInfo.getContentValues());
                    getContext().getContentResolver().notifyChange(mUri, null);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    dialog.dismiss();

                    Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
                    intent.putExtra(ConstantDef.ARG_ORDER_TEMP_SERIAL, mInfo.orderSerial);
                    intent.putExtra(ConstantDef.ARG_BOOLEAN, true);
                    startActivity(intent);

                    getActivity().finish();
                }
            }
        }).start();
    }
}
