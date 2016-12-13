package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.mygdx.game.Main;
import com.mygdx.game.Managers.CameraManager;
import com.mygdx.game.Managers.ScoreManager;

import java.util.Random;

/**
 * Created by z_ig_ on 11/14/2016.
 */

public class Heart implements ISpriteInterface {

    private Sprite sprite;
    private float velocity;
    private CameraManager cameraManager;
    private boolean hasCollided;
    private Circle circle;
    private ScoreManager scoreManager;
    private Sprite[] healthStatus;
    private boolean pulse;
    private Main main;
    private float heartscale;



    public Heart(CameraManager cameraManager, ScoreManager scoreManager,Main main)
    {
        hasCollided = false;
        this.main = main;
        this.scoreManager = scoreManager;
        circle = new Circle();
        this.cameraManager = cameraManager;


        velocity = cameraManager.getCamWidth()/125f;

        sprite = new Sprite(new Texture(Gdx.files.internal("heart.png")));
        sprite.setX(cameraManager.getCamWidth() + sprite.getWidth());
        sprite.setY(randomY());
        sprite.setScale(1.25f);

        heartscale  = 1.5f;

        healthStatus = new Sprite[3];
       float gap= cameraManager.getCamWidth()/2 - sprite.getWidth()/2f;
       float ygap=0;
        float scoreX = main.getScoreXY().x - sprite.getWidth()*1.25f;
        for (int i=0; i < healthStatus.length;i++)
        {
            healthStatus[i] = new Sprite(new Texture(Gdx.files.internal("heart.png")));

            if (i>0)
            {
                healthStatus[i].setScale(.25f,.25f);
                healthStatus[i].setAlpha(0);
                ygap =  healthStatus[i].getHeight()*2f;
            }
            if (i== healthStatus.length-1)
            {
                ygap *=2f;
                healthStatus[i].setPosition( gap,cameraManager.getCamHeight()/2-sprite.getHeight()/2 + ygap);
            }else
            {
                healthStatus[i].setPosition(gap, cameraManager.getCamHeight()/2-sprite.getHeight()/2 + ygap);
            }






        }

        circle.set(sprite.getX(),sprite.getY(),sprite.getWidth()/2);

    }

    @Override
    public void update(float dt) {


        switch (Main.health)
        {

            case 0:
            for(Sprite sprite: healthStatus)
            {
                if (sprite.getScaleX()>=.25f)
                {
                    sprite.scale(-0.05f);
                }else
                {
                    sprite.setAlpha(0f);
                }
            }

                break;


            case 1:

                for (int i =0; i < healthStatus.length;i++)
                {
                    if (i>0)
                    {
                        if (healthStatus[i].getScaleX() >0.10f)
                        {
                            healthStatus[i].setAlpha(1);
                            healthStatus[i].scale(-0.05f);
                        }
                        if  (healthStatus[i].getScaleX() <=0.10f)
                        {
                            healthStatus[i].setAlpha(0f);
                        }
                    }else
                    {
                        if (healthStatus[i].getScaleX() <=heartscale) {
                            pulse = false;
                            healthStatus[i].setAlpha(1f);
                            healthStatus[i].scale(0.05f);

                        }
                    }
                }


                break;
            case 2:
                for (int i =0; i < healthStatus.length;i++)
                {
                    if (i>1)
                    {
                        if (healthStatus[i].getScaleX() >0.10f)
                        {
                            healthStatus[i].setAlpha(1);
                            healthStatus[i].scale(-0.05f);
                        }
                        if  (healthStatus[i].getScaleX() <=0.10f)
                        {
                            healthStatus[i].setAlpha(0f);
                        }
                    }else
                    {
                        if  (healthStatus[i].getScaleX() <=heartscale)
                        {
                            healthStatus[i].setAlpha(1f);
                            healthStatus[i].scale(0.05f);
                        }
                    }
                }
                break;
            case 3:

                for (Sprite sprite: healthStatus)
                {
                    if (sprite.getScaleX() <=heartscale)
                    {
                        sprite.setAlpha(1f);
                        sprite.scale(0.05f);
                    }
                }
                break;

        }


        if (sprite.getX() <= -sprite.getWidth() || !scoreManager.heartIsDrawable() || hasCollided) //resets Hearts X
        {
            scoreManager.setHeartDrawable(false);
            sprite.setPosition(cameraManager.getCamWidth()+ sprite.getWidth(),randomY());
            hasCollided = false;
        }

        if (scoreManager.heartIsDrawable() || sprite.getX() > -sprite.getWidth()/2) {
            sprite.setX(sprite.getX() - velocity);
        }

        circle.set(sprite.getX() + sprite.getWidth()/2,sprite.getY()+ sprite.getHeight()/2,sprite.getWidth()/2);

    }

    @Override
    public void dispose() {

        sprite.getTexture().dispose();
        for (Sprite sprite: healthStatus)
        {
            sprite.getTexture().dispose();
        }
    }

    @Override
    public float getX(int i) {
        return sprite.getX();
    }

    @Override
    public float getY(int i) {
        return sprite.getY();
    }

    @Override
    public Sprite getSprite(int i) {
        return sprite;
    }

    public Circle getCircle(){return circle;}

    public float randomY()
    {
        Random random = new Random();
        float y = random.nextInt((int)cameraManager.getCamHeight());

        if (y <= sprite.getHeight())
        {
            y+= sprite.getHeight()/2;
        }
        if (y >= cameraManager.getCamHeight() -sprite.getHeight())
        {
          y = cameraManager.getCamHeight() -sprite.getHeight();
        }
        return y;
    }
    public void setCollision(boolean b){ hasCollided = b;}
    public Sprite[] getHealthStatus(){return healthStatus;}

}
