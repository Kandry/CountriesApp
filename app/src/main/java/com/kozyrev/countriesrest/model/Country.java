package com.kozyrev.countriesrest.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Country extends RealmObject {

    @PrimaryKey
    @SerializedName("name")
    private String name;

    @SerializedName("nativeName")
    private String nativeName;

    @SerializedName("capital")
    private String capital;

    @SerializedName("numericCode")
    private String numericCode;

    @SerializedName("region")
    private String region;

    @SerializedName("flag")
    private String flag;

    @SerializedName("population")
    private String population;

    @SerializedName("languages")
    private RealmList<Language> languages;

    public Country(){

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

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public void setNumericCode(String numericCode) {
        this.numericCode = numericCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public RealmList<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(RealmList<Language> languages) {
        this.languages = languages;
    }
}
