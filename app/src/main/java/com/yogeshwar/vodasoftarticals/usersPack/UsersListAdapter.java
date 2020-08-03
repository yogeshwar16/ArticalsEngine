package com.yogeshwar.vodasoftarticals.usersPack;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yogeshwar.vodasoftarticals.R;
import com.yogeshwar.vodasoftarticals.UserDetailActivity;
import com.yogeshwar.vodasoftarticals.articalsPack.Article;

public class UsersListAdapter extends PagedListAdapter<UserModel, UsersListAdapter.ItemViewHolder> {

    private Context mCtx;

    protected UsersListAdapter(Context mCtx) {
        super(DIFF_CALLBACK);
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.user_item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        final UserModel userModel = getItem(position);

        if (userModel != null) {

            Glide.with(mCtx)
                    .load(userModel.avatar)
                    .into(holder.imageViewUser);

            holder.textUserName.setText(userModel.name);
            holder.textUserDesignation.setText(userModel.designation);

            holder.textCity.setText(userModel.city);

            holder.storyLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(mCtx, UserDetailActivity.class);
                    intent.putExtra("userimage",userModel.avatar);
                    intent.putExtra("name",userModel.name);
                    intent.putExtra("designation",userModel.designation);
                    intent.putExtra("city",userModel.city);
                    intent.putExtra("about",userModel.about);
                    mCtx.startActivity(intent);
                }
            });


        } else {
            Toast.makeText(mCtx, "Item is null", Toast.LENGTH_LONG).show();
        }

    }


    private static DiffUtil.ItemCallback<UserModel> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<UserModel>() {
                @Override
                public boolean areItemsTheSame(UserModel oldItem, UserModel newItem) {
                    return oldItem.id == newItem.id;
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(UserModel oldItem, UserModel newItem) {
                    return oldItem.equals(newItem);
                }
            };


    class ItemViewHolder extends RecyclerView.ViewHolder {
        LinearLayout storyLayout;
        ImageView imageViewUser;
        TextView textUserName,textUserDesignation,textContent,textCity;

        public ItemViewHolder(View itemView) {
            super(itemView);
            storyLayout = (LinearLayout) itemView.findViewById(R.id.story_layout);
            imageViewUser = itemView.findViewById(R.id.imguser);
            textUserName = itemView.findViewById(R.id.textname);
            textUserDesignation =itemView.findViewById(R.id.textdesig);

            textCity = itemView.findViewById(R.id.textusercity);

        }
    }
}
