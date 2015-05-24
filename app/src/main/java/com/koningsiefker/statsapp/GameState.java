package com.koningsiefker.statsapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Brendan on 5/15/2015.
 */
public class GameState implements Serializable{

    private ArrayList<Integer> counters;
    private int turn;
    private HashMap<String, Integer> gameInfo;

    public GameState(){
        counters = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0));
        gameInfo = new HashMap<>();
        turn = 0;
    }


    public void increment(String number, String color){
        if(!gameInfo.containsKey(number)){
            gameInfo.put(number, 1);
        }
        else {
            int numberCount = gameInfo.get(number) + 1;
            gameInfo.remove(number);
            gameInfo.put(number, numberCount);
        }

        if(!gameInfo.containsKey(color)){
            gameInfo.put(color, 1);
        }
        else{
            int colorCount = gameInfo.get(color) + 1;
            gameInfo.remove(color);
            gameInfo.put(color, colorCount);
        }
        turn++;
    }

    public int getTurn(){
        return turn;
    }

    public HashMap getGameInfo(){
        return gameInfo;
    }

}
