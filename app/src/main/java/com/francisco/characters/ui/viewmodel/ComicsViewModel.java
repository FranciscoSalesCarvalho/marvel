package com.francisco.characters.ui.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.francisco.characters.AppController;
import com.francisco.characters.data.datasource.BaseConstants;
import com.francisco.characters.data.domain.Result;
import com.francisco.characters.data.domain.Thumbnail;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ComicsViewModel extends AndroidViewModel implements BaseConstants {

    private CompositeDisposable disposable;
    private AppController controller;
    private MutableLiveData<List<String>> images;
    private MutableLiveData<Boolean> loading;
    private MutableLiveData<Boolean> err;

    public ComicsViewModel(Application application) {
        super(application);
        disposable = new CompositeDisposable();
        controller = AppController.create(application);
        images = new MutableLiveData<>();
        loading = new MutableLiveData<>();
        err = new MutableLiveData<>();
    }

    public void fetchComics(long characterId) {
        loading.postValue(true);
        disposable.add(
                controller.getRestApi().fetchComics(characterId, TS, API_KEY, HASH)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(response -> {
                            loading.postValue(false);
                            images.postValue(mapImagesUrls(response.getData().getResults()));
                        }, erro -> {
                            loading.postValue(false);
                            err.postValue(true);
                        })
        );
    }

    private List<String> mapImagesUrls(List<Result> results) {
        List<String> urls = new ArrayList<>();
        for (Result result : results) {
            for (Thumbnail thumbnail : result.getImages()) {
                urls.add(
                        thumbnail.getPath().concat(".").concat(thumbnail.getExtension())
                );
            }
        }

        return urls;
    }

    public LiveData<List<String>> getImages() {
        return images;
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    public LiveData<Boolean> getErr() {
        return err;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
