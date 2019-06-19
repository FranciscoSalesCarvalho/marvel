package com.francisco.characters.ui.viewmodel;

import com.francisco.characters.data.rest.RestApi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.disposables.CompositeDisposable;

@RunWith(MockitoJUnitRunner.class)
public class ComicsViewModelTest {

    @Mock
    private ComicsViewModel viewModel;

    @Mock
    private RestApi controller;

    @Mock
    private CompositeDisposable disposable;

    @Test
    public void should_FetchComics() {

    }
}