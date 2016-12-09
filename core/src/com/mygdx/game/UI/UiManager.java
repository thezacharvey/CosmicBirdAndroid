package com.mygdx.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Managers.CameraManager;

import java.util.ArrayList;

/**
 * Created by z_ig_ on 12/8/2016.
 */

public class UiManager {

  private Texture uiSheet;
    private ArrayList<TextureRegion> uiRegion;
    private Sprite uiSprite[];
    private CameraManager cameraManager;

    public UiManager(CameraManager cameraManager)
    {

       this.cameraManager = cameraManager;

        Gdx.app.log("CamManThis",String.valueOf(this.cameraManager.getCamWidth()));
        Gdx.app.log("CamMan",String.valueOf(cameraManager.getCamWidth()));

        int uiCount = 4;

        uiSheet = new Texture(Gdx.files.internal("uisheet.png"));

        int uiWidth = uiSheet.getWidth() / uiCount;

        uiRegion = new ArrayList<TextureRegion>();
        uiSprite = new Sprite[uiCount-1];

        for (int i =0; i < uiCount; i++)
        {

            uiRegion.add(new TextureRegion(uiSheet,i*uiWidth,0,uiWidth,uiSheet.getHeight()));
            if (i<uiCount-1) {
                uiSprite[i] = new Sprite();
                uiSprite[i].setRegion(uiRegion.get(i));
                uiSprite[i].setPosition(cameraManager.getCamWidth()/2,cameraManager.getCamHeight()/2);

              //  Gdx.app.log("Sprite",String.valueOf(uiSprite[i].getX() + " " + uiSprite[i].getY() +" Scale:"+ uiSprite[i].getScaleX()));

            }

        }





        /*
        button = new ArrayList<Sprite>();
        button.add(0,new Sprite());
        */
    }


        public TextureRegion getUiSprite(String type){return uiSprite[1];}

}
