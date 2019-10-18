package com.kozyrev.countriesrest.base;

public interface ModelBase {

    interface OnFinishedListener {

        void onFailure(Throwable throwable);
    }
}
