package com.moana.carsharing.station;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.moana.carsharing.base.BaseContentProvider;

public class StationProvider extends BaseContentProvider {

    public final static String TABLE_STATION = "_table_station";
    public final static String TABLE_CAR = "_table_car";
    public final static String TABLE_PLUG = "_table_plug";
    public final static String TABLE_CAR_ORDER = "_table_car_order";
    public final static String TABLE_PLUG_ORDER = "_table_plug_order";

    public final static String FIELD_STATION_NAME = "_station_name";
    public final static String FIELD_STATION_ADDRESS = "_station_address";
    public final static String FIELD_STATION_PHOTO = "_station_photo";
    public final static String FIELD_LAT = "_lnt";
    public final static String FIELD_LNG = "_lng";
    public final static String FIELD_TOTAL = "_total";
    public final static String FIELD_USAGE = "_usage";
    public final static String FIELD_IS_RENT = "_is_rent";

    public final static String FIELD_CAR_BELONG_STATION = "_car_belong_station";
    public final static String FIELD_CAR_CHARGE = "_car_charge";
    public final static String FIELD_CAR_REMAIN = "_car_remain";

    public final static String FIELD_PLUG_BELONG_STATION = "_plug_belong_station";
    public final static String FIELD_PLUG_STATUS = "_plug_charge";

    public final static String FIELD_CAR_ORDER_SERIAL = "_car_order_serial";
    public final static String FIELD_CAR_ORDER_TIME = "_car_order_time";
    public final static String FIELD_CAR_ORDER_START_TIME = "_car_order_start_time";
    public final static String FIELD_CAR_ORDER_END_TIME = "_car_order_end_time";
    public final static String FIELD_CAR_ORDER_CAR_TYPE = "_car_order_car_type";
    public final static String FIELD_CAR_ORDER_RENT_SITE = "_car_order_rent_site";
    public final static String FIELD_CAR_ORDER_RETURN_SITE = "_car_order_return_site";
    public final static String FIELD_CAR_ORDER_USAGE = "_car_order_usage";
    public final static String FIELD_CAR_ORDER_FEE = "_car_order_fee";
    public final static String FIELD_CAR_ORDER_PAYMENT = "_car_order_payment";

    public final static String FIELD_PLUG_ORDER_SERIAL = "_plug_order_serial";
    public final static String FIELD_PLUG_ORDER_TIME = "_plug_order_time";
    public final static String FIELD_PLUG_ORDER_SITE = "_plug_order_site";

    public final static int PLUG_STATUS_AVAILABLE = 0;
    public final static int PLUG_STATUS_IN_USAGE= 1;
    public final static int PLUG_STATUS_ERROR = 2;

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
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_STATION + " ( "
                    + FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + FIELD_STATION_NAME + " TEXT, "
                    + FIELD_STATION_ADDRESS + " TEXT, "
                    + FIELD_STATION_PHOTO + " TEXT, "
                    + FIELD_LAT + " TEXT, "
                    + FIELD_LNG + " TEXT, "
                    + FIELD_USAGE + " INTEGER, "
                    + FIELD_TOTAL + " INTEGER, "
                    + FIELD_IS_RENT + " INTEGER "
                    + ");");

            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_CAR + " ( "
                    + FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + FIELD_CAR_BELONG_STATION + " TEXT, "
                    + FIELD_CAR_CHARGE + " INTEGER, "
                    + FIELD_CAR_REMAIN + " INTEGER "
                    + ");");

            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_PLUG + " ( "
                    + FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + FIELD_PLUG_BELONG_STATION + " TEXT, "
                    + FIELD_PLUG_STATUS + " INTEGER "
                    + ");");

            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_CAR_ORDER + " ( "
                    + FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + FIELD_CAR_ORDER_SERIAL + " TEXT, "
                    + FIELD_CAR_ORDER_TIME + " INTEGER, "
                    + FIELD_CAR_ORDER_START_TIME + " INTEGER, "
                    + FIELD_CAR_ORDER_END_TIME + " INTEGER, "
                    + FIELD_CAR_ORDER_RENT_SITE + " TEXT, "
                    + FIELD_CAR_ORDER_RETURN_SITE + " TEXT, "
                    + FIELD_CAR_ORDER_CAR_TYPE + " TEXT, "
                    + FIELD_CAR_ORDER_USAGE + " INTEGER, "
                    + FIELD_CAR_ORDER_FEE + " INTEGER, "
                    + FIELD_CAR_ORDER_PAYMENT + " TEXT "
                    + ");");

            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_PLUG_ORDER + " ( "
                    + FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + FIELD_PLUG_ORDER_SERIAL + " TEXT, "
                    + FIELD_PLUG_ORDER_SITE + " TEXT, "
                    + FIELD_PLUG_ORDER_TIME + " INTEGER "
                    + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATION);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAR);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLUG);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAR_ORDER);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLUG_ORDER);
            onCreate(db);
        }
    }
}
