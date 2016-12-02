package com.moana.carsharing.station.plug;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.moana.carsharing.R;
import com.moana.carsharing.base.BaseSettingFragment;
import com.moana.carsharing.base.ConstantDef;
import com.moana.carsharing.order.OrderDetailActivity;
import com.moana.carsharing.station.StationProvider;
import com.moana.carsharing.station.car.CarReserveOrderFragment;
import com.moana.carsharing.utils.TimeUtils;

import java.util.Calendar;

public class PlugReserveOrderFragment extends BaseSettingFragment {

    int mPlugId;
    String mSiteName;
    TextView mSite;
    TextView mPlugSerial;
    EditText mStartTime;
    PlugReserveInfo mInfo;

    public static PlugReserveOrderFragment newInstance(Bundle bundle) {
        PlugReserveOrderFragment f = new PlugReserveOrderFragment();
        f.setArguments(bundle);
        return f;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_plug_reserve_order, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBack.setVisibility(View.GONE);

        mPlugId = getArguments().getInt(ConstantDef.ARG_INT);
        mSiteName = getArguments().getString(ConstantDef.ARG_SITE_NAME);

        mSite = (TextView) view.findViewById(R.id.name);
        mSite.setText(mSiteName);

        mPlugSerial = (TextView) view.findViewById(R.id.serial);
        mPlugSerial.setText(mPlugId + "");

        mStartTime = (EditText) view.findViewById(R.id.edit_time_start);
        mStartTime.setText(TimeUtils.getYYYYMMDDStr(getContext(), System.currentTimeMillis()));
        mStartTime.setOnClickListener(new DatePickListener());

        // override on Click event
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Delete this temp order, open new activity to show result
                // use dummy data
                mInfo = new PlugReserveInfo();
                mInfo.orderTime = System.currentTimeMillis() + 8000000;
                mInfo.serial =  "Plug " + mInfo.orderTime /1000000;
                mInfo.id = mInfo.serial.hashCode();
                mInfo.site = mSiteName;
                mInfo.status = "處理中";
                mInfo.time = TimeUtils.getYYYYMMDDTimeStamp(getContext(), mStartTime.getText().toString());;

                getContext().getContentResolver().insert(mUri, mInfo.getContentValues());
                getContext().getContentResolver().notifyChange(mUri, null);
                dummySendOrder();
            }
        });
    }

    @Override
    protected Uri getProviderUri() {
        return StationProvider.getProviderUri(getString(R.string.auth_provider_plug), StationProvider.TABLE_PLUG_ORDER);
    }

    private void dummySendOrder() {
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", getString(R.string.action_sending_order), true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    dialog.dismiss();

                    Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
                    intent.putExtra(ConstantDef.ARG_ORDER_TEMP_SERIAL, mInfo.serial);
                    intent.putExtra(ConstantDef.ARG_BOOLEAN, false);
                    startActivity(intent);

                    getActivity().finish();
                }
            }
        }).start();
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
