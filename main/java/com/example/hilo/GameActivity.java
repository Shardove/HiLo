package com.example.hilo;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.Random;

public class GameActivity extends AppCompatActivity
{
    public int mCardNumber;
    public int mCardShape;

    public boolean high = false;
    public boolean low = false;
    public boolean first=true;
    boolean code;

    public int oldNumber, wonMoney, currMoney, mBet, nOld=1, nNew=1, init_money = 100,mHighscore;
    public String transformNumber,currUser,user1;

    public TextView txt_currMoney,txt_cardNumber,txt_timer,txt_result,txt_status,txt_kosong,textView2,txt_error;

    String value, choosingBet;
    Button btn_game;

    ImageView high_btn, low_btn,Coins,blackClover,blackScope,redDiamond, redHeart,blackBack, redBack,bg;
    EditText txtbet,txt_nama;
    private String transformOldNumber;


    public void onCreate(Bundle SavedInstanceState)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mRef = database.getInstance().getReference();

        super.onCreate(SavedInstanceState);
        setContentView(R.layout.game);

        btn_game = (Button) findViewById(R.id.btn_game);
        high_btn = (ImageView) findViewById(R.id.btn_high);
        low_btn = (ImageView) findViewById(R.id.btn_low);
        blackClover = (ImageView) findViewById(R.id.black_clover_image);
        blackScope = (ImageView) findViewById(R.id.black_scope_image);
        redDiamond = (ImageView) findViewById(R.id.red_diamond_image);
        redHeart = (ImageView) findViewById(R.id.red_heart_image);
        blackBack = (ImageView)findViewById(R.id.black_cards_view);
        redBack= (ImageView) findViewById(R.id.red_cards_view);

        txt_nama = (EditText) findViewById(R.id.txt_nama);
        txt_kosong = (TextView) findViewById(R.id.txt_osong);
        txtbet = (EditText) findViewById(R.id.txt_bet);
        txt_result = (TextView) findViewById(R.id.txt_result);
        txt_status = (TextView) findViewById(R.id.txt_status);
        txt_timer = (TextView) findViewById(R.id.txt_timer);
        txt_cardNumber = (TextView) findViewById(R.id.txt_Number);
        txt_currMoney = (TextView) findViewById(R.id.txt_money);
        textView2 = (TextView) findViewById(R.id.textView2);
        txt_error = (TextView) findViewById(R.id.txt_error);

        bg = (ImageView) findViewById(R.id.bg);

