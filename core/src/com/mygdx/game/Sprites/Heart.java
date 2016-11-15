package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Managers.CameraManager;

import java.util.Random;

/**
 * Created by z_ig_ on 11/14/2016.
 */

public class Heart implements ISpriteInterface {

    private Texture heartTexture;
    private Sprite sprite;
    private float velocity;
    private CameraManager cameraManager;
    private Random random;

    public Heart(CameraManager  cameraManager)
    {
        random = new Random();
        this.cameraManager = cameraManager;
        velocity = cameraManager.getCamWidth()/125f;
        heartTexture = new Texture(Gdx.files.internal("heart.png"));
        sprite = new Sprite(heartTexture);
        sprite.setX(cameraManager.getCamWidth() + sprite.getWidth());
        sprite.setY(random.nextInt((int)cameraManager.getCamHeight()/3) + sprite.getHeight());
        sprite.setScale(1.25f);

    }

    @Override
    public void update(float dt) {
        if (sprite.getX() <= - sprite.getWidth())
        {
            sprite.setX(cameraManager.getCamWidth() + sprite.getWidth());
            sprite.setY(random.nextInt((int)cameraManager.getCamHeight()/3) + sprite.getHeight());
        }
        sprite.setX(sprite.getX() - velocity);

    }

    @Override
    public void dispose() {
        heartTexture.dispose();
    }

    @Override
    public float getX() {
        return sprite.getX();
    }

    @Override
    public float getY() {
        return sprite.getY();
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }
}
