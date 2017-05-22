package com.sample.garyjacobs.slycedemogj;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sample.garyjacobs.slycedemogj.model.Category;
import com.squareup.picasso.Picasso;

/**
 * Created by garyjacobs on 5/19/17.
 */

public class ResultsFragment extends Fragment {

    private static final String PRODUCT_INFO_KEY = "PRODUCT_INFO_KEY";
    private static  ResultsFragment resultsFragment;


    private RecyclerView recyclerView;
    private ImageView sourceImageView;

    private Category category;

    public static ResultsFragment getInstance(String args) {
        // Create ResultsFragment
        resultsFragment = new ResultsFragment();

        // Load it's arguments (Category/Product data string)
        Bundle bundle = new Bundle();
        bundle.putString(PRODUCT_INFO_KEY, args);
        resultsFragment.setArguments(bundle);

        // Return created fragment
        return resultsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Get the results view
        View view = inflater.inflate(R.layout.fragment_results, null);

        sourceImageView = (ImageView) view.findViewById(R.id.source_image);

        recyclerView = (RecyclerView) view.findViewById(R.id.results_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutCompat.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        // Convert data string to POJO objects
        Gson gson = new Gson();
        Bundle bundle = getArguments();
        category = gson.fromJson((String) bundle.get(PRODUCT_INFO_KEY), Category.class);

        // Set list adapter
        recyclerView.setAdapter(new ProductListAdapter(category.getProducts()));

        // Set header category label
        TextView category_tv = (TextView) view.findViewById(R.id.category);
        category_tv.setText(category.getCategoryLabel());

        // Fetch user snapshot
        Picasso.with(getActivity())
                .load(category.getImageURL())
                .placeholder(R.drawable.placeholder_slyce)
                .into(sourceImageView);

        return view;
    }

}
