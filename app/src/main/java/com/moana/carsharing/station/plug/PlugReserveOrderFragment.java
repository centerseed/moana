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

import com.moana.carsharing.R;
import com.moana.carsharing.base.BaseSettingFragment;
import com.moana.carsharing.base.ConstantDef;
import com.moana.carsharing.utils.TimeUtils;

public class PlugReserveOrderFragment extends BaseSettingFragment {

    String mAddress;
    EditText mSite;
    EditText mStartTime;
    Spinner mUsage;

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

        mAddress = getArguments().getString(ConstantDef.ARG_STRING);

        mSite = (EditText) view.findViewById(R.id.edit_site_name);
        mSite.setText(mAddress);

        mStartTime = (EditText) view.findViewById(R.id.edit_time_start);
        mStartTime.setText(TimeUtils.getYYYYMMDDStr(getContext(), System.currentTimeMillis()));

        mUsage = (Spinner) view.findViewById(R.id.spinner_usage);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.rent_hour));
        mUsage.setAdapter(adapter);

        // override on Click event
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: generate order from API
                // use dummy data
                dummySendOrder();
            }
        });
    }

    @Override
    protected Uri getProviderUri() {
        return null;
    }

    private void dummySendOrder() {
        final PlugReserveInfo info = ((PlugReserveActivity) getActivity()).mInfo;
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), getString(R.string.action_loading), "", true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    dialog.dismiss();

                    info.name = "";
                    info.address = mAddress;
                    info.startTime = TimeUtils.getYYYYMMDDTimeStamp(getContext(), mStartTime.getText().toString());
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
