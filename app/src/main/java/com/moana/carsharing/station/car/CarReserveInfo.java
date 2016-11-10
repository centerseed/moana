package com.moana.carsharing.station.car;

import android.content.ContentValues;
import android.database.Cursor;

import com.moana.carsharing.station.StationProvider;

import java.io.Serializable;

public class CarReserveInfo implements Serializable {
    ContentValues mContentValues;
    public String carType;
    public String orderSerial;
    public long orderTime;
    public long startTime;
    public long endTime;
    public String rentSite;
    public String returnSite;
    public int usage;
    public String fee;
    public String payment;

    public CarReserveInfo() {
    }

    public CarReserveInfo(Cursor cursor) {
        carType = cursor.getString(cursor.getColumnIndex(StationProvider.FIELD_CAR_ORDER_CAR_TYPE));
        orderSerial = cursor.getString(cursor.getColumnIndex(StationProvider.FIELD_CAR_ORDER_SERIAL));
        orderTime = cursor.getLong(cursor.getColumnIndex(StationProvider.FIELD_CAR_ORDER_TIME));
        startTime = cursor.getLong(cursor.getColumnIndex(StationProvider.FIELD_CAR_ORDER_START_TIME));
        endTime = cursor.getLong(cursor.getColumnIndex(StationProvider.FIELD_CAR_ORDER_END_TIME));
        rentSite = cursor.getString(cursor.getColumnIndex(StationProvider.FIELD_CAR_ORDER_RENT_SITE));
        returnSite = cursor.getString(cursor.getColumnIndex(StationProvider.FIELD_CAR_ORDER_RETURN_SITE));
        usage = cursor.getInt(cursor.getColumnIndex(StationProvider.FIELD_CAR_ORDER_USAGE));
        fee = cursor.getString(cursor.getColumnIndex(StationProvider.FIELD_CAR_ORDER_FEE));
        payment = cursor.getString(cursor.getColumnIndex(StationProvider.FIELD_CAR_ORDER_PAYMENT));
    }

    public ContentValues getContentValues() {
        mContentValues = new ContentValues();
        mContentValues.put(StationProvider.FIELD_CAR_ORDER_SERIAL, orderSerial);
        mContentValues.put(StationProvider.FIELD_CAR_ORDER_CAR_TYPE, carType);
        mContentValues.put(StationProvider.FIELD_CAR_ORDER_TIME, orderTime);
        mContentValues.put(StationProvider.FIELD_CAR_ORDER_START_TIME, startTime);
        mContentValues.put(StationProvider.FIELD_CAR_ORDER_END_TIME, endTime);
        mContentValues.put(StationProvider.FIELD_CAR_ORDER_RENT_SITE, rentSite);
        mContentValues.put(StationProvider.FIELD_CAR_ORDER_RETURN_SITE, returnSite);
        mContentValues.put(StationProvider.FIELD_CAR_ORDER_USAGE, usage);
        mContentValues.put(StationProvider.FIELD_CAR_ORDER_FEE, fee);
        mContentValues.put(StationProvider.FIELD_CAR_ORDER_PAYMENT, payment);

        return mContentValues;
    }
}