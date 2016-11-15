package com.moana.carsharing.map;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.moana.carsharing.R;
import com.moana.carsharing.base.AsyncCallback;
import com.moana.carsharing.base.ConstantDef;
import com.moana.carsharing.base.PositionFragment;
import com.moana.carsharing.station.StationActivity;
import com.moana.carsharing.station.StationCarSyncer;
import com.moana.carsharing.station.StationPlugSyncer;
import com.moana.carsharing.station.StationProvider;
import com.moana.carsharing.utils.IconUtils;
import com.moana.carsharing.utils.PreferenceUtils;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class MapsFragment extends PositionFragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowLongClickListener, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    Marker mMarker;
    BottomSheetBehavior mBottomSheetBehavior;
    FloatingActionButton mFab;
    FloatingActionButton mLocalization;
    FloatingActionButton mTraffic;
    LatLng mCurrPosition;
    LocationManager mLocationManager;

    ArrayList<Marker> mMarkerList;
    Polyline mPolyline;
    Marker mReserveMarker;

    int mFunction = -1;
    boolean isMoveToCurrentPosition = false;
    boolean isEnableTraffic = false;

    public MapsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mLocationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        mFunction = PreferenceUtils.getCurrentFunction(getContext());
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mFab = (FloatingActionButton) view.findViewById(R.id.fabNavigation);
        mFab.setVisibility(View.GONE);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrPosition == null || mMarker == null) return;

                LatLng dest = mMarker.getPosition();
                String url = getDirectionsUrl(mCurrPosition, dest);

                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Call call = mClient.newCall(request);
                call.enqueue(new AsyncCallback(getContext()) {

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String json = response.body().string();

                        MapDirectionBuilder.MapDirectionResult result = new MapDirectionBuilder().build(getContext(), json);

                        final PolylineOptions options = result.getPolyline();
                        final CameraUpdate cu = result.getCameraUpdate();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (mPolyline != null) {
                                    mPolyline.remove();
                                    mPolyline = null;
                                }
                                if (mMap != null) {
                                    mPolyline = mMap.addPolyline(options);
                                    mMap.moveCamera(cu);
                                }
                            }
                        });
                    }
                });
            }
        });

        mTraffic = (FloatingActionButton) view.findViewById(R.id.fab_traffic);
        mTraffic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMap != null) {
                    isEnableTraffic = !isEnableTraffic;
                    mMap.setTrafficEnabled(isEnableTraffic);
                }
            }
        });

        mLocalization = (FloatingActionButton) view.findViewById(R.id.fabLocalize);
        mLocalization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isMoveToCurrentPosition = true;
                requestLocation();
            }
        });

        View bottomSheet = view.findViewById(R.id.bottom_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) mFab.setVisibility(View.GONE);
                else mFab.setVisibility(View.VISIBLE);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                mFab.setVisibility(View.GONE);
            }
        });

        bottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toStationInfoActivity();
            }
        });

        BottomSheetFragment f = new BottomSheetFragment();
        getFragmentManager().beginTransaction().replace(R.id.bottom_sheet, f).commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        PreferenceUtils.setCurrentFunction(getContext(), mFunction);
    }

    @Override
    protected void onLocationGet(Location location) {
        mCurrPosition = new LatLng(location.getLatitude(), location.getLongitude());

        Intent intent = new Intent();
        intent.setAction(ConstantDef.ACTION_GET_LOCATION);
        intent.putExtra(ConstantDef.ARG_LOCATION, location);
        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);

        if (mMap != null && isMoveToCurrentPosition) {
            moveCamera(11, location);
            isMoveToCurrentPosition = false;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mMap != null) {
            StationPlugSyncer.with(getContext()).getPlugInfos();
            StationCarSyncer.with(getContext()).getRentInfos();
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Location lastLocation = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            mCurrPosition = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
        }
    }

    @Override
    public void onNetworkFail(String fail) {

    }

    @Override
    protected Uri getProviderUri() {
        return StationProvider.getProviderUri(getString(R.string.auth_provider_plug), StationProvider.TABLE_STATION);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowLongClickListener(this);
        mMap.setOnInfoWindowClickListener(this);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (mBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_HIDDEN) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
                removeReserveMarker();
            }
        });

        moveToDummyPosition();

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);

        StationPlugSyncer.with(getContext()).getPlugInfos();
        StationCarSyncer.with(getContext()).getRentInfos();
    }

    @Override
    public void addIntentFilter(IntentFilter filter) {
        super.addIntentFilter(filter);
        filter.addAction(ConstantDef.ACTION_START_NAVIGATION);
        filter.addAction(ConstantDef.ACTION_SHOW_RENT_POSITION);
        filter.addAction(ConstantDef.ACTION_SHOW_PLUG_POSITION);
        filter.addAction(ConstantDef.ACTION_MOVE_TO_POSITION);
    }

    @Override
    public void onReceiveBroadcast(String action, Intent intent) {
        super.onReceiveBroadcast(action, intent);

        if (ConstantDef.ACTION_MOVE_TO_POSITION.equals(action) && mMarkerList != null) {

            for (Marker marker : mMarkerList) {
                // get location info and add mark
                String snippet = intent.getStringExtra(ConstantDef.ARG_STRING);
                if (marker.getSnippet().equals(snippet)) {
                    onMarkerClick(marker);
                    moveCamera(10, marker.getPosition());
                    break;
                }
            }
        }

        if (ConstantDef.ACTION_SHOW_PLUG_POSITION.equals(action)) {
            mFunction = ConstantDef.FUNC_PLUG;
        }

        if (ConstantDef.ACTION_SHOW_RENT_POSITION.equals(action)) {
            mFunction = ConstantDef.FUNC_RENT;
        }

        if (ConstantDef.ACTION_SHOW_PLUG_POSITION.equals(action) || ConstantDef.ACTION_SHOW_RENT_POSITION.equals(action)) {
            getLoaderManager().restartLoader(0, null, this);
            removeReserveMarker();
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
    }

    private void moveToDummyPosition() {
        Location location = new Location("");
        location.setLatitude(23.6000634);
        location.setLongitude(120.982024);
        moveCamera(7.62f, location);
    }

    private void moveCamera(float zoom, Location location) {
        CameraPosition cameraPosition =
                new CameraPosition.Builder()
                        .target(new LatLng(location.getLatitude(), location.getLongitude()))
                        .zoom(zoom)
                        .build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private void moveCamera(float zoom, LatLng latLng) {
        CameraPosition cameraPosition =
                new CameraPosition.Builder()
                        .target(latLng)
                        .zoom(zoom)
                        .build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cl = (CursorLoader) super.onCreateLoader(id, args);
        cl.setSelection(StationProvider.FIELD_IS_RENT + "=?");
        if (mFunction == ConstantDef.FUNC_PLUG) {
            cl.setSelectionArgs(new String[]{"0"});
        } else if (mFunction == ConstantDef.FUNC_RENT) {
            cl.setSelectionArgs(new String[]{"1"});
        } else {
            cl.setSelectionArgs(new String[]{"1"});
        }
        return cl;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst() && mMap != null) {
            mMap.clear();
            if (mMarkerList != null)
                mMarkerList.clear();
            else
                mMarkerList = new ArrayList<>();
            while (!data.isAfterLast()) {
                LatLng latLng = new LatLng(data.getFloat(data.getColumnIndex(StationProvider.FIELD_LAT)),
                        data.getFloat(data.getColumnIndex(StationProvider.FIELD_LNG)));

                String name = data.getString(data.getColumnIndex(StationProvider.FIELD_STATION_NAME));
                String address = data.getString(data.getColumnIndex(StationProvider.FIELD_STATION_ADDRESS));

                Drawable drawable = getResources().getDrawable(R.mipmap.ic_person_pin_circle_white_36dp);
                drawable.setColorFilter(Color.BLUE, PorterDuff.Mode.MULTIPLY);
                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(name).snippet(address)
                        .icon(IconUtils.getMarkerIconFromDrawable(drawable)));
                mMarkerList.add(marker);

                data.moveToNext();
            }

            if (mReserveMarker != null) {
                mReserveMarker = addReserveMarker(mReserveMarker);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public void onInfoWindowLongClick(Marker marker) {
    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        String sensor = "sensor=false";
        String parameters = str_origin + "&" + str_dest + "&" + sensor;
        String output = "json";
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;

        return url;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        mMarker = marker;
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        if (mReserveMarker == null) {
            mReserveMarker = addReserveMarker(marker);
        } else if (mReserveMarker != null && !marker.getSnippet().equals(mReserveMarker.getSnippet())) {
            // Change location, clear reserveMarker and add new one
            removeReserveMarker();
            mReserveMarker = addReserveMarker(marker);
        } else {
            toStationInfoActivity();
        }

        Intent intent = new Intent();
        intent.setAction(ConstantDef.ACTION_CHOOSE_POSIITON);
        intent.putExtra(ConstantDef.ARG_STRING, marker.getSnippet());
        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
        return true;
    }

    private Marker addReserveMarker(Marker oriMarker) {
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_person_pin_white_48dp);
        drawable.setColorFilter(Color.BLUE, PorterDuff.Mode.MULTIPLY);
        Marker marker = mMap.addMarker(new MarkerOptions()
                .position(oriMarker.getPosition())
                .title(oriMarker.getTitle()).snippet(oriMarker.getSnippet())
                .icon(IconUtils.getMarkerIconFromDrawable(drawable)));
        return marker;
    }

    private void removeReserveMarker() {
        if (mReserveMarker != null) mReserveMarker.remove();
        mReserveMarker = null;
    }

    private void toStationInfoActivity() {
        if (mMarker == null) return;

        Intent intent = new Intent(getActivity(), StationActivity.class);
        intent.putExtra(ConstantDef.ARG_STRING, mMarker.getSnippet());
        intent.putExtra(ConstantDef.ARG_INT, mFunction);
        getActivity().startActivity(intent);
    }
}
