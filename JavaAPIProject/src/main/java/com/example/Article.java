package com.example;
import java.io.FileWriter; // add articles to a text file
import java.io.IOException; // catch errors without ending the program
import java.util.ArrayList;
import java.util.Scanner; // reading files
import java.io.File;
import java.io.FileNotFoundException; // throws exception

import org.json.JSONObject; // format data to a JSON 

public class Article {
    private String author;
    private String title;
    private String url; 
    private String desc;
    private String date;
    private int num;

    private static ArrayList<Article> favorites = new ArrayList<>();

    public Article(String author, String title, String url, String desc, String date, int num){
        this.author = author;
        this.title = title;
        this.url = url;
        this.desc = desc;
        this.date = date;
        this.num = num;
    }

    public String getAuthor() {return author;}
    public String getTitle() {return title;}
    public String getUrl() {return url;}
    public String getDesc() {return desc;}
    public String getDate() {return date;}
    public int getNum() {return num;}
    public static ArrayList<Article> getFavorites() {return favorites;}

    // public void setAuthor(String s) {author = s;}
    // public void setTitle(String s) {title = s;}
    // public void setUrl(String s) {url = s;}
    // public void setDesc(String s) {desc = s;}
    // public void setDate(String s) {date = s;}


    public String toJson() {
        JSONObject obj = new JSONObject();
        obj.put("author", author);
        obj.put("title", title);
        obj.put("url", url);
        obj.put("desc", desc);
        obj.put("date", date);
        return obj.toString();
    }

    public void save() { // call the save method on individual article objects
        try (FileWriter file = new FileWriter("favorites.txt", true)) {
            file.write(toJson() + "\n");
            //System.out.println("Saved to favorites!");

        } catch (IOException e) {
            System.err.println("Unable to Save");
        }
    }

    public static void load() {
        favorites.clear();

        try {
            Scanner input = new Scanner(new File("favorites.txt"));
            int index = 1;
            while (input.hasNextLine()) {
                
                String line = input.nextLine();
                if (!line.isEmpty()) {
                    JSONObject obj = new JSONObject(line);
                    String author = obj.getString("author");
                    String title = obj.getString("title");
                    String url = obj.getString("url");
                    String desc = obj.getString("desc");
                    String date = obj.getString("date");
                    
                    Article temp = new Article(author, title, url, desc, date, index);
                    favorites.add(temp);
                    index++;
                }
            }
            input.close();
            
        } 
        
        catch (FileNotFoundException e) {
            System.err.println("Unable to load favorites.");
        }

        display();

    }

    public static void display() {
        if (favorites.size() == 0) {
            System.out.println("No favorites.");
            return;
        }
        for (Article article : favorites) {
            System.out.println();
            System.out.println("--------------------");
            System.out.println("#" + article.getNum());
            System.out.println(article.getAuthor());
            System.out.println(article.getTitle());
            System.out.println(article.getUrl());
            System.out.println(article.getDesc());
            System.out.println(article.getDate());
            System.out.println("--------------------");
        }
    }

    public static void delete(int index) {
        if (favorites.size() == 0) {
            System.out.println("No favorites.");
            return;
        }

        index--;
        favorites.remove(index);

        try (FileWriter writer = new FileWriter("favorites.txt")) {  
            writer.write(""); 
        } catch (IOException e) {
            System.err.println("error");
        }

        for (Article article : favorites) {
            article.save();
        }
    }

    public static void delete() {
        if (favorites.size() == 0) {
            System.out.println("No favorites.");
            return;
        }

         try (FileWriter writer = new FileWriter("favorites.txt")) {  
            writer.write(""); 
        } catch (IOException e) {
            System.err.println("error");
        }

        System.out.println("Favorites successfully cleared.");
    }

    public static void main(String[] args) {
        // delete();
        Article a1 = new Article("john doe", "president impeached", "https:/nyt.com", "president of the us is impeached", "03-12-2025", 2);
        Article a2 = new Article("jane doe", "lorem ispum", "https:/foxnews.net", "america on the rise", "04-12-2025", 2);
        Article a3 = new Article("alice smith", "tech breakthrough", "https://wired.com", "AI now writes novels", "05-20-2025", 3);
        Article a4 = new Article("bob johnson", "global warming update", "https://natgeo.com", "ice caps melting faster", "05-25-2025", 4);

        // a1.save();
        // a2.save();
        // a3.save();
        // a4.save();

        load();
        delete(3);
        load();


    }


}
