package com.example.noman.tddandroid.ui;

import com.example.noman.tddandroid.RxJavaTestRunner;
import com.example.noman.tddandroid.RxSchedulersOverrideRule;
import com.example.noman.tddandroid.retro.ApiServices;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


import io.reactivex.Observable;
import io.reactivex.plugins.RxJavaPlugins;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by noman on 17/2/18.
 */
@RunWith(RxJavaTestRunner.class)
public class MainPresenterTest4 {

    @Mock
    private ApiServices apiServices;

    @Mock
    private MainView mView;


    private MainPresenter mPresenter;

    private Employee employee;

    @Before
    public void setup() {
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> io.reactivex.schedulers.Schedulers.trampoline());

        MockitoAnnotations.initMocks(this);
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
    public void fetchData() {

/*
        when(apiServices.getEmployees())
                .thenReturn(rx.Observable.just(employee));

        mPresenter.getAllEmployee();

        InOrder inOrder = Mockito.inOrder(mView);
       // inOrder.verify(mView).setLoadingIndicator(false);
        inOrder.verify(mView).employeeResponse(employee);
*/

    }

    @Test
    public void getEmployeeSuccess() throws Exception {

        when(apiServices.getEmployees()).thenReturn(Observable.just(employee));

        mPresenter.getAllEmployee(new NetworkHandler());

        verify(mView).employeeResponse(Mockito.any(Employee.class));

    }

    @Test
    public void getEmployeeError() throws Exception {

        when(apiServices.getEmployees()).thenReturn(Observable.error(new Throwable("oops")));

        mPresenter.getAllEmployee(new NetworkHandler());

        verify(mView).errorOccured(Mockito.any(Throwable.class));

    }


    @Test
    public void fetchError() {

    }
}