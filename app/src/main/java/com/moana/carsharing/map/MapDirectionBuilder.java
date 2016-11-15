package com.moana.carsharing.map;


import android.content.Context;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.PolylineOptions;
import com.moana.carsharing.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MapDirectionBuilder {
    static PolylineOptions mOptions;
    static LatLngBounds mBounds;
    static float mDpi;

    MapDirectionResult mInstance;

    public MapDirectionResult build(Context context, String json) {
        mInstance = new MapDirectionResult(context, json);
        return mInstance;
    }


    public class MapDirectionResult {

        public MapDirectionResult(Context context, String json) {

            mDpi = context.getResources().getDisplayMetrics().density;
            mOptions = new PolylineOptions().width(5 * mDpi).color(context.getResources().getColor(R.color.colorDirectionPath)).geodesic(true);

            LatLngBounds.Builder builder = new LatLngBounds.Builder();

            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONObject overviewPolylines = jsonObject.getJSONArray("routes").getJSONObject(0).getJSONObject("overview_polyline");
                String encodedString = overviewPolylines.getString("points");

                List<LatLng> list = decodePoly(encodedString);
                mOptions.addAll(list);
                for (LatLng latLng : list) {
                    builder.include(latLng);
                }
                /* JSONArray array = jsonObject.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    LatLng start = new LatLng(object.getJSONObject("start_location").getDouble("lat"), object.getJSONObject("start_location").getDouble("lng"));
                    LatLng end = new LatLng(object.getJSONObject("end_location").getDouble("lat"), object.getJSONObject("end_location").getDouble("lng"));
                    mOptions.add(start, end);
                    builder.include(start);
                    builder.include(end);
                }
                */
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mBounds = builder.build();
        }

        public PolylineOptions getPolyline() {
            return mOptions;
        }

        public CameraUpdate getCameraUpdate() {
            return CameraUpdateFactory.newLatLngBounds(mBounds, (int) (120 * mDpi));
        }

        private List<LatLng> decodePoly(String encoded) {

            List<LatLng> poly = new ArrayList<LatLng>();
            int index = 0, len = encoded.length();
            int lat = 0, lng = 0;

            while (index < len) {
                int b, shift = 0, result = 0;
                do {
                    b = encoded.charAt(index++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
                } while (b >= 0x20);
                int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
                lat += dlat;

                shift = 0;
                result = 0;
                do {
                    b = encoded.charAt(index++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
                } while (b >= 0x20);
                int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
                lng += dlng;

                LatLng p = new LatLng( (((double) lat / 1E5)),
                        (((double) lng / 1E5) ));
                poly.add(p);
            }

            return poly;
        }
    }
}
