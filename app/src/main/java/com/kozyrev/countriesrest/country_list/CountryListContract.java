package com.kozyrev.countriesrest.country_list;

import com.kozyrev.countriesrest.base.ModelBase;
import com.kozyrev.countriesrest.base.PresenterBase;
import com.kozyrev.countriesrest.base.ViewBase;
import com.kozyrev.countriesrest.model.Country;

import java.util.List;

public interface CountryListContract {

    interface Model extends ModelBase {

        interface OnFinishedListener extends ModelBase.OnFinishedListener {

            void onFinished(List<Country> countries);
        }

        void getCountries(OnFinishedListener onFinishedListener);
    }

    interface View extends ViewBase {

        void setDataToRecyclerView(List<Country> countries);
    }

    interface Presenter extends PresenterBase {

        void requestDataFromServer();

        //void getMoreData(int pageNo);
    }
}
