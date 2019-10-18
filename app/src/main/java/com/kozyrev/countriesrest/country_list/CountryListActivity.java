package com.kozyrev.countriesrest.country_list;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kozyrev.countriesrest.R;
import com.kozyrev.countriesrest.model.Country;

import java.util.List;

public class CountryListActivity extends AppCompatActivity implements CountryListContract.View, CountryItemClickListener, ShowEmptyView{

    // В ЯЧЕЙКАХ ТОЛЬКО НАЗВАНИЕ. ЯЧЕЙКИ НА ВСЮ ШИРИНУ

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void setDataToRecyclerView(List<Country> countries) {

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

    @Override
    public void onCountryItemClick(int position) {

    }

    @Override
    public void showEmptyView() {

    }

    @Override
    public void hideEmptyView() {

    }
}
