package com.moana.carsharing.station.plug;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.moana.carsharing.R;
import com.moana.carsharing.base.BaseSettingPagerActivity;
import com.moana.carsharing.base.ConstantDef;
import com.moana.carsharing.station.StationProvider;

public class PlugReserveActivity extends BaseSettingPagerActivity {

    String mName;
    String mAddress;
    String mOrderTmpSerial;
    int mPlugID;
    public PlugReserveInfo mInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mName = getIntent().getStringExtra(ConstantDef.ARG_SITE_NAME);
        mAddress = getIntent().getStringExtra(ConstantDef.ARG_SITE_ADDRESS);
        mPlugID = getIntent().getIntExtra(ConstantDef.ARG_INT, 0);

        mOrderTmpSerial = System.currentTimeMillis() + "";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Uri uri = StationProvider.getProviderUri(getString(R.string.auth_provider_plug), StationProvider.TABLE_PLUG_ORDER);
        getContentResolver().delete(uri, StationProvider.FIELD_PLUG_ORDER_SERIAL + "=?", new String[]{mOrderTmpSerial});
    }

    @Override
    protected FragmentPagerAdapter getPagerAdapter(FragmentManager fm) {
        return new SectionsPagerAdapter(fm);
    }

    @Override
    protected String getActivityTitle() {
        return getResources().getString(R.string.title_plug_reserve);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putString(ConstantDef.ARG_ORDER_TEMP_SERIAL, mOrderTmpSerial);
            bundle.putInt(ConstantDef.ARG_INT, mPlugID);
            bundle.putString(ConstantDef.ARG_SITE_NAME, mName);
            bundle.putString(ConstantDef.ARG_SITE_ADDRESS, mAddress);
            switch (position) {
                case 0:
                    return PlugReserveOrderFragment.newInstance(bundle);
                case 1:
                    return PlugReserveDetailFragment.newInstance(bundle);
            }
            return null;
        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }
}
