package com.francisco.characters.data.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class Result implements Parcelable {

    private long id;
    private long digitalId;
    private String title;
    private int issueNumber;
    private String name;
    private String variantDescription;
    private String description;
    private String modified;
    private String isbn;
    private String upc;
    private String diamondCode;
    private String ean;
    private String issn;
    private String format;
    private int pageCount;
    private List<TextObject> textObjects;
    private String resourceURI;
    private Thumbnail thumbnail;
    private Inner comics;
    private Inner series;
    private List<Object> variants;
    private List<Object> collections;
    private List<Object> collectedIssues;
    private List<Date> dates;
    private List<Price> prices;
    private List<Thumbnail> images;
    private Inner creators;
    private Inner stories;
    private Inner characters;
    private Inner events;
    private List<Url> urls;

    protected Result(Parcel in) {
        id = in.readLong();
        name = in.readString();
        description = in.readString();
        modified = in.readString();
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    public long getId() {
        return id;
    }

    public long getDigitalId() {
        return digitalId;
    }

    public String getTitle() {
        return title;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public String getVariantDescription() {
        return variantDescription;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getModified() {
        return modified;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getUpc() {
        return upc;
    }

    public String getDiamondCode() {
        return diamondCode;
    }

    public String getEan() {
        return ean;
    }

    public String getIssn() {
        return issn;
    }

    public String getFormat() {
        return format;
    }

    public int getPageCount() {
        return pageCount;
    }

    public List<TextObject> getTextObjects() {
        return textObjects;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public Inner getComics() {
        return comics;
    }

    public Inner getSeries() {
        return series;
    }

    public Inner getStories() {
        return stories;
    }

    public List<Object> getVariants() {
        return variants;
    }

    public List<Object> getCollections() {
        return collections;
    }

    public List<Object> getCollectedIssues() {
        return collectedIssues;
    }

    public List<Date> getDates() {
        return dates;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public List<Thumbnail> getImages() {
        return images;
    }

    public Inner getEvents() {
        return events;
    }

    public Inner getCreators() {
        return creators;
    }

    public Inner getCharacters() {
        return characters;
    }

    public List<Url> getUrls() {
        return urls;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this) return true;

        Result result= (Result) obj;
        return result.id == this.id;
    }

    public static DiffUtil.ItemCallback<Result> DIFF_CALLBACK = new DiffUtil.ItemCallback<Result>() {
        @Override
        public boolean areItemsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