        Intent restartGameIntent = getIntent();
        code = restartGameIntent.getBooleanExtra("code2",false);
        if (code == true)
        {
            enableAll();
            currUser = restartGameIntent.getStringExtra("currUser2");
            code = false;
        }
        btn_game.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (txt_nama != null)
                {
                    currUser = String.valueOf(txt_nama.getText());
                    Log.d("user",currUser);
                    enableAll();
                }
            }
        });

        txt_currMoney.setText("100");
        txt_result.setText("");
        currMoney = init_money;
        
        txt_result.setText("Waiting To Bet");


        high_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                txt_status.setText("");
                txt_error.setText("");
                value="";
                choosingBet="";

                high=false;
                low = false;
                String getBet = txtbet.getText().toString();
                mBet =  Integer.parseInt(getBet);
                String abc = String.valueOf(mBet);
                Log.d("Betting",abc);
                if (mBet != 0)
                {
                    choosingBet = "HI";
                    if (currMoney == mBet || currMoney > mBet)
                    {
                        oldNumber = mCardNumber;

                        Log.d("kartu lama ke "+nOld+" : ",String.valueOf(oldNumber));
                        nOld++;
                        currMoney = (currMoney-mBet);
                        txt_currMoney.setText(String.valueOf(currMoney));
                        buffer();
                    }
                    else
                        txt_error.setText("Make Sure You Have Enough Coins");

                }

            }
        });

        low_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                txt_status.setText("");
                txt_error.setText("");
                value="";
                choosingBet="";
                high=false;
                low = false;
                String getBet = txtbet.getText().toString();
                mBet =  Integer.parseInt(getBet);
                String abc = String.valueOf(mBet);
                Log.d("Betting",abc);
                if (mBet != 0)
                {
                    choosingBet ="LO";
                    if (currMoney == mBet || currMoney >mBet )
                    {
                        oldNumber = mCardNumber;
                        Log.d("kartu lama ke "+nOld+" : ",String.valueOf(oldNumber));
                        nOld++;
                        currMoney = (currMoney-mBet);
                        txt_currMoney.setText(String.valueOf(currMoney));
                        buffer();
                    }
                    else
                        txt_error.setText("Make Sure You Have Enough Coins");
                }
            }
        });

    }
    private void buffer()
    {
        new CountDownTimer(4000,1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {

                high_btn.setClickable(false);
                low_btn.setClickable(false);
                txt_timer.setText("Result On : " +millisUntilFinished/1000);
                txt_result.setText("You Pick : "+choosingBet);
            }

            @Override
            public void onFinish()
            {
                generateCard();
                txt_timer.setText("Result : ");
            }
        }.start();
    }


    private void numberGenerate()
    {
        if (oldNumber!=0)
        {
            if (oldNumber < 10)
            {
                transformOldNumber= String.valueOf(oldNumber);
            }
            if (oldNumber == 10)
            {
                transformOldNumber= "J";
            }
            if (oldNumber == 11)
            {
                transformOldNumber= "Q";
            }
            if (oldNumber == 12)
            {
                transformOldNumber= "K";
            }
            if (oldNumber == 13)
            {
                transformOldNumber= "A";
            }
        }

        if (mCardNumber!=0)
        {
            if (mCardNumber <10 )
            {
                transformNumber = String.valueOf(mCardNumber);
                txt_cardNumber.setText(String.valueOf(transformNumber));
            }

            if (mCardNumber == 10)
            {
                transformNumber = "J";
                txt_cardNumber.setText("J");
            }
            else if (mCardNumber == 11)
            {
                transformNumber = "Q";
                txt_cardNumber.setText("Q");
            }
            else if (mCardNumber == 12)
            {
                transformNumber = "K";
                txt_cardNumber.setText("K");
            }
            else if (mCardNumber == 13)
            {
                transformNumber = "A";
                txt_cardNumber.setText("A");
            }
            else Log.d("Error","Cards Not Found");

            if (first == false )
            {
                checkingNumber();
            }
            else first = false;
        }
    }

    private void generateCard()
    {
        int mCardShape;

        if (mCardNumber!=0)
        {
            oldNumber = mCardNumber;
        }

        Random randNumber = new Random();
        mCardNumber = randNumber.nextInt(13-2) + 2;
        Log.d("nomor :",String.valueOf(mCardNumber));
        Random randShape = new Random();
        mCardShape = randShape.nextInt(4-1) + 1;
        Log.d("bentuk :",String.valueOf(mCardShape));

        if(mCardShape == 1)
        {
            blackBack.setVisibility(View.VISIBLE);
            blackScope.setVisibility(View.VISIBLE);

            blackClover.setVisibility(View.GONE);
            redBack.setVisibility(View.GONE);
            redDiamond.setVisibility(View.GONE);
            redHeart.setVisibility(View.GONE);
        }
        if (mCardShape == 2 )
        {
            blackBack.setVisibility(View.VISIBLE);
            blackClover.setVisibility(View.VISIBLE);

            blackScope.setVisibility(View.GONE);
            redBack.setVisibility(View.GONE);
            redDiamond.setVisibility(View.GONE);
            redHeart.setVisibility(View.GONE);
        }
        if (mCardShape == 3)
        {
            redBack.setVisibility(View.VISIBLE);
            redHeart.setVisibility(View.VISIBLE);

            blackScope.setVisibility(View.GONE);
            blackBack.setVisibility(View.GONE);
            redDiamond.setVisibility(View.GONE);
            blackClover.setVisibility(View.GONE);
        }
        if (mCardShape == 4)
        {
            redBack.setVisibility(View.VISIBLE);
            redDiamond.setVisibility(View.VISIBLE);

            blackScope.setVisibility(View.GONE);
            blackBack.setVisibility(View.GONE);
            redHeart.setVisibility(View.GONE);
            blackClover.setVisibility(View.GONE);
        }

        numberGenerate();

    }
    private void checkingNumber()
    {
        Log.d("kartu baru ke "+nNew +" :", String.valueOf(mCardNumber));
        nNew++;

        if (oldNumber < mCardNumber)
        {
            high = true;
            low = false;
            value = "HI";
        }

        else if (mCardNumber < oldNumber)
        {
            high = false;
            low = true;
            value = "LO";
        }

        else
        {
            high= false;
            low = false;
            value="Same As Old Card";
        }
        checkingBet();

    }

    private void checkingBet()
    {
        if (choosingBet == "HI")
        {
            if (value == "HI")
            {
                wonMoney = mBet*2;
                currMoney = (currMoney + mBet*2 );
                txt_currMoney.setText(String.valueOf(currMoney));
                choosingBet = "";
                txt_result.setText(transformOldNumber +" To " +transformNumber +": " +value);
                txt_status.setText("You Won : "+wonMoney + "Coins");
                high_btn.setClickable(true);
                low_btn.setClickable(true);
                checkingHighscore();
            }
            else if (value!="HI")
            {
                txt_status.setText("You Lose : "+mBet + "Coins");
                txt_result.setText(transformOldNumber +" To " +transformNumber +": " +value);
                checkingLose();
            }
        }

        else if (choosingBet == "LO")
        {
            if (value == "LO")
            {
                wonMoney = mBet*2;
                currMoney = (currMoney + wonMoney);
                txt_currMoney.setText(String.valueOf(currMoney));
                choosingBet = "";
                txt_result.setText(transformOldNumber +" To " +transformNumber +": " +value);
                txt_status.setText("You Won : "+wonMoney + "Coins");
                high_btn.setClickable(true);
                low_btn.setClickable(true);
                checkingLose();
                checkingHighscore();
            }

            else if (value != "LO")
            {
                txt_status.setText("You Lose : "+mBet + "Coins");
                txt_result.setText(transformOldNumber +" To " +transformNumber +": " +value);
            }
        }
        else
            txt_status.setText("You Lose : "+mBet + " Coins");
            checkingLose();
            /*txt_result.setText(transformOldNumber + transformNumber + value);*/
            high_btn.setClickable(true);
            low_btn.setClickable(true);
    }
    private void checkingHighscore()
    {
        if (mHighscore < currMoney)
        {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference mRef = database.getInstance().getReference();
            user1 = currUser;
            mHighscore = currMoney;
            Log.d("username", currUser);
            HighScoreActivityDatabase highScoreActivityDatabase = new HighScoreActivityDatabase(user1,currUser, mHighscore);
            mRef.child("Scoreboard").child(user1).setValue(highScoreActivityDatabase);
        }
    }
    private void checkingLose()
    {
        if (currMoney == 0)
        {
            Intent restartgame = new Intent(GameActivity.this, RestartActivity.class);
            restartgame.putExtra("currUser1",String.valueOf(currUser));
            restartgame.putExtra("mHighScore1",String.valueOf(mHighscore));
            startActivity(restartgame);
            finish();
        }
    }
    private void enableAll()
    {
        bg.setVisibility(View.GONE);
        btn_game.setVisibility(View.GONE);
        txt_nama.setVisibility(View.GONE);
        txt_kosong.setVisibility(View.GONE);

        generateCard();

        txtbet.isClickable();
        high_btn.isClickable();
        low_btn.isClickable();

        txtbet.setVisibility(View.VISIBLE);
        txt_status.setVisibility(View.VISIBLE);
        txt_currMoney.setVisibility(View.VISIBLE);
        txt_cardNumber.setVisibility(View.VISIBLE);
        txt_result.setVisibility(View.VISIBLE);
        txt_timer.setVisibility(View.VISIBLE);
        low_btn.setVisibility(View.VISIBLE);
        high_btn.setVisibility(View.VISIBLE);
        textView2.setVisibility(View.VISIBLE);
    }

}
