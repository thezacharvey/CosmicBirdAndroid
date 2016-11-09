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

public class Star extends SpaceBird {

    private Texture star;
    private Animation animation;
    static float starX;
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

    public Star(OrthographicCamera camera) {

        collided =false;
        random =new Random();
        intHeight = (int)camera.viewportHeight;
        intHeight = random.nextInt(intHeight);

        cameraWidth = camera.viewportWidth;
        cameraHeight = camera.viewportHeight;


        star = new Texture("star.png");
        animation = new Animation(new TextureRegion(star), 2, 0.35f);
        starHeight = animation.getFrame().getRegionHeight();
        starWidth = animation.getFrame().getRegionWidth();
        starX = cameraWidth + starWidth;
        newStarY = starY(0);
        velocity = cameraWidth / 2;
        circle = new Circle();
       // circle.set(starX,starY,starWidth/2);

    }



    @Override
    public void render() {
        update(Gdx.graphics.getDeltaTime());
        animation.update(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void update(float dt) {
        if (starX <= -starWidth)
        {
            newStarY = starY(newStarY);
            starX = cameraWidth+animation.getFrame().getRegionWidth();
            collided =false;
        }
        starX-=velocity*dt;
        circle.set(getX()+starWidth/2,getY()+getHeight()/2,getWidth()/2);

    }

    private int starY(float prev)
    {
        int prevY =(int) prev;
        int starY  = random.nextInt((int)cameraHeight);
        if (prevY==starY) {starY += starHeight;}
        int camH = (int)cameraHeight;
        int starH = (int)starHeight;
        if (starY >= camH-starH) {
            starY = camH - starH;
            if (prevY==starY) starY-=starH;
        }
        if (starY <= starH) {
            starY = starH;
            if (prevY==starY) starY+=starH;
        }

        return starY;
    }

    @Override
    public void dispose() {
        star.dispose();
    }

    public TextureRegion getTextureRegion() {return animation.getFrame();}
    public float getX(){return starX;}
    public float getWidth(){return starWidth;}
    public float getHeight(){return starHeight;}
    public float getY(){return newStarY;}
    public Circle getCircle(){return circle;}
    public boolean getCollision() {return collided;}
    public void setCollision(boolean co){ collided =co;}
}
