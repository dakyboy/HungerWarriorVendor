package com.dakiiii.hungerwarriorvendor.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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

        /*private TextView eTextViewCustomerName;
        private TextView eTextViewOrderid;
        private TextView eTextViewOrderTotal;
        private TextView eTextViewOrderDate;
        private Button eButtonRejectOrder;
        private Button eButtonAcceptOrder;*/
        Order order = eOrders.get(position);
        holder.eTextViewOrderid.setText(String.valueOf(order.getOrderId()));
        holder.eTextViewCustomerName.setText(order.getCustomerId());
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
        private TextView eTextViewCustomerName;
        private TextView eTextViewOrderid;
        private TextView eTextViewOrderTotal;
        private TextView eTextViewOrderDate;
        private Button eButtonRejectOrder;
        private Button eButtonAcceptOrder;

        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);

            eTextViewCustomerName = itemView.findViewById(R.id.textView_Order_customer_name);
            eTextViewOrderid = itemView.findViewById(R.id.textView_Order_OrderId);
            eTextViewOrderTotal = itemView.findViewById(R.id.textView_Order_Total);
            eTextViewOrderDate = itemView.findViewById(R.id.textView_Order_time);
            eButtonAcceptOrder = itemView.findViewById(R.id.button_Order_accept);
            eButtonRejectOrder = itemView.findViewById(R.id.button_Order_reject);

        }
    }
}
