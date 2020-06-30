package com.example.hilo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RestartActivity extends AppCompatActivity
{
    Button btn_restart;
    TextView txt_yourname,txt_hs;
    String user,hs,restart;
    boolean code = true;
    int iMoney =100;
    @Override
    public void onCreate(Bundle SavedInstanceState)
    {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.restartgame);
        txt_yourname = (TextView) findViewById(R.id.txt_yourname);
        txt_hs = (TextView) findViewById(R.id.txt_yourhs);

        btn_restart = (Button) findViewById(R.id.btn_restart);

        Intent gameActivity =getIntent();
        user = gameActivity.getStringExtra("currUser1");
        hs = gameActivity.getStringExtra("mHighScore1");

        txt_yourname.setText(user);
        txt_hs.setText(hs);

        btn_restart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent restartgame = new Intent(RestartActivity.this,GameActivity.class);
                restartgame.putExtra("currUser2", String.valueOf(user));
                restartgame.putExtra("code2", code);
                startActivity(restartgame);
                finish();
            }
        });
    }
}
