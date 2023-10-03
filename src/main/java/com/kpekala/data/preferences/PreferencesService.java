package com.kpekala.data.preferences;

import java.util.prefs.Preferences;

public class PreferencesService {

    private final Preferences preferences = Preferences.userRoot();

    public void updateUserHash(String hash){
        preferences.put("hash",hash);
    }
    public String getUserHash(){
        return preferences.get("hash","");
    }

    public void updateUserName(String name) {
        preferences.put("userName",name);
    }

    public String getUserName() {
        return preferences.get("userName","");
    }
}
