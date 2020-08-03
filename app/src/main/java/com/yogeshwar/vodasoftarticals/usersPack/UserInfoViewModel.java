package com.yogeshwar.vodasoftarticals.usersPack;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.yogeshwar.vodasoftarticals.articalsPack.Article;
import com.yogeshwar.vodasoftarticals.articalsPack.ArticleDataSource;
import com.yogeshwar.vodasoftarticals.articalsPack.ArticleDataSourceFactory;

public class UserInfoViewModel extends ViewModel {

    LiveData<PagedList<UserModel>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, UserModel>> liveDataSource;

    public UserInfoViewModel() {

        UserDataSourceFactory userDataSourceFactory = new UserDataSourceFactory();
        liveDataSource = userDataSourceFactory.getItemLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ArticleDataSource.PAGE_SIZE)
                        .build();

        itemPagedList = (new LivePagedListBuilder(userDataSourceFactory, config)).build();

    }
}
