package com.example;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
    
public class JSON {

    public static ArrayList<Article> getArticles(String data) {
        JSONObject main = new JSONObject(data);
        JSONArray arr = main.getJSONArray("articles");
        ArrayList<Article> list = new ArrayList<Article>();

        for (int i = 1; i < 6; i++) { // loop through 1 -> 5 instead of 0 -> 4
            JSONObject obj = arr.getJSONObject(i);
            String author = getAuthor(obj);
            String title = getTitle(obj);
            String url = getURL(obj);
            String desc = getDescription(obj);
            String date = getDate(obj);

            System.out.println("-----------------------------");
            System.out.println("#" + i);
            System.out.println(author);
            System.out.println(title);
            System.out.println(url);
            System.out.println(desc);
            System.out.println(date);
            System.out.println("-----------------------------");
            System.out.println();

            Article temp = new Article(author, title, url, desc, date, i);
            list.add(temp);
        }

        return list;
    
    }

    // Helper methods for retrieving data from the JSON object
    public static String getAuthor(JSONObject obj) {
        String author = obj.optString("author", "N/A");
        return author;
    }

    public static String getTitle(JSONObject obj) {
        String title = obj.optString("title", "N/A");
        return title;
    }

    public static String getDescription(JSONObject obj) {
        String description = obj.optString("description", "N/A");
        return description;
    }

    public static String getURL(JSONObject obj) {
        String url = obj.optString("url", "N/A");
        return url;
    }

    public static String getDate(JSONObject obj) {
        String date = obj.optString("publishedAt", "N/A");
        return date;
    }
}

