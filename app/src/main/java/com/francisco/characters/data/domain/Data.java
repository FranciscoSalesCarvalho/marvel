package com.francisco.characters.data.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Data implements Parcelable {

    private int offset;
    private int limit;
    private long total;
    private int count;
    private List<Result> results;

    protected Data(Parcel in) {
        offset = in.readInt();
        limit = in.readInt();
        total = in.readLong();
        count = in.readInt();
        results = in.createTypedArrayList(Result.CREATOR);
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public long getTotal() {
        return total;
    }

    public int getCount() {
        return count;
    }

    public List<Result> getResults() {
        return results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(offset);
        dest.writeInt(limit);
        dest.writeLong(total);
        dest.writeInt(count);
        dest.writeTypedList(results);
    }
}

