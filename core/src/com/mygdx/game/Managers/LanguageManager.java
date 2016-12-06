package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.Locale;

/**
 * Created by z_ig_ on 11/27/2016.
 */

public class LanguageManager {


    private Texture[] texture,gameOver,replayTexture;
    private String lang;
    private Sprite scoreSprite,gameSprite,highscoreSprite,tapToReplaySprite;
    public LanguageManager(Sprite scoreSprite,Sprite gameSprite, Sprite highscoreSprite, Sprite tapToReplaySprite)

    {
        this.tapToReplaySprite = tapToReplaySprite;
        this.highscoreSprite = highscoreSprite;
        this.gameSprite = gameSprite;
    this.scoreSprite = scoreSprite;
        lang= java.util.Locale.getDefault().toString().trim();

       // Gdx.app.log("Language", lang);


        replayTexture = new Texture[3];

        replayTexture[0] = new Texture(Gdx.files.internal("christmas/replaychristmas.png"));
        replayTexture[1] = new Texture(Gdx.files.internal("Port/replaychristmas.png"));
        replayTexture[2] = new Texture(Gdx.files.internal("japanese/replaychristmas.png"));

        texture = new Texture[8];
        texture[0] = new Texture(Gdx.files.internal("score.png"));
        texture[1] = new Texture(Gdx.files.internal("score2.png"));
        texture[2] = new Texture(Gdx.files.internal("japanese/score.png"));
        texture[3] = new Texture(Gdx.files.internal("japanese/score2.png"));
        texture[4] = new Texture(Gdx.files.internal("Port/score.png"));
        texture[5] = new Texture(Gdx.files.internal("Port/score2.png"));
        texture[6] = new Texture(Gdx.files.internal("elseif/score.png"));
        texture[7] = new Texture(Gdx.files.internal("elseif/score2.png"));

        gameOver = new Texture[5];
        gameOver [0] = new Texture(Gdx.files.internal("gameover.png"));
        gameOver [1] = new Texture(Gdx.files.internal("japanese/gameover.png"));
        gameOver [2] = new Texture(Gdx.files.internal("japanese/highscore.PNG"));
        gameOver [3] = new Texture(Gdx.files.internal("Port/gameover.png"));
        gameOver [4] = new Texture(Gdx.files.internal("Port/highscore.png"));
       /* gameOver [5] = new Texture(Gdx.files.internal("Spanish/gameover.png"));
        gameOver [6] = new Texture(Gdx.files.internal("Spanish/highscore.PNG"));
        gameOver [7] = new Texture(Gdx.files.internal("French/gameover.png"));
        gameOver [8] = new Texture(Gdx.files.internal("French/highscore.PNG"));


*/

    }

    public Texture getScoreTexture(int e){

        Gdx.app.log("Lang", Locale.getDefault().toString());
        lang = java.util.Locale.getDefault().toString().trim();

        if (lang.equals("ja_JP") )
        {

            if (e ==1)
            {
                scoreSprite.setTexture(texture[2]);
            }else
            {
                scoreSprite.setTexture(texture[3]);
            }


            gameSprite.setTexture(gameOver[1]);
            gameSprite.setScale(2.85f,1);
            highscoreSprite.setTexture(gameOver[2]);
            highscoreSprite.setScale(0.35f,1);
            tapToReplaySprite.setTexture(replayTexture[2]);

        }else if(lang.equals("pt_PR") || lang.equals("pt_BR" ) ||lang.equals("pt_PT"))
        {


            if (e ==1)
            {
                scoreSprite.setTexture(texture[5]);
            }else
            {
                scoreSprite.setTexture(texture[4]);
            }


            gameSprite.setTexture(gameOver[3]);
            gameSprite.setScale(1.25f,1.25f);
            highscoreSprite.setTexture(gameOver[4]);
            highscoreSprite.setScale(1.25f,1.25f);
            scoreSprite.setScale(1f,1f);
            tapToReplaySprite.setTexture(replayTexture[1]);

            //Portugeses (:

        }/*else if(lang.equals("es_US") || lang.equals("es_ES"))
        {
            if (e ==1)
            {
                scoreSprite.setTexture(texture[2]);
            }else
            {
                scoreSprite.setTexture(texture[3]);
            }


            gameSprite.setTexture(gameOver[1]);
            gameSprite.setScale(2.85f,1);
            highscoreSprite.setTexture(gameOver[2]);
            highscoreSprite.setScale(0.5f,1);
            //Spanish

        }else if(lang.equals("fr_BE") || lang.equals("fr_FR")|| lang.equals("fr_CA")|| lang.equals("fr_CH"))
        {

            if (e ==1)
            {
                scoreSprite.setTexture(texture[2]);
            }else
            {
                scoreSprite.setTexture(texture[3]);
            }


            gameSprite.setTexture(gameOver[1]);
            gameSprite.setScale(2.85f,1);
            highscoreSprite.setTexture(gameOver[2]);
            highscoreSprite.setScale(0.5f,1);
            //French
        }

*/


        else if (lang.equals("en_US")||lang.equals("en_UK")||lang.equals("en_AU") || lang.equals("en_ZA") ||lang.equals("en_CA") )
        {
            if (e ==1)
            {
                scoreSprite.setTexture(texture[0]);
            }else
            {
                scoreSprite.setTexture(texture[1]);
            } //English

            gameSprite.setTexture(gameOver[0]);

            tapToReplaySprite.setTexture(replayTexture[0]);
        }
        else
        {
            if (e ==1)
            {
                scoreSprite.setTexture(texture[6]);
            }else
            {
                scoreSprite.setTexture(texture[7]);
            } //English

        }
        return scoreSprite.getTexture();            //English

    }

    public void checkNewLang(String l)
    {
        if (!lang.equals(l))
        {
            scoreSprite.setTexture(getScoreTexture(0));
            gameSprite.setTexture(getScoreTexture(0));
            highscoreSprite.setTexture(getScoreTexture(0));

        }

    }
}
