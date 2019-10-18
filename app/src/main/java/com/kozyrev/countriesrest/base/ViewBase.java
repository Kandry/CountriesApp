package com.kozyrev.countriesrest.base;

public interface ViewBase {

    void showProgress();

    void hideProgress();

    void onResponseFailure(Throwable throwable);
}
