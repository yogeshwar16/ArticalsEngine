package com.yogeshwar.vodasoftarticals.articalsPack;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.yogeshwar.vodasoftarticals.R;

import java.util.List;

import static com.yogeshwar.vodasoftarticals.articalsPack.ArticleDataSource.pageKey;

public class ArticalsActivity extends AppCompatActivity {
    public ProgressBar progressBar;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articals);
        progressBar = (ProgressBar) findViewById(R.id.progressbara);
       //
        recyclerView = findViewById(R.id.recyclerView);
       // progressBar.setVisibility(View.VISIBLE);
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(ArticalsActivity.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Its loading....");
        progressDoalog.setTitle("ProgressDialog bar example");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // show it
        progressDoalog.show();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);

        ArticleViewModel articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
        final ArticleAdapter adapter = new ArticleAdapter(this);

        articleViewModel.itemPagedList.observe(this, new Observer<PagedList<Article>>() {
            @Override
            public void onChanged(@Nullable PagedList<Article> items) {
                adapter.submitList(items);
                System.out.println("/////////////////////////////"+items);
            }
        });

        recyclerView.setAdapter(adapter);
       // progressBar.setVisibility(View.GONE);
        progressDoalog.dismiss();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    // Scrolling up
                    if(pageKey!=0){
                        Toast.makeText(getApplicationContext(),"Page No:"+pageKey,Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Scrolling down
                    if(pageKey!=0){
                        Toast.makeText(getApplicationContext(),"Page No:"+pageKey,Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                    // Do something

                } else if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    // Do something

                } else {
                    // Do something

                }
            }
        });

        getSupportActionBar().setTitle("Articals");
    }
}