package com.moana.carsharing.station.plug;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.moana.carsharing.R;
import com.moana.carsharing.base.BaseSettingFragment;
import com.moana.carsharing.base.ConstantDef;
import com.moana.carsharing.station.StationProvider;
import com.moana.carsharing.utils.TimeUtils;

public class PlugReserveOrderFragment extends BaseSettingFragment {

    int mPlugId;
    String mSiteName;
    TextView mSite;
    TextView mPlugSerial;
    EditText mStartTime;

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


        // override on Click event
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Delete this temp order, open new activity to show result
                // use dummy data
                PlugReserveInfo info = new PlugReserveInfo();
                info.serial = getArguments().getString(ConstantDef.ARG_ORDER_TEMP_SERIAL);
                info.id = mPlugId;
                info.site = mSiteName;
                info.time = 0;

                getContext().getContentResolver().insert(mUri, info.getContentValues());
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
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (mListener != null) mListener.toNextFragment();
                        }
                    });
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
}
