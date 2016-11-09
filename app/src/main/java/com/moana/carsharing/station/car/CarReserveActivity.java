package com.moana.carsharing.station.car;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;

import com.moana.carsharing.R;
import com.moana.carsharing.base.BasePagerActivity;
import com.moana.carsharing.base.ConstantDef;
import com.moana.carsharing.station.plug.CarReserveConfirmFragment;
import com.moana.carsharing.station.plug.CarReserveOrderFragment;
import com.moana.carsharing.station.plug.PlugReserveConfirmFragment;
import com.moana.carsharing.station.plug.PlugReserveInfo;
import com.moana.carsharing.station.plug.PlugReserveOrderFragment;

import java.io.Serializable;

public class CarReserveActivity extends BasePagerActivity {

    public CarReserveInfo mInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInfo = (CarReserveInfo) getIntent().getSerializableExtra(ConstantDef.ARG_RESERVE_CAR_INFO);
    }

    @Override
    protected FragmentPagerAdapter getPagerAdapter(FragmentManager fm) {
        return new SectionsPagerAdapter(fm);
    }

    @Override
    protected String getActivityTitle() {
        return getResources().getString(R.string.title_rent_reserve);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putString(ConstantDef.ARG_STRING, mInfo.address);
            switch (position) {
                case 0:
                    return CarReserveOrderFragment.newInstance(bundle);
            }
            return CarReserveConfirmFragment.newInstance(bundle);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }

    public class RentReserveInfo implements Serializable {
        public String name;
        public String address;
        public long startTime;
        public long endTime;
        public String fee;
        public String cost;
    }
}
