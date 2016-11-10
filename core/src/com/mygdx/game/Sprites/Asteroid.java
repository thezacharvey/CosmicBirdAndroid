package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.SpaceBird;

import java.util.Random;

/**
 * Created by z_ig_ on 11/9/2016.
 */

public class Asteroid extends SpaceBird {

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
    private Vector2 originXY;
    public Asteroid(OrthographicCamera camera)
    {
        random = new Random();

        asteroid = new Texture("asteroid.png");
        asteroidHeight = asteroid.getHeight();
        asteroidWidth = asteroid.getWidth();
        cameraHeight = camera.viewportHeight;
        cameraWidth = camera.viewportWidth;
        velocity = cameraWidth *1.40f;
        circle = new Circle();
        newAsteroidY = asteroidY(0);
        asteroidX = cameraWidth + asteroidWidth;
        originXY = new Vector2(asteroidX,newAsteroidY);
    }

    @Override
    public void update(float dt) {

        if (!birdDead)
        {
            if (asteroidX <= -asteroidWidth)
            {
                asteroidX= cameraWidth + asteroidWidth;
                newAsteroidY = asteroidY(newAsteroidY);
            }
            asteroidX -=velocity*dt;
            circle.set(getX()+asteroidWidth/2, newAsteroidY+asteroidHeight/2,getWidth()/2);
        }

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
    public Texture getTexture(){return asteroid;}
    public float getWidth(){return asteroidWidth;}
    public Vector2 getOriginXY(){ return originXY;}
}
