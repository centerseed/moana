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
import com.moana.carsharing.station.car.CarReserveActivity;
import com.moana.carsharing.station.car.CarReserveInfo;
import com.moana.carsharing.utils.TimeUtils;

public class CarReserveConfirmFragment extends BaseSettingFragment{

    public String mAddress;
    public EditText mSite;
    public TextView mStartTime;
    public TextView mEndTime;
    static CarReserveConfirmFragment mInstance;

    public static CarReserveConfirmFragment newInstance(Bundle bundle) {
        mInstance = new CarReserveConfirmFragment();
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
            CarReserveInfo info = ((CarReserveActivity)mInstance.getActivity()).mInfo;
            if (info != null) {
                mInstance.mSite.setText(info.address);
                mInstance.mStartTime.setText(TimeUtils.getYYYYMMDDStr(mInstance.getContext(), info.startTime));
            }
        }
    }
}
