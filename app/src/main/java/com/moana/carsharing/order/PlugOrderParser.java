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

public class PlugOrderParser extends BaseParser {
    protected PlugOrderParser(Context context, Uri uri) {
        super(context, uri);
    }

    public static PlugOrderParser with(Context context, Uri uri) {
        return new PlugOrderParser(context, uri);
    }


    @Override
    public void parse(JSONObject object) {

    }

    @Override
    public void parseDummy() {
        mContext.getContentResolver().delete(mUri, StationProvider.FIELD_ID + "!=?", new String[]{"0"});

        ArrayList<ContentValues> arrayList = DummyOrderSource.getDummyPlugOrder();
        for (ContentValues values : arrayList) {
            mContext.getContentResolver().insert(mUri, values);
        }
    }
}
