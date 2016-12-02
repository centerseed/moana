package com.moana.carsharing.station.plug;

import android.content.ContentValues;
import android.database.Cursor;

import com.moana.carsharing.station.StationProvider;

import java.io.Serializable;

public class PlugReserveInfo implements Serializable {
    ContentValues mContentValues;

    int id = 0;
    public String serial;
    public String site;
    public long time;
    public long orderTime;
    public String status;

    public PlugReserveInfo() {
    }

    public PlugReserveInfo(Cursor cursor) {
        serial = cursor.getString(cursor.getColumnIndex(StationProvider.FIELD_PLUG_ORDER_SERIAL));
        site = cursor.getString(cursor.getColumnIndex(StationProvider.FIELD_PLUG_ORDER_SITE));
        time = cursor.getLong(cursor.getColumnIndex(StationProvider.FIELD_PLUG_START_TIME));
        orderTime = cursor.getLong(cursor.getColumnIndex(StationProvider.FIELD_PLUG_ORDER_TIME));
        status = cursor.getString(cursor.getColumnIndex(StationProvider.FIELD_PLUG_ORDER_STATUS));
    }

    public ContentValues getContentValues() {
        mContentValues = new ContentValues();
        if (id == 0)
            mContentValues.put(StationProvider.FIELD_ID, serial.hashCode());
        else
            mContentValues.put(StationProvider.FIELD_ID, id);

        mContentValues.put(StationProvider.FIELD_PLUG_ORDER_SERIAL, serial);
        mContentValues.put(StationProvider.FIELD_PLUG_ORDER_SITE, site);
        mContentValues.put(StationProvider.FIELD_PLUG_START_TIME, time);
        mContentValues.put(StationProvider.FIELD_PLUG_ORDER_TIME, orderTime);
        mContentValues.put(StationProvider.FIELD_PLUG_ORDER_STATUS, status);

        return mContentValues;
    }
}