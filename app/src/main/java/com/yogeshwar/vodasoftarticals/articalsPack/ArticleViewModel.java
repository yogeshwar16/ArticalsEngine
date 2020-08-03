package com.yogeshwar.vodasoftarticals.articalsPack;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import java.util.ArrayList;

public class ArticleViewModel extends ViewModel {

    LiveData<PagedList<Article>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, Article>> liveDataSource;

    public ArticleViewModel() {

        ArticleDataSourceFactory articleDataSourceFactory = new ArticleDataSourceFactory();
        liveDataSource = articleDataSourceFactory.getItemLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ArticleDataSource.PAGE_SIZE)
                        .build();

        itemPagedList = (new LivePagedListBuilder(articleDataSourceFactory, config)).build();

    }
}
