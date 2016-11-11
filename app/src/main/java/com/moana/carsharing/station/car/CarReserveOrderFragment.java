package com.moana.carsharing.station.car;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.moana.carsharing.R;
import com.moana.carsharing.base.BaseSettingFragment;
import com.moana.carsharing.base.ConstantDef;
import com.moana.carsharing.station.StationProvider;
import com.moana.carsharing.utils.TimeUtils;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.util.Calendar;

public class CarReserveOrderFragment extends BaseSettingFragment {

    TextView mName;
    TextView mAddress;
    TextView mRemainTime;
    EditText mStartTime;
    Spinner mDay;
    Spinner mHour;
    CircularProgressBar mProgressBar;
    TextView mCharge;

    ContentValues mOrder;
    public static CarReserveOrderFragment newInstance(Bundle bundle) {
        CarReserveOrderFragment f = new CarReserveOrderFragment();
        f.setArguments(bundle);
        return f;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_car_reserve_order, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBack.setVisibility(View.GONE);

        int charge = getArguments().getInt(ConstantDef.ARG_CHARGE);
        mProgressBar = (CircularProgressBar) view.findViewById(R.id.progressBar);
        mProgressBar.setProgress(charge);
        mProgressBar.setColor(CarAdapter.getProgressColor(getContext(), charge));

        mCharge = (TextView) view.findViewById(R.id.charge);
        mCharge.setText(charge + "%");
        mCharge.setTextColor(CarAdapter.getProgressColor(getContext(), charge));

        mRemainTime = (TextView) view.findViewById(R.id.remain_time);
        if (charge != 100)
            mRemainTime.setVisibility(View.VISIBLE);
        else
            mRemainTime.setVisibility(View.GONE);
        mStartTime = (EditText) view.findViewById(R.id.edit_time_start);
        mStartTime.setText(TimeUtils.getYYYYMMDDStr(getContext(), System.currentTimeMillis()));
        mStartTime.setOnClickListener(new DatePickListener());

        mDay = (Spinner) view.findViewById(R.id.spinner_day);
        ArrayAdapter<String> dayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.rent_day));
        mDay.setAdapter(dayAdapter);
        mDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("RoadPro", "CarReserveOrderFragment: " + "Spinner Day select " + i );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mHour = (Spinner) view.findViewById(R.id.spinner_hour);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.rent_hour));
        mHour.setAdapter(adapter);

        // override on Click event
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    CarReserveInfo info = new CarReserveInfo();

                    // TODO: Delete this temp order, open new activity to show result
                    info.orderSerial = getArguments().getString(ConstantDef.ARG_ORDER_TEMP_SERIAL);
                    info.startTime = TimeUtils.getYYYYMMDDTimeStamp(getContext(), mStartTime.getText().toString());
                    int usage = mDay.getSelectedItemPosition() * 24 + mHour.getSelectedItemPosition() + 1;
                    info.usage = usage;
                    info.endTime = info.startTime + usage * 3600 * 1000;

                    getContext().getContentResolver().insert(mUri, info.getContentValues());
                    getContext().getContentResolver().notifyChange(mUri, null);
                    mListener.toNextFragment();
                }
            }
        });
    }

    @Override
    protected Uri getProviderUri() {
        return StationProvider.getProviderUri(getString(R.string.auth_provider_plug), StationProvider.TABLE_CAR_ORDER);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    public class DatePickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            final EditText editText = (EditText) view;
            String dates[] = editText.getText().toString().split(" ");
            final Calendar c = Calendar.getInstance();

            int year, month, day;
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH) + 1;
            day = c.get(Calendar.DATE);

            final int hour = Integer.valueOf(dates[1].split(":")[0]);
            final int minute = Integer.valueOf(dates[1].split(":")[1]);

            DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                    new DatePickerDialog.OnDateSetListener() {
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            // 完成選擇，顯示日期
                            editText.setText(year + "/" + (monthOfYear + 1) + "/"
                                    + dayOfMonth);


                            TimePickerDialog tpd = new TimePickerDialog(getContext(),
                                    new TimePickerDialog.OnTimeSetListener() {
                                        public void onTimeSet(TimePicker view, int hourOfDay,
                                                              int minute) {
                                            // 完成選擇，顯示時間
                                            editText.setText(editText.getText().toString() + " " + hourOfDay + ":" + minute);
                                        }
                                    }, hour, minute, false);
                            tpd.show();

                        }
                    }, year, --month, day);
            dpd.show();
        }
    }
}
