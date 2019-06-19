package com.francisco.characters.data.domain;

import android.os.Parcel;
import android.os.Parcelable;

class Item implements Parcelable {

    private String resourceURI;
    private String name;
    private String role;

    protected Item(Parcel in) {
        resourceURI = in.readString();
        name = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public String getResourceURI() {
        return resourceURI;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(resourceURI);
        dest.writeString(name);
        dest.writeString(role);
    }
}
