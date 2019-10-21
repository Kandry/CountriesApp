package com.kozyrev.countriesrest.country_detail;

import android.util.Log;

import com.kozyrev.countriesrest.model.Country;
import com.kozyrev.countriesrest.network.ApiClient;
import com.kozyrev.countriesrest.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryDetailModel implements CountryDetailContract.Model {

    private static final String TAG = "CountryDetailModel";

    @Override
    public void getCountryDetails(OnFinishedListener onFinishedListener, String name) {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Country>> call = apiInterface.getCountryDetail(name);
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                List<Country> countries = response.body();
                Country country = countries.get(0);
                Log.d(TAG, "Country data received: " + country.toString());
                onFinishedListener.onFinished(country);
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                onFinishedListener.onFailure(t);
            }
        });
    }
}
