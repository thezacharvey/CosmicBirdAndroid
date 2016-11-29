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
    private Texture sunTexture,warningMessageTexture;
    private boolean showSun;
    private Rectangle rectangle;
    private Sprite sunSprite,warningMessageSprite;
    private Animation animation;
    private ScoreManager scoreManager;
    private CameraManager cameraManager;

    private boolean displayWarning;

    public Sun(ScoreManager scoreManager, CameraManager cameraManager) {
        this.cameraManager = cameraManager;
        this.scoreManager = scoreManager;
        displayWarning = false;
        showSun =false;
        hasVibrated = false;
        sunTexture = new Texture(Gdx.files.internal("sprites/sun.png"));
        rectangle = new Rectangle();
        animation = new Animation(new TextureRegion(sunTexture), 3, .5f);
        sunSprite = new Sprite(animation.getFrame());
        sunSprite.setX(this.cameraManager.getCamWidth() / 2 - sunSprite.getRegionWidth() / 2);
        sunSprite.setY(0);
        sunSprite.setScale(1.75f);
        sunSprite.translateY(-75f); //goal negative 15
        rectangle.set(0,sunSprite.getY() - sunSprite.getHeight()/2,cameraManager.getCamWidth(),cameraManager.getCamHeight()/4);

        warningMessageTexture = new Texture(Gdx.files.internal("sprites/warningmessagechristmas.png"));
        warningMessageSprite = new Sprite(warningMessageTexture);
        warningMessageSprite.setY(cameraManager.getCamHeight()/2);
        warningMessageSprite.setX(cameraManager.getCamWidth() /2 - warningMessageSprite.getRegionWidth()/2);
        warningMessageSprite.setScale(0.25f);

    }

    public void update(float dt) {


        if (Main.score % 8 ==0)
        {
            showSun = true;
        }
        if (Main.score % 12==0) {
            showSun = false;
        }

      //  if (Main.score > 35 && Main.score  < 350&& Main.score!= 0)
        if (showSun)
        {
            animation.update(dt);
            sunSprite.setRegion(animation.getFrame());
            displayWarning  = true;
            if (warningMessageSprite.getScaleX() < 1.25f)
            {
                warningMessageSprite.scale(0.25f);
            }
            if (sunSprite.getY() <- 25f)
            {
                sunSprite.translateY(0.55f);
                //displayWarning = false;
                if(!hasVibrated)
                {
                    Gdx.input.vibrate(new long[]{0,200,100,200},-1);
                    hasVibrated = true;
                }

            }
            if (sunSprite.getY()>=-25f)
            {
                    displayWarning = false;
            }

        }else
        { if (sunSprite.getY() > -75f || Main.gameState ==2)            //turns off warning message if dead or sun at certain possition
            sunSprite.translateY(-0.95f);
            displayWarning = false;
        }

        rectangle.setY(sunSprite.getY() - sunSprite.getHeight()/2);
    }


    public void dispose() {
        sunTexture.dispose();
        warningMessageTexture.dispose();
    }

    public Sprite getSunSprite() {
        return sunSprite;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
    public void setHasVibrated(boolean b){hasVibrated=b;}
    public boolean getDisplayWarning(){return displayWarning;}
    public Sprite getWarningMessageSprite(){return warningMessageSprite;}

}
