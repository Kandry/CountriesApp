package com.kozyrev.countriesrest.country_detail;

import com.kozyrev.countriesrest.base.ModelBase;
import com.kozyrev.countriesrest.base.PresenterBase;
import com.kozyrev.countriesrest.base.ViewBase;
import com.kozyrev.countriesrest.model.Country;

public interface CountryDetailContract {

    interface Model extends ModelBase {

        interface OnFinishedListener extends ModelBase.OnFinishedListener {

            void onFinished(Country country);
        }

        void getCountryDetails(OnFinishedListener onFinishedListener, String name);
    }

    interface View extends ViewBase {

        void setDataToViews(Country country);
    }

    interface Presenter extends PresenterBase {

        void requestCountryData(String name);
    }
}
