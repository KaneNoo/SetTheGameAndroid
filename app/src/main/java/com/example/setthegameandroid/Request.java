package com.example.setthegameandroid;

public class Request {

    String action;
    String nickname;
    int token;
    Card[] cards;

    public Request(String action, String nickname) {
        //action register
        this.action = action;
        this.nickname = nickname;
    }

    public Request(String action, int token) {
        //action fetch_cards
        this.action = action;
        this.token = token;
    }

    public Request(String action, int token, Card[] cards) {
        // action take_set
        this.action = action;
        this.token = token;
        this.cards = cards;
    }

    public static Request Register(String nickname){
        return new Request("register", nickname);
    }

    public static Request FetchCards(int token){

        return new Request("fetch_cards", token);
    }

    public static Request TakeSet(int token, Card[] set){
        return new Request("take_set", token, set);
    }

}
