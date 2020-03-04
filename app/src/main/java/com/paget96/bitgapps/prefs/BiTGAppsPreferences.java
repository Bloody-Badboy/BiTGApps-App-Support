package com.paget96.bitgapps.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.paget96.bitgapps.utils.Theme;
import com.paget96.bitgapps.utils.ThemeUtils;

public class BiTGAppsPreferences {

    private static final String PREFS_NAME = "bitgapps_prefs";
    private static final String PREF_DARK_MODE_ENABLED = "pref_dark_mode";
    private static volatile BiTGAppsPreferences sInstance = null;
    private SharedPreferences sharedPreferences;

    private BiTGAppsPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        if (sInstance != null) {
            throw new AssertionError(
                    "Another instance of "
                            + BiTGAppsPreferences.class.getName()
                            + " class already exists, Can't create a new instance.");
        }
    }

    public static BiTGAppsPreferences getInstance(Context context) {
        if (sInstance == null) {
            synchronized (BiTGAppsPreferences.class) {
                if (sInstance == null) {
                    sInstance = new BiTGAppsPreferences(context);
                }
            }
        }
        return sInstance;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public Theme getSelectedTheme() {
        return ThemeUtils.themeFromStorageKey(sharedPreferences.getString(PREF_DARK_MODE_ENABLED, ThemeUtils.getDefaultTheme().storageKey));
    }

    public void setSelectedTheme(Theme theme) {
        sharedPreferences.edit().putString(PREF_DARK_MODE_ENABLED, theme.storageKey).apply();
    }
}

