package com.nehvin.s04e49connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0 = yellow, 1 = red
    int activePlayer = 0;
    //setting each postion in the array to 2 to indicate unsed space
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    //below is a collection of possible winning positions
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    Button rematch = null;
    boolean gameIsActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rematch = (Button) findViewById(R.id.button2);
    }

    public void rematch(View view)
    {
        rematch.setVisibility(View.INVISIBLE);
        gameIsActive = true;
        activePlayer = 0;
        for (int i = 0 ; i < gameState.length; i++)
        {
            gameState[i] = 2;
        }

        GridLayout playerBoard = (GridLayout) findViewById(R.id.playerBoard);
        for(int i = 0; i < playerBoard.getChildCount(); i++)
        {
            ((ImageView)playerBoard.getChildAt(i)).setImageResource(0);
        }
    }

    public void dropIn(View view)
    {
        ImageView counter = (ImageView) view;
        int tag = Integer.parseInt(counter.getTag().toString());

        if(gameState[tag] == 2 && gameIsActive)
        {
            counter.setTranslationY(-1500f);
            gameState[tag] = activePlayer;
            if(activePlayer == 0)
            {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            }
            else
            {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500f).setDuration(300);

        }

        for(int[] winningPostion : winningPositions)
        {
            if(gameState[winningPostion[0]] == gameState[winningPostion[1]] &&
                    gameState[winningPostion[1]] == gameState[winningPostion[2]] &&
                    gameState[winningPostion[0]] != 2)
            {
                gameIsActive = false;
                rematch.setVisibility(View.VISIBLE);
                if (gameState[winningPostion[0]]==1)
                {
                    Toast.makeText(this, "Game Over, Red has won", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, "Game Over, Yellow has won", Toast.LENGTH_SHORT).show();
                }
            }
//            else
//            {
//            }
        }
        boolean isGameOver = true;
        for(int counterState : gameState)
        {
            if(counterState == 2) {
                isGameOver = false;
                break;
            }
        }
        if(isGameOver && gameIsActive)
        {
            gameIsActive = false;
            rematch.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Game Over, It is a draw", Toast.LENGTH_SHORT).show();
        }

    }
}