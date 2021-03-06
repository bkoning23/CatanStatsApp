package com.koningsiefker.statsapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;


public class MainActivity extends ActionBarActivity {

    GameState gameState;

    ArrayList<Integer> numberButtons;
    ArrayList<Integer> colorButtons;

    //TODO: Use these to detemine the last buttons pressed, quicker than going in a list
    int lastNumberId = -1;
    int lastColorId = -1;

    Stack turnStack = new Stack();

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
        else if(id == R.id.newGame){
            newGame();
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

    public void onClickUndo(View v){
        if(v.getId() == R.id.undoButton){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to undo?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    undo();
                }
            })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            builder.show();
        }
    }

    public void onSave(View v){

        String color = ((ToggleButton)findViewById(lastColorId)).getTextOff().toString().toLowerCase();
        String number = ((ToggleButton)findViewById(lastNumberId)).getTextOff().toString();

        gameState.increment(number, color);

        //Toggled buttons are cleared and the stored info on the last button pushed is reset.
        ((ToggleButton) findViewById(lastNumberId)).setChecked(false);
        ((ToggleButton) findViewById(lastColorId)).setChecked(false);
        lastNumberId = -1;
        lastColorId = -1;

        findViewById(R.id.saveButton).setVisibility(View.INVISIBLE);
        findViewById(R.id.rollText).setVisibility(View.INVISIBLE);
        findViewById(R.id.endButton).setVisibility(View.VISIBLE);

        updateTurnCount();
        setPreviousTurnField(color, number);

        findViewById(R.id.undoButton).setVisibility(View.VISIBLE);

        turnStack.push(new Pair(color, number));

        updatePirateText();

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

    public void onCheck(View v){
        checkStats();
    }

    private void endGame(){
        Intent i = new Intent(this, FinalStats.class);
        i.putExtra("turnCount", gameState.getTurn());
        i.putExtra("data", gameState.getGameInfo());
        gameState = new GameState();
        startActivity(i);
    }

    private void checkStats(){
        Intent i = new Intent(this, FinalStats.class);
        i.putExtra("turnCount", gameState.getTurn());
        i.putExtra("data", gameState.getGameInfo());
        startActivity(i);
    }

    private void undo(){

        turnStack.pop();

        try{
            Pair temp = (Pair) turnStack.peek();
            String color = (String) temp.first;
            String number = (String) temp.second;
            setPreviousTurnField(color, number);
            gameState.decrement(number, color);
        }
        catch(EmptyStackException e){
            setPreviousTurnField("No Previous Turns", "");
            findViewById(R.id.undoButton).setVisibility(View.INVISIBLE);
            gameState = new GameState();
        }


        updateTurnCount();

        updatePirateText();

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

    private void setPreviousTurnField(String color, String number){
        ((TextView) findViewById(R.id.lastTurnText)).setText("Last Turn: " + color + " " + number);
    }

    private void updateTurnCount(){
        ((TextView) findViewById(R.id.turnText)).setText("Turn: " + Integer.toString(gameState.getTurn()));
    }

    private void updatePirateText(){
        if(gameState.getPirateCounter() == 6){
            findViewById(R.id.pirateAttackText).setVisibility(View.VISIBLE);
        }
        else{
            findViewById(R.id.pirateAttackText).setVisibility(View.INVISIBLE);
        }
    }

    private void newGame() {
        gameState = new GameState();
        buttonLists();
        updateTurnCount();
        updatePirateText();
        findViewById(R.id.saveButton).setVisibility(View.INVISIBLE);
        findViewById(R.id.rollText).setVisibility(View.INVISIBLE);
        setPreviousTurnField("No Previous Turns", "");
    }

}
