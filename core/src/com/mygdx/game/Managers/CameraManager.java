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

    /*
    *Testing the screen width and adjusting
     * below
    */

    public void setCamZoom()
    {

        if (screenWidth  > 1440) {
            camera.setToOrtho(false, screenWidth / 15, screenHeight / 15);
            return;
        }
        switch ((int)screenWidth)
        {

            case 1440:
                camera.setToOrtho(false,screenWidth/14,screenHeight/14);
                break;
            case 1080:
                camera.setToOrtho(false,screenWidth/9.25f,screenHeight/9.25f);
                break;
            case 768:
                camera.setToOrtho(false,screenWidth/6.85f,screenHeight/6.85f);
                break;
            case 720:
                camera.setToOrtho(false,screenWidth/6,screenHeight/6);
                break;
            case 480:
                camera.setToOrtho(false,screenWidth/4,screenHeight/4);
                break;
            case 320:
                camera.setToOrtho(false,screenWidth/3,screenHeight/3);
                break;
            case 240:
                camera.setToOrtho(false,screenWidth/2,screenHeight/2);
                break;
            default:
                break;
        }

        camWidth = camera.viewportWidth;
        camHeight = camera.viewportHeight;

    }

    public float getCamWidth(){return camWidth;}
    public float getCamHeight(){return camHeight;}
    public float getCamZoom(){return camZoom;}
}
