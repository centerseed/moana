package com.moana.plugsearch.plug;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.moana.plugsearch.base.BaseContentProvider;

public class PlugProvider extends BaseContentProvider{

    public final static String TABLE_PLUG = "_table_plug";

    public final static String FIELD_PLUG_NAME = "_plug_name";
    public final static String FIELD_PLUG_ADDRESS = "_plug_address";
    public final static String FIELD_PLUG_PHOTO = "_plug_photo";
    public final static String FIELD_LAT = "_lnt";
    public final static String FIELD_LNG = "_lng";

    @Override
    public boolean onCreate() {
        mDb = new PlugDatabase(getContext());
        return false;
    }

    private class PlugDatabase extends SQLiteOpenHelper {

        private final static int _DBVersion = 2;
        private final static String _DBName = "plug.db";

        public PlugDatabase(Context context) {
            super(context, _DBName, null, _DBVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_PLUG + " ( "
                    + FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + FIELD_PLUG_NAME + " TEXT, "
                    + FIELD_PLUG_ADDRESS + " TEXT, "
                    + FIELD_PLUG_PHOTO + " TEXT, "
                    + FIELD_LAT + " TEXT, "
                    + FIELD_LNG + " TEXT "
                    + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLUG);
            onCreate(db);
        }
    }
}
