package com.moana.carsharing.station;

import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moana.carsharing.R;
import com.moana.carsharing.base.BroadcastActivity;
import com.moana.carsharing.base.ConstantDef;
import com.moana.carsharing.base.RecyclerFragment;
import com.moana.carsharing.station.car.CarListFragment;
import com.moana.carsharing.station.plug.PlugListFragment;
import com.squareup.picasso.Picasso;

public class StationActivity extends BroadcastActivity implements AppBarLayout.OnOffsetChangedListener {

    int mFunction;
    Toolbar mToolbar;
    String mSnippet;
    ImageView mScrollImage;
    TextView mAddress;
    CollapsingToolbarLayout mCollapsingBar;
    AppBarLayout mAppBar;

    FloatingActionButton mFab;
    LinearLayout mLabel;
    RecyclerFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAppBar = (AppBarLayout) findViewById(R.id.app_bar);
        mCollapsingBar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        mCollapsingBar.setExpandedTitleTextAppearance(R.style.ExpandAppBarText);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        mSnippet = getIntent().getStringExtra(ConstantDef.ARG_STRING);
        mFunction = getIntent().getIntExtra(ConstantDef.ARG_INT, ConstantDef.FUNC_RENT);
        mScrollImage = (ImageView) findViewById(R.id.scrollImg);

        mLabel = (LinearLayout) findViewById(R.id.label);
        mAddress = (TextView) findViewById(R.id.address);
        mFab = (FloatingActionButton) findViewById(R.id.fab);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(400);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent();
                        intent.setAction(ConstantDef.ACTION_START_NAVIGATION);
                        LocalBroadcastManager.getInstance(StationActivity.this).sendBroadcast(intent);
                    }
                }).start();

                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAppBar.addOnOffsetChangedListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAppBar.removeOnOffsetChangedListener(this);
    }

    @Override
    protected void addIntentFilter(IntentFilter filter) {

    }

    @Override
    protected void onReceiveBroadcast(String action, Intent intent) {

    }

    @Override
    protected void onNetworkFail(String fail) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected Uri getProviderUri() {
        return StationProvider.getProviderUri(getString(R.string.auth_provider_plug), StationProvider.TABLE_STATION);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cl = (CursorLoader) super.onCreateLoader(id, args);
        cl.setSelection(StationProvider.FIELD_STATION_ADDRESS + "=?");
        cl.setSelectionArgs(new String[]{mSnippet});
        return cl;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst()) {
            String name = data.getString(data.getColumnIndex(StationProvider.FIELD_STATION_NAME));
            String address = data.getString(data.getColumnIndex(StationProvider.FIELD_STATION_ADDRESS));
            String imgUrl = data.getString(data.getColumnIndex(StationProvider.FIELD_STATION_PHOTO));

            if (imgUrl != null && imgUrl.length() > 0)
                Picasso.with(this).load(imgUrl).into(mScrollImage);
            mCollapsingBar.setTitle(name);
            mAddress.setText(address);

            handler.sendEmptyMessage(2);
        }
    }

    private Handler handler = new Handler()  { // handler for commiting fragment after data is loaded
        @Override
        public void handleMessage(Message msg) {
            if (isFinishing()) return;
            if(msg.what == 2) {
                if (mFunction == ConstantDef.FUNC_RENT)
                    mFragment = CarListFragment.newInstance("", mCollapsingBar.getTitle().toString(), mAddress.getText().toString());
                else
                    mFragment = PlugListFragment.newInstance("", mCollapsingBar.getTitle().toString(), mAddress.getText().toString());

                getSupportFragmentManager().beginTransaction().replace(R.id.container, mFragment).commit();
            }
        }
    };

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (mFragment != null && mFragment.isResumed())
            if (mCollapsingBar.getHeight() + verticalOffset < 2 * ViewCompat.getMinimumHeight(mCollapsingBar)) {
                mFragment.enableRefresh(false);
            } else {
                mFragment.enableRefresh(true);
            }

        float percentage = (1 - (float) Math.abs(verticalOffset) / appBarLayout.getTotalScrollRange());
        mLabel.setAlpha(percentage);
        mFab.setAlpha(percentage);
    }
}
