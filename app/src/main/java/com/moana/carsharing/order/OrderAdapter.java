package com.moana.carsharing.order;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moana.carsharing.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    Context mContext;
    ArrayList<OrderItem> mItems = new ArrayList<>();
    LayoutInflater mInflater;

    public OrderAdapter(Context context) {
        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.listitem_order, parent, false);
        return new OrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        OrderItem item = mItems.get(position);
        holder.serial.setText(item.mSerial);
        holder.time.setText(item.mOrderTime);
        holder.status.setText(item.mStatus);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {

        public TextView serial;
        public TextView time;
        public TextView status;

        public OrderViewHolder(View itemView) {
            super(itemView);
            serial = (TextView) itemView.findViewById(R.id.order_serial);
            time = (TextView) itemView.findViewById(R.id.reserve_time);
            status = (TextView) itemView.findViewById(R.id.status);
        }
    }

    public void reset() {
        mItems.clear();
    }

    public void notifyDataChanged() {
        Collections.sort(mItems, new Comparator<OrderItem>() {
            @Override
            public int compare(OrderItem orderItem, OrderItem t1) {
                return orderItem.mStatus.equals("完成") ? 1 : -1;
            }
        });
        notifyDataSetChanged();
    }

    public void addCarItem(String serial, String orderTime, String status) {
        addOrderItem(serial, orderTime, status, true);
    }

    public void addPlugItem(String serial, String orderTime, String status) {
        addOrderItem(serial, orderTime, status, false);
    }

    private void addOrderItem(String serial, String orderTime, String status, boolean isCar) {
        OrderItem item = new OrderItem(serial, orderTime, status, isCar);
        mItems.add(item);
    }


    public class OrderItem {
        public String mSerial;
        public String mOrderTime;
        public String mStatus;
        boolean isCar;

        public OrderItem(String serial, String orderTime, String status, boolean isCar) {
            mSerial = serial;
            mOrderTime = orderTime;
            mStatus = status;

            this.isCar = isCar;
        }
    }
}
