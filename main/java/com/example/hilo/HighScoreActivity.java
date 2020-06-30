package com.example.hilo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class HighScoreActivity extends AppCompatActivity
{
    ListView list_name,list_score;
    ArrayList<String> HsName = new ArrayList<>();
    ArrayList<Integer> HsScore = new ArrayList<>();
    ArrayAdapter<String> arrayAdapterName;
    ArrayAdapter<Integer> arrayAdapterScore;

    String name;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference mHsDb = firebaseDatabase.getReference().child("Scoreboard");

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore);

        list_name = (ListView) findViewById(R.id.name_hs);
        list_score = (ListView) findViewById(R.id.score_hs);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mHsDb = firebaseDatabase.getReference();


        mHsDb.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                getHs(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
    }
    public void getHs(DataSnapshot snapshot)
    {

        for (DataSnapshot uniquedb :snapshot.getChildren())
        {
            for (DataSnapshot mdb : uniquedb.getChildren())
            {

                mHsDb.orderByValue();
                name = String.valueOf(mdb.child("currUser").getValue());
                Log.d("Name : " ,name);
                int score = Integer.valueOf(String.valueOf(mdb.child("mHighScore").getValue()));
                Log.d("Score : " , String.valueOf(score));

                HsName.addAll(Collections.singleton(name));
                HsScore.addAll(Collections.singleton(score));
                Log.d("Size 1 : " , String.valueOf(HsName.size()));

            }
            arrayAdapterName = new ArrayAdapter<String>(HighScoreActivity.this,android.R.layout.simple_list_item_1,HsName);
            arrayAdapterScore = new ArrayAdapter<Integer>(HighScoreActivity.this,android.R.layout.simple_list_item_1,HsScore);
            arrayAdapterName.notifyDataSetChanged();
            arrayAdapterScore.notifyDataSetChanged();
            Log.d("Size 2 : " , String.valueOf(HsName.size()));

            list_name.setAdapter(arrayAdapterName);
            list_score.setAdapter(arrayAdapterScore);

            Log.d("Size 3 : " , String.valueOf(HsName.size()));
            Log.d("xx1 : " , String.valueOf(HsName));
            Log.d("xx2 : " , String.valueOf(HsScore));

            for (int i = 0;i <=HsScore.size()-1;i++)
            {
                Log.d("i "+i,": " + i);
                for (int j = i+1 ;j <= HsScore.size()-1;j++)
                {
                    Log.d("j "+j,": " + j);
                    if (HsScore.get(i) < HsScore.get(j))
                    {
                        Log.d("i2 "+(i),": " + (i));
                        Log.d("j2 "+(j),": " + (j));
                        int temp1 = HsScore.get(i);
                        HsScore.set(i,HsScore.get(j));
                        HsScore.set(j,temp1);

                        String temp2= HsName.get(i);
                        HsName.set(i,HsName.get(j));
                        HsName.set(j,temp2);
                        Log.d("zz2 : " , String.valueOf(HsScore));
                        if (j>=1)
                        for (int l =j;l<=1;l--)
                        {
                            for(int m =l-1;m<=0;m--)
                            {
                                if (HsScore.get(m) < HsScore.get(l))
                                {
                                     temp1 = HsScore.get(m);
                                    HsScore.set(m,HsScore.get(l));
                                    HsScore.set(l,temp1);

                                    temp2= HsName.get(m);
                                    HsName.set(m,HsName.get(l));
                                    HsName.set(l,temp2);
                                    Log.d("yy3 : " , String.valueOf(HsScore));
                                }
                            }
                        }
                    }

                }
            }
            Log.d("zz1 : " , String.valueOf(HsName));
            Log.d("zz2 : " , String.valueOf(HsScore));

        }

    }
}
