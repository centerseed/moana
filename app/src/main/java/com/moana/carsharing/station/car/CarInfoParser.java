package com.moana.carsharing.station.car;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.moana.carsharing.base.BaseParser;
import com.moana.carsharing.dummy.DummyCarSource;
import com.moana.carsharing.station.StationProvider;

import org.json.JSONObject;

import java.util.ArrayList;

public class CarInfoParser extends BaseParser {
    protected CarInfoParser(Context context, Uri uri) {
        super(context, uri);
    }

    @Override
    public void parse(JSONObject object) {

    }

    @Override
    public void parseDummy() {
        mContext.getContentResolver().delete(mUri, StationProvider.FIELD_ID + "!=?", new String[]{"0"});

        ArrayList<ContentValues> arrayList = DummyCarSource.getDummyCar();
        for (ContentValues values : arrayList) {
            mContext.getContentResolver().insert(mUri, values);
        }

        mContext.getContentResolver().notifyChange(mUri, null);
    }
}
