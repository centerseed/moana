package com.moana.carsharing;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moana.carsharing.base.AbstractRecyclerCursorAdapter;
import com.moana.carsharing.station.StationProvider;

public class SiteSearchResultAdapter extends AbstractRecyclerCursorAdapter {

    ResultAdapterListener mListener;

    public interface ResultAdapterListener {
        void onResultClick(String snippet);
    }

    public SiteSearchResultAdapter(Context context, Cursor c) {
        super(context, c);
    }

    public void setSiteSearchResultAdapterListener(ResultAdapterListener listener) {
        mListener = listener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, Cursor cursor) {
        SiteViewHolder holder = (SiteViewHolder) viewHolder;
        holder.tvName.setText(cursor.getString(cursor.getColumnIndex(StationProvider.FIELD_STATION_NAME)));
        holder.tvAddress.setText(cursor.getString(cursor.getColumnIndex(StationProvider.FIELD_STATION_ADDRESS)));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = m_inflater.inflate(R.layout.listitem_site_search_result, parent, false);
        return new SiteViewHolder(v);
    }

    public class SiteViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvAddress;

        public SiteViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.name);
            tvAddress = (TextView) itemView.findViewById(R.id.address);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        Cursor cursor = (Cursor) getItem(getAdapterPosition());
                        mListener.onResultClick(cursor.getString(cursor.getColumnIndex(StationProvider.FIELD_STATION_ADDRESS)));
                    }
                }
            });
        }
    }
}
