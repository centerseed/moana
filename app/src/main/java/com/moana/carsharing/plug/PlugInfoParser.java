package com.moana.carsharing.plug;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.moana.carsharing.base.BaseParser;
import com.moana.carsharing.dummy.DummyStationSource;

import org.json.JSONObject;

import java.util.ArrayList;

public class PlugInfoParser extends BaseParser {

    protected PlugInfoParser(Context context, Uri uri) {
        super(context, uri);
    }

    public static BaseParser with(Context context, Uri uri) {
        return new PlugInfoParser(context, uri);
    }

    @Override
    public void parse(JSONObject object) {

    }

    @Override
    public void parseDummy() {
        mContext.getContentResolver().delete(mUri, PlugProvider.FIELD_ID + "!=? AND "  + PlugProvider.FIELD_IS_RENT + "=?", new String[]{"0", "0"});

        ArrayList<ContentValues> arrayList = DummyStationSource.getPlugList();
        for (ContentValues values : arrayList) {
            mContext.getContentResolver().insert(mUri, values);
        }

        mContext.getContentResolver().notifyChange(mUri, null);
    }
}
