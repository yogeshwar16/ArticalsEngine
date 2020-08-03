package com.yogeshwar.vodasoftarticals.usersPack;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yogeshwar.vodasoftarticals.R;
import com.yogeshwar.vodasoftarticals.articalsPack.ArticalsActivity;
import com.yogeshwar.vodasoftarticals.articalsPack.Article;
import com.yogeshwar.vodasoftarticals.articalsPack.ArticleAdapter;
import com.yogeshwar.vodasoftarticals.articalsPack.ArticleViewModel;

import static com.yogeshwar.vodasoftarticals.articalsPack.ArticleDataSource.pageKey;

public class UsersActivity extends AppCompatActivity {
    public ProgressBar progressBar;
    private RecyclerView recyclerView;
    ActionBar actionbar;
    TextView textview;
    RelativeLayout.LayoutParams layoutparams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        progressBar = (ProgressBar) findViewById(R.id.progressbara);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(UsersActivity.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Its loading....");
        progressDoalog.setTitle("ProgressDialog bar example");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // show it
        progressDoalog.show();
        UserInfoViewModel userInfoViewModel = ViewModelProviders.of(this).get(UserInfoViewModel.class);
        final UsersListAdapter adapter = new UsersListAdapter(this);

        userInfoViewModel.itemPagedList.observe(this, new Observer<PagedList<UserModel>>() {
            @Override
            public void onChanged(@Nullable PagedList<UserModel> items) {
                adapter.submitList(items);
                System.out.println("/////////////////////////////"+items);
            }
        });

        recyclerView.setAdapter(adapter);
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

        getSupportActionBar().setTitle("Users");

    }
}