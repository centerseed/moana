package com.moana.carsharing.plug;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.moana.carsharing.base.BaseParser;

import org.json.JSONObject;

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
        mContext.getContentResolver().delete(mUri, PlugProvider.FIELD_ID + "     !=?", new String[]{"0"});

        ContentValues values = new ContentValues();
        values.put(PlugProvider.FIELD_ID, "fake".hashCode());
        values.put(PlugProvider.FIELD_PLUG_NAME, "台中市政府");
        values.put(PlugProvider.FIELD_PLUG_ADDRESS, "市政北七路87號");
        values.put(PlugProvider.FIELD_LAT, 24.1675157);
        values.put(PlugProvider.FIELD_LNG, 120.6372358);
        values.put(PlugProvider.FIELD_PLUG_PHOTO, "https://p1-news.hfcdn.com/p1-news/NjE0NzM0bmV3cw,,/89f535b0aae4da55.jpg");

        mContext.getContentResolver().insert(mUri, values);
        mContext.getContentResolver().notifyChange(mUri, null);
    }
}