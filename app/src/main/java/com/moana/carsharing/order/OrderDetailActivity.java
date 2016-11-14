package com.moana.carsharing.order;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.moana.carsharing.R;
import com.moana.carsharing.base.BaseSettingFragment;
import com.moana.carsharing.base.ConstantDef;
import com.moana.carsharing.station.car.CarReserveDetailFragment;
import com.moana.carsharing.station.plug.PlugReserveDetailFragment;

public class OrderDetailActivity extends AppCompatActivity implements BaseSettingFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String serial = getIntent().getStringExtra(ConstantDef.ARG_ORDER_TEMP_SERIAL);
        boolean isCar = getIntent().getBooleanExtra(ConstantDef.ARG_BOOLEAN, true);

        Bundle bundle = new Bundle();
        bundle.putString(ConstantDef.ARG_ORDER_TEMP_SERIAL, serial);

        Fragment f;
        if (isCar)
            f = new CarReserveDetailFragment();
        else
            f = new PlugReserveDetailFragment();
        f.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, f).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void toBackFragment() {
        // blank
    }

    @Override
    public void toNextFragment() {
// blank
    }
}
