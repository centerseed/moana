package com.moana.carsharing;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import com.moana.carsharing.account.LoginActivity;
import com.moana.carsharing.base.ConstantDef;
import com.moana.carsharing.base.ContentActivity;
import com.moana.carsharing.map.MapsFragment;
import com.moana.carsharing.station.StationProvider;

public class MainActivity extends ContentActivity
        implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener, SiteSearchResultAdapter.ResultAdapterListener {

    RadioGroup mGroup;
    int mFunction = ConstantDef.FUNC_RENT;
    String mFunctionStr;
    SearchView mSearchView;
    RecyclerView mSearchResultList;
    SiteSearchResultAdapter mAdapter;

    String mSearchText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFunctionStr = getString(R.string.title_parking);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name) + " - " + mFunctionStr);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        toggle.setDrawerIndicatorEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        onNavigationItemSelected(navigationView.getMenu().getItem(0));
        navigationView.getMenu().getItem(0).setChecked(true);

        mGroup = (RadioGroup) findViewById(R.id.function_group);
        mGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                mFunction = i;
                Intent intent = new Intent();

                if (i == ConstantDef.FUNC_RENT) {
                    mFunctionStr = getString(R.string.title_parking);
                    if (mSearchView != null)
                        mSearchView.setQueryHint(getString(R.string.title_search_parking));
                    intent.setAction(ConstantDef.ACTION_SHOW_RENT_POSITION);
                } else {
                    mFunctionStr = getString(R.string.title_plug);
                    if (mSearchView != null)
                        mSearchView.setQueryHint(getString(R.string.title_search_plug));
                    intent.setAction(ConstantDef.ACTION_SHOW_PLUG_POSITION);
                }
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
                getSupportActionBar().setTitle(getString(R.string.app_name) + " - " + mFunctionStr);
            }
        });

        mAdapter = new SiteSearchResultAdapter(this, null);
        mAdapter.setSiteSearchResultAdapterListener(this);

        mSearchResultList = (RecyclerView) findViewById(R.id.search_result);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mSearchResultList.setLayoutManager(manager);
        mSearchResultList.setAdapter(mAdapter);

        mSearchResultList.setVisibility(View.GONE);
    }

    @Override
    protected Uri getProviderUri() {
        return StationProvider.getProviderUri(getString(R.string.auth_provider_plug), StationProvider.TABLE_STATION);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            mSearchView = (SearchView) searchItem.getActionView();
            mSearchView.setQueryHint(getString(R.string.title_search_parking));
        }
        if (mSearchView != null) {
            mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            mSearchView.setOnQueryTextListener(this);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment f = null;

        if (id == R.id.nav_map) {
            f = new MapsFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, f).commit();
        }

        if (id == R.id.nav_logout) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cl = (CursorLoader) super.onCreateLoader(id, args);
        String funArg;
        if (mFunction == ConstantDef.FUNC_PLUG) funArg = "0";
        else funArg = "1";
        cl.setSelection(StationProvider.FIELD_IS_RENT + "=" + funArg + " AND (" + StationProvider.FIELD_STATION_ADDRESS + " like '%" + mSearchText + "%' or " + StationProvider.FIELD_STATION_NAME + " like '%" + mSearchText + "%')");
        return cl;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst()) {
            mAdapter.swapCursor(data);
        } else {
            mAdapter.swapCursor(null);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText.length() > 0) {
            mSearchText = newText;
            getSupportLoaderManager().restartLoader(0, null, this);
            mSearchResultList.setVisibility(View.VISIBLE);
        } else {
            mSearchResultList.setVisibility(View.GONE);
        }
        return false;
    }

    @Override
    public void onResultClick(String snippet) {
        Intent intent = new Intent();
        intent.setAction(ConstantDef.ACTION_MOVE_TO_POSITION);
        intent.putExtra(ConstantDef.ARG_STRING, snippet);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

        mSearchText = "";
        mSearchView.onActionViewCollapsed();
        mSearchResultList.setVisibility(View.GONE);
    }
}
