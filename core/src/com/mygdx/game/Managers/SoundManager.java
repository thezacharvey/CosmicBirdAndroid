package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Zac on 11/12/2016.
 */

public class SoundManager {
    private Sound birdSound;
    public SoundManager()
    {
        birdSound= Gdx.audio.newSound(Gdx.files.internal("sound/birdsound.mp3"));
    }

    public void play()
    {
        birdSound.play(0.1f);
    }
}
