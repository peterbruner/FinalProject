package com.novauc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.lang.reflect.Array;

/**
 * Created by dangelojoyce on 4/10/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Store {
    int no;
    String name;
    String country;
    Array coordinates;
    String streetAddress;
    String city;
    String stateProvCode;
    String zip;
    String phoneNumber;
    boolean sundayOpen;
    String timezone;

    public Store(int no, String name, String country, Array coordinates, String streetAddress, String city, String stateProvCode, String zip, String phoneNumber, boolean sundayOpen, String timezone) {
        this.no = no;
        this.name = name;
        this.country = country;
        this.coordinates = coordinates;
        this.streetAddress = streetAddress;
        this.city = city;
        this.stateProvCode = stateProvCode;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.sundayOpen = sundayOpen;
        this.timezone = timezone;
    }

    public Store(){

    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
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

    public Array getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Array coordinates) {
        this.coordinates = coordinates;
    }

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

    public boolean isSundayOpen() {
        return sundayOpen;
    }

    public void setSundayOpen(boolean sundayOpen) {
        this.sundayOpen = sundayOpen;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
