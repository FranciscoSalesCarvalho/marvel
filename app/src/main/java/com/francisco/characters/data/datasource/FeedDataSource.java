package com.francisco.characters.data.datasource;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.francisco.characters.AppController;
import com.francisco.characters.data.domain.Result;
import com.francisco.characters.utill.NetworkState;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("CheckResult")
public class FeedDataSource extends PageKeyedDataSource<Long, Result> implements BaseConstants {

    private static final String TAG = FeedDataSource.class.getSimpleName();

    private AppController appController;

    private MutableLiveData networkState;
    private MutableLiveData initialLoading;

    public FeedDataSource(AppController appController) {
        this.appController = appController;

        networkState = new MutableLiveData();
        initialLoading = new MutableLiveData();
    }

    public MutableLiveData getNetworkState() {
        return networkState;
    }

    public MutableLiveData getInitialLoading() {
        return initialLoading;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params,
                            @NonNull LoadInitialCallback<Long, Result> callback) {

        initialLoading.postValue(NetworkState.LOADING);
        networkState.postValue(NetworkState.LOADING);

        appController.getRestApi().fetchCharacters(TS, API_KEY, HASH, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    callback.onResult(response.getData().getResults(), null, 2l);
                    initialLoading.postValue(NetworkState.LOADED);
                    networkState.postValue(NetworkState.LOADED);
                }, err -> {
                    initialLoading.postValue(
                            new NetworkState(NetworkState.Status.FAILED,
                                    err.getMessage()));
                    String errorMessage = err == null ? "unknown error" : err.getMessage();
                    networkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params,
                           @NonNull LoadCallback<Long, Result> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params,
                          @NonNull LoadCallback<Long, Result> callback) {

        Log.i(TAG, "Loading Rang " + params.key + " Count " + params.requestedLoadSize);

        networkState.postValue(NetworkState.LOADING);

        appController.getRestApi().fetchCharacters(TS, API_KEY, HASH, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    long nextKey = (params.key == response.getData().getTotal()) ?
                            null : params.key + 1;
                    callback.onResult(response.getData().getResults(), nextKey);
                    networkState.postValue(NetworkState.LOADED);
                }, err -> {
                    initialLoading.postValue(
                            new NetworkState(NetworkState.Status.FAILED,
                                    err.getMessage()));
                    String errorMessage = err == null ? "unknown error" : err.getMessage();
                    networkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
                });
    }
}