package com.example.hilo;

public class HighScoreActivityDatabase
{
    String currUser,user1;
    int mHighScore;
    public HighScoreActivityDatabase(String user1, String currUser, int mHighscore)
    {
        this.currUser = currUser;
        this.mHighScore = mHighscore;
    }
    public String getUser1()
    {
        return user1;
    }

    public String getCurrUser() {
        return currUser;
    }
    public int getmHighScore(){
        return mHighScore;
    }
}
