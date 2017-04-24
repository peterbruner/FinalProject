package com.novauc.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties
public class WalmartStores {

    private String no;
    private String name;
    private String country;
    //    private String coordinates;
    private String streetAddress;
    private String city;
    private String stateProvCode;
    private String zip;
    private String phoneNumber;
    private String sundayOpen;
    private String timezone;

    public WalmartStores() {
    }

    public WalmartStores(String no, String name, String country, String streetAddress, String city, String stateProvCode, String zip, String phoneNumber, String sundayOpen, String timezone) {
        this.no = no;
        this.name = name;
        this.country = country;
//        this.coordinates = coordinates;
        this.streetAddress = streetAddress;
        this.city = city;
        this.stateProvCode = stateProvCode;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.sundayOpen = sundayOpen;
        this.timezone = timezone;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

//    public String getCoordinates() {
//        return coordinates;
//    }
//
//    public void setCoordinates(String coordinates) {
//        this.coordinates = coordinates;
//    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvCode() {
        return stateProvCode;
    }

    public void setStateProvCode(String stateProvCode) {
        this.stateProvCode = stateProvCode;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSundayOpen() {
        return sundayOpen;
    }

    public void setSundayOpen(String sundayOpen) {
        this.sundayOpen = sundayOpen;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}