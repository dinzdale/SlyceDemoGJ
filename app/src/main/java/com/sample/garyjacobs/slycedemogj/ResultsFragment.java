package com.sample.garyjacobs.slycedemogj;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.sample.garyjacobs.slycedemogj.model.Category;

/**
 * Created by garyjacobs on 5/19/17.
 */

public class ResultsFragment extends Fragment {
    private static final String PRODUCT_INFO_KEY = "PRODUCT_INFO_KEY";
    private static ResultsFragment resultsFragment;
    private RecyclerView recyclerView;
    private ImageView sourceImageView;

    private String productInfo;
    private Category category;
    public static ResultsFragment getInstance(String args) {
        if (resultsFragment == null) {
            resultsFragment = new ResultsFragment();
            Bundle bundle = new Bundle();
            bundle.putString(PRODUCT_INFO_KEY, args);
            resultsFragment.setArguments(bundle);
        }
        return resultsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, null);

        sourceImageView = (ImageView) view.findViewById(R.id.source_image);
        recyclerView = (RecyclerView) view.findViewById(R.id.results_list);

        Bundle bundle = getArguments();
        productInfo = (String) bundle.get(PRODUCT_INFO_KEY);

        Gson gson = new Gson();

        category = gson.fromJson(productInfo, Category.class);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();


    }
}
