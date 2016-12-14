package com.moana.carsharing.guideline;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.moana.carsharing.R;
import com.moana.carsharing.account.RegisterSection1Fragment;
import com.moana.carsharing.account.RegisterSection2Fragment;
import com.moana.carsharing.base.BasePagerActivity;
import com.moana.carsharing.base.BaseSettingPagerActivity;

public class RentCarGuideActivity extends BasePagerActivity {


    @Override
    protected FragmentPagerAdapter getPagerAdapter(FragmentManager fm) {
        return new SectionsPagerAdapter(fm);
    }

    @Override
    protected String getActivityTitle() {
        return "借還車說明";
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return GuideContentFragment.newInstance(R.mipmap.icon_member_reservation, R.string.guide_reserve_car);
                case 1:
                    return GuideContentFragment.newInstance(R.mipmap.icon_member_use, R.string.guide_rent_car);
                default:
                    return GuideContentFragment.newInstance(R.mipmap.icon_member_return, R.string.guide_return_car);
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return ++position + "";
        }
    }
}
