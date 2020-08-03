package com.yogeshwar.vodasoftarticals.usersPack;


import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.yogeshwar.vodasoftarticals.articalsPack.Article;
import com.yogeshwar.vodasoftarticals.articalsPack.ArticleDataSource;

public class UserDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, UserModel>> itemLiveDataSource = new MutableLiveData<>();


    @Override
    public DataSource create() {
        UserDataSource userDataSource = new UserDataSource();
        itemLiveDataSource.postValue(userDataSource);
        return userDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, UserModel>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
