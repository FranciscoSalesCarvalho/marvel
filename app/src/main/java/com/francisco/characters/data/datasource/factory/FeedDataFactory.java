package com.francisco.characters.data.datasource.factory;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.francisco.characters.AppController;
import com.francisco.characters.data.datasource.FeedDataSource;

public class FeedDataFactory extends DataSource.Factory {

    private MutableLiveData<FeedDataSource> mutableLiveData;
    private FeedDataSource feedDataSource;
    private AppController appController;

    public FeedDataFactory(AppController appController) {
        this.appController = appController;
        this.mutableLiveData = new MutableLiveData<FeedDataSource>();
    }

    @Override
    public DataSource create() {
        feedDataSource = new FeedDataSource(appController);
        mutableLiveData.postValue(feedDataSource);
        return feedDataSource;
    }


    public MutableLiveData<FeedDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
