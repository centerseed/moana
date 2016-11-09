package com.moana.carsharing.station.plug;

import android.database.Cursor;

import com.moana.carsharing.station.StationProvider;

import java.io.Serializable;

public class PlugReserveInfo implements Serializable {
    public String name;
    public String address;
    public long startTime;
    public long endTime;
    public String fee;
    public String cost;

    public PlugReserveInfo(Cursor cursor) {
        name = cursor.getString(cursor.getColumnIndex(StationProvider.FIELD_ID));
        address = cursor.getString(cursor.getColumnIndex(StationProvider.FIELD_PLUG_BELONG_STATION));
    }
}