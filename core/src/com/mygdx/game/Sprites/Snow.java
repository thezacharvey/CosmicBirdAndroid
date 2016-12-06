package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Managers.CameraManager;

import java.util.Random;

/**
 * Created by z_ig_ on 11/24/2016.
 */

public class Snow implements ISpriteInterface {


    private Sprite[] snowFlakes;
    private Texture snowFlakeTextures[];
    private CameraManager cameraManager;
    private Random random;
    private float fallspeed[];


    public Snow(CameraManager cameraManager)
    {
        random = new Random();
        this.cameraManager = cameraManager;
        snowFlakeTextures = new Texture[2];
        for (int i= 0; i < snowFlakeTextures.length;i++)
        {
            String filename = "christmas/snowflake"  + String.valueOf(i) + ".png";
            //Gdx.app.log("Filename",filename);
            snowFlakeTextures[i] = new Texture(Gdx.files.internal(filename));
        }


        snowFlakes = new Sprite[8];
        fallspeed = new float[snowFlakes.length];

        int c=0;
        int distance =2;

        for (int i=0; i < snowFlakes.length;i++)
        {
            if (c>=2)
            {c=0;}
            snowFlakes[i] = new Sprite(snowFlakeTextures[c]);
            snowFlakes[i].setX(cameraManager.getCamWidth()/3+snowFlakes[i].getWidth()/2+distance);
            snowFlakes[i].setY(snowFlakes[i].getHeight());
            if (i!=0)
            {
                fallspeed[i] = 0.5f*i;
            }else
            {
                fallspeed[i] =0.45f;
            }

            c++;
            distance +=6;
        }




    }

    @Override
    public float getX(int i) {
        return  snowFlakes[i].getX();
    }

    @Override
    public float getY(int i) {
        return snowFlakes[i].getY();
    }

    @Override
    public void update(float dt) {

        int i=0;

        for (Sprite sprite : snowFlakes)
        {

            if (sprite.getY() <= -sprite.getHeight())
            {
                int newX =  (int)(cameraManager.getCamWidth() - sprite.getWidth());
                sprite.setY(cameraManager.getCamHeight()+ sprite.getHeight());
                sprite.setX(random.nextInt(newX));
            }

            sprite.setY(sprite.getY()-fallspeed[i]);
            sprite.rotate(3f);
            i++;
        }

    }

    @Override
    public void dispose() {
        for (Texture texture : snowFlakeTextures)
        {
            texture.dispose();
        }
    }

    @Override
    public Sprite getSprite(int i) {
        return snowFlakes[i];
    }

    public Sprite[] getSpriteArray()
    {
        return snowFlakes;
    }
}
