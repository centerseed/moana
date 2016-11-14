package com.moana.carsharing.account;


import android.content.Context;
import android.content.SharedPreferences;

import com.moana.carsharing.R;
import com.moana.carsharing.utils.SimpleCrypto;

public class AccountUtils {

    Context mContext;

    private static AccountUtils mInstance;
    private final String PREF_NAME = "com.moana.cardharing";

    private AccountUtils(Context context) {
        mContext = context;
    }

    public static AccountUtils with(Context context) {
        if (mInstance == null) {
            mInstance = new AccountUtils(context);
        }
        return mInstance;
    }

    public void createAccount(String account, String password) {
        String crypto;
        try {
            crypto = SimpleCrypto.encrypt(mContext.getString(R.string.title_account), password);
        } catch (Exception e) {
            e.printStackTrace();
            crypto = password;
        }

        SharedPreferences preferences = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        preferences.edit().putString("account", account).commit();
        preferences.edit().putString("password", crypto).commit();
    }

    public String getAccountName() {
        return mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getString("account", null);
    }

    public void clearAcconut() {
        SharedPreferences preferences = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        preferences.edit().putString("account", null).commit();
        preferences.edit().putString("password", null).commit();
    }

    public String getPasseord() {
        String crypto = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getString("password", null);
        try {
            return SimpleCrypto.decrypt(mContext.getString(R.string.title_account), crypto);
        } catch (Exception e) {
            e.printStackTrace();
            return crypto;
        }
    }

    // TODO: Process token function(if has...)
    public void getToken(String token) {

    }
}
