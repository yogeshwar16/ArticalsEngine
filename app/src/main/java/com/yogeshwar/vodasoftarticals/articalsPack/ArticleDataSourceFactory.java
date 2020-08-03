package com.yogeshwar.vodasoftarticals.articalsPack;


import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import java.util.ArrayList;

public class ArticleDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Article>> itemLiveDataSource = new MutableLiveData<>();


    @Override
    public DataSource create() {
        ArticleDataSource articleDataSource = new ArticleDataSource();
        itemLiveDataSource.postValue(articleDataSource);
        return articleDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Article>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
