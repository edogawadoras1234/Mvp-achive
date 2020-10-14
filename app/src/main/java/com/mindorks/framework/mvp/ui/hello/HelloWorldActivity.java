package com.mindorks.framework.mvp.ui.hello;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mindorks.framework.mvp.R;
import com.mindorks.framework.mvp.data.db.model.Article;
import com.mindorks.framework.mvp.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HelloWorldActivity extends BaseActivity implements HelloWorldMvpView{

    @Inject
    HelloWorldMvpPresenter<HelloWorldMvpView> mPresenter;
    List<Article> articles = new ArrayList<>();
    @BindView(R.id.text_show_list)
    TextView txt_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);
        getActivityComponent().inject(this);
        mPresenter.onAttach(HelloWorldActivity.this);
        ButterKnife.bind(this);
    }

    @Override
    protected void setUp() {

    }

    @OnClick(R.id.button_chuyentrang)
    void changePage() {
        mPresenter.onLoadData();
    }

    @Override
    public void loadData(List<Article> articleList) {
        articles = articleList;
        for (int i = 0; i< articleList.size(); i++){
            txt_list.setText(articleList.get(i).getTitle());
            Log.i("Loggggggggg", "" + articleList.get(i).getTitle());
        }
    }
}

