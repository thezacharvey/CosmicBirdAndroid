package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
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

public class Asteroid {

    private Texture asteroid,asteroid2;
    private Circle asteroidCircle[];
    private float  asteroidX;
    private Random random;
    private float cameraHeight;
    private float asteroidHeight;
    private float asteroidWidth;
    private float cameraWidth;
    private float newAsteroidY;
    private  float velocity;
    private Sprite sprite[];
    private Vector2 originXY;
    private Main main;
    private float fallSpeed;

    public Asteroid(OrthographicCamera camera,Main main)
    {
        fallSpeed = 0f;
        random = new Random();
        asteroid2 = new Texture(Gdx.files.internal("christmas/asteroid2christmas.png"));
        asteroid = new Texture(Gdx.files.internal("christmas/asteroidsnow.png"));
        asteroidHeight = asteroid.getHeight();
        asteroidWidth = asteroid.getWidth();

        this.main = main;

        cameraHeight = camera.viewportHeight;
        cameraWidth = camera.viewportWidth;
        velocity = cameraWidth *1.40f;

        newAsteroidY = asteroidY(0);
        asteroidX = cameraWidth + asteroidWidth;
        originXY = new Vector2(asteroidX,newAsteroidY);

        sprite = new Sprite[2];
        sprite[0] = new Sprite(asteroid);
        sprite[1] = new Sprite(new Texture(Gdx.files.internal("christmas/metorite.png")));
        sprite[0].setOrigin(asteroidWidth/2,asteroidHeight/2);
        sprite[1].setOrigin(sprite[1].getWidth()/2,sprite[1].getHeight()/2);
        sprite[1].scale(-.5f);


        asteroidCircle = new Circle[2];


        for (int i =0; i < asteroidCircle.length; i++)
        {
            asteroidCircle[i] = new Circle();
           asteroidCircle[i].setRadius(sprite[i].getWidth()/2);
        }


    }


    public void update(float dt) {

//        if (asteroidX <= -asteroidWidth) {
//                    asteroidX = cameraWidth + asteroidWidth;
//                    newAsteroidY = asteroidY(newAsteroidY);
//
//        }


            float rotate = 1.55f;
            float moveSpeed = 1f;

            for (int i=0; i <sprite.length;i++)
            {


                if (sprite[i].getX() < -sprite[i].getWidth())
                {
                    sprite[i].setX(cameraWidth + sprite[i].getWidth());
                    sprite[i].setY(asteroidY(sprite[i].getY()));
                    main.setCanGiveDamage(true);
                }
                if (i>0)
                {
                    moveSpeed = .5f;
                    rotate=-1.55f;
                    asteroidCircle[1].setRadius(sprite[1].getWidth()/2.75f);
                    //fallSpeed = -.15f;
                    sprite[1].translateY(fallSpeed);
                }
                sprite[i].rotate(rotate);
                sprite[i].translateX((-velocity *dt )* moveSpeed);
                //sprite[i].translateY(fallSpeed);
                asteroidCircle[i].setPosition(sprite[i].getX() + sprite[i].getWidth()/2,sprite[i].getY() + sprite[i].getHeight()/2);
                //asteroidCircle[i].setRadius(sprite[i].getWidth()/2);
            }









    }

    public void setFallSpeed(float f){fallSpeed = f;}



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


    public void dispose() {
        asteroid.dispose();
        asteroid2.dispose();
        sprite[1].getTexture().dispose();
    }
    public void setX(float x){ asteroidX = x;}
    public float getX(){return asteroidX;}
    public float getY(){return  newAsteroidY;}
    public Circle[] getCircleArr(){return asteroidCircle;}
    public Sprite[] getSpriteArr(){return sprite;}
    public float getHeight(){return asteroidHeight;}
    public float getWidth(){return asteroidWidth;}
    public Vector2 getOriginXY(){ return originXY;}
    public void setAsteroidTexture(int a)
    {
        switch (a)
        {
            case 0:
                sprite[0].setTexture(asteroid);
                sprite[0].setScale(1f,1f);
                velocity =  cameraWidth *.85f;
                break;
            case 1:

                    sprite[0].setTexture(asteroid2);
                    sprite[0].setScale(1.45f,1.45f);
                    velocity =  cameraWidth *1.15f;
                break;

        }
    }
}
