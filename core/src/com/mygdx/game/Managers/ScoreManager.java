package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Main;
import com.mygdx.game.Sprites.Coin;
import com.mygdx.game.Sprites.ScoreMultiplier;

/**
 * Created by z_ig_ on 11/10/2016.
 */

public class ScoreManager {
    private Coin coin;
    private int score;
    private int highScore;
    private Sprite gameOverSprite;
    private Texture gameOverTexture;
    private Preferences preferences;
    public ScoreManager(int score, Coin coin)
    {
        this.coin = coin;
        this.score = score;

        if (preferences == null) {
            preferences = Gdx.app.getPreferences("My Preferences");
            highScore = 0;
           // preferences.putInteger("highscore", highScore);
        }else
        {
            highScore = getPreferences().getInteger("highScore");
        }

/*
        gameOverTexture = new Texture(Gdx.files.internal("gameover.png"));
        gameOverSprite = new Sprite(gameOverTexture);
        gameOverSprite.setX("")*/
    }

    public void update(int s) {
        score = s;
        if (highScore < score && Main.gameState ==1) {
            highScore = score;
            preferences.putInteger("highScore", highScore);
            preferences.flush();
        }
       switch (score % 2)
       {
           case 5:
                coin.setVelocity(2);
               break;
           case 10:
               coin.setVelocity(.95f);
               break;
           case 15:
               coin.setVelocity(2);
               break;
           case 20:
               coin.setVelocity(.95f);
               break;
           case 30:
               coin.setVelocity(2.15f);
               break;
           default:
               break;
       }

    }

    public int getScore(){return  score;}
    public int getHighScore(){return highScore;}
    public Preferences getPreferences() {
        return preferences;
    }
    //public Sprite getSprite(return gameSprite;);
}
