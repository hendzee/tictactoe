package com.hendzee.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
    int[] seedState = {3, 3, 3, 3, 3, 3, 3, 3, 3};
    boolean activeGame = true;
    String popUpMessage = "";
    int countTurn = 0;

    public void playGame(View view){
        ImageView seed = (ImageView) view;
        int[][] winerPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6},
                {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

        int selectedPosition = Integer.parseInt(seed.getTag().toString());

        if (seedState[selectedPosition] == 3 && activeGame == true){
            seed.setTranslationY(-1000f);
            seed.setAlpha(1f);

            seedState[selectedPosition] = activePlayer;

            if (activePlayer == 0){
                seed.setImageResource(R.drawable.mushroom);
                activePlayer = 1;
            } else {
                seed.setImageResource(R.drawable.acorn);
                activePlayer = 0;
            }

            seed.animate().translationYBy(1000f).setDuration(300);

            for (int[] position: winerPosition) {
                if (seedState[position[0]] == seedState[position[1]] && seedState[position[1]] ==
                        seedState[position[2]] && seedState[position[0]] != 3){
                    activeGame = false;

                    if (seedState[position[0]] == 0){
                        popUpMessage = "Mushroom WIN";
                    }else{
                        popUpMessage = "Acorn WIN";
                    }
                }
            }

            countTurn+=1;
        }

        if (countTurn == 9  && activeGame == true){
            activeGame =  false;
            popUpMessage = "DRAW";
        }

        if (activeGame == false){
            LinearLayout layoutPopup = (LinearLayout) findViewById(R.id.LinearLayoutPopup);
            TextView message = (TextView) findViewById(R.id.textViewTPopup);

            message.setText(popUpMessage);
            layoutPopup.setVisibility(View.VISIBLE);
            layoutPopup.animate().alpha(1f).setDuration(500);
        }
    }

    public void playAgain(View view){
        activePlayer = 0;
        activeGame = true;
        countTurn = 0;
        LinearLayout layoutPopup = (LinearLayout)findViewById(R.id.LinearLayoutPopup);
        android.support.v7.widget.GridLayout layoutBoard = (android.support.v7.widget.GridLayout)findViewById(R.id.GridLayoutBoard);

        layoutPopup.setVisibility(View.INVISIBLE);

        for (int counter=0; counter<seedState.length; counter++){
            seedState[counter] = 3;
        }

        for (int counter=0; counter<layoutBoard.getChildCount(); counter++){
            ((ImageView) layoutBoard.getChildAt(counter)).setImageResource(R.drawable.dry_leaf);
            ((ImageView) layoutBoard.getChildAt(counter)).setAlpha(0.5f);
        }

        System.out.println("Ready for use");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
