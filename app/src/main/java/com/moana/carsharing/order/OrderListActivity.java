package com.moana.carsharing.order;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.moana.carsharing.R;
import com.moana.carsharing.base.ConstantDef;
import com.moana.carsharing.station.StationProvider;
import com.moana.carsharing.utils.TimeUtils;

public class OrderListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, OrderAdapter.OrderAdapterListener {

    final int LOADER_CAR_ORDER = 0;
    final int LOADER_PLUG_ORDER = 1;

    RecyclerView mRecycler;
    Uri mCarUri;
    Uri mPlugUri;
    OrderAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.title_query_order));

        mCarUri = StationProvider.getProviderUri(getString(R.string.auth_provider_plug), StationProvider.TABLE_CAR_ORDER);
        mPlugUri = StationProvider.getProviderUri(getString(R.string.auth_provider_plug), StationProvider.TABLE_PLUG_ORDER);

        mRecycler = (RecyclerView) findViewById(R.id.recycler);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(manager);

        mAdapter = new OrderAdapter(this);
        mAdapter.setOnClickListener(this);
        mRecycler.setAdapter(mAdapter);

        getSupportLoaderManager().initLoader(LOADER_CAR_ORDER, null, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        OrderSyncer.with(this).getOrderList();
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
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cl = new CursorLoader(this);
        if (id == LOADER_CAR_ORDER)
            cl.setUri(mCarUri);
        if (id == LOADER_PLUG_ORDER)
            cl.setUri(mPlugUri);
        return cl;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst()) {
            if (loader.getId() == LOADER_CAR_ORDER) {
                mAdapter.reset();

                while (!data.isAfterLast()) {
                    String serial = data.getString(data.getColumnIndex(StationProvider.FIELD_CAR_ORDER_SERIAL));
                    String time = TimeUtils.getYYYYMMDDStr(this, data.getLong(data.getColumnIndex(StationProvider.FIELD_CAR_ORDER_TIME)));
                    String status = data.getString(data.getColumnIndex(StationProvider.FIELD_CAR_ORDER_STATUS));
                    mAdapter.addCarItem(serial, time, status);

                    data.moveToNext();
                }
                getSupportLoaderManager().initLoader(LOADER_PLUG_ORDER, null, this);
            } else {
                while (!data.isAfterLast()) {
                    String serial = data.getString(data.getColumnIndex(StationProvider.FIELD_PLUG_ORDER_SERIAL));
                    String time = TimeUtils.getYYYYMMDDStr(this, data.getLong(data.getColumnIndex(StationProvider.FIELD_PLUG_ORDER_TIME)));
                    String status = data.getString(data.getColumnIndex(StationProvider.FIELD_PLUG_ORDER_STATUS));
                    mAdapter.addPlugItem(serial, time, status);

                    data.moveToNext();
                }
                mAdapter.notifyDataChanged();
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onCarOrderClick(String serial) {
        toOrderDetail(serial, true);
    }

    @Override
    public void onPlugOrderClick(String serial) {
        toOrderDetail(serial, false);
    }

    private void toOrderDetail(String serial, boolean isCar) {
        Intent intent = new Intent(this, OrderDetailActivity.class);
        intent.putExtra(ConstantDef.ARG_ORDER_TEMP_SERIAL, serial);
        intent.putExtra(ConstantDef.ARG_BOOLEAN, isCar);
        startActivity(intent);
    }
}
