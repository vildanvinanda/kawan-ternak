package com.example.projectkt;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class preferences {
    private static final String KEY_DATA = "key_data";
    private static final String NAMA_DATA = "nama_data";
    private static final String ACTIVE_DATA = "active_data";

    private static SharedPreferences getSharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setKey(Context context, String s){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(KEY_DATA,s);
        editor.apply();
    }

    public static String getKeyData(Context context){
        return getSharedPreferences(context).getString(KEY_DATA, "");
    }
    public static void setNamaData(Context context, String s){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(NAMA_DATA,s);
        editor.apply();
    }
    public static String getNamaData(Context context){
        return getSharedPreferences(context).getString(NAMA_DATA, "");
    }

    public static void setActiveData(Context context, boolean isActive){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(ACTIVE_DATA,isActive);
        editor.apply();
    }
    public static String getActiveData(Context context){
        return getSharedPreferences(context).getString(ACTIVE_DATA, "");
    }

    public static void clearData(Context context){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.remove(KEY_DATA);
        editor.remove(NAMA_DATA);
        editor.remove(ACTIVE_DATA);
        editor.apply();
    }
}
