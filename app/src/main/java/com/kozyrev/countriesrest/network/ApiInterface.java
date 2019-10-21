package com.kozyrev.countriesrest.network;

import com.kozyrev.countriesrest.model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("all?fields=name")
    Call<List<Country>> getCountries();

    @GET("name/{name}")
    Call<List<Country>> getCountryDetail(@Path("name") String name);
}
