package com.paget96.bitgapps.utils;

import android.os.Build;

import androidx.appcompat.app.AppCompatDelegate;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class ThemeUtils {

    public static Theme getDefaultTheme() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return Theme.SYSTEM;
        } else {
            return Theme.BATTERY_SAVER;
        }
    }

    public static List<Theme> availableThemes() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return Arrays.asList(Theme.LIGHT, Theme.DARK, Theme.SYSTEM);
        } else {
            return Arrays.asList(Theme.LIGHT, Theme.DARK, Theme.BATTERY_SAVER);
        }
    }

    public static Theme themeFromStorageKey(String storageKey) {
        for (Theme theme : Theme.values()) {
            if (theme.storageKey.equalsIgnoreCase(storageKey)) {
                return theme;
            }
        }
        throw new NoSuchElementException("No theme found matching the storageKey=" + storageKey);
    }

    public static void updateForTheme(Theme theme) {
        switch (theme) {
            case DARK:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case LIGHT:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case SYSTEM:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            case BATTERY_SAVER:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
                break;
        }
    }
}
