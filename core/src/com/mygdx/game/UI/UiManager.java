package com.mygdx.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
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
    private Rectangle muteRectangle,playRectangle,helpRectangle;
    private boolean toggle,canClick;
    private SoundManager soundManager;
    private StateManager stateManager;
    private Sprite playSprite;
    private boolean pulseing;
    private float originScale;

    public UiManager(CameraManager cameraManager, SoundManager soundManager, StateManager stateManager)
    {
        this.stateManager = stateManager;
        this.soundManager = soundManager;
        touchCircle = new Circle();
        uiSprite = new ArrayList<Sprite>();
        mousePos = new Vector3(0,0,0);
        unproject = new Vector3(0,0,0);
        pulseing = false;

        originScale =  .65f;

        play = new Texture("taptoplay.png");

        playRectangle = new Rectangle();
        helpRectangle = new Rectangle();


        playSprite = new Sprite(play);
        playSprite.setPosition(cameraManager.getCamWidth() / 2 - playSprite.getRegionWidth() / 2, cameraManager.getCamHeight() / 2-play.getHeight()/2);
        playRectangle.set(playSprite.getX(),playSprite.getY(),playSprite.getRegionWidth(),playSprite.getRegionHeight());

        canClick =true;

        this.cameraManager = cameraManager;

        toggle =false;

        uiSheet = new Texture(Gdx.files.internal("uisheet.png"));
        int uiCount = 3;

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
                uiSprite.get(i).setRegion(uiRegion.get(i));
                if (i==1)
                {
                    uiSprite.get(i).setPosition(cameraManager.getCamWidth()/2 - uiSprite.get(i).getWidth()/2, playSprite.getY() - uiSprite.get(i).getHeight());
                    muteRectangle.set(uiSprite.get(i).getX(),uiSprite.get(i).getY(),uiSprite.get(i).getRegionWidth()/1.5f,uiSprite.get(i).getRegionHeight());
                }

                uiSprite.get(i).setScale(originScale);

                //uiSprite.get(i).setPosition(cameraManager.getCamWidth()/2,cameraManager.getCamHeight()/2);
            }

        }
        uiSprite.get(0).setPosition(cameraManager.getCamWidth()/2-uiSprite.get(0).getRegionWidth()/2,uiSprite.get(1).getY() -uiSprite.get(0).getRegionHeight());
        helpRectangle.set(uiSprite.get(0).getX(),uiSprite.get(0).getY(),uiSprite.get(0).getRegionWidth(),uiSprite.get(0).getRegionHeight());


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
            for (Sprite sprite: uiSprite)
            {
                sprite.draw(batch);
            }
        }

        if (Main.gameState ==0)
        {
            playSprite.draw(batch);
           // Gdx.app.log("Ghost",String.valueOf());

            if (Intersector.overlaps(touchCircle,playRectangle))
            {
                stateManager.setStartGame(true);
            }
        }

    }

    public Rectangle getRectangle(){return helpRectangle;}


    public void update(float dt)
    {
        Gdx.app.log("Scale",String.valueOf(Math.round(uiSprite.get(0).getScaleX())));
        if (Main.gameState==2 || Main.gameState ==0)
        {
            pulse(uiSprite.get(1),false,dt);
            pulse(uiSprite.get(0),false,dt);

            if (Main.gameState ==0)
            {
                pulse(playSprite,true,dt);
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

    private void pulse(Sprite sprite,boolean isPlayButton, float dt)
    {
        if (isPlayButton)
        {
            if (pulseing)
            {
                sprite.scale(.002f);
            }else
            {
                sprite.scale(-.002f);
            }
            if (sprite.getScaleX() >=1)
            {
                pulseing = false;
            }else if (sprite.getScaleX() <=.8)
            {
                pulseing= true;
            }

            float scale =sprite.getScaleX();
            playRectangle.setCenter(cameraManager.getCamWidth() / 2 , cameraManager.getCamHeight() / 2+ sprite.getHeight()/2);
            playRectangle.setSize(sprite.getRegionWidth()*scale,sprite.getRegionHeight()*scale);
        }else
        {
               if (sprite.getScaleX() < originScale)
               {
                   sprite.scale(.005f);
               }

        }
    }



}
