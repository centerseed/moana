package com.moana.carsharing.station.car;

import android.database.Cursor;

import com.moana.carsharing.station.StationProvider;

import java.io.Serializable;

public class CarReserveInfo implements Serializable {
    public String name;
    public String address;
    public long startTime;
    public long endTime;
    public int charge;
    public String cost;

    public CarReserveInfo(Cursor cursor) {
        name = cursor.getString(cursor.getColumnIndex(StationProvider.FIELD_ID));
        address = cursor.getString(cursor.getColumnIndex(StationProvider.FIELD_CAR_BELONG_STATION));
        charge = cursor.getInt(cursor.getColumnIndex(StationProvider.FIELD_CAR_CHARGE));
    }
}