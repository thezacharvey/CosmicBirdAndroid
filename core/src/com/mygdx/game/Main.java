package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.mygdx.game.Managers.CameraManager;
import com.mygdx.game.Managers.ScoreManager;
import com.mygdx.game.Managers.SoundManager;
import com.mygdx.game.Managers.StateManager;
import com.mygdx.game.Sprites.Asteroid;
import com.mygdx.game.Sprites.Bird;
import com.mygdx.game.Sprites.Coin;
import com.mygdx.game.Sprites.ScoreMultiplier;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;

	private Animation animation,newAnimation;
 public static Texture texture,gameOver,highScore, newTexture,taptoReplay;
	private Texture tapToPlay;
	public static boolean birdDead;
	private OrthographicCamera camera;
	private Coin coin;
	private ShapeRenderer shapeRenderer;
	private BitmapFont scoreFont;
	public static int score;
	private Bird bird;
	public static  Sprite gameSprite,highScoreSprite,newSprite,tapToReplaySprite;
	private ScoreManager scoreManager;
	private StateManager stateManager;
	public static CameraManager cameraManager;
	public static int gameState;
	private Asteroid asteroid;
	private ScoreMultiplier scoreMultiplier;
	private SoundManager soundManager;
	@Override
	public void create () {
		score = 0;
		soundManager = new SoundManager();
		gameState = 0;		//Menu
		birdDead =true;
		scoreFont= new BitmapFont();
		scoreFont.setColor(Color.WHITE);
		tapToPlay = new Texture("taptoplay.png");
		gameOver= new Texture("gameover.png");
		highScore = new Texture("highscore.png");
		newTexture = new Texture("new.png");
	     taptoReplay = new Texture("replay.png");


		batch = new SpriteBatch();
		texture = new Texture("score.png");
		animation = new Animation(new TextureRegion(texture),3,0.25f);  //passes texture , frame count, speed
			shapeRenderer = new ShapeRenderer();
		camera = new OrthographicCamera();
		camera.setToOrtho(false,Gdx.graphics.getWidth()/20,Gdx.graphics.getHeight()/20);

		cameraManager = new CameraManager(camera);
		cameraManager.setCamSize();
		camera.zoom = cameraManager.getCamZoom();
		camera.update();

		asteroid = new Asteroid(camera);
		coin = new Coin(camera,asteroid);
		bird = new Bird(camera,soundManager);

		scoreManager = new ScoreManager(score,coin,asteroid);
		stateManager = new StateManager(bird,asteroid,coin,cameraManager,scoreManager);   //BACS
		scoreMultiplier = new ScoreMultiplier(cameraManager);

		gameSprite = new Sprite(gameOver);
		gameSprite.setScale(0.5f,0.5f);
		gameSprite.setY(cameraManager.getCamHeight()-gameSprite.getRegionHeight()*2);
		gameSprite.setX(cameraManager.getCamWidth()/2 - gameSprite.getWidth()/2);

		highScoreSprite = new Sprite(highScore);
		highScoreSprite.setScale(0.25f,0.25f);
		highScoreSprite.setY(cameraManager.getCamHeight()/2 - highScoreSprite.getHeight()/4f);
	    highScoreSprite.setX(cameraManager.getCamWidth()/2 -highScoreSprite.getRegionWidth()/2 );

		newAnimation = new Animation(new TextureRegion(newTexture),2,.25f);
		newSprite = new Sprite(newAnimation.getFrame());
		//newSprite.setScale(5f,5f);
		newSprite.setY(cameraManager.getCamHeight()/2 + newSprite.getHeight());
		newSprite.setX(cameraManager.getCamWidth()/2 -newSprite.getRegionWidth()/2 );

		tapToReplaySprite = new Sprite(taptoReplay);
		tapToReplaySprite.setX(cameraManager.getCamWidth()/2 - taptoReplay.getWidth()/2);
		tapToReplaySprite.setY(tapToPlay.getHeight()/2.35f);
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
		bird.getSprite().draw(batch);
		//batch.draw(bird.getTextureRegion(),bird.getX(),bird.getY());
		if (!coin.getCollision()) {
			batch.draw(coin.getTextureRegion(), coin.getX(), coin.getY());

		}
		if (!scoreMultiplier.getCollision()) {
			scoreMultiplier.getSprite().draw(batch);
		}

			asteroid.getSprite().draw(batch);
		if (gameState==0)
		{
			batch.draw(tapToPlay,cameraManager.getCamWidth()/2 - tapToPlay.getWidth()/2,cameraManager.getCamHeight()/2 - tapToPlay.getHeight()/2);
		}
		else if (gameState==1)
		{
			scoreFont.draw(batch,String.valueOf(score),texture.getWidth()*1.15f,camera.viewportHeight );
			batch.draw(texture,0 ,cameraManager.getCamHeight()-texture.getHeight());
		}else
		{
			//	batch.draw(gameOver,cameraManager.getCamWidth()/2 - gameOver.getWidth()/2,cameraManager.getCamHeight()/2 - gameOver.getHeight()/2);
			if (gameSprite.getScaleX() < 2.25f)
			{
				gameSprite.scale(.125f);
			}
			if (highScoreSprite.getScaleX() <= 1f)
			{
				highScoreSprite.scale(.125f);
			}
			if (tapToReplaySprite.getScaleX() < 1.75f)
			{
				tapToReplaySprite.scale(.125f);
			}
			gameSprite.draw(batch);
			highScoreSprite.draw(batch);
			tapToReplaySprite.draw(batch);
			if (scoreManager.gotNewHighScore())
			{
				newSprite.draw(batch);
			}
			if (highScoreSprite.getScaleX() >= 1f)
			{scoreFont.draw(batch,String.valueOf(scoreManager.getPreferences().getInteger("highScore")), highScoreSprite.getWidth()/2 +5.5f ,highScoreSprite.getY() - highScoreSprite.getRegionHeight());
			}

		}

		batch.end();

/*
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.circle(scoreMultiplier.getCircle().x,scoreMultiplier.getCircle().y,scoreMultiplier.getCircle().radius);
		//shapeRenderer.rect(birdRect.x,birdRect.y,birdRect.width,birdRect.height);
       shapeRenderer.end();*/

	}

	public void update(float dt)
	{

		animation.update(Gdx.graphics.getDeltaTime());
		coin.update(dt);
		scoreMultiplier.update(dt);
		asteroid.update(dt);
		bird.update(dt);
		if (scoreManager.gotNewHighScore())
		{
			newAnimation.update(dt);
			newSprite.setRegion(newAnimation.getFrame());
		}


		if (Intersector.overlaps(coin.getCircle(),bird.getRectangle()) && !coin.getCollision())
		{
			score++;
			scoreManager.update(score);
			coin.setCollision(true);
			if (!birdDead)soundManager.playSoundEffect(1); //plays coin sound effect**
		//	Gdx.app.log("jooz",String.valueOf(scoreManager.getPreferences().getInteger("highscore")));
		}
		if (Intersector.overlaps(scoreMultiplier.getCircle(),bird.getRectangle()) && !scoreMultiplier.getCollision() )
		{
			score*=2;
			scoreManager.update(score);
			scoreMultiplier.setCollision(true);


			//	Gdx.app.log("jooz",String.valueOf(scoreManager.getPreferences().getInteger("highscore")));
		}
		if (Intersector.overlaps(asteroid.getCircle(),bird.getRectangle()) && gameState ==1 )
		{
			birdDead = true;
			gameState = 2; //dead state
		}
		stateManager.handleState(gameState);

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		texture.dispose();
		bird.dispose();
		asteroid.dispose();
		coin.dispose();
		scoreFont.dispose();
		newTexture.dispose();
		tapToPlay.dispose();
		taptoReplay.dispose();
	}
}
