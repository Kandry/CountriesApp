package com.kozyrev.countriesrest.country_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.kozyrev.countriesrest.R;
import com.kozyrev.countriesrest.adapter.CountriesAdapter;
import com.kozyrev.countriesrest.country_detail.CountryDetailActivity;
import com.kozyrev.countriesrest.model.Country;

import java.util.ArrayList;
import java.util.List;

import static com.kozyrev.countriesrest.utils.Constants.COUNTRY_NAME;

public class CountryListActivity extends AppCompatActivity implements CountryListContract.View, CountryItemClickListener, ShowEmptyView{

    private static final String TAG = "CountryListActivity";
    private CountryListPresenter countryListPresenter;
    private RelativeLayout rlRootView;
    private RecyclerView rvCountries;
    private List<Country> countries;
    private CountriesAdapter countriesAdapter;
    private ProgressBar pbLoading;
    private TextView tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(getString(R.string.app_title));
        initViews();

        countryListPresenter = new CountryListPresenter(this);
        countryListPresenter.requestDataFromServer();
    }

    private void initViews(){
        rlRootView = findViewById(R.id.rl_root_view);
        rvCountries = findViewById(R.id.rv_country_list);

        countries = new ArrayList<>();
        countriesAdapter = new CountriesAdapter(this, countries);

        rvCountries.setLayoutManager(new GridLayoutManager(this, 1));
        rvCountries.setItemAnimator(new DefaultItemAnimator());
        rvCountries.setAdapter(countriesAdapter);

        pbLoading = findViewById(R.id.pb_loading);
        tvEmpty = findViewById(R.id.tv_empty);
    }

    @Override
    public void setDataToRecyclerView(List<Country> countries) {
        this.countries = countries;
        countriesAdapter.dataSetChanged(countries);
    }

    @Override
    public void showProgress() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.e(TAG, throwable.getMessage());
        Snackbar.make(rlRootView, getString(R.string.communication_error), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onCountryItemClick(int position) {
        Intent intent = new Intent(this, CountryDetailActivity.class);
        intent.putExtra(COUNTRY_NAME, countries.get(position).getName());
        startActivity(intent);
    }

    @Override
    public void showEmptyView() {
        rvCountries.setVisibility(View.GONE);
        tvEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyView() {
        tvEmpty.setVisibility(View.GONE);
        rvCountries.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countryListPresenter.onDestroy();
    }
}