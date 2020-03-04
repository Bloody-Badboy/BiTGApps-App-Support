package com.paget96.bitgapps;

import android.app.Application;

import androidx.annotation.NonNull;

import timber.log.Timber;

public class BiTGAppsApplication extends Application {
    private static BiTGAppsApplication sInstance;

    public static BiTGAppsApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

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
