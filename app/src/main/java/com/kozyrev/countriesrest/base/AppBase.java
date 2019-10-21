package com.kozyrev.countriesrest.base;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AppBase extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        initRealmConfiguration();
    }

    private void initRealmConfiguration(){
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
