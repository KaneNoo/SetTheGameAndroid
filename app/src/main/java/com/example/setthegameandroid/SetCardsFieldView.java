package com.example.setthegameandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SetCardsFieldView extends View {

    //12 карт = 3 в строке, 4 в столбце
    // 1 карта = 3 разделителя = 3 части
    // 1 разделитель = 1 часть
    // Строка = 3 карты, 4 разделителя = 3*3+4 = 13 частей
    // Столбец = 4 карты, 5 разделителей = 4*3+5 = 17 частей

    static int cardWidth;
    static int cardHeight;
    static int distanceLR;
    static int distanceUD;

    static final int baseCardColor = Color.WHITE;
    static final int pickedCardColor = Color.LTGRAY;

    static final int textColor = Color.BLACK;
    static final int token = Game.token;


    static ArrayList<Card> cards;
    Paint paint = new Paint();

    public SetCardsFieldView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getCards();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        // TODO: отобразить состояние карт
        distanceLR = getWidth() / 17;
        distanceUD = getHeight() / 13;
        cardHeight = 3 * distanceUD;
        cardWidth = 3 * distanceLR;

        for (Card card : cards){
            if(card.isTouched){

                paint.setColor(baseCardColor);
                canvas.drawRect(card.x, card.y, card.x + cardWidth, card.y + cardHeight, paint);
            }
            else{
                paint.setColor(pickedCardColor);
                canvas.drawRect(card.x, card.y, card.x + cardWidth, card.y + cardHeight, paint);
            }


        }




    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO: в случе касания, определить, какой карты коснулись


        return super.onTouchEvent(event);
    }


    public void getCards() {

        Request fetchCards = Request.FetchCards(token);
        SetServerTask task = new SetServerTask();
        task.requestToSetServer(fetchCards);
        Card[] takenCards =new Card[12];
        try {
             takenCards = task.get().cards;
        } catch (ExecutionException | InterruptedException e){

            Log.d("DEBUG", "94. task.get()");

        }


        cards = new ArrayList<>();

        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 3; j++){
                cards.add(new Card(takenCards[i], j*cardWidth + distanceLR * (j+1), i*cardHeight + distanceUD * (i+1)));
            }
        }



    }
}
