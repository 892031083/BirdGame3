package com.example.birdgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    GameView gameView;
    int Score=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        gameView=findViewById(R.id.gameView);
        gameView.setGameListener(new GameView.GameListener() {
            @Override
            public void addScore(final int score) {
                runOnUiThread(new Runnable() {//切换至UI现场
                    @Override
                    public void run() {
                        Score=score;
                        ((TextView)findViewById(R.id.score)).setText(""+score);
                    }
                });
            }

            @Override
            public void gameOver() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ((TextView)findViewById(R.id.scoreTxt)).setText("分数:"+Score);
                        findViewById(R.id.relative).setVisibility(View.VISIBLE);
                    }
                });
            }

            @Override
            public void gameReady() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        findViewById(R.id.relative).setVisibility(View.GONE);
                    }
                });
            }
        });
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //重新开始
               // findViewById(R.id.relative).setVisibility(View.GONE);
                gameView.reSet();//重新开始
                Score=0;
                ((TextView)findViewById(R.id.score)).setText(""+0);
            }
        });
    }
}
