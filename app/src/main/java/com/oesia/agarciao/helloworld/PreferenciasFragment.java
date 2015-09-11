package com.oesia.agarciao.helloworld;

import android.os.Bundle;
import android.preference.PreferenceFragment;


/**
 * Created by Alberto on 10/09/2015.
 */
public class PreferenciasFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
