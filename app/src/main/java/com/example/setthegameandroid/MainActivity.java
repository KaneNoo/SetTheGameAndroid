package com.example.setthegameandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    //Для SharedPreferences
    public static final String APP_PREFERENCES = "savedPlayer";
    public static final String APP_PREFERENCES_NAME = "Nickname";
    public static final String  APP_PREFERENCES_TOKEN = "Token";
    SharedPreferences savedPlayer;

    //Получение данных с сервера
    public static String newNickname;
    public static int newNToken;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            savedPlayer = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        }


    public void actionRegisterSign(View view) throws ExecutionException, InterruptedException {

        EditText nicknameField = findViewById(R.id.nickname);
        String nickname = nicknameField.getText().toString();
        try {
            String savedNick = savedPlayer.getString(APP_PREFERENCES_NAME, "");
            if(!savedNick.equals(nickname)) throw new NullPointerException("Использован другой ник или ник не найдеy");

        } catch (NullPointerException e){

            Request register = Request.Register(nickname);
            SetServerTask task = new SetServerTask();

            Response response = task.execute(register).get();
            newNickname = nickname;
            SharedPreferences.Editor editor = savedPlayer.edit();
            editor.putString(APP_PREFERENCES_NAME, newNickname);
            editor.putInt(APP_PREFERENCES_TOKEN, response.token);
            editor.apply();

        }
        finally {
            Intent totheGame = new Intent(MainActivity.this, Game.class);
            totheGame.putExtra(APP_PREFERENCES_NAME, savedPlayer.getString(APP_PREFERENCES_NAME, ""));
            totheGame.putExtra(APP_PREFERENCES_TOKEN, savedPlayer.getInt(APP_PREFERENCES_TOKEN, newNToken));
            startActivity(totheGame);
        }


    }

    // TODO: предусмотреть текстовое поле для имени игрока
        // TODO: добавить кнопки для регистрации и отправки карт
        // TODO: отобразить число очков игрока
    }
