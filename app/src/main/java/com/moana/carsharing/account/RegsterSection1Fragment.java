package com.moana.carsharing.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moana.carsharing.R;
import com.moana.carsharing.base.BaseSettingFragment;

public class RegsterSection1Fragment extends BaseSettingFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register_section1, container, false);
    }
}
