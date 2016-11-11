package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.SpaceBird;
import com.mygdx.game.Sprites.Asteroid;
import com.mygdx.game.Sprites.Bird;
import com.mygdx.game.Sprites.Coin;

/**
 * Created by z_ig_ on 11/9/2016.
 */

public class StateManager {


    private Texture texture;
    private boolean gameStarted;
    private Bird bird;
    private Asteroid asteroid;
    private Coin coin;
       private int tapCount;
    private CameraManager cameraManager;

    public StateManager(Bird bird, Asteroid asteroid, Coin coin, CameraManager cameraManager)
    {
        tapCount=0;
        this.cameraManager = cameraManager;
            this.bird = bird;
            this.coin = coin;
            this.asteroid = asteroid;

       // texture = new Texture("");
    }

    public void handleState(int gamestate)
    {
        switch (gamestate)
        {
            case 0:                 //menu
                    SpaceBird.birdDead =true;
                    bird.setY(SpaceBird.cameraManager.getCamHeight()/2 - bird.getTextureRegion().getRegionHeight()/2);
                if (Gdx.input.justTouched())
                    tapCount++;
                if (tapCount >=1) {
                    SpaceBird.birdDead = false;
                    SpaceBird.gameState = 1;
                    tapCount=0;
                }
                break;
            case 1: //playing
                break;
            case 2:
                    bird.applyGravity(); //dead
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
        SpaceBird.birdDead =false;
        tapCount =0;
        bird.setY(bird.getOriginXY().y + bird.getHeight()*2);
        asteroid.setX(asteroid.getOriginXY().x);
        coin.setX(coin.getOriginXY().x);
        coin.setVelocity(1.15f);
        SpaceBird.score = 0;
        SpaceBird.gameState = 1;
        bird.setVelocity(0);
    }





}
