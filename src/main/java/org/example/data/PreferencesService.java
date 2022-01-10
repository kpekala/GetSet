package org.example.data;

import java.util.prefs.Preferences;

public class PreferencesService {

    private Preferences preferences = Preferences.userRoot();

    public void updateUserHash(String hash){
        preferences.put("hash",hash);
    }
    public String getUserHash(){
        return preferences.get("hash","");
    }
}
