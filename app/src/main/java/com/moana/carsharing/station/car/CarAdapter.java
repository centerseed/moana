package com.moana.carsharing.station.car;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.IconRoundCornerProgressBar;
import com.moana.carsharing.R;
import com.moana.carsharing.base.AbstractRecyclerCursorAdapter;
import com.moana.carsharing.station.StationProvider;

public class CarAdapter extends AbstractRecyclerCursorAdapter {
    CarAdapter.ResultAdapterListener mListener;
    String mPlugStatus[];

    public interface ResultAdapterListener {
        void onCarClick(Cursor cursor);
    }

    public void setCarAdapterListener(CarAdapter.ResultAdapterListener listener) {
        mListener = listener;
    }

    public CarAdapter(Context context, Cursor c) {
        super(context, c);
        mPlugStatus = context.getResources().getStringArray(R.array.plug_status);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, Cursor cursor) {
        CarViewHolder holder = (CarViewHolder) viewHolder;
        int charge = cursor.getInt(cursor.getColumnIndex(StationProvider.FIELD_CAR_CHARGE));
        holder.car.setColorFilter(getProgressColor(m_context, charge));
        holder.progressBar.invalidate();
        holder.progressBar.setProgress(charge);
        holder.progressBar.setProgressColor(getProgressColor(m_context, charge));

        holder.charge.setText(charge + "%");
        holder.charge.setTextColor(getProgressColor(m_context, charge));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = m_inflater.inflate(R.layout.listitem_car, parent, false);
        return new CarAdapter.CarViewHolder(v);
    }

    public class CarViewHolder extends RecyclerView.ViewHolder {
        TextView charge;
        ImageView car;
        IconRoundCornerProgressBar progressBar;

        public CarViewHolder(View itemView) {
            super(itemView);
            charge = (TextView) itemView.findViewById(R.id.charge);
            car = (ImageView) itemView.findViewById(R.id.car);
            progressBar = (IconRoundCornerProgressBar) itemView.findViewById(R.id.progressBar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        Cursor cursor = (Cursor) getItem(getAdapterPosition());
                        mListener.onCarClick(cursor);
                    }
                }
            });
        }
    }

    public static int getProgressColor(Context context, int progress) {
        if (progress > 20) return context.getResources().getColor(R.color.colorPlugAvailable);
        return context.getResources().getColor(R.color.colorInUsage);

    }
}
