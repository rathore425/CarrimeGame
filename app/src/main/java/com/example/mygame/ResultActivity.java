package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    int highestScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextView scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        TextView highScoreLabel = (TextView) findViewById(R.id.highScoreLabel);
//        TextView gamesPlayedLabel = (TextView) findViewById(R.id.gamesPlayedLabel);

        int score = getIntent().getIntExtra("Score", 0);
        scoreLabel.setText("" +score);

        SharedPreferences preferencesScore = this.getSharedPreferences("HIGHESTSCORE", Context.MODE_PRIVATE);
        highestScore = preferencesScore.getInt("HIGHESTSCORE", highestScore);

        if(score >= highestScore){
            highScoreLabel.setText("Highest Score: " + score);

            SharedPreferences.Editor editor = preferencesScore.edit();
            editor.putInt("HIGHESTSCORE", score);
            editor.commit();
        }
        else {
            highScoreLabel.setText("HIGHEST SCORE: " + highestScore);
        }

//        SharedPreferences preferencesGames = getSharedPreferences("GAMES", Context.MODE_PRIVATE);
//        int games = preferencesGames.getInt("GAMES", 0);
//
//        gamesPlayedLabel.setText("GAMES PLAYED: " + (games + 1));
//
//        SharedPreferences.Editor editor = preferencesGames.edit();
//        editor.putInt("GAMES", (games + 1));
//        editor.commit();

    }

    public  void tryAgain(View view){
        startActivity(new Intent(getApplicationContext(), StartActivity.class));
        finish();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        if(event.getAction() == KeyEvent.ACTION_DOWN){
            switch (event.getKeyCode()){
                case KeyEvent.KEYCODE_BACK:
                    return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }
}
