package com.example.shopinglist;

import java.util.HashMap;
import java.util.Map;

public class Product {
    public String key;
    private int id;
    private String sid;
    private String name;
    private Double price;
    private int count;
    private boolean bought ;

    public Product() {

    }

    public Product(int id, String name, Double price, int count, boolean bought) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
        this.bought = bought;
    }
    public Product(String sid, String name, Double price, int count, boolean bought) {
        this.sid = sid;
        this.name = name;
        this.price = price;
        this.count = count;
        this.bought = bought;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSId() {
        return sid;
    }

    public void setSId(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean getBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("key", this.key);
        result.put("bought", bought);

        return result;
    }

}
