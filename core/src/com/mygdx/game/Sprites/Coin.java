package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Animation;
import com.mygdx.game.Main;

import java.util.Random;

/**
 * Created by z_ig_ on 11/8/2016.
 */

public class Coin {

    private Texture star;
    private Vector2 originXY;
    private Animation animation;
    static float coinX;
    private float cameraWidth;
    private float cameraHeight;
    private float velocity;
    private float starWidth;
    private float starHeight;
    private int intHeight;
    private Circle circle;
    private Asteroid asteroid;
    private float newStarY;
    private Random random;
    private boolean collided;
    private Sprite sprite;
   // private Sprite gameSprite;

    public Coin(OrthographicCamera camera, Asteroid asteroid) {

        this.asteroid = asteroid;
        collided =false;
        random =new Random();
        intHeight = (int)camera.viewportHeight;
        intHeight = random.nextInt(intHeight);

        cameraWidth = camera.viewportWidth;
        cameraHeight = camera.viewportHeight;

        star = new Texture(Gdx.files.internal("christmas/starchristmas.png"));
        animation = new Animation(new TextureRegion(star), 3, 1f);
        starHeight = animation.getFrame().getRegionHeight();
        starWidth = animation.getFrame().getRegionWidth();
        coinX = cameraWidth + starWidth;
        newStarY = starY(0);
        velocity = cameraWidth *.95f;
        circle = new Circle();
        originXY = new Vector2(coinX,newStarY);

        sprite = new Sprite(animation.getFrame());
        sprite.setY(newStarY);
        sprite.setX(coinX);


               // circle.set(starX,starY,starWidth/2);

    }




      public void update(float dt) {
     animation.update(dt);
          //sprite.setRegion(animation.getFrame());
            if (coinX <= -starWidth)
            {
                newStarY = starY(newStarY);
                coinX = cameraWidth+animation.getFrame().getRegionWidth();
                collided =false;
                sprite.setY(newStarY);
            }
            coinX-=velocity*dt;
          sprite.setX(coinX);
            circle.set(getX()+sprite.getRegionWidth()/2,sprite.getY()+sprite.getRegionHeight()/2,sprite.getWidth()/2);




    }

    private int starY(float prev)
    {
        int prevY =(int) prev;
        int coinY  = random.nextInt((int)cameraHeight  - Main.scoreTexture.getHeight());
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

        return coinY ;
    }

    public void dispose() {
        star.dispose();
    }

    public TextureRegion getTextureRegion() {return animation.getFrame();}
    public float getX(){return coinX;}
    public void setX(float x){coinX = x;}
    public float getWidth(){return starWidth;}
    public float getHeight(){return starHeight;}
    public float getY(){return newStarY + asteroid.getHeight()/1.85f;}
    public Circle getCircle(){return circle;}
    public boolean getCollision() {return collided;}
    public void setCollision(boolean co){ collided =co;}
    public Vector2 getOriginXY(){ return originXY;}
    public void setVelocity(float v){velocity =cameraWidth * v;}
    public float getVelocity(){return velocity;}
    public Sprite getSprite(){return sprite;}
}
