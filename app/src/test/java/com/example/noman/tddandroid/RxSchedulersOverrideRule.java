package com.example.noman.tddandroid;

import org.junit.rules.TestRule;
import org.junit.runners.model.Statement;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by noman on 18/2/18.
 */

public class RxSchedulersOverrideRule implements TestRule {
    @Override
    public Statement apply(Statement base, org.junit.runner.Description description) {
        return new Statement() {
            @Override public void evaluate() throws Throwable {
                RxAndroidPlugins.reset();
                RxAndroidPlugins.setMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());

                RxJavaPlugins.reset();
                RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
                RxJavaPlugins.setComputationSchedulerHandler(scheduler -> Schedulers.trampoline());
                RxJavaPlugins.setNewThreadSchedulerHandler(scheduler -> Schedulers.trampoline());

                base.evaluate();

                RxAndroidPlugins.reset();
                RxJavaPlugins.reset();
            }
        };
    }

}
