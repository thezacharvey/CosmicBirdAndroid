package com.mygdx.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Main;
import com.mygdx.game.Managers.CameraManager;
import com.mygdx.game.Managers.SoundManager;
import com.mygdx.game.Managers.StateManager;

import java.util.ArrayList;

/**
 * Created by z_ig_ on 12/8/2016.
 */

public class UiManager {


  private Texture uiSheet,play;
    private ArrayList<TextureRegion> uiRegion;
    private ArrayList<Sprite> uiSprite;
    private CameraManager cameraManager;
    private Vector3 mousePos,unproject;
    private Circle touchCircle;
    private Rectangle muteRectangle,playRectangle,helpRectangle,resetRectangle;
    private boolean toggle,canClick;
    private SoundManager soundManager;
    private StateManager stateManager;
    private Sprite playSprite;
    private boolean pulseing,panUp,reset;
    private float originScale,resetScale;

    public UiManager(CameraManager cameraManager, SoundManager soundManager, StateManager stateManager)
    {
        this.stateManager = stateManager;
        this.soundManager = soundManager;
        touchCircle = new Circle();
        uiSprite = new ArrayList<Sprite>();
        mousePos = new Vector3(0,0,0);
        unproject = new Vector3(0,0,0);
        resetUIBooleans();

        originScale =  .65f;

        play = new Texture("taptoplay.png");

        playRectangle = new Rectangle();
        helpRectangle = new Rectangle();
        resetRectangle = new Rectangle();


        playSprite = new Sprite(play);
        playSprite.setPosition(cameraManager.getCamWidth() / 2 - playSprite.getRegionWidth() / 2, cameraManager.getCamHeight() / 2-play.getHeight()/2);
        playRectangle.set(playSprite.getX(),playSprite.getY(),playSprite.getRegionWidth(),playSprite.getRegionHeight());

        canClick =true;

        this.cameraManager = cameraManager;

        toggle =false;

        uiSheet = new Texture(Gdx.files.internal("uisheet.png"));
        int uiCount = 4;

        int uiWidth = uiSheet.getWidth() / uiCount;
        touchCircle.setRadius(uiWidth/15);

        uiRegion = new ArrayList<TextureRegion>();
        muteRectangle = new Rectangle();

        uiSprite.clear();
        uiRegion.clear();


        for (int i =0; i < uiCount; i++)
        {
            if (i==0)
            {
                uiRegion.add(new TextureRegion(uiSheet,4,0,18,uiSheet.getHeight()));
            }else
            {
                uiRegion.add(new TextureRegion(uiSheet,i*uiWidth,0,uiWidth,uiSheet.getHeight()));
            }


            if (i<uiCount-1) {
                uiSprite.add(i,new Sprite(uiRegion.get(i)));
                if (i <2) {

                    uiSprite.get(i).setRegion(uiRegion.get(i));
                    uiSprite.get(i).setPosition(i * (cameraManager.getCamWidth() - uiSprite.get(i).getWidth()), 0);
                }
                uiSprite.get(i).setScale(originScale);
            }

        }
        uiSprite.get(2).setRegion(uiRegion.get(3));
        resetScale=1.25f;
        uiSprite.get(2).setScale(resetScale);
        uiSprite.get(2).setPosition(cameraManager.getCamWidth()/2 -uiSprite.get(2).getWidth()/2,uiSprite.get(2).getHeight());


        helpRectangle.set(uiSprite.get(0).getX(),uiSprite.get(0).getY(),uiSprite.get(0).getRegionWidth(),uiSprite.get(0).getRegionHeight());
        muteRectangle.set(uiSprite.get(1).getX(),uiSprite.get(1).getY(),uiSprite.get(1).getRegionWidth()/1.5f,uiSprite.get(1).getRegionHeight());
        resetRectangle.set(uiSprite.get(2).getX(),uiSprite.get(2).getY(),uiSprite.get(2).getRegionWidth(),uiSprite.get(2).getRegionHeight());



    }
    public void dispose()
    {
        for (Sprite sprite : uiSprite)
        {
            sprite.getTexture().dispose();
        }
        for (TextureRegion textureRegion : uiRegion)
        {
            textureRegion.getTexture().dispose();
        }
        playSprite.getTexture().dispose();
        uiSheet.dispose();
    }

