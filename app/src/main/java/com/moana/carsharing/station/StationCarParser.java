package com.moana.carsharing.station;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.moana.carsharing.base.BaseParser;
import com.moana.carsharing.dummy.DummyRentSource;

import org.json.JSONObject;

import java.util.ArrayList;

public class StationCarParser extends BaseParser {

    protected StationCarParser(Context context, Uri uri) {
        super(context, uri);
    }

    public static BaseParser with(Context context, Uri uri) {
        return new StationCarParser(context, uri);
    }

    @Override
    public void parse(JSONObject object) {

    }

    @Override
    public void parseDummy() {
    }
}
