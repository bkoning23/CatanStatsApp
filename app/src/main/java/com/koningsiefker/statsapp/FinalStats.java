package com.koningsiefker.statsapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;


public class FinalStats extends ActionBarActivity {

    HashMap<String, Integer> gameInfo;
    int turnCount;
    private List<String> keys = Arrays.asList("two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "blue", "black", "green", "yellow");
    private HashMap<String, String> numerals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_stats);
        Intent i = getIntent();
        gameInfo = (HashMap<String, Integer>) i.getSerializableExtra("data");
        turnCount = i.getIntExtra("turnCount", -1);
        fillNumerals();
        setValues();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_final_stats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setValues(){
        for(String key : keys){
            float value;
            float percent;
            String keyNumeral;
            if(numerals.containsKey(key)){
                keyNumeral = numerals.get(key);
                if(!gameInfo.containsKey(keyNumeral)){
                    value = 0;
                    percent = 0;
                }
                else{
                    value = gameInfo.get(keyNumeral);
                    percent = (value / (turnCount));
                }
            }
            else{
                if(!gameInfo.containsKey(key)){
                    value = 0;
                    percent = 0;
                }
                else{
                    value = gameInfo.get(key);
                    percent = value / (turnCount);
                }
            }
            percent = percent * 100.0f;
            int countId = getResources().getIdentifier(key + "Count", "id", getPackageName());
            int percentId = getResources().getIdentifier(key + "Percent", "id", getPackageName());
            ((TextView) findViewById(countId)).setText(Integer.toString(Math.round(value)));
            ((TextView) findViewById(percentId)).setText(String.format("%.2f", percent) + "%");

        }
    }

    private void fillNumerals(){
        numerals = new HashMap<>();
        numerals.put("two", "2");
        numerals.put("three", "3");
        numerals.put("four", "4");
        numerals.put("five", "5");
        numerals.put("six", "5");
        numerals.put("seven", "7");
        numerals.put("eight", "8");
        numerals.put("nine", "9");
        numerals.put("ten", "10");
        numerals.put("eleven", "11");
        numerals.put("twelve", "12");
    }
}
