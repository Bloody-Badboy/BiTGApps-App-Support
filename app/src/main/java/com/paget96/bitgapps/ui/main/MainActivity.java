package com.paget96.bitgapps.ui.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;
import com.paget96.bitgapps.R;
import com.paget96.bitgapps.utils.Utils;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    // Variables
    private Utils utils = new Utils();
    private TextView gappsPackage, platform, sdk, version, buildDate, buildId, developer;
    private MaterialCardView xda, telegram, gitHub;

    private void initializeViews() {
        gappsPackage = findViewById(R.id.gapps_package);
        platform = findViewById(R.id.platform);
        sdk = findViewById(R.id.sdk);
        version = findViewById(R.id.version);
        buildDate = findViewById(R.id.build_date);
        buildId = findViewById(R.id.build_id);
        developer = findViewById(R.id.developer);

        xda = findViewById(R.id.xda);
        telegram = findViewById(R.id.telegram);
        gitHub = findViewById(R.id.github);
    }

    private void getText() {
        gappsPackage.setText("Gapps package: " + getLineContent(1).split("=")[1]);
        platform.setText("Platform: " + getLineContent(2).split("=")[1]);
        sdk.setText("SDK: " + getLineContent(3).split("=")[1]);
        version.setText("Version: " + getLineContent(4).split("=")[1]);
        buildDate.setText("Build date: " + getLineContent(5).split("=")[1]);
        buildId.setText("Build ID: " + getLineContent(6).split("=")[1]);
        developer.setText("Developer: " + getLineContent(7).split("=")[1]);
    }

    private void onClick() {
        xda.setOnClickListener(v -> utils.openLink(MainActivity.this, "https://forum.xda-developers.com/android/software/custom-bitgapps-android-t4012165"));
        telegram.setOnClickListener(v -> utils.openLink(MainActivity.this, "https://t.me/bitgapps_official"));
        gitHub.setOnClickListener(v -> utils.openLink(MainActivity.this, "https://github.com/BiTGApps"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.toolbar));
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        initializeViews();getText();
        onClick();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_choose_theme) {
            ThemeSettingDialogFragment.newInstance().show(getSupportFragmentManager(), ThemeSettingDialogFragment.TAG);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public String getLineContent(int line) {
        return utils.runCommand("cat /system/etc/g.prop | sed -n " + line + "p", true);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }
}
