package com.novauc;

import java.math.BigDecimal;

/**
 * Created by dangelojoyce on 4/12/17.
 */
public class Item {
    private String title ;
    private BigDecimal price ;
    private String url ;

    public Item(String title, BigDecimal price, String url) {
        this.title = title;
        this.price = price;
        this.url = url;
    }
    public Item(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
