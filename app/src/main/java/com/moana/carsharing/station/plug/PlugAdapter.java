package com.moana.carsharing.station.plug;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moana.carsharing.R;
import com.moana.carsharing.SiteSearchResultAdapter;
import com.moana.carsharing.base.AbstractRecyclerCursorAdapter;
import com.moana.carsharing.station.StationProvider;

public class PlugAdapter extends AbstractRecyclerCursorAdapter {
    PlugAdapter.ResultAdapterListener mListener;
    String mPlugStatus[];

    public interface ResultAdapterListener {
        void onPlugClick(Cursor cursor);
    }

    public void setPlugAdapterListener(PlugAdapter.ResultAdapterListener listener) {
        mListener = listener;
    }

    public PlugAdapter(Context context, Cursor c) {
        super(context, c);
        mPlugStatus = context.getResources().getStringArray(R.array.plug_status);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, Cursor cursor) {
        PlugViewHolder holder = (PlugViewHolder) viewHolder;
        int status = cursor.getInt(cursor.getColumnIndex(StationProvider.FIELD_PLUG_STATUS));
        holder.tvStatus.setText(mPlugStatus[status]);
        holder.tvStatus.setTextColor(getStatusTextColor(status));
        holder.plug.setImageResource(getStatusResource(status));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = m_inflater.inflate(R.layout.listitem_plug, parent, false);
        return new PlugAdapter.PlugViewHolder(v);
    }

    public class PlugViewHolder extends RecyclerView.ViewHolder {
        TextView tvStatus;
        ImageView plug;

        public PlugViewHolder(View itemView) {
            super(itemView);
            tvStatus = (TextView) itemView.findViewById(R.id.status);
            plug = (ImageView) itemView.findViewById(R.id.plug);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        Cursor cursor = (Cursor) getItem(getAdapterPosition());
                        mListener.onPlugClick(cursor);
                    }
                }
            });
        }
    }

    private int getStatusResource(int status) {
        switch (status) {
            case StationProvider.PLUG_STATUS_AVAILABLE:
                return R.mipmap.icon_evinfo_no_green;
            case StationProvider.PLUG_STATUS_IN_USAGE:
                return R.mipmap.icon_evinfo_no_red;
            default:
                return R.mipmap.bk_evinfo_ev_gray;
        }
    }

    private int getStatusTextColor(int status) {
        switch (status) {
            case StationProvider.PLUG_STATUS_AVAILABLE:
                return m_context.getResources().getColor(R.color.colorPlugAvailable);
            case StationProvider.PLUG_STATUS_IN_USAGE:
                return m_context.getResources().getColor(R.color.colorInUsage);
            default:
                return m_context.getResources().getColor(R.color.colorDarkGrey);
        }
    }
}
