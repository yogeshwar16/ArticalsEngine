package com.yogeshwar.vodasoftarticals.articalsPack;


import android.annotation.SuppressLint;
import android.content.Context;

import android.content.Intent;
import android.util.Log;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ArticleAdapter extends PagedListAdapter<Article, ArticleAdapter.ItemViewHolder> {

    private Context mCtx;

    protected ArticleAdapter(Context mCtx) {
        super(DIFF_CALLBACK);
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.artical_item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

      final Article articleItem = getItem(position);

        if (articleItem != null) {

            Glide.with(mCtx)
                    .load(articleItem.user.get(0).avatar)
                    .into(holder.imageViewUser);

            holder.textUserName.setText(articleItem.user.get(0).name);
            holder.textUserDesignation.setText(articleItem.user.get(0).designation);
            try {
                if (null!=articleItem.media||articleItem.media.size()==0) {
                    Glide.with(holder.imageViewArticle.getContext())
                            .load(articleItem.media.get(0).image)
                            .into(holder.imageViewArticle);
                    holder.textTitle.setText(articleItem.media.get(0).title);
                    holder.textUrl.setText(articleItem.media.get(0).url);

                }else{
                    holder.imageViewArticle.setVisibility(View.GONE);
                    holder.textTitle.setVisibility(View.GONE);
                    holder.textUrl.setVisibility(View.GONE);
                }
            }catch (IndexOutOfBoundsException ie){
                System.out.println("//////////////////////////"+ie.toString());
            }catch (NullPointerException ne){
                System.out.println("//////////////////////////"+ne.toString());
            }
            holder.textContent.setText(articleItem.content);


                holder.textLikes.setText(String.valueOf(articleItem.likes));
                holder.textComments.setText(String.valueOf(articleItem.comments));
            try {
                //"2020-04-17T12:13:44.575Z"
                String createdTime = articleItem.createdAt;
                String[] dateTime = createdTime.split("T");
                String date = dateTime[0];
                String strtime = dateTime[1];
                String[] strrrrtime = strtime.split("Z");
                String time = strrrrtime[0];
                //String[] tttime = sstime.split(".");
                //String time = tttime[0];

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String myDateTime = date+" "+time;


                Date oldDate = dateFormat.parse(myDateTime);
                System.out.println(oldDate);

                Date currentDate = new Date();

                long diff = currentDate.getTime() - oldDate.getTime();
                long seconds = diff / 1000;
                long minutes = seconds / 60;
                long hours = minutes / 60;
                long days = hours / 24;

                if (oldDate.before(currentDate)) {

                    Log.e("oldDate", "is previous date");
                    Log.e("Difference: ", " seconds: " + seconds + " minutes: " + minutes
                            + " hours: " + hours + " days: " + days);
                    holder.textCreated.setText(myDateTime);
                }else{
                    holder.textCreated.setText(hours+""+minutes+""+seconds);
                }



            } catch (ParseException e) {

                e.printStackTrace();
            }

            holder.storyLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(mCtx, UserDetailActivity.class);
                    intent.putExtra("userimage",articleItem.user.get(0).avatar);
                    intent.putExtra("name",articleItem.user.get(0).name);
                    intent.putExtra("designation",articleItem.user.get(0).designation);
                    intent.putExtra("city",articleItem.user.get(0).city);
                    intent.putExtra("about",articleItem.user.get(0).about);
                    mCtx.startActivity(intent);
                }
            });
        } else {
            Toast.makeText(mCtx, "Item is null", Toast.LENGTH_LONG).show();
        }

    }


    private static DiffUtil.ItemCallback<Article> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Article>() {
                @Override
                public boolean areItemsTheSame(Article oldItem, Article newItem) {
                    return oldItem.id == newItem.id;
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(Article oldItem, Article newItem) {
                    return oldItem.equals(newItem);
                }
            };


    class ItemViewHolder extends RecyclerView.ViewHolder {
        LinearLayout storyLayout;
        ImageView imageViewUser,imageViewArticle;
        TextView textUserName,textUserDesignation,textContent,textTitle,textUrl,textLikes,
                textComments,textCreated;

        public ItemViewHolder(View itemView) {
            super(itemView);
            storyLayout = (LinearLayout) itemView.findViewById(R.id.story_layout);
            textCreated = itemView.findViewById(R.id.textcreated);
            imageViewUser = itemView.findViewById(R.id.imguser);
            textUserName = itemView.findViewById(R.id.textname);
            textUserDesignation =itemView.findViewById(R.id.textdesig);
            imageViewArticle = itemView.findViewById(R.id.imgarticle);
            textContent = itemView.findViewById(R.id.textcontent);
            textTitle = itemView.findViewById(R.id.texttitle);
            textUrl = itemView.findViewById(R.id.texturl);
            textLikes = itemView.findViewById(R.id.textlikes);
            textComments = itemView.findViewById(R.id.textcomments);
        }
    }
}
