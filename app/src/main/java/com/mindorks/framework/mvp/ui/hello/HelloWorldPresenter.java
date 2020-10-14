package com.mindorks.framework.mvp.ui.hello;

import com.mindorks.framework.mvp.data.DataManager;
import com.mindorks.framework.mvp.data.db.model.News;
import com.mindorks.framework.mvp.ui.base.BasePresenter;
import com.mindorks.framework.mvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import okhttp3.Response;
import retrofit2.HttpException;

public class HelloWorldPresenter <V extends HelloWorldMvpView> extends BasePresenter<V> implements HelloWorldMvpPresenter<V> {

    @Inject
    public HelloWorldPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onLoadData() {
        getCompositeDisposable().add(getDataManager()
                .getNewsApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<News>() {
                    @Override
                    public void accept(News news) throws Exception {
                        getMvpView().loadData(news.getArticles());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) {
                        if (throwable instanceof HttpException) {
                            String errorCode;
                            Response response = null;
                            switch (response.code()) {
                                case 404:
                                    errorCode = "404 not found";
                                    // Toast.makeText(homeNewsFragment.getContext(), " " + errorCode, Toast.LENGTH_SHORT).show();
                                    break;
                                case 500:
                                    errorCode = "500 server broken";
                                    //  Toast.makeText(homeNewsFragment.getContext(), " " + errorCode, Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    errorCode = "unknown error";
                                    //   Toast.makeText(homeNewsFragment.getContext(), " " + errorCode, Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    }
                }));
    }
}
