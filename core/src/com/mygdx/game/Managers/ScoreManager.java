package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Main;
import com.mygdx.game.Sprites.Asteroid;
import com.mygdx.game.Sprites.Coin;
import com.mygdx.game.Sprites.Heart;
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
    private boolean canDrawHeart;

    private CameraManager cameraManager;

    private Preferences preferences;
    public ScoreManager(int score, Coin coin, Asteroid asteroid,CameraManager cameraManager)
    {
        canDrawHeart =false;

        this.cameraManager = cameraManager;
        this.coin = coin;
        this.score = score;
        this.asteroid = asteroid;

        newHighScore = false;
        increaseSpeed = false;

        asteroid.setAsteroidTexture(0);
        preferences = Gdx.app.getPreferences("My Preferences");
        highScore = preferences.getInteger("highScore",0);

    }

    public void update() {
        score = Main.score;

        if (score % 6 ==0)
        {
            increaseSpeed = true;
        }
        if (score% 9 ==0)
        {
            increaseSpeed = false;
        }
        if (Main.score%12==0 && Main.score!=0)
        {
            canDrawHeart = true;
        }
        if (Main.health >=3)
        {
            canDrawHeart =false;
        }

        if (score >=1500 ) {
            asteroid.setAsteroidTexture(1);
        }
        if (score > 300 && score <1250f)
        {
            asteroid.setFallSpeed(-.25f);
        }else
        {
            asteroid.setFallSpeed(0);

        }


        if (highScore < score && Main.gameState == 1) {
            highScore = score;
            newHighScore = true;
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

    public int getHighScore(){return highScore;}
    public int getScore(){return score;}
    public boolean gotNewHighScore(){return newHighScore;}
    public void setNewHighScore(boolean hs){newHighScore = hs;}
    public boolean heartIsDrawable(){return canDrawHeart;}
    public void setHeartDrawable(boolean b){ canDrawHeart = b;}
    public Preferences getPreferences() {
        return preferences;
    }
}
