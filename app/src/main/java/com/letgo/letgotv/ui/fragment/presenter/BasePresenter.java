package com.letgo.letgotv.ui.fragment.presenter;

import android.support.v4.app.Fragment;

import com.letgo.letgotv.ui.fragment.view.BaseView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Base Presenter
 */
public class BasePresenter implements Presenter {

    protected BaseView view;
    protected Fragment fragment;

    private CompositeSubscription subscriptions;

    public BasePresenter() {

        this.subscriptions = new CompositeSubscription();
    }

    public void setView(BaseView view, Fragment fragment) {

        this.view = view;
        this.fragment = fragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

        cancelAllSubscriptions();

        this.view = null;

        this.fragment = null;
    }

    protected void addSubscription(Subscription subscription) {

        subscriptions.add(subscription);
    }

    private void cancelAllSubscriptions() {

        subscriptions.unsubscribe();
    }
}
