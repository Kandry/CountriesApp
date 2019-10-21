package com.kozyrev.countriesrest.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Language extends RealmObject {

    @SerializedName("name")
    private String name;

    @SerializedName("nativeName")
    private String nativeName;

    public Language(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }
}
