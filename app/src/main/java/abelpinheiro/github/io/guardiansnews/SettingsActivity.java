package abelpinheiro.github.io.guardiansnews;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public static class NewsPreferenceFragment extends PreferenceFragmentCompat implements android.support.v7.preference.Preference.OnPreferenceChangeListener {

        @Override
        public void onCreatePreferences(Bundle bundle, String s) {
            addPreferencesFromResource(R.xml.settings_main);
            android.support.v7.preference.Preference searchTag = findPreference(getString(R.string.settings_search_tag_key));
            bindPreferenceSummaryToValue(searchTag);
        }

        private void bindPreferenceSummaryToValue(android.support.v7.preference.Preference preference) {
            preference.setOnPreferenceChangeListener(this);

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
            String preferenceString = preferences.getString(preference.getKey(), "");
            onPreferenceChange(preference, preferenceString);
        }

        @Override
        public boolean onPreferenceChange(android.support.v7.preference.Preference preference, Object o) {
            String stringValue = o.toString();
            preference.setSummary(stringValue);
            return false;
        }
    }
}
