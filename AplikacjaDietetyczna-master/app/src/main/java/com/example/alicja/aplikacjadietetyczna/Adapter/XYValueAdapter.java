package com.example.alicja.aplikacjadietetyczna.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alicja.aplikacjadietetyczna.R;
import com.example.alicja.aplikacjadietetyczna.Objects.XYValue;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XYValueAdapter extends RecyclerView.Adapter<XYValueAdapter.ViewHolder> {
    ArrayList<XYValue> xyValues;
    Context context;
    XYValue xyValue=new XYValue();
    public XYValueAdapter(ArrayList<XYValue> list, Context context) {
        this.xyValues = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.xyitem, null);
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.date_txt.setText(String .valueOf(XYValue.getDate(xyValues.get(position).getX(),"dd-MM-yyyy")));
        holder.weight_txt.setText(String.format("%s kg", String.valueOf(xyValues.get(position).getY())));
        holder.progress_txt.setText(String.format("%s kg", String.valueOf(xyValue.returnOdds(xyValues, position))));
    }


    @Override
    public int getItemCount() {
        return xyValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_date)
        TextView date_txt;
        @BindView(R.id.item_weight)
        TextView weight_txt;
        @BindView(R.id.text_progress)
        TextView progress_txt;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
