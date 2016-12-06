package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Main;
import com.mygdx.game.Sprites.Asteroid;
import com.mygdx.game.Sprites.Bird;
import com.mygdx.game.Sprites.Coin;
import com.mygdx.game.Sprites.Sun;

/**
 * Created by z_ig_ on 11/9/2016.
 */

public class StateManager {

    private ScoreManager scoreManager;
    private Bird bird;
    private Asteroid asteroid;
    private Sun sun;
    private Coin coin;
    private Sprite splashSprite;
    private boolean showSplash;
       private int tapCount;
    private BackgroundManager backgroundManager;
    private CameraManager cameraManager;

    public StateManager(Bird bird, Asteroid asteroid, Coin coin, CameraManager cameraManager,ScoreManager scoreManager,Sun sun,BackgroundManager backgroundManager)
    {
        this.backgroundManager = backgroundManager;
        this.sun = sun;
        this.scoreManager = scoreManager;
        tapCount=0;
        this.cameraManager = cameraManager;
            this.bird = bird;
            this.coin = coin;
            this.asteroid = asteroid;

        showSplash = true;
        splashSprite = new Sprite(new Texture(Gdx.files.internal("splash/logo.png")));
        splashSprite.setPosition(cameraManager.getCamWidth()/2 - splashSprite.getWidth()/2,cameraManager.getCamHeight()/2 - splashSprite.getHeight()/2);


       // scoreTexture = new Texture("");
    }

    public void handleState(int gamestate)
    {
        switch (gamestate)
        {

            case -1:       //SplashScreen

                if (cameraManager.getCamera().zoom >1.0f)
                {
                    cameraManager.getCamera().zoom -= .80f+Gdx.graphics.getDeltaTime();
        }else
                {
                    cameraManager.getCamera().zoom = 1.0f;
                    showSplash = false;
                    Main.gameState = 0;
                }

                break;

            case 0:                 //menu
                    Main.birdDead =true;
                    bird.setY(Main.cameraManager.getCamHeight()/2 - bird.getTextureRegion().getRegionHeight()/2);
                if (Gdx.input.justTouched())
                    tapCount++;
                if (tapCount >=1) {
                    Main.birdDead = false;
                    Main.gameState = 1;
                    Main.health =1;
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

    public boolean showSplashScreen(){return showSplash;}
    public Sprite getSplashSprite(){return splashSprite;}

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
        Main.bgSprite.setTexture(backgroundManager.getBackground());
        Main.health =1;
        bird.getSprite().setAlpha(1f);
        bird.startFlickerAnimation(false);
        asteroid.setFallSpeed(0);


    }





}
