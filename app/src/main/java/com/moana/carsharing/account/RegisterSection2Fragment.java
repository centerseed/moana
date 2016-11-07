package com.moana.carsharing.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.moana.carsharing.R;
import com.moana.carsharing.base.BaseSettingFragment;
import com.weiwangcn.betterspinner.library.BetterSpinner;

public class RegisterSection2Fragment extends BaseSettingFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register_section2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        BetterSpinner textView = (BetterSpinner) view.findViewById(R.id.spinner_post_code);
        textView.setAdapter(adapter);
    }

    private static final String[] COUNTRIES = new String[]{
            "Belgium", "France", "Italy", "Germany", "Spain"
    };
}
