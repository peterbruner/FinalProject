package com.novauc.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StoresByZip {

    private String smName;
    private String smAddress;
    private String smCity;
    private String smState;
    private String smZip;
    private String smPhone;
    private String smId;

    public StoresByZip(String smName, String smAddress, String smCity, String smState, String smZip, String smPhone, String smId) {
        this.smName = smName;
        this.smAddress = smAddress;
        this.smCity = smCity;
        this.smState = smState;
        this.smZip = smZip;
        this.smPhone = smPhone;
        this.smId = smId;
    }

    public String getSmName() {
        return smName;
    }

    public void setSmName(String smName) {
        this.smName = smName;
    }

    public String getSmAddress() {
        return smAddress;
    }

    public void setSmAddress(String smAddress) {
        this.smAddress = smAddress;
    }

    public String getSmCity() {
        return smCity;
    }

    public void setSmCity(String smCity) {
        this.smCity = smCity;
    }

    public String getSmState() {
        return smState;
    }

    public void setSmState(String smState) {
        this.smState = smState;
    }

    public String getSmZip() {
        return smZip;
    }

    public void setSmZip(String smZip) {
        this.smZip = smZip;
    }

    public String getSmPhone() {
        return smPhone;
    }

    public void setSmPhone(String smPhone) {
        this.smPhone = smPhone;
    }

    public String getSmId() {
        return smId;
    }

    public void setSmId(String smId) {
        this.smId = smId;
    }
}