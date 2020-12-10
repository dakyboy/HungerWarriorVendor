package com.dakiiii.hungerwarriorvendor.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dakiiii.hungerwarriorvendor.R;
import com.dakiiii.hungerwarriorvendor.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder> {

    List<Order> eOrders = new ArrayList<>();

    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_row_item, parent, false);
        return new OrdersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, int position) {

        Order order = eOrders.get(position);
        holder.eTextViewOrderId.setText(String.valueOf(order.getOrderId()));

    }

    @Override
    public int getItemCount() {
        if (eOrders != null) {
            return eOrders.size();
        }
        return 0;
    }

    public void setOrders(List<Order> orders) {
        eOrders = orders;
        notifyDataSetChanged();
    }

    public class OrdersViewHolder extends RecyclerView.ViewHolder {
        private TextView eTextViewOrderId;
        private TextView eTextViewOrderDate;

        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);

            eTextViewOrderId = itemView.findViewById(R.id.textView_Order_OrderId);
            eTextViewOrderDate = itemView.findViewById(R.id.textView_Order_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), eOrders.get(getAdapterPosition()).getCustomerId(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
