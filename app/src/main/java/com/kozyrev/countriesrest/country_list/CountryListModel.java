package com.kozyrev.countriesrest.country_list;

import android.util.Log;

import com.kozyrev.countriesrest.model.Country;
import com.kozyrev.countriesrest.network.ApiClient;
import com.kozyrev.countriesrest.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryListModel implements CountryListContract.Model {

    private static final String TAG = "CountryListModel";

    @Override
    public void getCountries(OnFinishedListener onFinishedListener) {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Country>> call = apiInterface.getCountries();
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                List<Country> countries = response.body();
                Log.d(TAG, "Number of countries received: " + countries.size());
                onFinishedListener.onFinished(countries);
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t);
            }
        });
    }
}
