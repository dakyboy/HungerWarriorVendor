package com.dakiiii.hungerwarriorvendor.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dakiiii.hungerwarriorvendor.R;
import com.dakiiii.hungerwarriorvendor.model.Food;
import com.dakiiii.hungerwarriorvendor.ui.AddFoodActivity;

import java.util.List;

public class AllFoodsAdapter extends RecyclerView.Adapter<AllFoodsAdapter.AllFoodsViewHolder> {

    private List<Food> eFoods;
    public final String FOOD_DETAILS_EXTRA = "com.dakiiii.hungerwarriorvendor.EXTRA.FOOD";

    @NonNull
    @Override
    public AllFoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_foods_row_item,
                parent, false);
        AllFoodsViewHolder allFoodsViewHolder = new AllFoodsViewHolder(view);
        return allFoodsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllFoodsViewHolder holder, int position) {
        Food food = eFoods.get(position);

        holder.eTextViewFoodName.setText(food.getFoodName());
//        holder.eTextViewFoodDescription.setText(food.getFoodDescription());
        holder.eTextViewFoodPrice.setText(Integer.toString(food.getFoodPrice()));

    }

    @Override
    public int getItemCount() {
        if (eFoods != null){
            return eFoods.size();
        }
        return 0;
    }

    public void setFoods(List<Food> foods) {
        eFoods = foods;
        notifyDataSetChanged();
    }


    public class AllFoodsViewHolder extends RecyclerView.ViewHolder {

        TextView eTextViewFoodName;
        //        TextView eTextViewFoodDescription;
        TextView eTextViewFoodPrice;
        ImageView eImageViewFoodImage;

        public AllFoodsViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = itemView.getContext();
                    Intent intent = new Intent(context, AddFoodActivity.class);
                    Food food = eFoods.get(getAdapterPosition());

                    Bundle bundle = new Bundle();

                    intent.putExtra(FOOD_DETAILS_EXTRA, bundle);
                    context.startActivity(intent);
                }
            });
            eTextViewFoodName = itemView.findViewById(R.id.textView_FoodName);
            eTextViewFoodPrice = itemView.findViewById(R.id.textView_FoodPrice);
            eImageViewFoodImage = itemView.findViewById(R.id.imageViewFood);
        }
    }
}
