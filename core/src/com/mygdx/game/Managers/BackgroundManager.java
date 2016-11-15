package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

/**
 * Created by z_ig_ on 11/14/2016.
 */

public class BackgroundManager {

    private Texture[] backgrounds;
        private Random random;


    public BackgroundManager()
    {

        random = new Random();
        backgrounds = new Texture[6];
        for (int i=0; i  < backgrounds.length; i++)
        {
            String nameOfTexture = "bg" + String.valueOf(i) +".png";
            //Gdx.app.log("bgName",nameOfTexture.trim());
            backgrounds[i] = new Texture(Gdx.files.internal("backgrounds/"+nameOfTexture));
        }
    }

    public Texture getBackground (){return backgrounds[ random.nextInt(backgrounds.length)]; }
}
