package com.moana.carsharing.base;

import android.content.Context;
import android.net.Uri;

import org.json.JSONObject;

public abstract class BaseParser {
    protected Context mContext;
    protected Uri mUri;
    protected BaseParser mInstance;

    protected BaseParser(Context context, Uri uri){
        mContext = context;
        mUri = uri;
    }

    abstract public void parse(JSONObject object);

    abstract public void parseDummy();
}
