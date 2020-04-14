package com.example.currencyconverter;

public class ItemModel {
    private String  name;
    private int currencySymbol;
    private  double weight;


    public ItemModel(String name, int currencySymbol, double weight) {
        this.name = name;
        this.currencySymbol = currencySymbol;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(int currencySymbol) {
        this.currencySymbol = currencySymbol;
    }
}
