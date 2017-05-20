package com.sample.garyjacobs.slycedemogj;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sample.garyjacobs.slycedemogj.model.Product;

import java.util.ArrayList;

/**
 * Created by garyjacobs on 5/20/17.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ListViewHolder>{
    private ArrayList<Product> products;

    public ProductListAdapter(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {

        Product product = products.get(position);
        holder.productName.setText(product.getProductName());
        holder.productPrice.setText(product.getProductPrice());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
 class ListViewHolder extends RecyclerView.ViewHolder {
    public TextView productName, productPrice;
    public ImageView productImage;

    public ListViewHolder(View itemView) {
        super(itemView);
        productName = (TextView)itemView.findViewById(R.id.product_name);
        productPrice= (TextView)itemView.findViewById(R.id.product_price);
        productImage= (ImageView)itemView.findViewById(R.id.product_image);
    }
}