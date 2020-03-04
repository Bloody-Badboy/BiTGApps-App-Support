package com.paget96.bitgapps;

import android.app.Application;

import androidx.annotation.NonNull;

import com.paget96.bitgapps.prefs.BiTGAppsPreferences;
import com.paget96.bitgapps.utils.Theme;
import com.paget96.bitgapps.utils.ThemeUtils;

import timber.log.Timber;

public class BiTGAppsApplication extends Application {
    private static BiTGAppsApplication sInstance;
    private static BiTGAppsPreferences sBiTGAppsPreferences;

    public static BiTGAppsApplication getInstance() {
        return sInstance;
    }

    public static BiTGAppsPreferences getsPreferences() {
        return sBiTGAppsPreferences;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
        sBiTGAppsPreferences = BiTGAppsPreferences.getInstance(getApplicationContext());

        Theme theme = sBiTGAppsPreferences.getSelectedTheme();
        ThemeUtils.updateForTheme(theme);

        if (BuildConfig.DEBUG) {
            plantTimberDebug();
        }
    }

    private void plantTimberDebug() {
        Timber.plant(new Timber.DebugTree() {
            @Override
            protected String createStackElementTag(@NonNull StackTraceElement element) {
                return "BiTGApps - "
                        + super.createStackElementTag(element)
                        + ":"
                        + element.getLineNumber();
            }
        });
    }
}
