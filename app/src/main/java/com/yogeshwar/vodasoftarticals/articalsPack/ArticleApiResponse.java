package com.yogeshwar.vodasoftarticals.articalsPack;

import java.util.ArrayList;
import java.util.List;

class Media{

    public String createdAt;
    public String image;
    public String title;
    public String url;
}

class UserInfo{
    public String createdAt;
    public String name;
    public String designation;
    public String avatar;
    public String city;
    public String about;
}

public class ArticleApiResponse {
    public List<Article> articleList;

}
