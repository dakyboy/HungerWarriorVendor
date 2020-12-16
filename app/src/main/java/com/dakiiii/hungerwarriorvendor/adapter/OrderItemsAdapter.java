package com.dakiiii.hungerwarriorvendor.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.dakiiii.hungerwarriorvendor.R;
import com.dakiiii.hungerwarriorvendor.model.OrderItem;
import com.dakiiii.hungerwarriorvendor.viewmodel.OrderItemViewModel;

import java.util.List;

public class OrderItemsAdapter extends RecyclerView.Adapter<OrderItemsAdapter.OrderItemsViewHolder> {
    List<OrderItem> eOrderItems;
    OrderItemViewModel eOrderItemViewModel;
    MutableLiveData<String> eStringMutableLiveData = new MutableLiveData<>();
    LifecycleOwner eLifecycleOwner;

    public OrderItemsAdapter(OrderItemViewModel orderItemViewModel, LifecycleOwner lifecycleOwner) {
        eOrderItemViewModel = orderItemViewModel;
        eLifecycleOwner = lifecycleOwner;

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

        String orderItemStatus = orderItem.getStatus();
        switch (orderItemStatus) {
            case "pending":
                holder.eSpinner.setSelection(0);
                break;
            case "preparing":
                holder.eSpinner.setSelection(1);
                break;
            case "cancelled":
                holder.eSpinner.setSelection(3);
                break;
            case "completed":
                holder.eSpinner.setSelection(2);
                break;
            default:

        }

        holder.eTextViewStatus.setText(orderItemStatus);



    /*    eOrderItemViewModel.getOrderItemStatus(orderItem.getId()).observe(eLifecycleOwner, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                holder.eTextViewStatus.setText(s);

                switch (s) {
                    case "pending":
                        holder.eSpinner.setSelection(0);
                        break;
                    case "preparing":
                        holder.eSpinner.setSelection(1);
                        break;
                    case "cancelled":
                        holder.eSpinner.setSelection(3);
                        break;
                    case "completed":
                        holder.eSpinner.setSelection(2);
                        break;
                    default:

                }
            }
        });*/


        if (holder.eSpinner != null) {
            holder.eSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                        change orderItemStatus of food item
                    String status = parent.getItemAtPosition(position).toString();

                    if (!status.equals(orderItemStatus)) {

                        eOrderItemViewModel.setOrderItemStatus(orderItem.getId(), status);

                        Log.d("keppa", status);
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
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
        private final TextView eTextViewStatus;
        private final Spinner eSpinner;

        public OrderItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            eTextViewFoodName = itemView.findViewById(R.id.textView_OrderItemName);
            eTextViewQuantity = itemView.findViewById(R.id.textView_OrderItemQty);
            eSpinner = itemView.findViewById(R.id.spinner_order_status);
            eTextViewStatus = itemView.findViewById(R.id.textViewStatus);

            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(itemView.getContext()
                            , R.array.status_labels_array, android.R.layout.simple_spinner_item);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            eSpinner.setAdapter(adapter);
        }
    }
}
