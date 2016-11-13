package com.mygdx.game.Sprites;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Animation;
import com.mygdx.game.Main;
import com.mygdx.game.Managers.CameraManager;
import com.mygdx.game.Managers.ScoreManager;

/**
 * Created by Zac on 11/12/2016.
 */

public class Sun {
        private boolean hasVibrated;
    private Texture sunTexture;
    private Rectangle rectangle;
    private Sprite sunSprite;
    private Animation animation;
    private ScoreManager scoreManager;
    private CameraManager cameraManager;

    public Sun(ScoreManager scoreManager, CameraManager cameraManager) {
        this.cameraManager = cameraManager;
        this.scoreManager = scoreManager;
        hasVibrated = false;
        sunTexture = new Texture(Gdx.files.internal("sprites/sun.png"));
        rectangle = new Rectangle();
        animation = new Animation(new TextureRegion(sunTexture), 3, .5f);
        sunSprite = new Sprite(animation.getFrame());
        sunSprite.setX(this.cameraManager.getCamWidth() / 2 - sunSprite.getRegionWidth() / 2);
        sunSprite.setY(0);
        sunSprite.setScale(1.75f);
        sunSprite.translateY(-55f); //goal negative 15
        rectangle.set(0,sunSprite.getY() - sunSprite.getHeight()/2,cameraManager.getCamWidth(),cameraManager.getCamHeight()/4);

    }

    public void update(float dt) {


        if (Main.score % 5 == 0 && Main.score!= 0)
        {
            animation.update(dt);
            sunSprite.setRegion(animation.getFrame());
            if (sunSprite.getY() <- 15f)
            {
                sunSprite.translateY(0.75f);
                if(!hasVibrated)
                {

                    Gdx.input.vibrate(new long[]{0,200,100,200},-1);
                    hasVibrated = !hasVibrated;
                }
            }

        }else
        { if (sunSprite.getY() > -55f)
            sunSprite.translateY(-0.75f);
        }

        rectangle.setY(sunSprite.getY() - sunSprite.getHeight()/2);
    }


    public void dispose() {
        sunTexture.dispose();
    }

    public Sprite getSunSprite() {
        return sunSprite;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
    public void setHasVibrated(boolean b){hasVibrated=b;}

}
