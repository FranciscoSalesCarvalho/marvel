package com.francisco.characters.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.francisco.characters.R;
import com.francisco.characters.databinding.PhotoGalleryDialogBinding;
import com.francisco.characters.ui.adapter.PhotoGalleryPageAdapter;

import java.util.ArrayList;
import java.util.List;

public class PhotoGalleryDialog extends DialogFragment {

    private PhotoGalleryDialog(){}

    private PhotoGalleryDialogBinding binding;
    private List<String> images;

    private static PhotoGalleryDialog sInstance;

    public static PhotoGalleryDialog newInstance(List<String> thumbnails) {
        if (sInstance == null)
            sInstance = new PhotoGalleryDialog();

        Bundle args = new Bundle();
        args.putStringArrayList("images", (ArrayList<String>) thumbnails);
        sInstance.setArguments(args);

        return sInstance;
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(STYLE_NO_TITLE);



        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(
                        getContext()),
                        R.layout.photo_gallery_dialog,
                        null,
                        false);

        assert getArguments() != null;
        images = getArguments().getStringArrayList("images");

        configPhotoGallery(images);

        return new AlertDialog.Builder(getActivity())
                .setView(binding.getRoot())
                .create();
    }

    private void configPhotoGallery(List<String> images) {
        binding.photoGallery.setAdapter(new PhotoGalleryPageAdapter(getContext(), images));
        binding.dots.setupWithViewPager(binding.photoGallery, true);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        getActivity().onBackPressed();
    }
}
