package com.moana.carsharing.rent;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.moana.carsharing.base.BaseParser;
import com.moana.carsharing.dummy.DummySource;
import com.moana.carsharing.plug.PlugProvider;

import org.json.JSONObject;

import java.util.ArrayList;

public class RentInfoParser extends BaseParser {

    protected RentInfoParser(Context context, Uri uri) {
        super(context, uri);
    }

    public static BaseParser with(Context context, Uri uri) {
        return new RentInfoParser(context, uri);
    }

    @Override
    public void parse(JSONObject object) {

    }

    @Override
    public void parseDummy() {
        mContext.getContentResolver().delete(mUri, PlugProvider.FIELD_ID + "!=? AND "  + PlugProvider.FIELD_IS_RENT + "=?", new String[]{"0", "1"});

        ArrayList<ContentValues> arrayList = DummySource.getRentList();
        for (ContentValues values : arrayList) {
            mContext.getContentResolver().insert(mUri, values);
        }

        mContext.getContentResolver().notifyChange(mUri, null);
    }
}
