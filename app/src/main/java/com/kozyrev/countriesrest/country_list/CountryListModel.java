package com.kozyrev.countriesrest.country_list;

import android.util.Log;

import com.kozyrev.countriesrest.model.Country;
import com.kozyrev.countriesrest.network.ApiClient;
import com.kozyrev.countriesrest.network.ApiInterface;

import java.util.List;

public class CountryListModel implements CountryListContract.Model {

    private static final String TAG = "CountryListModel";

    @Override
    public void getCountries(OnFinishedListener onFinishedListener) {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        List<Country> countries = apiInterface.getCountries();
        if (countries.size() > 0){
            Log.d(TAG, "Number of countries received: " + countries.size());
            onFinishedListener.onFinished(countries);
        } else {
            Throwable throwable = new Exception("Countries not found");
            Log.e(TAG, throwable.toString());
            onFinishedListener.onFailure(throwable);
        }
    }
}
