package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Animation;
import com.mygdx.game.SpaceBird;

/**
 * Created by z_ig_ on 11/8/2016.
 */

public class Bird extends SpaceBird {

    private float birdX;
    private float birdWidth;
    private float birdHeight;
    private float birdY;
    private float camH;
    private float velocity;
    private Rectangle rectangle;
    private boolean collided;
    Animation animation;
    private float gravity;
    private Texture birdTexture;
    public Bird(OrthographicCamera camera){
        gravity = 3f;
        velocity =0f;
        rectangle = new Rectangle();
        camH = camera.viewportHeight;
        birdTexture = new Texture("bird.png");
        animation = new Animation(new TextureRegion(birdTexture),3,0.25f);
        birdWidth = animation.getFrame().getRegionWidth();
        birdHeight = animation.getFrame().getRegionHeight();
        collided =false;
        birdX = camera.viewportWidth/2 - getWidth()/2;
        birdY = camH/2 - animation.getFrame().getRegionHeight()/2;
        Vector2 vector2 = new Vector2();

    }

    @Override
    public void update(float dt) {

        animation.update(dt);

        rectangle.set(getX(),getY(),getWidth(),getHeight());
    }

    @Override
    public void dispose() {
        birdTexture.dispose();
    }

    public TextureRegion getTextureRegion() {return animation.getFrame();}
    public void setVelocity(float v){velocity =v;}
    public float getVelocity(){return velocity;}
    public float getX(){return birdX;}
    public float getWidth(){return birdWidth;}
    public float getHeight(){return birdHeight;}
    public float getY(){return birdY;}
    public void setY(float y){birdY +=y;}
    public Rectangle getRectangle(){return rectangle;}
    public boolean getCollision() {return collided;}
    public void setCollision(boolean co){ collided =co;}
}
