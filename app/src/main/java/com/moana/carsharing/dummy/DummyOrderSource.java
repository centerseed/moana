package com.moana.carsharing.dummy;

import android.content.ContentValues;

import com.moana.carsharing.station.StationProvider;

import java.util.ArrayList;

public class DummyOrderSource {
    public static ArrayList<ContentValues> getDummyCarOrder() {
        ArrayList<ContentValues> arrayList = new ArrayList<>();

        ContentValues values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "order1".hashCode());
        values.put(StationProvider.FIELD_CAR_ORDER_FEE, "999");
        values.put(StationProvider.FIELD_CAR_ORDER_CAR_TYPE, "Kget - e6");
        values.put(StationProvider.FIELD_CAR_ORDER_END_TIME, System.currentTimeMillis());
        values.put(StationProvider.FIELD_CAR_ORDER_RENT_SITE, "高雄左營站");
        values.put(StationProvider.FIELD_CAR_ORDER_PAYMENT, "");
        values.put(StationProvider.FIELD_CAR_ORDER_START_TIME, System.currentTimeMillis());
        values.put(StationProvider.FIELD_CAR_ORDER_USAGE, "20");
        values.put(StationProvider.FIELD_CAR_ORDER_RETURN_SITE, "");
        values.put(StationProvider.FIELD_CAR_ORDER_SERIAL, "Car 1234");
        values.put(StationProvider.FIELD_CAR_ORDER_STATUS, "預約");
        values.put(StationProvider.FIELD_CAR_ORDER_TIME, System.currentTimeMillis() + 1000000);

        arrayList.add(values);

        values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "order2".hashCode());
        values.put(StationProvider.FIELD_CAR_ORDER_FEE, "888");
        values.put(StationProvider.FIELD_CAR_ORDER_CAR_TYPE, "Kget - e6");
        values.put(StationProvider.FIELD_CAR_ORDER_END_TIME, System.currentTimeMillis());
        values.put(StationProvider.FIELD_CAR_ORDER_RENT_SITE, "西子灣站");
        values.put(StationProvider.FIELD_CAR_ORDER_PAYMENT, "");
        values.put(StationProvider.FIELD_CAR_ORDER_START_TIME, System.currentTimeMillis());
        values.put(StationProvider.FIELD_CAR_ORDER_USAGE, "2");
        values.put(StationProvider.FIELD_CAR_ORDER_RETURN_SITE, "");
        values.put(StationProvider.FIELD_CAR_ORDER_SERIAL, "Car 2234");
        values.put(StationProvider.FIELD_CAR_ORDER_STATUS, "處理中");
        values.put(StationProvider.FIELD_CAR_ORDER_TIME, System.currentTimeMillis() + 1500000);
        arrayList.add(values);

        values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "order3".hashCode());
        values.put(StationProvider.FIELD_CAR_ORDER_FEE, "999");
        values.put(StationProvider.FIELD_CAR_ORDER_CAR_TYPE, "Kget - e6");
        values.put(StationProvider.FIELD_CAR_ORDER_END_TIME, System.currentTimeMillis());
        values.put(StationProvider.FIELD_CAR_ORDER_RENT_SITE, "美麗島站");
        values.put(StationProvider.FIELD_CAR_ORDER_PAYMENT, "");
        values.put(StationProvider.FIELD_CAR_ORDER_START_TIME, System.currentTimeMillis());
        values.put(StationProvider.FIELD_CAR_ORDER_USAGE, "10");
        values.put(StationProvider.FIELD_CAR_ORDER_RETURN_SITE, "");
        values.put(StationProvider.FIELD_CAR_ORDER_SERIAL, "Car 3234");
        values.put(StationProvider.FIELD_CAR_ORDER_STATUS, "完成");
        values.put(StationProvider.FIELD_CAR_ORDER_TIME, System.currentTimeMillis() - 2000000);
        arrayList.add(values);

        return arrayList;
    }

    public static ArrayList<ContentValues> getDummyPlugOrder() {
        ArrayList<ContentValues> arrayList = new ArrayList<>();

        ContentValues values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "order1".hashCode());
        values.put(StationProvider.FIELD_PLUG_ORDER_SERIAL, "PLUG 129");
        values.put(StationProvider.FIELD_PLUG_ORDER_SITE, "都會南");
        values.put(StationProvider.FIELD_PLUG_ORDER_STATUS, "預約");
        values.put(StationProvider.FIELD_PLUG_START_TIME, System.currentTimeMillis());
        values.put(StationProvider.FIELD_PLUG_ORDER_TIME, System.currentTimeMillis() + 600000);

        arrayList.add(values);

        values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "order2".hashCode());
        values.put(StationProvider.FIELD_PLUG_ORDER_SERIAL, "PLUG 888");
        values.put(StationProvider.FIELD_PLUG_ORDER_SITE, "神岡停車場");
        values.put(StationProvider.FIELD_PLUG_ORDER_STATUS, "完成");
        values.put(StationProvider.FIELD_PLUG_START_TIME, System.currentTimeMillis());
        values.put(StationProvider.FIELD_PLUG_ORDER_TIME, System.currentTimeMillis() - 3000000);
        arrayList.add(values);

        values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "order3".hashCode());
        values.put(StationProvider.FIELD_PLUG_ORDER_SERIAL, "PLUG 999");
        values.put(StationProvider.FIELD_PLUG_ORDER_SITE, "車麗屋");
        values.put(StationProvider.FIELD_PLUG_ORDER_STATUS, "處理中");
        values.put(StationProvider.FIELD_PLUG_START_TIME, System.currentTimeMillis());
        values.put(StationProvider.FIELD_PLUG_ORDER_TIME, System.currentTimeMillis() + 160000);
        arrayList.add(values);

        return arrayList;
    }
}
