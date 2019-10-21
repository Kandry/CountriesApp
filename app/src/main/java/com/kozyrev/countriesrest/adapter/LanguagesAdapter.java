package com.kozyrev.countriesrest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kozyrev.countriesrest.R;
import com.kozyrev.countriesrest.model.Language;

import java.util.List;

public class LanguagesAdapter extends RecyclerView.Adapter<LanguagesAdapter.ViewHolder> {

    private List<Language> languages;

    public LanguagesAdapter(List<Language> languages){
        this.languages = languages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_language, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Language language = languages.get(position);
        holder.tvLanguageName.setText(language.getName());
        holder.tvLanguageNativeName.setText(language.getNativeName());
    }

    @Override
    public int getItemCount() {
        return languages.size();
    }

    public void dataSetChanged(List<Language> languages){
        this.languages = languages;
        this.notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvLanguageName;
        TextView tvLanguageNativeName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvLanguageName = itemView.findViewById(R.id.tv_language_name);
            tvLanguageNativeName = itemView.findViewById(R.id.tv_language_native_name);
        }
    }
}
