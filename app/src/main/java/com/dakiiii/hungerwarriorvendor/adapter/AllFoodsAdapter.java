package com.dakiiii.hungerwarriorvendor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;


import com.dakiiii.hungerwarriorvendor.R;
import com.dakiiii.hungerwarriorvendor.model.Food;

import java.util.List;

public class AllFoodsAdapter extends RecyclerView.Adapter<AllFoodsAdapter.AllFoodsViewHolder> {

    private List<Food> eFoods;
    private Context eContext;
    private LiveData<List<Food>> eFoodsLiveData;

    @NonNull
    @Override
    public AllFoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(eContext).inflate(R.layout.all_foods_row_item,
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
        return eFoodsLiveData.getValue().size();
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

            eTextViewFoodName = itemView.findViewById(R.id.textView_FoodName);
            eTextViewFoodPrice = itemView.findViewById(R.id.textView_FoodPrice);
            eImageViewFoodImage = itemView.findViewById(R.id.imageViewFood);
        }
    }
}
