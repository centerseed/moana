package com.moana.carsharing.map;


import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.moana.carsharing.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapDirectionHelper {
    public static PolylineOptions drawDirection(Context context, String json) {
        if (json == null) return null;

        float dpi= context.getResources().getDisplayMetrics().density;

        PolylineOptions polylineOptions = new PolylineOptions().width(5 * dpi).color(context.getResources().getColor(R.color.colorDirectionPath)).geodesic(true);

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray array = jsonObject.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps");
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                LatLng start = new LatLng(object.getJSONObject("start_location").getDouble("lat"), object.getJSONObject("start_location").getDouble("lng"));
                LatLng end = new LatLng(object.getJSONObject("end_location").getDouble("lat"), object.getJSONObject("end_location").getDouble("lng"));
                polylineOptions.add(start, end);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return polylineOptions;
    }

}
