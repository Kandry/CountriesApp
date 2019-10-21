package com.kozyrev.countriesrest.country_list;

import com.kozyrev.countriesrest.model.Country;

import java.util.List;

public class CountryListPresenter implements CountryListContract.Presenter, CountryListContract.Model.OnFinishedListener {

    private CountryListContract.View countryListView;
    private CountryListContract.Model countryListModel;

    public CountryListPresenter(CountryListContract.View countryListView){
        this.countryListView = countryListView;
        this.countryListModel = new CountryListModel();
    }

    @Override
    public void requestDataFromServer() {
        if (countryListView != null){
            countryListView.showProgress();
        }
        countryListModel.getCountries(this);
    }

    @Override
    public void onDestroy() {
        this.countryListView = null;
    }

    @Override
    public void onFinished(List<Country> countries) {
        countryListView.setDataToRecyclerView(countries);
        if (countryListView != null){
            countryListView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable throwable) {
        countryListView.onResponseFailure(throwable);
        if (countryListView != null){
            countryListView.hideProgress();
        }
    }
}
