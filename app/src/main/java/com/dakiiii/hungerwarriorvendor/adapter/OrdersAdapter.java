package com.dakiiii.hungerwarriorvendor.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dakiiii.hungerwarriorvendor.R;
import com.dakiiii.hungerwarriorvendor.model.Order;
import com.dakiiii.hungerwarriorvendor.ui.ViewOrderItemsActivity;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = dateFormat.parse(order.getOrderedOn());
            holder.eTextViewOrderDate.setText(dateFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }


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
        private final TextView eTextViewOrderId;
        private final TextView eTextViewOrderDate;

        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);

            eTextViewOrderId = itemView.findViewById(R.id.textView_Order_OrderId);
            eTextViewOrderDate = itemView.findViewById(R.id.textView_Order_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Order order = eOrders.get(getAdapterPosition());
                    Intent intent = new Intent(itemView.getContext(), ViewOrderItemsActivity.class);
                    intent.putExtra(ViewOrderItemsActivity.ORDER_EXTRA, new Gson().toJson(order));
                    itemView.getContext().startActivity(intent);

                }
            });

        }
    }
}
