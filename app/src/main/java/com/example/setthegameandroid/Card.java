package com.example.setthegameandroid;

public class Card {

    private int fill;
    private int color;
    private int shape;
    private int count;

    private Card withoutXY;

    public int x;
    public int y;
    public boolean isTouched = false;


    public Card(int fill, int color, int shape, int count) {
        this.fill = fill;
        this.color = color;
        this.shape = shape;
        this.count = count;
    }

    public Card() {
    }

    public Card(Card withoutXY, int x, int y) {
        this.withoutXY = withoutXY;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.color == ((Card) obj).color &&
                this.count == ((Card) obj).count &&
                this.fill == ((Card) obj).fill &&
                this.shape == ((Card) obj).shape){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {

        return "Color: " + this.color + " | " +
                "Count: " + this.count + " | " +
                "Fill: " + this.fill + " | " +
                "Shape: " + this.shape;
    }

    private int getThirdParameter(int param1, int param2){
        if (param1 == param2){
            return param1;
        }
        return 6 - (param1 + param2);
    }

    public Card getThird(Card card2){
        return new Card(
                getThirdParameter(this.fill, card2.fill),
                getThirdParameter(this.color, card2.color),
                getThirdParameter(this.shape, card2.shape),
                getThirdParameter(this.count, card2.count)
        );

    }

}
