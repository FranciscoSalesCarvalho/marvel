package com.francisco.characters.data.domain;

import android.os.Parcel;
import android.os.Parcelable;

class Date implements Parcelable {

    private String type;
    private String date;

    protected Date(Parcel in) {
        type = in.readString();
        date = in.readString();
    }

    public static final Creator<Date> CREATOR = new Creator<Date>() {
        @Override
        public Date createFromParcel(Parcel in) {
            return new Date(in);
        }

        @Override
        public Date[] newArray(int size) {
            return new Date[size];
        }
    };

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(date);
    }
}
