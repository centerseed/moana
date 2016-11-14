package com.moana.carsharing.order;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.moana.carsharing.base.BaseParser;
import com.moana.carsharing.dummy.DummyOrderSource;
import com.moana.carsharing.dummy.DummyPlugSource;
import com.moana.carsharing.station.StationProvider;

import org.json.JSONObject;

import java.util.ArrayList;

public class CarOrderParser extends BaseParser {
    public static CarOrderParser with(Context context, Uri uri) {
        return new CarOrderParser(context, uri);
    }

    protected CarOrderParser(Context context, Uri uri) {
        super(context, uri);
    }

    @Override
    public void parse(JSONObject object) {

    }

    @Override
    public void parseDummy() {
        mContext.getContentResolver().delete(mUri, StationProvider.FIELD_ID + "!=?", new String[]{"0"});

        ArrayList<ContentValues> arrayList = DummyOrderSource.getDummyCarOrder();
        for (ContentValues values : arrayList) {
            mContext.getContentResolver().insert(mUri, values);
        }
    }
}
