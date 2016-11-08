package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpaceBird extends ApplicationAdapter {
	SpriteBatch batch;

	private Animation animation;
	private Texture texture;
	private OrthographicCamera camera;

	@Override
	public void create () {
		batch = new SpriteBatch();

		texture = new Texture("bird.png");
		animation = new Animation(new TextureRegion(texture),3,0.25f);  //passes texture , frame count, speed

		camera = new OrthographicCamera();
		camera.setToOrtho(false,Gdx.graphics.getWidth()/20,Gdx.graphics.getHeight()/20);
	}

	@Override
	public void render () {
		animation.update(Gdx.graphics.getDeltaTime());

		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		batch.draw(animation.getFrame(), 0, 0);
		batch.end();


	}
	
	@Override
	public void dispose () {
		batch.dispose();
		texture.dispose();
	}
}
