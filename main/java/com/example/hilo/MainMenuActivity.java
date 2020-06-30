package com.example.hilo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainMenuActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        ImageView btn_newgame,btn_leaderboards;

        final boolean user = false;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mRef = database.getInstance().getReference();

        btn_newgame = (ImageView) findViewById(R.id.new_game_btn);
        btn_leaderboards = (ImageView) findViewById(R.id.leaderboards_btn);

        btn_newgame.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if( user == false)
                {
                    Intent newgame_intent = new Intent(MainMenuActivity.this,GameActivity.class);
                    startActivity(newgame_intent);
                    finish();
                }
            }
        });
        btn_leaderboards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent highscore_intent = new Intent(MainMenuActivity.this,HighScoreActivity.class);
                startActivity(highscore_intent);
                finish();
            }
        });
    }
}