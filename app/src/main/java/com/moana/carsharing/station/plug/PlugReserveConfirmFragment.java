package com.moana.carsharing.station.plug;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.moana.carsharing.R;
import com.moana.carsharing.base.BaseSettingFragment;
import com.moana.carsharing.utils.TimeUtils;

public class PlugReserveConfirmFragment extends BaseSettingFragment{

    public String mAddress;
    public EditText mSite;
    public TextView mStartTime;
    public TextView mEndTime;
    static PlugReserveConfirmFragment mInstance;

    public static PlugReserveConfirmFragment newInstance(Bundle bundle) {
        mInstance = new PlugReserveConfirmFragment();
        mInstance.setArguments(bundle);
        return mInstance;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_plug_reserve_confirm, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSite = (EditText) view.findViewById(R.id.edit_site_name);
        mStartTime = (TextView) view.findViewById(R.id.time_start);
        mEndTime = (TextView) view.findViewById(R.id.time_end);
    }

    public static void onPageSelected() {
        if (mInstance != null && mInstance.isResumed()) {
            PlugReserveActivity.PlugReserveInfo info = ((PlugReserveActivity)mInstance.getActivity()).mInfo;
            if (info != null) {
                mInstance.mSite.setText(info.address);
                mInstance.mStartTime.setText(TimeUtils.getYYYYMMDDStr(mInstance.getContext(), info.startTime));
            }
        }
    }
}
