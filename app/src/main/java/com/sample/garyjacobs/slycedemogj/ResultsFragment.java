package com.sample.garyjacobs.slycedemogj;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.sample.garyjacobs.slycedemogj.model.Category;
import com.squareup.picasso.Picasso;

/**
 * Created by garyjacobs on 5/19/17.
 */

public class ResultsFragment extends Fragment {
    private static final String PRODUCT_INFO_KEY = "PRODUCT_INFO_KEY";
    private static ResultsFragment resultsFragment;
    private RecyclerView recyclerView;
    private ImageView sourceImageView;

    private Category category;

    public static ResultsFragment getInstance(String args) {
        resultsFragment = new ResultsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(PRODUCT_INFO_KEY, args);
        resultsFragment.setArguments(bundle);
        return resultsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, null);

        sourceImageView = (ImageView) view.findViewById(R.id.source_image);

        recyclerView = (RecyclerView) view.findViewById(R.id.results_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutCompat.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        Gson gson = new Gson();
        Bundle bundle = getArguments();
        category = gson.fromJson((String) bundle.get(PRODUCT_INFO_KEY), Category.class);
        Picasso.with(getActivity())
                .load(category.getImageURL())
                .placeholder(R.drawable.placeholder_slyce)
                .into(sourceImageView);
        recyclerView.setAdapter(new ProductListAdapter(category.getProducts()));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
