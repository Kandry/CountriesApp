package com.kozyrev.countriesrest.country_detail;

import com.kozyrev.countriesrest.model.Country;

public class CountryDetailPresenter implements CountryDetailContract.Presenter, CountryDetailContract.Model.OnFinishedListener {

    private CountryDetailContract.View countryDetailView;
    private CountryDetailContract.Model countryDetailModel;

    public CountryDetailPresenter(CountryDetailContract.View countryDetailView){
        this.countryDetailView = countryDetailView;
        this.countryDetailModel = new CountryDetailModel();
    }

    @Override
    public void requestCountryData(String name) {
        if (countryDetailView != null){
            countryDetailView.showProgress();
        }
        countryDetailModel.getCountryDetails(this, name);
    }

    @Override
    public void onDestroy() {
        countryDetailView = null;
    }

    @Override
    public void onFinished(Country country) {

        if (countryDetailView != null){
            countryDetailView.hideProgress();
        }
        countryDetailView.setDataToViews(country);
    }

    @Override
    public void onFailure(Throwable throwable) {
        if (countryDetailView != null){
            countryDetailView.hideProgress();
        }
        countryDetailView.onResponseFailure(throwable);
    }
}
