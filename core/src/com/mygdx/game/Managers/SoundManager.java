package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Zac on 11/12/2016.
 */

public class SoundManager {
    private Sound birdSound, coinSound,deathsound;
    public SoundManager()
    {
        birdSound= Gdx.audio.newSound(Gdx.files.internal("sound/birdsound.mp3"));
        coinSound = Gdx.audio.newSound(Gdx.files.internal("sound/coinpop.mp3"));
        deathsound = Gdx.audio.newSound(Gdx.files.internal("sound/deathsound.mp3"));
    }

    public void playSoundEffect(int type)
    {
        switch (type)
        {
            case 0:
                birdSound.play(0.1f);       //plays bird sound effect
                break;
            case 1:
                 coinSound.play(0.15f);
                //plays coin sound effect
                break;
            case 2:
                deathsound.play(0.45f);
                break;
            default:
                break;
        }

    }

    public void dispose()
    {
        birdSound.dispose();
        coinSound.dispose();
        deathsound.dispose();
    }
}
