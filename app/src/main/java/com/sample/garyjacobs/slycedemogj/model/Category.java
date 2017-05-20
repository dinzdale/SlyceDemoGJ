package com.sample.garyjacobs.slycedemogj.model;

import java.util.List;

/**
 * Created by garyjacobs on 5/20/17.
 */

public class Category {
    String categoryID;
    String categoryLabel;
    String clientID;
    String imageURL;
    String keywords;
    List<Product> products;
    Boolean searchFinished;
    String token;
    Boolean training;

    public Category() {
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryLabel() {
        return categoryLabel;
    }

    public void setCategoryLabel(String categoryLabel) {
        this.categoryLabel = categoryLabel;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Boolean getSearchFinished() {
        return searchFinished;
    }

    public void setSearchFinished(Boolean searchFinished) {
        this.searchFinished = searchFinished;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getTraining() {
        return training;
    }

    public void setTraining(Boolean training) {
        this.training = training;
    }
}
