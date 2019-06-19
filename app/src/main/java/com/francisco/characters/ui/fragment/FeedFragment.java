package com.francisco.characters.ui.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.francisco.characters.AppController;
import com.francisco.characters.R;
import com.francisco.characters.databinding.FragmentFeedBinding;
import com.francisco.characters.ui.adapter.FeedListAdapter;
import com.francisco.characters.ui.viewmodel.FeedViewModel;
import com.francisco.characters.utill.NetworkState;
import com.google.android.material.snackbar.Snackbar;

public class FeedFragment extends Fragment {

    private FeedListAdapter adapter;
    private FragmentFeedBinding binding;
    private FeedViewModel feedViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_feed,
                container,
                false);

        feedViewModel = new FeedViewModel(AppController.create(getContext()));

        binding.listFeed.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new FeedListAdapter(getContext());

        feedViewModel.getArticleLiveData().observe(this, pagedList ->
                adapter.submitList(pagedList));

        feedViewModel.getNetworkState().observe(this, networkState -> {

            if (networkState.getStatus().equals(NetworkState.Status.RUNNING)) {
                binding.progressBar.setVisibility(View.VISIBLE);
            } else {
                binding.progressBar.setVisibility(View.GONE);
            }

            if (networkState.getStatus().equals(NetworkState.Status.FAILED)) {
                Snackbar.make(
                        getView().getRootView(),
                        getString(R.string.network_err),
                        Snackbar.LENGTH_LONG
                ).show();

                return;
            }
            adapter.setNetworkState(networkState);
        });


        binding.listFeed.setAdapter(adapter);

        return binding.getRoot();
    }
}
