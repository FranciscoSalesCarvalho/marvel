package com.francisco.characters.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.francisco.characters.R;
import com.francisco.characters.databinding.FragmentComicsBinding;
import com.francisco.characters.ui.dialog.PhotoGalleryDialog;
import com.francisco.characters.ui.viewmodel.ComicsViewModel;

public class ComicsFragment extends Fragment {

    private ComicsViewModel viewmodel;
    private FragmentComicsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_comics,
                container,
                false);

        viewmodel = ViewModelProviders.of(this).get(ComicsViewModel.class);

        binding.setLifecycleOwner(this);

        final ComicsFragmentArgs args = ComicsFragmentArgs.fromBundle(getArguments());

        viewmodel.fetchComics(args.getCharacterId());
        watchImages();

        return binding.getRoot();
    }

    private void watchImages() {
        viewmodel.getImages().observe(this, thumbnails -> {
            PhotoGalleryDialog galleryDialog = PhotoGalleryDialog.newInstance(thumbnails);
            galleryDialog.show(getFragmentManager(), "GALLERY");
        });
    }
}
