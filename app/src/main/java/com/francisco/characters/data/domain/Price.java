package com.francisco.characters.data.domain;

import android.os.Parcel;
import android.os.Parcelable;

class Price implements Parcelable {

    private String type;
    private double price;

    protected Price(Parcel in) {
        type = in.readString();
        price = in.readDouble();
    }

    public static final Creator<Price> CREATOR = new Creator<Price>() {
        @Override
        public Price createFromParcel(Parcel in) {
            return new Price(in);
        }

        @Override
        public Price[] newArray(int size) {
            return new Price[size];
        }
    };

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeDouble(price);
    }
}
