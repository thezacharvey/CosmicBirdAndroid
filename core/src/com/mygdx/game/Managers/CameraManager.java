package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.GdxFileSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import org.w3c.dom.css.Rect;

/**
 * Created by z_ig_ on 11/9/2016.
 */

public class CameraManager {

    private float camWidth, camHeight;
    private float camZoom;
    private float screenHeight,screenWidth;
    private float originX,originY,originZ;
    private OrthographicCamera camera;
    private    Vector3 unproject;
    private Sprite debug;
    private Rectangle rectangle;
    public CameraManager(OrthographicCamera camera)
    {
        this.camera = camera;
        unproject = camera.unproject(new Vector3(camera.position.x,camera.position.y,0));
        camera.setToOrtho(false, Gdx.graphics.getWidth()/unproject.x*.2f,Gdx.graphics.getHeight()/unproject.x*.2f);
        camWidth = camera.viewportWidth;
        camHeight = camera.viewportHeight;
        camZoom = camera.zoom;

        originX = camera.position.x;
        originY = camera.position.y;
        originZ = camera.position.z;

        debug = new Sprite(new Texture(Gdx.files.internal("debug.png")));
        debug.setPosition(getCamWidth()/2 - debug.getWidth(),  debug.getHeight());
        rectangle = new Rectangle(debug.getX(),debug.getY(),debug.getWidth(),debug.getHeight());
    }

    private void debugInfomation()
    {
            Gdx.app.log("DEBUG-CAM",String.valueOf("Version :" +Gdx.app.getVersion()));
            Gdx.app.log("DEBUG-CAM",String.valueOf("Graphics Width/Height :" + Gdx.graphics.getWidth() + ","+Gdx.graphics.getHeight()));
            Gdx.app.log("DEBUG-CAM",String.valueOf("Graphics isContRender :" + Gdx.graphics.isContinuousRendering()));
            Gdx.app.log("DEBUG-CAM",String.valueOf("Native Orientation:" +Gdx.input.getNativeOrientation()));
            Gdx.app.log("DEBUG-CAM",String.valueOf("Pitch :" +Gdx.input.getPitch()));
            Gdx.app.log("DEBUG-CAM",String.valueOf("Roll :" +Gdx.input.getRoll()));
            Gdx.app.log("DEBUG-CAM",String.valueOf("Rotation :" +Gdx.input.getRotation()));
            debug.rotate(45f);

    }

    public void debugUpdate(Circle touchCircle)
    {
        if (Gdx.input.justTouched() && Intersector.overlaps(touchCircle,rectangle))
        {
            debugInfomation();
        }
    }
    public void debugRender(SpriteBatch batch)
    {
        debug.draw(batch);
    }

    public void setCamSize()
    {


        //if (screenWidth > 2560)
        //{
          //  camera.setToOrtho(false,screenWidth/13f,screenHeight/13f);
            //return;
       // }

//        switch ((int)screenWidth)
//        {
//
//            case 2560:
//                camera.setToOrtho(false,screenWidth/12.25f,screenHeight/12.25f);
//                break;
//            case 2048:
//                camera.setToOrtho(false,screenWidth/10.25f,screenHeight/10.25f);
//                break;
//            case 1800:
//                camera.setToOrtho(false,screenWidth/10.1f,screenHeight/10.1f);
//                break;
//            case 1600:
//                camera.setToOrtho(false,screenWidth/9.75f,screenHeight/9.75f);
//                break;
//            case 1536:
//                camera.setToOrtho(false,screenWidth/9.45f,screenHeight/9.45f);
//                break;
//            case 1440:
//                camera.setToOrtho(false,screenWidth/9.45f,screenHeight/9.45f);
//                break;
//            case 1200:
//                camera.setToOrtho(false,screenWidth/9.30f,screenHeight/9.30f);
//                break;
//            case 1080:
//                camera.setToOrtho(false,screenWidth/9.25f,screenHeight/9.25f);
//                break;
//            case 854:   //tablet suppport
//                camera.setToOrtho(false,screenWidth/5.67f,screenHeight/5.67f);          //Bigger the divider closer the zoom
//                break;
//            case 800:   //tablet suppport
//                camera.setToOrtho(false,screenWidth/5.65f,screenHeight/5.65f);          //Bigger the divider closer the zoom
//                break;
//            case 768:
//                camera.setToOrtho(false,screenWidth/5.35f,screenHeight/5.35f);
//                break;
//            case 720:
//                camera.setToOrtho(false,screenWidth/5.25f,screenHeight/5.25f);
//                break;
//            case 600:
//                camera.setToOrtho(false,screenWidth/4,screenHeight/4);
//                break;
//            case 480:
//                camera.setToOrtho(false,screenWidth/3.78f,screenHeight/3.78f);
//                break;
//            case 320:
//                camera.setToOrtho(false,screenWidth/3,screenHeight/3);
//                break;
//            case 240:
//                camera.setToOrtho(false,screenWidth/2,screenHeight/2);
//                break;
//            default:
//                camera.setToOrtho(false,screenWidth/5,screenHeight/5);
//                break;
//        }



    }

    public float getCamWidth(){return camWidth;}
    public float getCamHeight(){return camHeight;}
    public float getCamZoom(){return camZoom;}
    public Vector3 getCamOriginPos(){return new Vector3(originX,originY,originZ);}
    public OrthographicCamera getCamera(){return camera;}
}
