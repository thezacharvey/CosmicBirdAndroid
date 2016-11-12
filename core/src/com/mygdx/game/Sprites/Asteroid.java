package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Main;

import java.util.Random;

/**
 * Created by z_ig_ on 11/9/2016.
 */

public class Asteroid extends Main {

    private Texture asteroid;
    private Circle circle;
    private float  asteroidX;
    private Random random;
    private float cameraHeight;
    private float asteroidHeight;
    private float asteroidWidth;
    private float cameraWidth;
    private float newAsteroidY;
    private  float velocity;
    private Sprite sprite;
    private Vector2 originXY;
    public Asteroid(OrthographicCamera camera)
    {
        random = new Random();

        asteroid = new Texture("asteroid.png");
        asteroidHeight = asteroid.getHeight();
        asteroidWidth = asteroid.getWidth();

        sprite = new Sprite(asteroid);

        cameraHeight = camera.viewportHeight;
        cameraWidth = camera.viewportWidth;
        velocity = cameraWidth *1.40f;
        circle = new Circle();
        newAsteroidY = asteroidY(0);
        asteroidX = cameraWidth + asteroidWidth;
        originXY = new Vector2(asteroidX,newAsteroidY);
        sprite.setOrigin(asteroidWidth/2,asteroidHeight/2);

    }

    @Override
    public void update(float dt) {


                if (asteroidX <= -asteroidWidth) {
                    asteroidX = cameraWidth + asteroidWidth;
                    newAsteroidY = asteroidY(newAsteroidY);

                }
            asteroidX -= velocity * dt;

            sprite.setY(getY()); sprite.setX(getX());
            sprite.rotate(1.55f);
            circle.set(getX() + sprite.getWidth() / 2, newAsteroidY + sprite.getHeight() / 2, getWidth() / 2);


    }



    private int asteroidY(float prev)
    {
        int prevY =(int) prev;
        int aY  = random.nextInt((int)cameraHeight);
        if (prevY==aY) {aY += asteroidHeight;}
        int camH = (int)cameraHeight;
        int aH = (int)asteroidHeight;                       //Randomizes asteroid Y
        if (aY >= camH-aH) {
            aY = camH - aH;
            if (prevY==newAsteroidY) newAsteroidY-=aH;
        }
        if (aY <= aH) {
            aY = aH;
            if (prevY==aY) aY+=aH;
        }

        return aY;
    }

    @Override
    public void dispose() {
        asteroid.dispose();

    }
    public void setX(float x){ asteroidX = x;}
    public float getX(){return asteroidX;}
    public float getY(){return  newAsteroidY;}
    public Circle getCircle(){return circle;}
    public Sprite getSprite(){return sprite;}
    public float getHeight(){return asteroidHeight;}
    public float getWidth(){return asteroidWidth;}
    public Vector2 getOriginXY(){ return originXY;}
}
