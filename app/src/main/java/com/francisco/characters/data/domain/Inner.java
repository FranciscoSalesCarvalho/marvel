package com.francisco.characters.data.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

class Inner implements Parcelable {

    private int available;
    private String collectionURI;
    private List<Item> items;
    private int returned;

    protected Inner(Parcel in) {
        available = in.readInt();
        collectionURI = in.readString();
        returned = in.readInt();
    }

    public static final Creator<Inner> CREATOR = new Creator<Inner>() {
        @Override
        public Inner createFromParcel(Parcel in) {
            return new Inner(in);
        }

        @Override
        public Inner[] newArray(int size) {
            return new Inner[size];
        }
    };

    public int getAvailable() {
        return available;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public List<Item> getItems() {
        return items;
    }

    public int getReturned() {
        return returned;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(available);
        dest.writeString(collectionURI);
        dest.writeInt(returned);
    }
}
