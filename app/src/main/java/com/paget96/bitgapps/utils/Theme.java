package com.paget96.bitgapps.utils;

public enum Theme {
    LIGHT("light"),
    DARK("dark"),
    SYSTEM("system"),
    BATTERY_SAVER("battery_saver");

    public final String storageKey;

    Theme(String storageKey) {
        this.storageKey = storageKey;
    }
}
