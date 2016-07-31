package com.abed.quandoo.ui.Tables;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abed.quandoo.R;
import com.abed.quandoo.data.model.Table;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TablesAdapter extends RecyclerView.Adapter<TablesAdapter.CustomViewHolder> {

    private List<Table> tables;
    private ViewHolderClicks clicksListener;


    @Inject
    public TablesAdapter() {
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table, parent, false);
        return new CustomViewHolder(view, clicksListener);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        if (tables == null || tables.size() == 0) {
            return;
        }
        holder.setTable(tables.get(position));
    }

    @Override
    public int getItemCount() {
        if (tables == null) {
            return 0;
        }
        return tables.size();
    }

    public void updateList(List<Table> tables) {
        this.tables = tables;
        notifyDataSetChanged();
    }

    public void setClicksListener(ViewHolderClicks clicksListener) {
        this.clicksListener = clicksListener;
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_title)
        TextView txt_title;

        @BindView(R.id.hex_img_view)
        ImageView hex_img_view;

        Table table;

        public CustomViewHolder(final View view, ViewHolderClicks clicksListener) {
            super(view);
            ButterKnife.bind(this, view);
            hex_img_view.setOnClickListener(v -> clicksListener.onItemClick(view, getLayoutPosition(), table));
        }


        public void setTable(Table table) {
            this.table = table;
            if (table.isReserved()) {
                hex_img_view.setImageDrawable(ContextCompat.getDrawable(hex_img_view.getContext(), R.drawable.red));
                txt_title.setText("#" + table.getId() + " Reserved");
            } else {
                hex_img_view.setImageDrawable(ContextCompat.getDrawable(hex_img_view.getContext(), R.drawable.grey));
                txt_title.setText("#" + table.getId() + " Available");
            }
        }


    }

    public interface ViewHolderClicks {
        void onItemClick(View view, int position, Table table);
    }
}
