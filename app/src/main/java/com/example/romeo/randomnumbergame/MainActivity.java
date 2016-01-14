package com.example.romeo.randomnumbergame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resetAnswer();
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

    public void check_guess(View view) {
        EditText text = (EditText)findViewById(R.id.guess);
        String guess = text.getText().toString();
        int number;
        if(guess.equals("")){
            Toast.makeText(MainActivity.this, "Don't be lazy! Type something!", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(isNumeric(guess)){
            number = Integer.parseInt(guess);
            handleNumber(number);
        } else {
            Toast.makeText(MainActivity.this, "\"" + guess + "\"" + " is not a number!", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleNumber(int number) {
        if (answer == 0){
            resetAnswer();
        }
        if(number > answer){
            Toast.makeText(MainActivity.this, number + " is too high!", Toast.LENGTH_SHORT).show();
        } else if(number < answer){
            Toast.makeText(MainActivity.this, number + " is too low!", Toast.LENGTH_SHORT).show();
        } else {
            displayWin();
        }
    }

    private void displayWin() {
        ImageView img = (ImageView) findViewById(R.id.image);
        img.setImageResource(R.drawable.winner);
        Toast.makeText(MainActivity.this, "You did it!", Toast.LENGTH_SHORT).show();
    }

    private boolean isNumeric(String guess) {
        for(int i = 0; i < guess.length(); i++){
            if(!Character.isDigit(guess.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public void newGame(View view) {
        resetAnswer();
        ImageView img = (ImageView) findViewById(R.id.image);
        img.setImageResource(0);
    }

    private void resetAnswer() {
        Random rand = new Random();
        answer = rand.nextInt(1000) + 1;
    }
}
