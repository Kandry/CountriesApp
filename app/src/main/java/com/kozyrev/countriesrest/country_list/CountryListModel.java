package com.kozyrev.countriesrest.country_list;

import android.util.Log;

import com.kozyrev.countriesrest.model.Country;
import com.kozyrev.countriesrest.network.ApiClient;
import com.kozyrev.countriesrest.network.ApiInterface;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryListModel implements CountryListContract.Model {

    private static final String TAG = "CountryListModel";
    private Realm realm = Realm.getDefaultInstance();

    @Override
    public void getCountries(OnFinishedListener onFinishedListener) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Country>> call = apiInterface.getCountries();
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                List<Country> countries = response.body();

                realm.beginTransaction();
                realm.copyToRealmOrUpdate(countries);
                realm.commitTransaction();

                Log.d(TAG, "Number of countries received: " + countries.size());
                onFinishedListener.onFinished(countries);
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
                onFinishedListener.onFailure(throwable);
            }
        });
    }
}
