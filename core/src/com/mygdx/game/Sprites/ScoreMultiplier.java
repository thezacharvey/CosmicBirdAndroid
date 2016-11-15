package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Animation;
import com.mygdx.game.Main;
import com.mygdx.game.Managers.CameraManager;
import com.mygdx.game.Managers.ScoreManager;

import java.util.Random;

/**
 * Created by z_ig_ on 11/11/2016.
 */

public class ScoreMultiplier {


    private float x;
    private float velocity;
    private Circle circle;
    private Texture texture[];
    private Sprite sprite;
     private Random random;
    private CameraManager cameraManager;
    private boolean beenHit;
    private float y;
    private float spriteWidth;
    private Animation animation;
    private boolean changeMulitplier;
    private boolean canSpawn;


    public ScoreMultiplier(CameraManager cameraManager)
    {
        changeMulitplier = false;
        this.cameraManager = cameraManager;

        canSpawn = false;
        circle = new Circle();
        beenHit = false;
        random = new Random();
        velocity = cameraManager.getCamWidth() *0.45f;

        texture = new Texture[2];

        texture[0]=  new Texture(Gdx.files.internal("two.png"));
        texture[1]=  new Texture(Gdx.files.internal("four.png"));

        animation = new Animation(new TextureRegion(texture[0]),2,0.35f);

        sprite = new Sprite(animation.getFrame());
        spriteWidth = texture[0].getWidth();

        y= randomizedY(0);
        x= cameraManager.getCamWidth() + sprite.getRegionWidth();
        sprite.setScale(1.25f);
        sprite.setY(y);
        sprite.setX(cameraManager.getCamWidth()+ sprite.getRegionWidth());
        circle.set(getX(),getY(),spriteWidth/2);


    }
    private float randomizedY(float prev)
    {
        int prevY = (int) prev;
        int newY = random.nextInt((int) cameraManager.getCamHeight() - sprite.getRegionHeight());
        if (prevY == newY)
        {
            newY+= sprite.getRegionHeight()/2;
            if (newY >=  cameraManager.getCamHeight() - sprite.getRegionHeight()/2) newY = (int)cameraManager.getCamHeight() - sprite.getRegionHeight();
            if (newY <= sprite.getRegionHeight()) newY = sprite.getRegionHeight();

        }

        return newY;
    }

    public void update(float dt)
    {


        animation.update(dt);

        switch (Main.score % 5)
        {
            case 0:
                canSpawn = true;
                break;
            case 1:
                canSpawn = false;

                break;
            default:
                break;
        }

        if (!canSpawn)
        {

            sprite.setY(y);
            sprite.setX(x);
            circle.set(getX() + sprite.getRegionWidth()/2, getY()+ sprite.getRegionHeight()/2, sprite.getRegionWidth()/2);
            return;
        }//returns if false


        sprite.setRegion(animation.getFrame());
        if (x<= -sprite.getRegionWidth())
        {
            x= cameraManager.getCamWidth() + spriteWidth;
            y= randomizedY(y);              //resets
            beenHit = false;
        }
        x-=  velocity *dt;
         sprite.setY(getY()); sprite.setX(getX());
        circle.set(getX() + sprite.getRegionWidth()/2, getY()+ sprite.getRegionHeight()/2, sprite.getRegionWidth()/2);

    }

    public float getY(){return y;}
    public Sprite getSprite(){return sprite;}
    public float getX(){return x;}
    public Circle getCircle(){return  circle;}
    public boolean getCollision(){return beenHit;}
    public void setCollision(boolean c){ beenHit = c;}
    public boolean getCanSpawn(){return canSpawn;}
   // public void setCanSpawn(boolean s){canSpawn =s;}
}
