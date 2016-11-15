package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by z_ig_ on 11/14/2016.
 */

public interface ISpriteInterface {

    public float getX();
    public float getY();
    public void update(float dt);
    public void dispose();
    public Sprite getSprite();
}
