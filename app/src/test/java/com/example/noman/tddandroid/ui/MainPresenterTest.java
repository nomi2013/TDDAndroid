package com.example.noman.tddandroid.ui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Created by noman on 15/2/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    private MainView view;

    private MainPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new MainPresenter();
        presenter.attachView(view);
    }

    @Test
    public void presenterNotNull() throws Exception {
        assertNotNull(presenter);
    }

    @Test
    public void viewNotNull() throws Exception {
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void attachView() throws Exception {
    }

    @Test
    public void whenTokenEmpty() throws Exception {

        // if presenter needs data, mock fake data
        Mockito.when(view.getToken()).thenReturn("");

        // unit test
        presenter.getData();

        Mockito.verify(view).emptyToken("Token is empty.");

    }

    @Test
    public void whenTokenNonEmpty() throws Exception {

        // if presenter needs data, mock fake data
        Mockito.when(view.getToken()).thenReturn("abcd");

        // unit test
        presenter.getData();

        // verify presenter calls those methods.
        Mockito.verify(view).emptyNonToken("Token is non empty.");

    }


}
