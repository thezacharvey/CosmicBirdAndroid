package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Main;
import com.mygdx.game.Sprites.Asteroid;
import com.mygdx.game.Sprites.Bird;
import com.mygdx.game.Sprites.Coin;
import com.mygdx.game.Sprites.Sun;

/**
 * Created by z_ig_ on 11/9/2016.
 */

public class StateManager {


    private Texture texture;
    private boolean gameStarted;
    private ScoreManager scoreManager;
    private Bird bird;
    private Asteroid asteroid;
    private Sun sun;
    private Coin coin;
       private int tapCount;
    private CameraManager cameraManager;

    public StateManager(Bird bird, Asteroid asteroid, Coin coin, CameraManager cameraManager,ScoreManager scoreManager,Sun sun)
    {
        this.sun = sun;
        this.scoreManager = scoreManager;
        tapCount=0;
        this.cameraManager = cameraManager;
            this.bird = bird;
            this.coin = coin;
            this.asteroid = asteroid;

       // scoreTexture = new Texture("");
    }

    public void handleState(int gamestate)
    {
        switch (gamestate)
        {
            case 0:                 //menu
                    Main.birdDead =true;
                    bird.setY(Main.cameraManager.getCamHeight()/2 - bird.getTextureRegion().getRegionHeight()/2);
                if (Gdx.input.justTouched())
                    tapCount++;
                if (tapCount >=1) {
                    Main.birdDead = false;
                    Main.gameState = 1;
                    tapCount=0;

                }
                break;
            case 1: //playing
                break;
            case 2:
                    bird.applyGravity(Gdx.graphics.getDeltaTime()); //dead

                if (Gdx.input.justTouched())
                    tapCount++;
                if (tapCount >=3)
                {
                    resetGame();
                }
                break;

            default:
                break;
        }
    }

    private void resetGame()
    {
        Main.birdDead =false;
        tapCount =0;
        bird.setY(bird.getOriginXY().y + bird.getHeight()*2);
        asteroid.setX(asteroid.getOriginXY().x);
        coin.setX(coin.getOriginXY().x);
        coin.setVelocity(1.15f);
        Main.score = 0;
        scoreManager.setNewHighScore(false);
        Main.gameState = 1;
        bird.setVelocity(0);
        bird.getSprite().setRotation(0f);
        Main.gameSprite.setScale(0.5f,0.5f);
        asteroid.setAsteroidTexture(0);
        sun.getSunSprite().setY(-55f);
        sun.getRectangle().setY(sun.getSunSprite().getY() - sun.getSunSprite().getHeight()/2);
        sun.setHasVibrated(false);
        Main.hasPlayed = false;
    }





}
