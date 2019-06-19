package com.francisco.characters.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.francisco.characters.R;
import com.francisco.characters.databinding.PhotoGalleryItemBinding;

import java.util.List;

public class PhotoGalleryPageAdapter extends PagerAdapter {

    private Context context;
    private List<String> urls;
    private LayoutInflater mLayoutInflater;

    public PhotoGalleryPageAdapter(Context context, List<String> urls) {
        this.context = context;
        this.urls = urls;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ImageView) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final PhotoGalleryItemBinding itemView =
                DataBindingUtil.inflate(
                        mLayoutInflater,
                        R.layout.photo_gallery_item,
                        container,
                        false);

        ImageView image = itemView.galleryPhotoItem;

        Glide.with(context)
                .load(urls.get(position))
                .apply(
                        new RequestOptions()
                                .placeholder(R.drawable.placeholder)
                                .centerCrop()
                )
                .into(image);

        if (image.getParent() != null) {
            ((ViewGroup) image.getParent()).removeView(image);
        }
        container.addView(image);

        return image;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }
}
