package com.francisco.characters.data.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Answer implements Parcelable {

    private long code;
    private String status;
    private String copyright;
    private String attributionHTML;
    private String attributionText;
    private String etag;
    private Data data;

    protected Answer(Parcel in) {
        code = in.readLong();
        status = in.readString();
        copyright = in.readString();
        attributionHTML = in.readString();
        etag = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(code);
        dest.writeString(status);
        dest.writeString(copyright);
        dest.writeString(attributionHTML);
        dest.writeString(etag);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Answer> CREATOR = new Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };

    public long getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getAttributionHTML() {
        return attributionHTML;
    }

    public String getAttributionText() {
        return attributionText;
    }

    public String getEtag() {
        return etag;
    }

    public Data getData() {
        return data;
    }
}

