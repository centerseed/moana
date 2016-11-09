package com.moana.carsharing.station.plug;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.moana.carsharing.base.BaseParser;
import com.moana.carsharing.dummy.DummyCarSource;
import com.moana.carsharing.dummy.DummyPlugSource;
import com.moana.carsharing.station.StationProvider;

import org.json.JSONObject;

import java.util.ArrayList;

public class PlugInfoParser extends BaseParser {
    protected PlugInfoParser(Context context, Uri uri) {
        super(context, uri);
    }

    public static PlugInfoParser with(Context context, Uri uri) {
        return new PlugInfoParser(context, uri);
    }

    @Override
    public void parse(JSONObject object) {

    }

    @Override
    public void parseDummy() {
        mContext.getContentResolver().delete(mUri, StationProvider.FIELD_ID + "!=?", new String[]{"0"});

        ArrayList<ContentValues> arrayList = DummyPlugSource.getDummyPlug();
        for (ContentValues values : arrayList) {
            mContext.getContentResolver().insert(mUri, values);
        }

        mContext.getContentResolver().notifyChange(mUri, null);
    }
}
