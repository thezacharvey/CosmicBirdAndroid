package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Animation;
import com.mygdx.game.Main;
import com.mygdx.game.Managers.SoundManager;

/**
 * Created by z_ig_ on 11/8/2016.
 */

public class Bird extends Main {

    private float birdX;
    private float birdWidth;
    private float birdHeight;
    private float birdY;
    private float camH;
    private float velocity;
    private Rectangle rectangle;
    private boolean collided;
    private Animation animation;
    private Vector2 originXY;
    private float gravity;
    private Texture birdTexture;
    private Sprite sprite;
    private Vector2 mousePos;
   private SoundManager soundManager;
    public Bird(OrthographicCamera camera, SoundManager soundManager){
        this.soundManager = soundManager;

        mousePos = new Vector2();
        velocity =0f;
        rectangle = new Rectangle();
        camH = camera.viewportHeight;
        birdTexture = new Texture("bird.png");
        animation = new Animation(new TextureRegion(birdTexture),3,0.25f);
        birdWidth = animation.getFrame().getRegionWidth();
        birdHeight = animation.getFrame().getRegionHeight();
        collided =false;
        birdX = getWidth()/2;
        birdY = camH/2 - animation.getFrame().getRegionHeight()/2;
        originXY = new Vector2(birdX,birdY);
        gravity = camH /250f;
        sprite = new Sprite(getTextureRegion());
        sprite.setX(getX());
        sprite.setY(getY());


    }

    @Override
    public void update(float dt) {
        if (!birdDead)
        {
            animation.update(dt);
            sprite.setRegion(getTextureRegion());
            if(Gdx.input.justTouched() && birdY < camH - scoreTexture.getHeight())
            {
                velocity = -camH/19f;
                sprite.setRotation(cameraManager.getCamHeight()/4f + dt);
               // mousePos.set(Gdx.input.getX(),Gdx.input.getY());
               // Gdx.app.log("Cheese", String.valueOf(mousePos.x / cameraManager.getCamWidth()));   // possible screen side testing
            }
            if (Gdx.input.justTouched())
            {
                soundManager.playSoundEffect(0);
            }
            rectangle.set(getX(),getY(),getWidth(),getHeight());

                applyGravity(dt);
        }

}      public void applyGravity(float dt)
    {
        if (birdY >= -birdHeight/2 || velocity < 0 &&  gameState==1)
        {
            velocity = velocity +gravity;
            birdY -= velocity;
            sprite.setY(getY() +dt);
         //   if (gameSprite.getRotation())
            if (sprite.getRotation() >= -91f)
            sprite.rotate(-cameraManager.getCamHeight()/50f);

        }


    }

    @Override
    public void dispose() {
        birdTexture.dispose();
    }

    public TextureRegion getTextureRegion() {return animation.getFrame();}
    public void setVelocity(float v){velocity =v;}
    public float getVelocity(){return velocity;}
    public Sprite getSprite(){return sprite;}
    public float getX(){return birdX;}
    public float getWidth(){return birdWidth;}
    public float getHeight(){return birdHeight;}
    public float getY(){return birdY;}
    public void setY(float y){birdY =y;}
    public Rectangle getRectangle(){return rectangle;}
    public boolean getCollision() {return collided;}
    public void setCollision(boolean co){ collided =co;}
    public Vector2 getOriginXY(){ return originXY;}
}
