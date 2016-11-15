package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Main;
import com.mygdx.game.Sprites.Asteroid;
import com.mygdx.game.Sprites.Coin;
import com.mygdx.game.Sprites.ScoreMultiplier;

/**
 * Created by z_ig_ on 11/10/2016.
 */

public class ScoreManager {
    private Coin coin;
    private int score;
    private boolean increaseSpeed;
    private int highScore;
    private Asteroid asteroid;
    private boolean newHighScore;

    private Preferences preferences;
    public ScoreManager(int score, Coin coin,Asteroid asteroid)
    {
        this.coin = coin;
        this.score = score;
            newHighScore = false;
        increaseSpeed = false;
            this.asteroid = asteroid;
            asteroid.setAsteroidTexture(0);
            preferences = Gdx.app.getPreferences("My Preferences");
            highScore = preferences.getInteger("highScore",0);

/*
        gameOverTexture = new Texture(Gdx.files.internal("gameover.png"));
        gameOverSprite = new Sprite(gameOverTexture);
        gameOverSprite.setX("")*/
    }

    public void update(int s) {
        score = s;

        if (score % 6 ==0)
        {
            increaseSpeed = true;
        }
        if (score% 9 ==0)
        {
            increaseSpeed = false;
        }

        if (highScore < score) {
            newHighScore = true;
        }
        if (s >=80 ) {
            asteroid.setAsteroidTexture(1);
        }

        if (highScore < score && Main.gameState == 1) {
            highScore = score;
            preferences.putInteger("highScore", highScore);
            preferences.flush();
        }

        if (increaseSpeed) {
            coin.setVelocity(1.65f);
        }
        if (!increaseSpeed)
        {
            coin.setVelocity(.95f);
        }




    }


    public boolean gotNewHighScore(){return newHighScore;}
    public void setNewHighScore(boolean hs){newHighScore = hs;}
    public Preferences getPreferences() {
        return preferences;
    }
}
