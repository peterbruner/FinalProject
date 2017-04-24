package com.novauc.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    private String itemId;
    private String parentItemId;
    private String name;
    private String msrp;
    private String salePrice;
    private String upc;
    private String categoryPath;
    private String longDescription;
    private String thumbnailImage;
    private String shortDescription;
    private String mediumImage;
    private String largeImage;
    private String productTrackingUrl;
    private String standardShipRate;
    private String marketplace;
    private String productUrl;
    private String categoryNode;
    private String bundle;
    private String stock;
    private String addToCartUrl;
    private String affiliateAddToCartUrl;
    //    private String[] giftOptions;
//    private String[] imageEntities;
    private String offerType;
    private String isTwoDayShippingEligible;
    private String availableOnline;

    public Item() {
    }

    public Item(String itemId, String parentItemId, String name, String msrp, String salePrice, String upc, String categoryPath, String longDescription, String thumbnailImage, String shortDescription, String mediumImage, String largeImage, String productTrackingUrl, String standardShipRate, String marketplace, String productUrl, String categoryNode, String bundle, String stock, String addToCartUrl, String affiliateAddToCartUrl, String offerType, String isTwoDayShippingEligible, String availableOnline) {
        this.itemId = itemId;
        this.parentItemId = parentItemId;
        this.name = name;
        this.msrp = msrp;
        this.salePrice = salePrice;
        this.upc = upc;
        this.categoryPath = categoryPath;
        this.longDescription = longDescription;
        this.thumbnailImage = thumbnailImage;
        this.shortDescription = shortDescription;
        this.mediumImage = mediumImage;
        this.largeImage = largeImage;
        this.productTrackingUrl = productTrackingUrl;
        this.standardShipRate = standardShipRate;
        this.marketplace = marketplace;
        this.productUrl = productUrl;
        this.categoryNode = categoryNode;
        this.bundle = bundle;
        this.stock = stock;
        this.addToCartUrl = addToCartUrl;
        this.affiliateAddToCartUrl = affiliateAddToCartUrl;
//        this.giftOptions = giftOptions;
//        this.imageEntities = imageEntities;
        this.offerType = offerType;
        this.isTwoDayShippingEligible = isTwoDayShippingEligible;
        this.availableOnline = availableOnline;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getParentItemId() {
        return parentItemId;
    }

    public void setParentItemId(String parentItemId) {
        this.parentItemId = parentItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsrp() {
        return msrp;
    }

    public void setMsrp(String msrp) {
        this.msrp = msrp;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(String categoryPath) {
        this.categoryPath = categoryPath;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(String thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    public String getMediumImage() {
        return mediumImage;
    }

    public void setMediumImage(String mediumImage) {
        this.mediumImage = mediumImage;
    }

    public String getLargeImage() {
        return largeImage;
    }

    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }

    public String getProductTrackingUrl() {
        return productTrackingUrl;
    }

    public void setProductTrackingUrl(String productTrackingUrl) {
        this.productTrackingUrl = productTrackingUrl;
    }

    public String getStandardShipRate() {
        return standardShipRate;
    }

    public void setStandardShipRate(String standardShipRate) {
        this.standardShipRate = standardShipRate;
    }

    public String getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(String marketplace) {
        this.marketplace = marketplace;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getCategoryNode() {
        return categoryNode;
    }

    public void setCategoryNode(String categoryNode) {
        this.categoryNode = categoryNode;
    }

    public String getBundle() {
        return bundle;
    }

    public void setBundle(String bundle) {
        this.bundle = bundle;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getAddToCartUrl() {
        return addToCartUrl;
    }

    public void setAddToCartUrl(String addToCartUrl) {
        this.addToCartUrl = addToCartUrl;
    }

    public String getAffiliateAddToCartUrl() {
        return affiliateAddToCartUrl;
    }

    public void setAffiliateAddToCartUrl(String affiliateAddToCartUrl) {
        this.affiliateAddToCartUrl = affiliateAddToCartUrl;
    }
//
//    public String[] getImageEntities() {
//        return imageEntities;
//    }
//
//    public void setImageEntities(String[] imageEntities) {
//        this.imageEntities = imageEntities;
//    }

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public String getAvailableOnline() {
        return availableOnline;
    }

    public void setAvailableOnline(String availableOnline) {
        this.availableOnline = availableOnline;
    }

    public String getIsTwoDayShippingEligible() {
        return isTwoDayShippingEligible;
    }

    public void setIsTwoDayShippingEligible(String isTwoDayShippingEligible) {
        this.isTwoDayShippingEligible = isTwoDayShippingEligible;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
//
//    public String[] getGiftOptions() {
//        return giftOptions;
//    }
//
//    public void setGiftOptions(String[] giftOptions) {
//        this.giftOptions = giftOptions;
//    }
}