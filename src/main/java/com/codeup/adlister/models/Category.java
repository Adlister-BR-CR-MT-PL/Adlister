package com.codeup.adlister.models;

public class Category {
    private long id;
    private long adId;//Todo: ?
    private String name;

    public Category () {}

    public Category(String name) {
        this.name = name;
    }

    /*Todo: Um.... DB or java issue??*/
    public Category(long id, String name) {
        this.id = id;
        this.adId = adId;
        this.name = name;
    }

    public Category(long id, long adId, String name) {
        this.id = id;
        this.adId = adId;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*Todo: Same here*/
    public long getAdId() {
        return adId;
    }

    public void setAdId(long adId) {
        this.adId = adId;
    }
}


//package com.codeup.adlister.models;
//
//import java.io.Serializable;
//
//public class Category implements Serializable {
//    private long id;
//    private String title;
//
//    public Category() {}
//
//    public Category(String title) {
//        this.title = title;
//    }
//
//    public Category(long id, String title) {
//        this.id = id;
//        this.title = title;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//   Todo: What is this?
//    public String getCaplizedFirstLetterTitle() {
//        String c = (title != null) ? title.trim() : "";
//        String[] words = c.split(" ");
//        StringBuilder result = new StringBuilder();
//        for (String w : words) {
//            result.append(w.length() > 1 ? w.substring(0, 1).toUpperCase() + w.substring(1).toLowerCase() : w.toUpperCase()).append(" ");
//        }
//        return result.toString().trim();
//    }
//  Todo: What is this?
//    public String getPurifiedTitle() {
//        String c = (title != null) ? title.trim() : "";
//        String[] words = c.split(" ");
//        StringBuilder result = new StringBuilder();
//        for (String w : words) {
//            if (!w.equals("&")) {
//                result.append(w.length() > 1 ? w.substring(0, 1).toUpperCase() + w.substring(1).toLowerCase() : w.toUpperCase()).append(" ");
//            }
//        }
//        return result.toString().trim();
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//}







