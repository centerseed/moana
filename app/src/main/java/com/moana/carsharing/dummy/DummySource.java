package com.moana.carsharing.dummy;

import android.content.ContentValues;

import com.moana.carsharing.plug.PlugProvider;

import java.util.ArrayList;

public class DummySource {
    public static ArrayList<ContentValues> getRentList() {
        ArrayList<ContentValues> arrayList = new ArrayList<>();

        // Dummy 1
        ContentValues values = new ContentValues();
        values.put(PlugProvider.FIELD_ID, "rent1".hashCode());
        values.put(PlugProvider.FIELD_PLUG_NAME, "台中市政府");
        values.put(PlugProvider.FIELD_PLUG_ADDRESS, "市政北七路87號");
        values.put(PlugProvider.FIELD_LAT, 24.1675157);
        values.put(PlugProvider.FIELD_LNG, 120.6372358);
        values.put(PlugProvider.FIELD_PLUG_PHOTO, "https://p1-news.hfcdn.com/p1-news/NjE0NzM0bmV3cw,,/89f535b0aae4da55.jpg");
        values.put(PlugProvider.FIELD_TOTAL, 10);
        values.put(PlugProvider.FIELD_USAGE, 5);
        values.put(PlugProvider.FIELD_IS_RENT, 1);
        arrayList.add(values);

        // Dummy 2
        values = new ContentValues();
        values.put(PlugProvider.FIELD_ID, "rent2".hashCode());
        values.put(PlugProvider.FIELD_PLUG_NAME, "台中市纖維工藝博物館");
        values.put(PlugProvider.FIELD_PLUG_ADDRESS, "台中市大里區勝利二路1號");
        values.put(PlugProvider.FIELD_LAT, 24.1009633);
        values.put(PlugProvider.FIELD_LNG, 120.6820501);
        values.put(PlugProvider.FIELD_PLUG_PHOTO, "http://www.webnews.tw/wp-content/uploads/2016/07/%E7%BA%96%E7%B6%AD%E5%A5%BD%E5%A5%BD%E7%8E%A9-300x225.jpg");
        values.put(PlugProvider.FIELD_TOTAL, 8);
        values.put(PlugProvider.FIELD_USAGE, 5);
        values.put(PlugProvider.FIELD_IS_RENT, 1);
        arrayList.add(values);

        return arrayList;
    }

    public static ArrayList<ContentValues> getPlugList() {
        ArrayList<ContentValues> arrayList = new ArrayList<>();

        // Dummy 1
        ContentValues values = new ContentValues();
        values.put(PlugProvider.FIELD_ID, "plug1".hashCode());
        values.put(PlugProvider.FIELD_PLUG_NAME, "台中公園");
        values.put(PlugProvider.FIELD_PLUG_ADDRESS, "台中市北區雙十路一段65號");
        values.put(PlugProvider.FIELD_LAT, 24.144834);
        values.put(PlugProvider.FIELD_LNG, 120.6844612);
        values.put(PlugProvider.FIELD_PLUG_PHOTO, "http://paas.cmoremap.com.tw/userfiles/kevin432186/20130814144224.jpg");
        values.put(PlugProvider.FIELD_TOTAL, 10);
        values.put(PlugProvider.FIELD_USAGE, 5);
        values.put(PlugProvider.FIELD_IS_RENT, 0);
        arrayList.add(values);

        // Dummy 2
        values = new ContentValues();
        values.put(PlugProvider.FIELD_ID, "plug2".hashCode());
        values.put(PlugProvider.FIELD_PLUG_NAME, "國立台灣美術館");
        values.put(PlugProvider.FIELD_PLUG_ADDRESS, "台中市西區五權西路一段2號");
        values.put(PlugProvider.FIELD_LAT, 24.1409473);
        values.put(PlugProvider.FIELD_LNG, 120.6635911);
        values.put(PlugProvider.FIELD_PLUG_PHOTO, "http://itravel.gogoblog.tw/images/album/48/Image/20110318317/317_201103181307350.jpg");
        values.put(PlugProvider.FIELD_TOTAL, 5);
        values.put(PlugProvider.FIELD_USAGE, 4);
        values.put(PlugProvider.FIELD_IS_RENT, 0);
        arrayList.add(values);

        return arrayList;
    }
}
