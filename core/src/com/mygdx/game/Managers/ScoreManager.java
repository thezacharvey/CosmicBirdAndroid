package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.Sprites.Coin;

/**
 * Created by z_ig_ on 11/10/2016.
 */

public class ScoreManager {
    private Coin coin;
    private int score;
    private int highScore;
    private Preferences preferences;
    private OrthographicCamera camera;
    public ScoreManager(int score, OrthographicCamera camera, Coin coin)
    {
        this.coin = coin;
        this.camera = camera;
        this.score = score;
        preferences = Gdx.app.getPreferences("My Preferences");

    }

    public void update(int s) {
        score = s;
       switch (score)
       {
           case 5:
                coin.setVelocity(2);
               break;
           case 10:
               coin.setVelocity(1.15f);
               break;
           case 15:
               coin.setVelocity(2);
               break;
           case 20:
               coin.setVelocity(1.15f);
               break;

           default:
               break;
       }

    }

    public int getScore(){return  score;}
}
