package com.chapterone.chapteroneapp.models;



public class ChargeRequest {
    private String token;
    private int amount;

    public ChargeRequest() {
    }

    public ChargeRequest(String token, int amount) {
        this.token = token;
        this.amount = amount;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
