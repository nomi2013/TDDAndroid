package com.example.noman.tddandroid.ui;

import com.example.noman.tddandroid.retro.ApiServices;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.Arrays;
import okhttp3.CacheControl;
import okhttp3.Request;
import okhttp3.mockwebserver.Dispatcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.subscribers.TestSubscriber;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by noman on 17/2/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest3 {
    @Mock
    private MainView mView;

    List<Topics> mResultList;
    MockWebServer mMockWebServer;
    TestSubscriber<List<Topics>> mSubscriber;


    MainPresenter mPresenter;

    Employee employee;

    @Before
    public void setUp() {
        Topics topics = new Topics(1, "Discern The Beach");
        Topics topicsTwo = new Topics(2, "Discern The Football Player");
        mResultList = new ArrayList();
        mResultList.add(topics);
        mResultList.add(topicsTwo);

        mMockWebServer = new MockWebServer();
        mSubscriber = new TestSubscriber<>();

        employee = new Employee();
        employee.setError(false);
        employee.setErrorDiscription("");
        employee.setStatusCode("200");

        Datum datumm = new Datum();

        datumm.setId(43243);
        datumm.setAddress("hjhjgjhkj");
        datumm.setName("jhgjhgjh");
        datumm.setCity("hgfghfhgfhghg");
        datumm.setDob("hjjhgjhgjhgjh");
        datumm.setOptedin(false);
        datumm.setWork("hghgjg");
        datumm.setEmail("dkjk");

        List<Datum> mList = new ArrayList<>();
        mList.add(datumm);

        employee.setData(mList);
        mPresenter = new MainPresenter();
        mPresenter.attachView(mView);

    }

    @Test
    public void serverCallWithError() {
        //Given
        String url = "dfdf/";
        mMockWebServer.enqueue(new MockResponse().setBody(new Gson().toJson(mResultList)));
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(mMockWebServer.url(url))
                .build();
        ApiServices remoteDataSource = retrofit.create(ApiServices.class);

        //When
        // remoteDataSource.getTopicsRx().subscribe(mSubscriber);

        //Then
        mSubscriber.assertNoErrors();
    }

    @Test
    public void severCallWithSuccessful() {
        try {

            //Given
        String url = "https://guessthebeach.herokuapp.com/api/";
        MockResponse mockResponse = new MockResponse();
        mockResponse.addHeader("Content-Type", "application/json; charset=utf-8");
        mockResponse.addHeader("Cache-Control", "no-cache");
        mockResponse.setBody("{}");
        mockResponse.throttleBody(1024,1, TimeUnit.SECONDS);
        // mockResponse.setBody(new Gson().toJson(mResultList)
        mMockWebServer.enqueue(mockResponse);

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(mMockWebServer.url(url))
                .build();

        ApiServices remoteDataSource = retrofit.create(ApiServices.class);

        Observable<List<Topics>> call = remoteDataSource.getTopicsRx();

        assertTrue(call.toString() != null);

        mSubscriber.assertNoErrors();


            mMockWebServer.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void apiCalls() {
        try {
            // Create a MockWebServer. These are lean enough that you can create a new
            // instance for every unit test.

            HttpUrl baseUrl = mMockWebServer.url("https://guessthebeach.herokuapp.com/api/topics/");
            mMockWebServer.url(baseUrl.toString());

            // Schedule some responses.
            MockResponse mockResponse = new MockResponse();

            // Start the server.
           // server.start();

            final Dispatcher dispatcher = new Dispatcher() {

                @Override
                public MockResponse dispatch(RecordedRequest request) throws InterruptedException {

                    if (request.getPath().equals("/v1/login/auth/")){
                        return new MockResponse().setResponseCode(200);
                    } else if (request.getPath().equals("v1/check/version/")){
                        return new MockResponse().setResponseCode(200).setBody("version=9");
                    } else if (request.getPath().equals("/v1/profile/info")) {
                        return new MockResponse().setResponseCode(200).setBody(new Gson().toJson(mResultList));
                    }
                    return new MockResponse().setResponseCode(404);
                }
            };

            mMockWebServer.setDispatcher(dispatcher);
            mMockWebServer.enqueue(mockResponse.setBody(new Gson().toJson(mResultList)));





            //  mMockWebServer.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateNoteRequest() throws Exception {
        mMockWebServer.shutdown();

        HttpUrl baseUrl = mMockWebServer.url("https://guessthebeach.herokuapp.com/api/topics");
        mMockWebServer.url(baseUrl.toString());

       // mMockWebServer.start();

        mMockWebServer.enqueue(new MockResponse());

        RecordedRequest request = mMockWebServer.takeRequest(10, TimeUnit.SECONDS);
        assertEquals("/topics", request.getPath());
        assertEquals("POST", request.getMethod());

    }

    @Test public void cacheControl() throws Exception {
        Request request = new Request.Builder()
                .cacheControl(new CacheControl.Builder().noCache().build())
                .url("https://guessthebeach.herokuapp.com/api/topics/")
                .build();
        assertEquals(Arrays.asList("no-cache"), request.headers("Cache-Control"));
    }



}

//    RecordedRequest recordedRequest = server.takeRequest();
//    assertEquals("POST", recordedRequest.getMethod());
//        assertEquals("def", recordedRequest.getBody().readUtf8());
//        assertEquals("3", recordedRequest.getHeader("Content-Length"));
//        assertEquals("text/plain; charset=utf-8", recordedRequest.getHeader("Content-Type"));