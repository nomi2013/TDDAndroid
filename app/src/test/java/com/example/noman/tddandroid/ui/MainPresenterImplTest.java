package com.example.noman.tddandroid.ui;

import com.example.noman.tddandroid.data.ApiService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Created by noman on 15/2/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterImplTest {

    @Mock
    private MainView view;
    @Mock
    ApiService apiService;

    private MainPresenterImpl presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new MainPresenterImpl(view, apiService);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void notNullPresenter() throws Exception {
        assertNotNull(presenter);
    }

    @Test
    public void notNullMvpViews() throws Exception {
        assertNotNull(view);
    }

    @Test
    public void loadMain() throws Exception {
        presenter.loadMain();
        verify(view).makeToast("Nice..");
    }

}