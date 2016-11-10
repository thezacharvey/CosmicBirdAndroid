package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by z_ig_ on 11/9/2016.
 */

public class CameraManager {

    private float camWidth, camHeight;
    private float camZoom;
    private float screenHeight,screenWidth;
    private OrthographicCamera camera;
    public CameraManager(OrthographicCamera camera)
    {
        this.camera = camera;
        camWidth = camera.viewportWidth;
        camHeight = camera.viewportHeight;
        camZoom = camera.zoom;
        screenHeight = Gdx.graphics.getHeight();
        screenWidth = Gdx.graphics.getWidth();
    }

    public void setCamZoom()
    {
       // if(screenWidth==768)

        switch ((int)screenWidth)
        {



            case 1440:
                camera.setToOrtho(false,screenWidth/14,screenHeight/14);
                break;

            case 1080:
                camera.setToOrtho(false,screenWidth/9.25f,screenHeight/9.25f);
                break;
            case 720:
                camera.setToOrtho(false,screenWidth/8,screenHeight/8);
                break;
            case 480:
                camera.setToOrtho(false,screenWidth/20,screenHeight/20);
                break;
            case 320:
                camera.setToOrtho(false,screenWidth/23,screenHeight/23);
                break;
            case 240:
                camera.setToOrtho(false,screenWidth/25,screenHeight/25);
                break;
            default:
                break;
        }

    }

    public float getCamWidth(){return camWidth;}
    public float getCamHeight(){return camHeight;}
    public float getCamZoom(){return camZoom;}
}
