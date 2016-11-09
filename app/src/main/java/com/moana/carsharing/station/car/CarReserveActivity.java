package com.moana.carsharing.station.car;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;

import com.moana.carsharing.R;
import com.moana.carsharing.base.BasePagerActivity;
import com.moana.carsharing.base.ConstantDef;
import com.moana.carsharing.station.plug.PlugReserveConfirmFragment;

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

    @Override
    public void onPageSelected(int position) {
        super.onPageSelected(position);
        if (position == 1) {
            CarReserveConfirmFragment.onPageSelected();
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(ConstantDef.ARG_RESERVE_CAR_INFO, mInfo);
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
}
