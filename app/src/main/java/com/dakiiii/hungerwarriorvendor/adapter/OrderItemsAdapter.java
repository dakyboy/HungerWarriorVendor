package com.dakiiii.hungerwarriorvendor.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dakiiii.hungerwarriorvendor.R;
import com.dakiiii.hungerwarriorvendor.model.OrderItem;
import com.dakiiii.hungerwarriorvendor.viewmodel.FoodViewModel;

import java.util.List;

public class OrderItemsAdapter extends RecyclerView.Adapter<OrderItemsAdapter.OrderItemsViewHolder> {
    List<OrderItem> eOrderItems;
    FoodViewModel eFoodViewModel;

    public OrderItemsAdapter(FoodViewModel foodViewModel) {
        eFoodViewModel = foodViewModel;
    }

    @NonNull
    @Override
    public OrderItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderitem_row_item, parent, false);

        return new OrderItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemsViewHolder holder, int position) {
        OrderItem orderItem = eOrderItems.get(position);

        String foodName = orderItem.getFoodName();
        holder.eTextViewFoodName.setText(foodName);

        String quantity = String.valueOf(orderItem.getQuantity());
        holder.eTextViewQuantity.setText(quantity);

        String status = orderItem.getStatus();

        switch (status) {
            case "pending":

                break;
            default:
        }


    }

    @Override
    public int getItemCount() {
        if (eOrderItems != null) {
            return eOrderItems.size();
        }
        return 0;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        eOrderItems = orderItems;
        notifyDataSetChanged();
    }

    public class OrderItemsViewHolder extends RecyclerView.ViewHolder {
        private final TextView eTextViewFoodName;
        private final TextView eTextViewQuantity;
        private final Spinner eSpinner;
        FoodViewModel eFoodViewModel;

        public OrderItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            eTextViewFoodName = itemView.findViewById(R.id.textView_OrderItemName);
            eTextViewQuantity = itemView.findViewById(R.id.textView_OrderItemQty);
            eSpinner = itemView.findViewById(R.id.spinner_order_status);


            if (eSpinner != null) {
                eSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                        change status of food item
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(itemView.getContext()
                            , R.array.status_labels_array, android.R.layout.simple_spinner_item);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            eSpinner.setAdapter(adapter);
        }
    }
}
