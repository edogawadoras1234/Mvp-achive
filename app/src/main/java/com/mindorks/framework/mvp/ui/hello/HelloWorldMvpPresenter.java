package com.mindorks.framework.mvp.ui.hello;

import com.mindorks.framework.mvp.di.PerActivity;
import com.mindorks.framework.mvp.ui.base.MvpPresenter;
import com.mindorks.framework.mvp.ui.base.MvpView;

@PerActivity
public interface HelloWorldMvpPresenter <V extends HelloWorldMvpView & MvpView> extends MvpPresenter<V> {
    void onLoadData();
}