    public void render(SpriteBatch batch)
    {
        if(Main.gameState ==0 || Main.gameState ==2) {

            update(Gdx.graphics.getDeltaTime());
            for (int i=0; i < uiSprite.size()-1;i++)
            {
                uiSprite.get(i).draw(batch);
            }

            if (Main.gameState ==2)
            {
                uiSprite.get(2).draw(batch);
            }
            if (Main.gameState==0)
            {
                playSprite.draw(batch);
            }

        }

        if (Main.gameState ==1)
        {
            resetUIBooleans();
        }

    }
    private void resetUIBooleans()
    {
        pulseing = false;
        panUp = false;
        reset = false;
    }

    public Rectangle getRectangle(){return resetRectangle;}


    public void update(float dt)
    {

        float upY = cameraManager.getCamHeight() *2;

        if (Main.gameState==2 || Main.gameState ==0)
        {

            pulse(uiSprite.get(0),false,dt,originScale);
            pulse(uiSprite.get(1),false,dt,originScale);
            pulse(uiSprite.get(2),false,dt,resetScale);

            if (uiSprite.get(2).getScaleX()>=resetScale-.01f && reset)
            {
                Main.gameState =1;
                stateManager.resetGame();
            }

            if (uiSprite.get(0).getScaleX()>=originScale-.01f && panUp)
            {
                if (cameraManager.getCamera().position.y >= upY)
                {
                    panUp = false;
                }
                    cameraManager.getCamera().position.interpolate(new Vector3(cameraManager.getCamOriginPos().x,cameraManager.getCamOriginPos().y+upY,cameraManager.getCamOriginPos().z),dt,Interpolation.bounceIn);

            }
            if (!panUp)
            {
               // cameraManager.getCamera().translate(0,-0.5f);
                cameraManager.getCamera().position.interpolate(cameraManager.getCamOriginPos(),dt,Interpolation.bounceIn);

            }

            if (Main.gameState ==0)
            {
                pulse(playSprite,true,dt,1.25f);
                if (Intersector.overlaps(touchCircle,playRectangle))
                {
                    stateManager.setStartGame(true);
                }
            }

        }


        if (Gdx.input.justTouched())
        {

            mousePos.set(Gdx.input.getX(),Gdx.input.getY(),0);
            unproject =cameraManager.getCamera().unproject(mousePos);
            touchCircle.setPosition(unproject.x,unproject.y);

            if (Intersector.overlaps(touchCircle,helpRectangle))
            {
                uiSprite.get(0).setScale(originScale-.10f);
                panUp =true;
            }
            if (Intersector.overlaps(touchCircle,resetRectangle) &&Main.gameState ==2)
            {
                uiSprite.get(2).setScale(resetScale-.10f);
                reset =true;
            }

                canClick = !canClick;
                if (Intersector.overlaps(touchCircle, muteRectangle) && canClick && Main.gameState!=1) {
                    toggle = !toggle;
                    uiSprite.get(1).setScale(originScale-.10f);

                    if (toggle) {
                        uiSprite.get(1).setRegion(uiRegion.get(2));//muted
                        soundManager.mute(true);

                    } else {
                        uiSprite.get(1).setRegion(uiRegion.get(1));// unmuted
                        soundManager.mute(false);
                    }
                }



        }


    }

    private void pulse(Sprite sprite,boolean growAndShrink, float dt, float scl)
    {
        if (growAndShrink)
        {
            if (pulseing)
            {
                sprite.scale(.005f);
            }else
            {
                sprite.scale(-.005f);
            }
            if (sprite.getScaleX() >=scl)
            {
                pulseing = false;
            }else if (sprite.getScaleX() <=.8)
            {
                pulseing= true;
            }

            float scale =sprite.getScaleX();
            playRectangle.setCenter(cameraManager.getCamWidth() / 2 , cameraManager.getCamHeight() / 2);
            playRectangle.setSize(sprite.getRegionWidth()*scale,sprite.getRegionHeight()*scale);

        }else
        {
               if (sprite.getScaleX() < scl)
               {
                   sprite.scale(.005f);
               }
        }
    }



}
