package com.kozyrev.countriesrest.country_detail;

import android.util.Log;

import com.kozyrev.countriesrest.model.Country;
import com.kozyrev.countriesrest.network.ApiClient;
import com.kozyrev.countriesrest.network.ApiInterface;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryDetailModel implements CountryDetailContract.Model {

    private static final String TAG = "CountryDetailModel";
    private Realm realm = Realm.getDefaultInstance();

    @Override
    public void getCountryDetails(OnFinishedListener onFinishedListener, String name) {

        realm.beginTransaction();
        Country country = realm.where(Country.class).equalTo("name", name).findFirst();
        realm.commitTransaction();

        if (country.getNativeName() != null){
            onFinishedListener.onFinished(country);
        } else {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<List<Country>> call = apiInterface.getCountryDetail(name);
            call.enqueue(new Callback<List<Country>>() {
                @Override
                public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                    List<Country> countries = response.body();
                    Country country = countries.get(0);

                    realm.beginTransaction();
                    realm.copyToRealmOrUpdate(country);
                    realm.commitTransaction();

                    Log.d(TAG, "Country data received: " + country.getName());
                    onFinishedListener.onFinished(country);
                }

                @Override
                public void onFailure(Call<List<Country>> call, Throwable throwable) {
                    Log.e(TAG, throwable.getMessage());
                    onFinishedListener.onFailure(throwable);
                }
            });
        }
    }
}
