package com.novauc.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchByProductName {

    private String Itemname;
    private String itemDescription;
    private String itemCategory;
    private String itemID;
    private String itemImage;
    private String aisleNumber;

    public SearchByProductName() {
    }

    public SearchByProductName(String itemname, String itemDescription, String itemCategory, String itemID, String itemImage, String aisleNumber) {
        this.Itemname = itemname;
        this.itemDescription = itemDescription;
        this.itemCategory = itemCategory;
        this.itemID = itemID;
        this.itemImage = itemImage;
        this.aisleNumber = aisleNumber;
    }

    public String getItemname() {
        return Itemname;
    }

    public void setItemname(String itemname) {
        Itemname = itemname;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getAisleNumber() {
        return aisleNumber;
    }

    public void setAisleNumber(String aisleNumber) {
        this.aisleNumber = aisleNumber;
    }
}