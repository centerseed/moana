package com.moana.plugsearch.plug;

import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.model.LatLng;
import com.moana.plugsearch.R;
import com.moana.plugsearch.base.BroadcastActivity;
import com.moana.plugsearch.base.ConstantDef;
import com.squareup.picasso.Picasso;

public class PlugInfoActivity extends BroadcastActivity {

    String mSnippet;
    ImageView mScrollImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plug_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        mSnippet = getIntent().getStringExtra(ConstantDef.ARG_STRING);
        mScrollImage = (ImageView) findViewById(R.id.scrollImg);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
        return PlugProvider.getProviderUri(getString(R.string.auth_provider_plug), PlugProvider.TABLE_PLUG);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cl = (CursorLoader) super.onCreateLoader(id, args);
        cl.setSelection(PlugProvider.FIELD_PLUG_ADDRESS + "=?");
        cl.setSelectionArgs(new String[]{mSnippet});
        return cl;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst()) {
            String name = data.getString(data.getColumnIndex(PlugProvider.FIELD_PLUG_NAME));
            String address = data.getString(data.getColumnIndex(PlugProvider.FIELD_PLUG_ADDRESS));
            String imgUrl = data.getString(data.getColumnIndex(PlugProvider.FIELD_PLUG_PHOTO));

            Picasso.with(this).load(imgUrl).into(mScrollImage);
            getSupportActionBar().setTitle(name);
            getSupportActionBar().setSubtitle(address);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
