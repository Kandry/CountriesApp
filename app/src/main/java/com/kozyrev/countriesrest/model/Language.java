package com.kozyrev.countriesrest.model;

import com.google.gson.annotations.SerializedName;

public class Language {
    @SerializedName("name")
    private String name;

    @SerializedName("nativeName")
    private String nativeName;

    public Language(String name, String nativeName){
        this.name = name;
        this.nativeName = nativeName;
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

    @Override
    public String toString() {
        return "Language{" +
                "name='" + name + '\'' +
                ", nativeName='" + nativeName + '\'' +
                '}';
    }
}
