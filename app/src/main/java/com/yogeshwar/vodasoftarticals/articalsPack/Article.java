package com.yogeshwar.vodasoftarticals.articalsPack;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Article{
   public String id;

   public String content;

   @SerializedName("comments")
   public String comments;

   @SerializedName("likes")
   public String likes;

   public List<Media> media;
   public List<UserInfo> user;

   public String createdAt;
}
