package com.example.setthegameandroid;

import java.util.ArrayList;

public class Response {

    int token, points, cards_left;
    String status;
    Card[] cards;


    public Response(String status, int cards_left, int points ) {
        //action take_set
        this.points = points;
        this.cards_left = cards_left;
        this.status = status;
    }

    public Response(int token, String status) {
        // action register
        this.token = token;
        this.status = status;
    }

    public Response(String status, Card[] cards) {
        // action fetch_cards
        this.status = status;
        this.cards = cards;
    }


}
