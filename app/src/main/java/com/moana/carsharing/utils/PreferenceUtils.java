package com.moana.carsharing.utils;

import android.content.Context;
import android.preference.PreferenceManager;

import com.moana.carsharing.base.ConstantDef;

public class PreferenceUtils {
    public static void setCurrentFunction(Context context, int func) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt("function", func).commit();
    }

    public static int getCurrentFunction(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt("function", ConstantDef.FUNC_RENT);
    }
}
