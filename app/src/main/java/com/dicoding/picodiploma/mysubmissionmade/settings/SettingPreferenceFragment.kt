package com.dicoding.picodiploma.mysubmissionmade.settings

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.dicoding.picodiploma.mysubmissionmade.R

class SettingPreferenceFragment : PreferenceFragmentCompat() {

    private lateinit var LANGUAGE: String

    private lateinit var languageSetting: Preference

    override fun onCreatePreferences(
        savedInstanceState: Bundle?,
        rootKey: String?
    ) {
        addPreferencesFromResource(R.xml.preference)

        LANGUAGE = resources.getString(R.string.language)
        languageSetting = findPreference<Preference>(LANGUAGE) as Preference
        languageSetting.setOnPreferenceClickListener {
            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(intent)

            true
        }
    }
}