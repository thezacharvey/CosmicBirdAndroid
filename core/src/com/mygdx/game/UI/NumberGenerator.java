package com.mygdx.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Main;
import com.mygdx.game.Managers.CameraManager;
import com.mygdx.game.Managers.ScoreManager;

import java.util.ArrayList;

/**
 * Created by z_ig_ on 12/9/2016.
 */

public class NumberGenerator {
    private Texture font;
    private ArrayList<TextureRegion> numbers;
    private ArrayList<Sprite> scoreSprite;
    private CameraManager cameraManager;
    private ScoreManager scoreManager;


    public NumberGenerator(CameraManager cameraManager, ScoreManager scoreManager)
    {
        this.scoreManager = scoreManager;
        this.cameraManager =cameraManager;
        font = new Texture(Gdx.files.internal("numbers.png"));

        numbers = new ArrayList<TextureRegion>();


        scoreSprite = new ArrayList<Sprite>();

        int frameWidth = font.getWidth()/10;

        for (int i=0; i < 10;i++)
        {
            numbers.add(new TextureRegion(font,i*frameWidth,0,frameWidth,font.getHeight()));
        }




    }



    public ArrayList<Sprite>getText(float heartY,float heartHeight,boolean isHighScore)
    {
        String substring,score;
        int length;
        if (!isHighScore)
        {
             score =String.valueOf(Main.score);
             length= String.valueOf(Main.score).length();
        }else
        {
            score = String.valueOf(scoreManager.getPreferences().getInteger("highScore"));
            length = String.valueOf(scoreManager.getPreferences().getInteger("highScore")).length();
        }


        int lastDigit;
        float y=  heartY-(heartHeight*2.5f);
        float x;



        scoreSprite.clear();

        for (int i=0; i < length;i++)
        {

            substring = score.substring(i,i+1);
            lastDigit =Integer.parseInt(substring.trim()) ;

            scoreSprite.add(i, new Sprite(numbers.get(lastDigit)));
            x = centerX(i,length);
            scoreSprite.get(i).scale(cameraManager.getCamWidth()/1000);
           // Gdx.app.log("CAM",String.valueOf(cameraManager.getCamWidth()/1000));
            scoreSprite.get(i).setPosition(x,y);
            //scoreSprite.get(i).setPosition(cameraManager.getCamWidth()/2-scoreSprite.get(i).getWidth()/2+i*scoreSprite.get(i).getWidth()*1.5f,cameraManager.getCamHeight()/2-scoreSprite.get(i).getHeight()*2.5f);

        }

        return scoreSprite;



    }

    private float centerX(int i, int length)
    {

        float originX = cameraManager.getCamWidth()/2 - scoreSprite.get(i).getWidth()/2;
        float x = originX;
        float gap =(scoreSprite.get(i).getWidth()*1.25f)*i;

        if(length>1)
        {
            x-= (scoreSprite.get(i).getWidth()/2)*length;
            x+= gap;
        }



        return x;
    }





}
