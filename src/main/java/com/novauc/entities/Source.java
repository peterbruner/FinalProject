package com.novauc.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties
public class Source {

    private ArrayList<WalmartStore> wmStoreAL;

    public Source() {
    }

    public Source(ArrayList<WalmartStore> wmStoreAL) {
        this.wmStoreAL = wmStoreAL;
    }

    public ArrayList<WalmartStore> getWmStoreAL() {
        return wmStoreAL;
    }

    public void setWmStoreAL(ArrayList<WalmartStore> wmStoreAL) {
        this.wmStoreAL = wmStoreAL;
    }
}
