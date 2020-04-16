package com.example.setthegameandroid;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Game extends Activity {

    public static String nickname;
    public static int token;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_view);


        nickname = getIntent().getExtras().getString("Nickname");
        token = getIntent().getExtras().getInt("Token");
        Toast.makeText(getApplicationContext(), "Hello, " + nickname + " " + token, Toast.LENGTH_LONG).show();


    }
}
