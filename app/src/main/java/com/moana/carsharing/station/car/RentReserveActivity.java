package com.moana.carsharing.station.car;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;

import com.moana.carsharing.R;
import com.moana.carsharing.base.BasePagerActivity;

import java.io.Serializable;

public class RentReserveActivity extends BasePagerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected FragmentPagerAdapter getPagerAdapter(FragmentManager fm) {
        return null;
    }

    @Override
    protected String getActivityTitle() {
        return getResources().getString(R.string.title_rent_reserve);
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
