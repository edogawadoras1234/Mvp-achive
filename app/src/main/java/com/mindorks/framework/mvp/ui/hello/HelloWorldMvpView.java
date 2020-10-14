package com.mindorks.framework.mvp.ui.hello;

import com.mindorks.framework.mvp.data.db.model.Article;
import com.mindorks.framework.mvp.ui.base.MvpView;

import java.util.List;

public interface HelloWorldMvpView extends MvpView {
    void loadData(List<Article> articleList);
}
