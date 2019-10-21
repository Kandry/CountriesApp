package com.kozyrev.countriesrest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Country {

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
    private List<Language> languages;

    public Country(String name, String nativeName, String capital, String numericCode, String region, String flag, String population, List<Language> languages){
        this.name = name;
        this.nativeName = nativeName;
        this.capital = capital;
        this.numericCode = numericCode;
        this.region = region;
        this.flag = flag;
        this.population = population;
        this.languages = languages;
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

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    @Override
    public String toString(){
        return "Country{" +
                "name = " + name + '\'' +
                ", nativeName = " + nativeName + '\'' +
                ", capital = " + capital + '\'' +
                ", numericCode = " + numericCode + '\'' +
                ", region = " + region + '\'' +
                ", flag = " + flag + '\'' +
                ", population = " + population + '\'' +
                ", languages = " + languages + '\'' +
                "}";
    }
}
