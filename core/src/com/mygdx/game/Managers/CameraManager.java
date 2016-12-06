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

    public void setCamSize()
    {

            Gdx.app.log("ScreenWidth", String.valueOf(screenWidth));

       // if (screenWidth  > 1440) {
            //camera.setToOrtho(false, screenWidth / 15, screenHeight / 15);
          //  return;
        //}
        switch ((int)screenWidth)
        {

            case 2560:
                camera.setToOrtho(false,screenWidth/12.25f,screenHeight/12.25f);
                break;
            case 2048:
                camera.setToOrtho(false,screenWidth/10.25f,screenHeight/10.25f);
                break;
            case 1800:
                camera.setToOrtho(false,screenWidth/10.1f,screenHeight/10.1f);
                break;
            case 1600:
                camera.setToOrtho(false,screenWidth/9.75f,screenHeight/9.75f);
                break;
            case 1536:
                camera.setToOrtho(false,screenWidth/9.45f,screenHeight/9.45f);
                break;
            case 1440:
                camera.setToOrtho(false,screenWidth/9.45f,screenHeight/9.45f);
                break;
            case 1200:
                camera.setToOrtho(false,screenWidth/9.30f,screenHeight/9.30f);
                break;
            case 1080:
                camera.setToOrtho(false,screenWidth/9.25f,screenHeight/9.25f);
                break;
            case 854:   //tablet suppport
                camera.setToOrtho(false,screenWidth/5.67f,screenHeight/5.67f);          //Bigger the divider closer the zoom
                break;
            case 800:   //tablet suppport
                camera.setToOrtho(false,screenWidth/5.65f,screenHeight/5.65f);          //Bigger the divider closer the zoom
                break;
            case 768:
                camera.setToOrtho(false,screenWidth/5.35f,screenHeight/5.35f);
                break;
            case 720:
                camera.setToOrtho(false,screenWidth/5.25f,screenHeight/5.25f);
                break;
            case 600:
                camera.setToOrtho(false,screenWidth/4,screenHeight/4);
                break;
            case 480:
                camera.setToOrtho(false,screenWidth/3.78f,screenHeight/3.78f);
                break;
            case 320:
                camera.setToOrtho(false,screenWidth/3,screenHeight/3);
                break;
            case 240:
                camera.setToOrtho(false,screenWidth/2,screenHeight/2);
                break;
            default:
                camera.setToOrtho(false,screenWidth/5,screenHeight/5);
                break;
        }

        camWidth = camera.viewportWidth;
        camHeight = camera.viewportHeight;



    }

    public float getCamWidth(){return camWidth;}
    public float getCamHeight(){return camHeight;}
    public float getCamZoom(){return camZoom;}
    public OrthographicCamera getCamera(){return camera;}
}
