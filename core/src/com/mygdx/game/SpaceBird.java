package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Managers.CameraManager;
import com.mygdx.game.Sprites.Asteroid;
import com.mygdx.game.Sprites.Bird;
import com.mygdx.game.Sprites.Coin;

public class SpaceBird extends ApplicationAdapter {
	SpriteBatch batch;

	private Animation animation;
	private Texture texture;
	public static boolean birdDead;
	private OrthographicCamera camera;
	private Coin coin;
	private ShapeRenderer shapeRenderer;
	private BitmapFont scoreFont;
	private int score;
	private Bird bird;
	public static CameraManager cameraManager;
	private Asteroid asteroid;
	@Override
	public void create () {
		score = 0;
		birdDead =false;
		scoreFont= new BitmapFont();
		scoreFont.setColor(Color.WHITE);
		//asteroid = new Texture("asteroid.png");

		batch = new SpriteBatch();
		texture = new Texture("score.png");
		animation = new Animation(new TextureRegion(texture),3,0.25f);  //passes texture , frame count, speed
			shapeRenderer = new ShapeRenderer();
		camera = new OrthographicCamera();
		camera.setToOrtho(false,Gdx.graphics.getWidth()/20,Gdx.graphics.getHeight()/20);
		//camera.zoom = 2f;

		cameraManager = new CameraManager(camera);
		cameraManager.setCamZoom();
		camera.zoom = cameraManager.getCamZoom();
		camera.update();

		coin = new Coin(camera);
		bird = new Bird(camera);
		asteroid = new Asteroid(camera);
		//bird.setVelocity(camera.viewportHeight/100);
		//birdRect.set(0,camera.viewportHeight/2 - animation.getFrame().getRegionHeight()/2,animation.getFrame().getRegionWidth(),animation.getFrame().getRegionHeight());

	}

	@Override
	public void render () {
		update(Gdx.graphics.getDeltaTime());
		camera.update();

		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	//	star.render();
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		batch.draw(bird.getTextureRegion(),bird.getX(),bird.getY());
		if (!coin.getCollision()) {
			batch.draw(coin.getTextureRegion(), coin.getX(), coin.getY());
			//batch.draw(coin2.getTextureRegion(), coin2.getX(), coin2.getY());
		}

		scoreFont.draw(batch,String.valueOf(score),camera.viewportWidth/2,camera.viewportHeight );
		batch.draw(texture,cameraManager.getCamWidth()/2 ,cameraManager.getCamHeight()-texture.getHeight());
		batch.draw(asteroid.getTexture(),asteroid.getX(),asteroid.getY());
		batch.end();

		/*
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.circle(asteroid.getCircle().x,asteroid.getCircle().y,asteroid.getCircle().radius);
		//shapeRenderer.rect(birdRect.x,birdRect.y,birdRect.width,birdRect.height);
       shapeRenderer.end();*/

	}

	public void update(float dt)
	{

		animation.update(Gdx.graphics.getDeltaTime());
		coin.update(dt);
		asteroid.update(dt);
		bird.update(dt);

		if (Intersector.overlaps(coin.getCircle(),bird.getRectangle()) && !coin.getCollision())
		{
			Gdx.app.log("Cheese","True");
			score++;
			coin.setCollision(true);
		}
		if (Intersector.overlaps(asteroid.getCircle(),bird.getRectangle()))
		{
			birdDead = true;
		}



	}
	
	@Override
	public void dispose () {
		batch.dispose();
		texture.dispose();
		bird.dispose();
		asteroid.dispose();
		coin.dispose();
	}
}
