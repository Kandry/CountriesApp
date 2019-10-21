package com.kozyrev.countriesrest.country_detail;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.kozyrev.countriesrest.R;
import com.kozyrev.countriesrest.adapter.LanguagesAdapter;
import com.kozyrev.countriesrest.model.Country;
import com.kozyrev.countriesrest.model.Language;

import java.util.ArrayList;
import java.util.List;

import static com.kozyrev.countriesrest.utils.Constants.COUNTRY_NAME;

public class CountryDetailActivity extends AppCompatActivity implements CountryDetailContract.View {

    private static final String TAG = "CountryDetailActivity";

    private CoordinatorLayout clRootViewDetail;

    private ImageView ivFlag;
    private ProgressBar pbLoadFlag;

    private TextView tvName;
    private TextView tvNativeName;
    private TextView tvCapital;
    private TextView tvNumericCode;
    private TextView tvRegion;
    private TextView tvPopulation;

    private LanguagesAdapter languagesAdapter;
    private List<Language> languages;
    private ProgressBar pbLoadLanguages;

    private String countryName;

    private CountryDetailPresenter countryDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initCollapsingToolbar();
        initViews();

        Intent intent = getIntent();
        String name = intent.getStringExtra(COUNTRY_NAME);

        countryDetailPresenter = new CountryDetailPresenter(this);
        countryDetailPresenter.requestCountryData(name);
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");

        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1){
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(countryName);
                    isShow = true;
                } else if(isShow){
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    private void initViews(){
        clRootViewDetail = findViewById(R.id.cl_root_view_detail);

        ivFlag = findViewById(R.id.iv_flag);
        pbLoadFlag = findViewById(R.id.pb_load_flag);

        tvName = findViewById(R.id.tv_country_name);
        tvNativeName = findViewById(R.id.tv_native_name_value);
        tvCapital = findViewById(R.id.tv_capital_value);
        tvNumericCode = findViewById(R.id.tv_numeric_code_value);
        tvRegion = findViewById(R.id.tv_region_value);
        tvPopulation = findViewById(R.id.tv_population_value);

        languages = new ArrayList<>();
        RecyclerView rvLanguages = findViewById(R.id.rv_languages);
        //languagesAdapter = new LanguagesAdapter(this, languages);
        //rvLanguages.setAdapter(languagesAdapter);
        pbLoadLanguages = findViewById(R.id.pb_languages_loading);
    }

    @Override
    public void setDataToViews(Country country) {
        if (country != null){
            countryName = country.getName();
            tvName.setText(countryName);
            tvNativeName.setText(country.getNativeName());
            tvCapital.setText(country.getCapital());
            tvNumericCode.setText(country.getNumericCode());
            tvRegion.setText(country.getRegion());
            tvPopulation.setText(country.getPopulation());

            GlideToVectorYou
                    .init()
                    .with(this)
                    .withListener(new GlideToVectorYouListener() {
                        @Override
                        public void onLoadFailed() {
                            pbLoadFlag.setVisibility(View.GONE);
                        }

                        @Override
                        public void onResourceReady() {
                            pbLoadFlag.setVisibility(View.GONE);
                        }
                    })
                    .load(Uri.parse(country.getFlag()), ivFlag);
/*
            Glide.with(this)
                    .load(country.getFlag())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            pbLoadFlag.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            pbLoadFlag.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .apply(new RequestOptions().placeholder(R.drawable.ic_place_holder).error(R.drawable.ic_place_holder))
                    .into(ivFlag);*/

        }
    }

    @Override
    public void showProgress() {
        pbLoadFlag.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLoadFlag.setVisibility(View.GONE);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.e(TAG, throwable.getMessage());
        Snackbar.make(clRootViewDetail, getString(R.string.communication_error), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countryDetailPresenter.onDestroy();
    }
}
