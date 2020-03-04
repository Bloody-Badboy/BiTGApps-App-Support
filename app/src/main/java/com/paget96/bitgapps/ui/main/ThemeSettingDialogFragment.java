package com.paget96.bitgapps.ui.main;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.paget96.bitgapps.BiTGAppsApplication;
import com.paget96.bitgapps.R;
import com.paget96.bitgapps.utils.Theme;
import com.paget96.bitgapps.prefs.BiTGAppsPreferences;
import com.paget96.bitgapps.utils.ThemeUtils;

public class ThemeSettingDialogFragment extends DialogFragment {

    static final String TAG = "theme_chooser_dialog";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        BiTGAppsPreferences preferenceStorage = BiTGAppsApplication.getsPreferences();

        ArrayAdapter<ThemeHolder> listAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_list_item_single_choice
        );

        for (Theme theme : ThemeUtils.availableThemes()) {
            listAdapter.add(new ThemeHolder(theme, getTitleForTheme(theme)));
        }

        int checkedItemPosition = getSelectedPosition(listAdapter, preferenceStorage.getSelectedTheme());

        return new MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.all_choose_theme)
                .setSingleChoiceItems(listAdapter, checkedItemPosition, (dialog, position) -> {
                    dialog.dismiss();
                    ThemeHolder themeHolder = listAdapter.getItem(position);
                    if (themeHolder != null) {
                        preferenceStorage.setSelectedTheme(themeHolder.theme);
                        ThemeUtils.updateForTheme(themeHolder.theme);
                    }
                }).create();
    }


    private String getTitleForTheme(Theme theme) {
        if (theme == Theme.LIGHT) {
            return getString(R.string.settings_theme_light);
        } else if (theme == Theme.DARK) {
            return getString(R.string.settings_theme_dark);
        } else if (theme == Theme.SYSTEM) {
            return getString(R.string.settings_theme_system);
        } else {
            return getString(R.string.settings_theme_battery_saver);
        }
    }

    private int getSelectedPosition(ArrayAdapter<ThemeHolder> adapter, Theme selected) {
        int selectedPosition = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            ThemeHolder themeHolder = adapter.getItem(i);
            if (themeHolder != null && themeHolder.theme == selected) {
                selectedPosition = i;
            }
        }
        return selectedPosition;
    }

    static ThemeSettingDialogFragment newInstance() {
        return new ThemeSettingDialogFragment();
    }

    private static class ThemeHolder {
        Theme theme;
        String title;

        ThemeHolder(Theme theme, String title) {
            this.theme = theme;
            this.title = title;
        }

        @NonNull
        @Override
        public String toString() {
            return title;
        }
    }
}
