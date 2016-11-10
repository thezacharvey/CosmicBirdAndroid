package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.mygdx.game.Animation;
import com.mygdx.game.SpaceBird;

import java.util.Random;

/**
 * Created by z_ig_ on 11/8/2016.
 */

public class Coin extends SpaceBird {

    private Texture star;
    private Animation animation;
    static float coinX;
    private float cameraWidth;
    private float cameraHeight;
    private float velocity;
    private float starWidth;
    private float starHeight;
    private int intHeight;
    private Circle circle;
    private float newStarY;
    private Random random;
    private boolean collided;
   // private Sprite sprite;

    public Coin(OrthographicCamera camera) {

        collided =false;
        random =new Random();
        intHeight = (int)camera.viewportHeight;
        intHeight = random.nextInt(intHeight);

        cameraWidth = camera.viewportWidth;
        cameraHeight = camera.viewportHeight;


        star = new Texture("star.png");
        animation = new Animation(new TextureRegion(star), 3, 0.35f);
        starHeight = animation.getFrame().getRegionHeight();
        starWidth = animation.getFrame().getRegionWidth();
        coinX = cameraWidth + starWidth;
        newStarY = starY(0);
        velocity = cameraWidth *1.2f;
        circle = new Circle();
       // circle.set(starX,starY,starWidth/2);

    }



    @Override
    public void update(float dt) {
        if (!birdDead)
        {
            animation.update(dt);
            if (coinX <= -starWidth)
            {
                newStarY = starY(newStarY);
                coinX = cameraWidth+animation.getFrame().getRegionWidth();
                collided =false;
            }
            coinX-=velocity*dt;
            circle.set(getX()+starWidth/2,getY()+getHeight()/2,getWidth()/2);
        }


    }

    private int starY(float prev)
    {
        int prevY =(int) prev;
        int coinY  = random.nextInt((int)cameraHeight);
        if (prevY==coinY) {coinY += starHeight;}
        int camH = (int)cameraHeight;
        int coinH = (int)starHeight;
        if (coinY >= camH-coinH) {
            coinY = camH - coinH;
            if (prevY==coinY) coinY-=coinH;
        }
        if (coinY <= coinH) {
            coinY = coinH;
            if (prevY==coinY) coinY+=coinH;
        }

        return coinY;
    }

    @Override
    public void dispose() {
        star.dispose();
    }

    public TextureRegion getTextureRegion() {return animation.getFrame();}
    public float getX(){return coinX;}
    public float getWidth(){return starWidth;}
    public float getHeight(){return starHeight;}
    public float getY(){return newStarY;}
    public Circle getCircle(){return circle;}
    public boolean getCollision() {return collided;}
    public void setCollision(boolean co){ collided =co;}
}
