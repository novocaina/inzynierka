package com.example.alicja.aplikacjadietetyczna;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> {
    private ArrayList<DailyMeal> listMeals;
    public ImageView image;
    private Context context;

    public MealAdapter(ArrayList<DailyMeal> list, Context context) {
        this.listMeals = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meal_item, null);
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.m_title.setText(listMeals.get(position).getName());
        holder.m_portion.setText(listMeals.get(position).getPortions());
        holder.cal_txt.setText(String.valueOf(listMeals.get(position).getCalories()));
        holder.protein_txt.setText(String.valueOf(listMeals.get(position).getProteins()));
        holder.carbons_txt.setText(String.valueOf(listMeals.get(position).getCarbohydrates()));
        holder.fat_text.setText(String.valueOf(listMeals.get(position).getFat()));
        holder.ingredient_txt.setText(listMeals.get(position).getIngredients());
        holder.recipes_txt.setText(listMeals.get(position).getPrepare());


    }


    @Override
    public int getItemCount() {
        return listMeals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.meal_title)
        TextView m_title;
        @BindView(R.id.meal_portion)
        TextView m_portion;
        @BindView(R.id.calories_txt)
        TextView cal_txt;
        @BindView(R.id.proteins_txt)
        TextView protein_txt;
        @BindView(R.id.carbohydrates_txt)
        TextView carbons_txt;
        @BindView(R.id.fat_txt)
        TextView fat_text;
        @BindView(R.id.ingredients_txt)
        TextView ingredient_txt;
        @BindView(R.id.recipe_txt)
        TextView recipes_txt;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
