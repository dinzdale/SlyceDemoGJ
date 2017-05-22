package com.sample.garyjacobs.slycedemogj;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sample.garyjacobs.slycedemogj.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by garyjacobs on 5/20/17.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ListViewHolder> {
    private List<Product> products;

    public ProductListAdapter(List<Product> products) {
        this.products = products;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View newItemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_item, parent, false);

        return new ListViewHolder(newItemView);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {

        Product product = products.get(position);
        holder.product_name.setText(product.getProductName());
        holder.product_price.setText(product.getProductPrice());
        holder.product_link.setText(product.getProductURL());
        try {
            Picasso.with(holder.product_name.getContext())
                    .load(product.getProductImageURL())
                    .placeholder(R.drawable.placeholder_slyce)
                    .error(R.drawable.placeholder_slyce)
                    .into(holder.product_image);
        }
        catch (IllegalArgumentException iae) {
            // sometimes image url is empty ... check ?
            iae.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}

class ListViewHolder extends RecyclerView.ViewHolder {
    public ImageView product_image;
    public TextView product_name;
    public TextView product_price;
    public TextView product_link;

    public ListViewHolder(View itemView) {
        super(itemView);
        product_name = (TextView) itemView.findViewById(R.id.product_name);
        product_price = (TextView) itemView.findViewById(R.id.product_price);
        product_image = (ImageView) itemView.findViewById(R.id.product_image);
        product_link = (TextView) itemView.findViewById(R.id.product_link);
    }
}