package com.koningsiefker.statsapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    GameState gameState;

    ArrayList<Integer> numberButtons;
    ArrayList<Integer> colorButtons;

    //TODO: Use these to detemine the last buttons pressed, quicker than going in a list
    int lastNumberId = -1;
    int lastColorId = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameState = new GameState();
        buttonLists();

        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void onClick(View v){
        Button b = (Button) v;
        int id = v.getId();

        //If a number button was pushed
        if(numberButtons.contains(id)){
            //If lastNumberId == -1 there is no button currently toggled
            if(lastNumberId != -1){
                unToggleLastButton(lastNumberId);
            }
            lastNumberId = id;
            int i = Integer.parseInt(b.getText().toString());
        }
        //If a color button was pushed
        else if(colorButtons.contains(id)){
            if(lastColorId != -1){
                unToggleLastButton(lastColorId);
            }
            lastColorId = id;
        }
        if(lastColorId != -1 && lastNumberId != -1){
            updateCurrentRoll();
            findViewById(R.id.saveButton).setVisibility(View.VISIBLE);
            findViewById(R.id.rollText).setVisibility(View.VISIBLE);
        }

    }

    public void onSave(View v){
        gameState.increment(((ToggleButton)findViewById(lastNumberId)).getTextOff().toString(), ((ToggleButton)findViewById(lastColorId)).getTextOff().toString().toLowerCase());

        //Toggled buttons are cleared and the stored info on the last button pushed is reset.
        ((ToggleButton) findViewById(lastNumberId)).setChecked(false);
        ((ToggleButton) findViewById(lastColorId)).setChecked(false);
        lastNumberId = -1;
        lastColorId = -1;

        findViewById(R.id.saveButton).setVisibility(View.INVISIBLE);
        findViewById(R.id.rollText).setVisibility(View.INVISIBLE);
        findViewById(R.id.endButton).setVisibility(View.VISIBLE);

        ((TextView) findViewById(R.id.turnText)).setText("Turn: " + Integer.toString(gameState.getTurn()));
    }

    public void onEnd(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to end the game?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                endGame();
            }
        })
        .setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    private void endGame(){
        Intent i = new Intent(this, FinalStats.class);

        i.putExtra("turnCount", gameState.getTurn());
        i.putExtra("data", gameState.getGameInfo());
        startActivity(i);
    }

    //Creates a list of all the buttons so they can be toggled
    private void buttonLists(){
        numberButtons = new ArrayList<>();
        numberButtons.add(R.id.buttonTwo);
        numberButtons.add(R.id.buttonThree);
        numberButtons.add(R.id.buttonFour);
        numberButtons.add(R.id.buttonFive);
        numberButtons.add(R.id.buttonSix);
        numberButtons.add(R.id.buttonSeven);
        numberButtons.add(R.id.buttonEight);
        numberButtons.add(R.id.buttonNine);
        numberButtons.add(R.id.buttonTen);
        numberButtons.add(R.id.buttonEleven);
        numberButtons.add(R.id.buttonTwelve);

        colorButtons = new ArrayList<>();
        colorButtons.add(R.id.buttonBlack);
        colorButtons.add(R.id.buttonBlue);
        colorButtons.add(R.id.buttonGreen);
        colorButtons.add(R.id.buttonYellow);
    }

    //Untoggles the last button that was toggled, doesn't matter if it is
    //a color or number button, the onClick method determines which was pushed.
    private void unToggleLastButton(int id){
        ToggleButton temp = (ToggleButton)findViewById(id);
        temp.setChecked(false);
    }

    private void updateCurrentRoll(){
        TextView v = (TextView) findViewById(R.id.rollText);
        String number = ((ToggleButton) findViewById(lastNumberId)).getTextOff().toString();
        String color = ((ToggleButton) findViewById(lastColorId)).getTextOff().toString();

        v.setText(number + " " + color);
    }



}
