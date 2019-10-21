package com.kozyrev.countriesrest.country_detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kozyrev.countriesrest.R;
import com.kozyrev.countriesrest.model.Country;

public class CountryDetailActivity extends AppCompatActivity implements CountryDetailContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);
    }

    @Override
    public void setDataToViews(Country country) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onResponseFailure(Throwable throwable) {

    }
}
