package com.moana.carsharing.station.car;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.moana.carsharing.R;
import com.moana.carsharing.base.BaseSettingPagerActivity;
import com.moana.carsharing.base.ConstantDef;
import com.moana.carsharing.station.StationProvider;

public class CarReserveActivitySetting extends BaseSettingPagerActivity {
    String mSiteName;
    String mSiteAddress;
    String mOrderTmpSerial;
    int mCharge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSiteName = getIntent().getStringExtra(ConstantDef.ARG_SITE_NAME);
        mSiteAddress = getIntent().getStringExtra(ConstantDef.ARG_SITE_ADDRESS);
        mCharge = getIntent().getIntExtra(ConstantDef.ARG_CHARGE, 0);

        mOrderTmpSerial = System.currentTimeMillis() + "";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Uri uri = StationProvider.getProviderUri(getString(R.string.auth_provider_plug), StationProvider.TABLE_CAR_ORDER);
        getContentResolver().delete(uri, StationProvider.FIELD_CAR_ORDER_SERIAL + "=?", new String[]{mOrderTmpSerial});
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
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putString(ConstantDef.ARG_ORDER_TEMP_SERIAL, mOrderTmpSerial);
            bundle.putString(ConstantDef.ARG_SITE_NAME, mSiteName);
            bundle.putString(ConstantDef.ARG_SITE_ADDRESS, mSiteAddress);
            bundle.putInt(ConstantDef.ARG_CHARGE, mCharge);
            switch (position) {
                case 0:
                    return CarReserveOrderFragment.newInstance(bundle);
                case 1:
                    return CarReserveConfirmFragment.newInstance(bundle);
            }
            return CarReserveDetailFragment.newInstance(bundle);
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
