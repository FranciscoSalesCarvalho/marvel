package com.francisco.characters.data.domain;

import android.os.Parcel;
import android.os.Parcelable;

class TextObject implements Parcelable {

    private String type;
    private String language;
    private String text;

    protected TextObject(Parcel in) {
        type = in.readString();
        language = in.readString();
        text = in.readString();
    }

    public static final Creator<TextObject> CREATOR = new Creator<TextObject>() {
        @Override
        public TextObject createFromParcel(Parcel in) {
            return new TextObject(in);
        }

        @Override
        public TextObject[] newArray(int size) {
            return new TextObject[size];
        }
    };

    public String getType() {
        return type;
    }

    public String getLanguage() {
        return language;
    }

    public String getText() {
        return text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(language);
        dest.writeString(text);
    }
}
