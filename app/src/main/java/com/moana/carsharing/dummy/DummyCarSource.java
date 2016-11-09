package com.moana.carsharing.dummy;

import android.content.ContentValues;

import com.moana.carsharing.station.StationProvider;

import java.util.ArrayList;

public class DummyCarSource {
    public static ArrayList<ContentValues> getDummyCar() {
        ArrayList<ContentValues> arrayList = new ArrayList<>();

        ContentValues values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "car1".hashCode());
        values.put(StationProvider.FIELD_CAR_BELONG_STATION, "");
        values.put(StationProvider.FIELD_CAR_CHARGE, 100);
        arrayList.add(values);

        values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "car2".hashCode());
        values.put(StationProvider.FIELD_CAR_BELONG_STATION, "");
        values.put(StationProvider.FIELD_CAR_CHARGE, 80);
        arrayList.add(values);

        values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "car3".hashCode());
        values.put(StationProvider.FIELD_CAR_BELONG_STATION, "");
        values.put(StationProvider.FIELD_CAR_CHARGE, 20);
        arrayList.add(values);

        return arrayList;
    }
}
