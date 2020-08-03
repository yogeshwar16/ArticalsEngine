package com.yogeshwar.vodasoftarticals.articalsPack;



import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.yogeshwar.vodasoftarticals.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleDataSource extends PageKeyedDataSource<Integer, Article> {

    public static final int PAGE_SIZE = 10;
    private static final int FIRST_PAGE = 1;
  //  private static final String SITE_NAME = "stackoverflow";

    public static int pageKey =0;


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Article> callback) {

        RetrofitClient.getInsance()
                .getApi()
                .getArticals(FIRST_PAGE, PAGE_SIZE)
                .enqueue(new Callback<List<Article>>() {
                    @Override
                    public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {

                        if(response.body() != null){

                            callback.onResult( (List<Article>)response.body(), null, FIRST_PAGE + 1);

                           // System.out.println("///////////////////////////////////////////////////"+response.body().get(0));
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Article>> call, Throwable t) {

                    }
                });

    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Article> callback) {

        RetrofitClient.getInsance()
                .getApi()
                .getArticals(params.key, PAGE_SIZE)
                .enqueue(new Callback<List<Article>>() {
                    @Override
                    public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {



                        if(response.body() != null){
                            Integer key = (params.key > 1) ? params.key - 1 : null;
                            callback.onResult((List<Article>)response.body(), key);
                            pageKey =key;
                          //  System.out.println("///////////////////////////////////////////////////"+response.body().get(0));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Article>> call, Throwable t) {

                    }
                });

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Article> callback) {

        RetrofitClient.getInsance()
                .getApi()
                .getArticals(params.key, PAGE_SIZE)
                .enqueue(new Callback<List<Article>>() {
                    @Override
                    public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {

                        if(response.body() != null){
                            //Integer key = response.body().has_more ? params.key + 1 : null;
                            Integer key = (params.key !=5) ? params.key + 1 : null;
                            callback.onResult((List<Article>)response.body(), key);
                           // pageKey =key;
                           // System.out.println("///////////////////////////////////////////////////"+response.body().get(0));
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Article>> call, Throwable t) {

                    }
                });


    }
}
