package com.moana.plugsearch.map;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.moana.plugsearch.R;
import com.moana.plugsearch.base.AsyncCallback;
import com.moana.plugsearch.base.ConstantDef;
import com.moana.plugsearch.base.PositionFragment;
import com.moana.plugsearch.plug.PlugInfoActivity;
import com.moana.plugsearch.plug.PlugProvider;
import com.moana.plugsearch.sync.PlugSyncer;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MapsFragment extends PositionFragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowLongClickListener, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    LatLng mCurrPosition;
    LocationManager mLocationManager;

    public MapsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mLocationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    protected void onPositionGet(Location location) {
        mCurrPosition = new LatLng(location.getLatitude(), location.getLongitude());
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mMap != null) {
            PlugSyncer.with(getContext()).getPlugInfos();
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
        return PlugProvider.getProviderUri(getString(R.string.auth_provider_plug), PlugProvider.TABLE_PLUG);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnInfoWindowLongClickListener(this);
        mMap.setOnInfoWindowClickListener(this);

        moveToDummyPosition();

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);

        PlugSyncer.with(getContext()).getPlugInfos();
    }

    private void moveToDummyPosition() {
        Location location = new Location("");
        location.setLatitude(24.122771);
        location.setLongitude(120.651540);
        moveCamera(12, location);
    }

    private void moveCamera(float zoom, Location location) {
        CameraPosition cameraPosition =
                new CameraPosition.Builder()
                        .target(new LatLng(location.getLatitude(), location.getLongitude()))
                        .zoom(zoom)
                        .build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst() && mMap != null) {
            mMap.clear();

            while (!data.isAfterLast()) {
                LatLng latLng = new LatLng(data.getFloat(data.getColumnIndex(PlugProvider.FIELD_LAT)),
                        data.getFloat(data.getColumnIndex(PlugProvider.FIELD_LNG)));

                String name = data.getString(data.getColumnIndex(PlugProvider.FIELD_PLUG_NAME));
                String address = data.getString(data.getColumnIndex(PlugProvider.FIELD_PLUG_ADDRESS));
                mMap.addMarker(new MarkerOptions().position(latLng).title(name).snippet(address));

                data.moveToNext();
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent intent = new Intent(getActivity(), PlugInfoActivity.class);
        intent.putExtra(ConstantDef.ARG_STRING, marker.getSnippet());

        getActivity().startActivity(intent);
    }

    @Override
    public void onInfoWindowLongClick(Marker marker) {
        if (mCurrPosition == null) return;

        LatLng dest = marker.getPosition();
        String url = getDirectionsUrl(mCurrPosition, dest);

        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new AsyncCallback(getContext()) {

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();

                final PolylineOptions options = MapDirectionHelper.drawDirection(getActivity(), json);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mMap != null) {
                            mMap.addPolyline(options);
                        }
                    }
                });
            }
        });
    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;

        return url;
    }
}
