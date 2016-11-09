package com.moana.carsharing.dummy;

import android.content.ContentValues;

import com.moana.carsharing.base.ConstantDef;
import com.moana.carsharing.station.StationProvider;

import java.util.ArrayList;

public class DummyPlugSource {
    public static ArrayList<ContentValues> getDummyPlug() {
        ArrayList<ContentValues> arrayList = new ArrayList<>();

        ContentValues values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "p1".hashCode());
        values.put(StationProvider.FIELD_PLUG_BELONG_STATION, "");
        values.put(StationProvider.FIELD_PLUG_STATUS, StationProvider.PLUG_STATUS_AVALIABLE);
        arrayList.add(values);

        values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "p2".hashCode());
        values.put(StationProvider.FIELD_PLUG_BELONG_STATION, "");
        values.put(StationProvider.FIELD_PLUG_STATUS, StationProvider.PLUG_STATUS_AVALIABLE);
        arrayList.add(values);

        values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "p3".hashCode());
        values.put(StationProvider.FIELD_PLUG_BELONG_STATION, "");
        values.put(StationProvider.FIELD_PLUG_STATUS, StationProvider.PLUG_STATUS_AVALIABLE);
        arrayList.add(values);

        values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "p4".hashCode());
        values.put(StationProvider.FIELD_PLUG_BELONG_STATION, "");
        values.put(StationProvider.FIELD_PLUG_STATUS, StationProvider.PLUG_STATUS_IN_USAGE);
        arrayList.add(values);

        values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "p5".hashCode());
        values.put(StationProvider.FIELD_PLUG_BELONG_STATION, "");
        values.put(StationProvider.FIELD_PLUG_STATUS, StationProvider.PLUG_STATUS_ERROR);
        arrayList.add(values);
        return arrayList;
    }
}
