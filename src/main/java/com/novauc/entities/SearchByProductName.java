package com.novauc.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//@JsonIgnoreProperties(ignoreUnknown = true)
//@XmlRootElement(name="ArrayOfProduct")
@XmlRootElement
public class SearchByProductName {

    private String itemName;
    private String itemDescription;
    private String itemCategory;
    private String itemID;
    private String itemImage;
    private String aisleNumber;

    public SearchByProductName() {}

    public SearchByProductName(String itemName, String itemDescription, String itemCategory, String itemID, String itemImage, String aisleNumber) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemCategory = itemCategory;
        this.itemID = itemID;
        this.itemImage = itemImage;
        this.aisleNumber = aisleNumber;
    }

    public String getItemName() {
        return itemName;
    }

    @XmlElement(name="Itemname")
    public void setItemName(String itemname) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    @XmlElement(name="ItemDescription")
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    @XmlElement(name="ItemCategory")
    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemID() {
        return itemID;
    }

    @XmlElement(name="ItemID")
    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemImage() {
        return itemImage;
    }

    @XmlElement(name="ItemImage")
    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getAisleNumber() {
        return aisleNumber;
    }

    @XmlElement(name="AisleNumber")
    public void setAisleNumber(String aisleNumber) {
        this.aisleNumber = aisleNumber;
    }
}
