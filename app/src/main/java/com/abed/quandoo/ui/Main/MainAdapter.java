package com.abed.quandoo.ui.Main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abed.quandoo.R;
import com.abed.quandoo.data.model.Customer;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {

    private ViewHolderClicks clicksListener;
    private List<Customer> customers;

    @Inject
    public MainAdapter() {
    }

    public void setClicksListener(ViewHolderClicks clicksListener) {
        this.clicksListener = clicksListener;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title_subtitle, parent, false);
        return new CustomViewHolder(view, clicksListener);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        if (customers == null || customers.size() == 0) {
            return;
        }
        holder.setCustomer(customers.get(position));
    }

    @Override
    public int getItemCount() {
        if (customers == null) {
            return 0;
        }
        return customers.size();
    }

    public void updateList(List<Customer> customers) {
        this.customers = customers;
        notifyDataSetChanged();
    }


    public static class CustomViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.txt_title)
        TextView txt_name;
        Customer customer;

        public CustomViewHolder(final View view, ViewHolderClicks clicksListener) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(v -> clicksListener.onItemClick(view, getLayoutPosition(), customer));
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
            txt_name.setText(customer.getCustomerFirstName() + " " + customer.getCustomerLastName());
        }
    }


    public interface ViewHolderClicks {
        void onItemClick(View view, int position, Customer customer);
    }

}
