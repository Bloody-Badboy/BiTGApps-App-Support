package com.paget96.bitgapps;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BiTGAppsProp {

    private static final String G_PROP_PATH = "/system/etc/g.prop";
    private File gProp = new File(G_PROP_PATH);

    private String customGAppsPackage;
    private String platform;
    private String sdk;
    private String version;
    private String buildDate;
    private String buildID;
    private String developer;

    public String getCustomGAppsPackage() {
        return customGAppsPackage;
    }

    public String getPlatform() {
        return platform;
    }

    public String getSdk() {
        return sdk;
    }

    public String getVersion() {
        return version;
    }

    public String getBuildDate() {
        return buildDate;
    }

    public String getBuildID() {
        return buildID;
    }

    public String getDeveloper() {
        return developer;
    }

    boolean isGPropExists() {
        return gProp.exists();
    }

    void readProperties() {
        try {
            FileInputStream fileInputStream = new FileInputStream(gProp);
            Properties properties = new Properties();
            properties.load(fileInputStream);

            customGAppsPackage = properties.getProperty("CustomGAppsPackage");
            platform = properties.getProperty("platform");
            sdk = properties.getProperty("sdk");
            version = properties.getProperty("version");
            buildDate = properties.getProperty("BuildDate");
            buildID = properties.getProperty("BuildID");
            developer = properties.getProperty("Developer");

            fileInputStream.close();
            properties.clear();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
