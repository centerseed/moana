package com.moana.carsharing.station.plug;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.moana.carsharing.R;
import com.moana.carsharing.base.BasePagerActivity;
import com.moana.carsharing.base.ConstantDef;

import java.io.Serializable;

public class PlugReserveActivity extends BasePagerActivity {

    String mAddress;
    public PlugReserveInfo mInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAddress = getIntent().getStringExtra(ConstantDef.ARG_STRING);
        mInfo = new PlugReserveInfo();
    }

    @Override
    protected FragmentPagerAdapter getPagerAdapter(FragmentManager fm) {
        return new SectionsPagerAdapter(fm);
    }

    @Override
    protected String getActivityTitle() {
        return getResources().getString(R.string.title_plug_reserve);
    }

    @Override
    public void onPageSelected(int position) {
        super.onPageSelected(position);
        if (position == 1) {
            PlugReserveConfirmFragment.onPageSelected();
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putString(ConstantDef.ARG_STRING, mAddress);
            switch (position) {
                case 0:
                    return PlugReserveOrderFragment.newInstance(bundle);
                case 1:
                    return PlugReserveConfirmFragment.newInstance(bundle);
            }
            return null;
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

    public class PlugReserveInfo implements Serializable {
        public String name;
        public String address;
        public long startTime;
        public long endTime;
        public String fee;
        public String cost;
    }
}
