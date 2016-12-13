package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Animation;
import com.mygdx.game.Main;
import com.mygdx.game.Managers.CameraManager;
import com.mygdx.game.Managers.SoundManager;

/**
 * Created by z_ig_ on 11/8/2016.
 */

public class Bird {

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
    private boolean hitAnimation;
    private CameraManager cameraManager;
    private boolean toggle;
    private int flickerCount;
   private SoundManager soundManager;
    public Bird(OrthographicCamera camera, SoundManager soundManager, CameraManager cameraManager){
        this.soundManager = soundManager;
        this.cameraManager = cameraManager;
        flickerCount =0;
        velocity =0f;
        rectangle = new Rectangle();
        hitAnimation = false;
        camH =  cameraManager.getCamHeight();
        birdTexture = new Texture(Gdx.files.internal("christmas/birdchristmas.png"));
        animation = new Animation(new TextureRegion(birdTexture),3,0.15f);
        birdWidth = animation.getFrame().getRegionWidth();
        birdHeight = animation.getFrame().getRegionHeight();
        collided =false;
        birdX = getWidth()/2;
        birdY = camH/2 - animation.getFrame().getRegionHeight()/2;
        originXY = new Vector2(birdX,birdY);

        toggle =false;

        gravity = .50f;
        Gdx.app.log("gravity",String.valueOf(gravity));
        sprite = new Sprite(getTextureRegion());
        sprite.setX(getX());
        sprite.setY(getY());



    }




    public void update(float dt) {

        if (Main.gameState!=2)
        {
            animation.update(dt);
            if (hitAnimation && flickerCount <=100) {

                if (toggle)
                {
                    sprite.setAlpha(.35f);

                }else
                {
                    sprite.setAlpha(.75f);
                }
                toggle = !toggle;
                flickerCount++;

            }else{ sprite.setAlpha(1f);startFlickerAnimation(false);flickerCount =0;}
        }
        if (!Main.birdDead)
        {

            if(Gdx.input.justTouched() && birdY < camH - Main.scoreTexture.getHeight())
            {
                velocity =  -(birdHeight/1.5f);
                sprite.setRotation(45);
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

        sprite.setRegion(getTextureRegion());


    }      public void applyGravity(float dt)
    {
        if (birdY >= -birdHeight/2 || velocity < 0 &&  Main.gameState==1)
        {
            velocity = velocity +(gravity+dt);
            birdY -= velocity;
            sprite.setY(getY());

            if (sprite.getRotation() >= -45f)
            sprite.rotate(-birdWidth/6f);

        }


    }


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
    public void startFlickerAnimation(boolean b){hitAnimation = b;}
}
