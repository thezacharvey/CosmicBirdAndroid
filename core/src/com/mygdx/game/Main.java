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
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Managers.BackgroundManager;
import com.mygdx.game.Managers.CameraManager;
import com.mygdx.game.Managers.LanguageManager;
import com.mygdx.game.Managers.ScoreManager;
import com.mygdx.game.Managers.SoundManager;
import com.mygdx.game.Managers.StateManager;
import com.mygdx.game.Sprites.Asteroid;
import com.mygdx.game.Sprites.Bird;
import com.mygdx.game.Sprites.Coin;
import com.mygdx.game.Sprites.Heart;
import com.mygdx.game.Sprites.ScoreMultiplier;
import com.mygdx.game.Sprites.Snow;
import com.mygdx.game.Sprites.Sun;
import com.mygdx.game.UI.UiManager;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;

	private Animation animation,newAnimation;
  private static Texture gameOver,highScore, newTexture,taptoReplay;
	public static Texture scoreTexture,score2Texture;
	private Texture tapToPlay;
	public static boolean birdDead;
	private OrthographicCamera camera;
	private Coin coin;
	private ShapeRenderer shapeRenderer;
	private BitmapFont scoreFont;
	public static boolean hasPlayed;
	public static int score;
	private Bird bird;
	private boolean hasScored; //changes Score Texture only
	private Sun sun;
   public static Sprite gameSprite,highScoreSprite,newSprite,tapToReplaySprite,bgSprite;
	private Sprite scoreSprite;
	private ScoreManager scoreManager;
	private StateManager stateManager;
	public static CameraManager cameraManager;
	public static int gameState;
	private Snow snow;
	private Heart heart;
	private Asteroid asteroid;
	private ScoreMultiplier scoreMultiplier;
	private SoundManager soundManager;
	AdHandler handler;
	private BackgroundManager backgroundManager;
	private LanguageManager languageManager;
	public static int health;
	private boolean canGiveDamage;
	private UiManager uiManager;

	public Main(AdHandler handler)
	{
		this.handler = handler;
	}



	@Override
	public void create () {
		handler.showAds(true);
		health =0;
		score = 0;

		soundManager = new SoundManager();
		gameState = -1;		//Menu
		hasPlayed = false;
		birdDead =true;
		canGiveDamage =true;
		
		scoreFont= new BitmapFont();
		scoreFont.setColor(Color.WHITE);

		tapToPlay = new Texture("taptoplay.png");
		gameOver= new Texture("gameover.png");
		highScore = new Texture("highscore.png");
		newTexture = new Texture("new.png");
	     taptoReplay = new Texture(Gdx.files.internal("christmas/replaychristmas.png"));

		//bg = new Texture(Gdx.files.internal("backgrounds/bg2.png"));
		hasScored = false;

		batch = new SpriteBatch();
		scoreTexture = new Texture("score.png");
		score2Texture = new Texture("score2.png");
		animation = new Animation(new TextureRegion(scoreTexture),3,0.25f);  //passes scoreTexture , frame count, speed
			shapeRenderer = new ShapeRenderer();
		camera = new OrthographicCamera();
		camera.setToOrtho(false,Gdx.graphics.getWidth()/20,Gdx.graphics.getHeight()/20);

		cameraManager = new CameraManager(camera);
		cameraManager.setCamSize();
		camera.zoom = 125f;
		camera.update();

		asteroid = new Asteroid(camera,this);
		coin = new Coin(camera,asteroid);
		bird = new Bird(camera,soundManager, cameraManager);
		sun = new Sun(scoreManager,cameraManager);
		scoreManager = new ScoreManager(score,coin,asteroid,cameraManager);


		gameSprite = new Sprite(gameOver);
		gameSprite.setScale(0.5f,0.5f);
		gameSprite.setY(cameraManager.getCamHeight()-gameSprite.getRegionHeight()*2.35f);
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

		backgroundManager = new BackgroundManager();


		scoreSprite = new Sprite(scoreTexture);
		scoreSprite.setX(cameraManager.getCamWidth()/2-scoreSprite.getWidth()/2);
		scoreSprite.setY(scoreSprite.getHeight()/2);
		scoreSprite.flip(true,false);


		bgSprite = new Sprite(backgroundManager.getBackground());
		bgSprite.setX(cameraManager.getCamWidth()/2 + bgSprite.getRegionWidth()/3.85f);
		bgSprite.setY(cameraManager.getCamHeight()/2 - bgSprite.getHeight()/2);
		bgSprite.scale(2f);


		stateManager = new StateManager(bird,asteroid,coin,cameraManager,scoreManager,sun,backgroundManager);   //BACS
		scoreMultiplier = new ScoreMultiplier(cameraManager);

		snow = new Snow(cameraManager);
		languageManager = new LanguageManager(scoreSprite,gameSprite,highScoreSprite,tapToReplaySprite);
		heart = new Heart(cameraManager,scoreManager,this);
		//uiManager = new UiManager(cameraManager);
		//implement later
	}


	@Override
	public void render () {

		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		update(Gdx.graphics.getDeltaTime());
		camera.update();


		if (gameState==0||gameState==2)
		{
			handler.showAds(true);
		}


		batch.begin();
		batch.setProjectionMatrix(camera.combined);

		if (!stateManager.showSplashScreen()) {


			bgSprite.setX(cameraManager.getCamWidth() /2);
			bgSprite.draw(batch);

			bird.getSprite().draw(batch);


			//heart.getSpriteArr().draw(batch);
			//batch.draw(bird.getTextureRegion(),bird.getX(),bird.getY());
			if (coin.getCollision()) {
				if (coin.getSprite().getScaleX() >= 0f) {
					coin.getSprite().scale(-0.35f);
				}
				if (coin.getSprite().getScaleX() <= 0f) {
					coin.getSprite().setX(cameraManager.getCamWidth() + coin.getSprite().getWidth());
				}

			} else {
				coin.getSprite().setScale(1, 1);
			}

			if (scoreManager.heartIsDrawable())
			{
				heart.getSprite(0).draw(batch);

			}

			if (!scoreMultiplier.getCollision()) {
				scoreMultiplier.getSprite().draw(batch);
			}

			    asteroid.getSpriteArr()[0].draw(batch);
				asteroid.getSpriteArr()[1].draw(batch);

				//asteroid.getCircleArr()[1].setPosition(asteroid.getSpriteArr()[1].getX(),asteroid.getSpriteArr()[1].getY());


			if (gameState == 0) {
				batch.draw(tapToPlay, cameraManager.getCamWidth() / 2 - tapToPlay.getWidth() / 2, cameraManager.getCamHeight() / 2 - tapToPlay.getHeight() / 2);
			} else if (gameState == 1) {



				handler.showAds(false);


				if (hasScored) {
					scoreSprite.setTexture(languageManager.getScoreTexture(1));
				} else {
					scoreSprite.setTexture(languageManager.getScoreTexture(0));
				}
				//scoreSprite.draw(batch);

				scoreFont.draw(batch, String.valueOf(score), autoAudjustScorePos(false).x,autoAudjustScorePos(false).y);
			} else {
				//	batch.draw(gameOver,cameraManager.getCamWidth()/2 - gameOver.getWidth()/2,cameraManager.getCamHeight()/2 - gameOver.getHeight()/2);
				if (gameSprite.getScaleX() < 2.f) {
					gameSprite.scale(.125f);
				}
				if (highScoreSprite.getScaleX() <= 1f) {
					highScoreSprite.scale(.125f);
				}
				if (tapToReplaySprite.getScaleX() < 1.75f) {
					tapToReplaySprite.scale(.125f);
				}

				gameSprite.draw(batch);
				highScoreSprite.draw(batch);
				//tapToReplaySprite.setTexture(languageManager.getScoreTexture(0));
				tapToReplaySprite.draw(batch);
				if (scoreManager.gotNewHighScore()) {
					newSprite.draw(batch);
				}
				if (highScoreSprite.getScaleX() >= 1f) {
					scoreFont.draw(batch, String.valueOf(scoreManager.getPreferences().getInteger("highScore")),autoAudjustScorePos(true).x,autoAudjustScorePos(true).y);
				}

			}

			for (Sprite sprite : snow.getSpriteArray()) {
				sprite.draw(batch);
			}
			for (Sprite sprite: heart.getHealthStatus())
			{
				sprite.draw(batch);
			}

			sun.getSunSprite().draw(batch);

		}else
		{
			stateManager.getSplashSprite().draw(batch);

		}
		coin.getSprite().draw(batch);
		if (sun.getDisplayWarning() && gameState == 1) {
			sun.getWarningMessageSprite().draw(batch);
		}


		//batch.draw(uiManager.getSprite(2),cameraManager.getCamWidth()/2,cameraManager.getCamHeight()/2);

		//uiManager.getUiSprite().draw(batch);

		batch.end();

		/*shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.setColor(Color.RED);
		for (Circle circle : asteroid.getCircleArr())
		{
			shapeRenderer.circle(circle.x,circle.y,circle.radius);
		}

    	shapeRenderer.rect(sun.getRectangle().x,sun.getRectangle().y,sun.getRectangle().width, sun.getRectangle().height);
      shapeRenderer.end();*/

	}

	private Vector2 autoAudjustScorePos(boolean isHighScore) {

		int length;

		if (isHighScore)
		{
			length = String.valueOf(scoreManager.getPreferences().getInteger("highScore")).length();
		}else
		{
			length = String.valueOf(score).length();
		}

		float multiple = 2.25f / length ;

		float y= cameraManager.getCamHeight()/2-heart.getHealthStatus()[0].getHeight();
		float x =cameraManager.getCamWidth()/2-heart.getHealthStatus()[0].getWidth()/multiple;
		return new Vector2(x,y);


	}

	public void update(float dt)
	{
		animation.update(Gdx.graphics.getDeltaTime());
		coin.update(dt);
		scoreMultiplier.update(dt);
		asteroid.update(dt);
		bird.update(dt);
		sun.update(dt);
		snow.update(dt);





		//Gdx.app.log("Touch pos",String.valueOf(Gdx.input.getInputProcessor().touchDown(int screenXint screenY, int pointer, int button)));



		//heart.update(dt);
		//Gdx.app.log("FPS",String.valueOf(Gdx.graphics.getFramesPerSecond()));

		if (scoreManager.gotNewHighScore())
		{
			newAnimation.update(dt);
			newSprite.setRegion(newAnimation.getFrame());
		}


		if (Intersector.overlaps(coin.getCircle(),bird.getRectangle()) && !coin.getCollision() && gameState !=2)
		{
			score++;
			hasScored = !hasScored;
			coin.setCollision(true);
			if (!birdDead)soundManager.playSoundEffect(1); //plays coin sound effect**
		//	Gdx.app.log("jooz",String.valueOf(scoreManager.getPreferences().getInteger("highscore")));
		}
		if (Intersector.overlaps(scoreMultiplier.getCircle(),bird.getRectangle()) && !scoreMultiplier.getCollision() && gameState !=2)
		{
			score*=2;
			hasScored = !hasScored;
			scoreMultiplier.setCollision(true);


			//	Gdx.app.log("jooz",String.valueOf(scoreManager.getPreferences().getInteger("highscore")));
		}




		for (int i=0; i < asteroid.getCircleArr().length;i++) {


			if (Intersector.overlaps(asteroid.getCircleArr()[i],bird.getRectangle())&&gameState == 1 && i ==0 ||Intersector.overlaps(asteroid.getCircleArr()[i],bird.getRectangle())&&gameState == 1 ){
				soundManager.playSoundEffect(2);
				if (canGiveDamage && health > 0) {
					health--;
					canGiveDamage = false;
					bird.startFlickerAnimation(true);
				}
				if (health <= 0) {
					birdDead = true;
					gameState = 2; //dead state
				}


			}
		}


		if (Intersector.overlaps(bird.getRectangle(), sun.getRectangle()))
		{
			birdDead = true;
			health = 0;

			gameState = 2;
			if (!hasPlayed){
				soundManager.playSoundEffect(2);
				hasPlayed = true;
			}

		}
		if (Intersector.overlaps(heart.getCircle(),bird.getRectangle()))
		{
			heart.setCollision(true);
			if (health <3)
			{
				health++;
				bird.startFlickerAnimation(false);
			}


		}

		scoreManager.update();
		heart.update(dt);
		stateManager.handleState(gameState);

	}

	@Override
	public void resume() {

		languageManager.checkNewLang(java.util.Locale.getDefault().toString());
	}

	@Override
	public void dispose () {
		batch.dispose();
		scoreTexture.dispose();
		bird.dispose();
		asteroid.dispose();
		coin.dispose();
		scoreFont.dispose();
		newTexture.dispose();
		tapToPlay.dispose();
		taptoReplay.dispose();
		sun.dispose();
		soundManager.dispose();
		snow.dispose();
		stateManager.getSplashSprite().getTexture().dispose();
		heart.dispose();
		
	}

	public void setCanGiveDamage(boolean b){ canGiveDamage = b;}
	public Vector2 getScoreXY(){return new Vector2(cameraManager.getCamWidth()/2,scoreSprite.getHeight());}
}
