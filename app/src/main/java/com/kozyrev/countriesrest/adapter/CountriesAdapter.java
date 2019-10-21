package com.kozyrev.countriesrest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kozyrev.countriesrest.R;
import com.kozyrev.countriesrest.country_list.CountryListActivity;
import com.kozyrev.countriesrest.model.Country;

import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ViewHolder> {

    private CountryListActivity countryListActivity;
    private List<Country> countries;

    public CountriesAdapter(CountryListActivity countryListActivity, List<Country> countries){
        this.countryListActivity = countryListActivity;
        this.countries = countries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_country, parent, false);
        ViewHolder viewHolder = new ViewHolder(cardView);

        cardView.setOnClickListener((v) -> {
            int adapterPosition = viewHolder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION){
                countryListActivity.onCountryItemClick(adapterPosition);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Country country = countries.get(position);
        holder.tvCountryName.setText(country.getName());
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public void dataSetChanged(List<Country> countries){
        this.countries = countries;
        this.notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvCountryName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCountryName = itemView.findViewById(R.id.tv_country_name);
        }
    }
}
