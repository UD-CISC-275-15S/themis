package edu.udel.cisc275_15S.themis;


import java.io.IOException;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.udel.cisc275_15S.themis.handlers.GameStateHandler;
import edu.udel.cisc275_15S.themis.handlers.MainCamera;
import edu.udel.cisc275_15S.themis.handlers.TouchInputHandler;

public class Themis implements ApplicationListener {
	
	public static final String TITLE = "Themis";
	// iPhone 3S has a screen resolution of 480x320
	// 4S has a resolution of 960x640
	// Scale the application from 480x320 to 960x640, just arbitrary global vars
	public static final int WIDTH = 480;
	public static final int HEIGHT = 320;
	public static final int SCALE = 2;
	// Game runs at speed designated by us
	private float accum;
	public static final float STEP = 1/60f; // frames per second
	
	public Music background;
	private SpriteBatch sb;
	private OrthographicCamera hudcam; // our HUD which will display the backpack, UDSIS, resources, info etc. Does not move
	private MainCamera cam; // main game camera
	private GameStateHandler gsh;
	private static BitmapFont bmf;
	public SpriteBatch getSpriteBatch() {return sb;}
	public MainCamera getCamera() {return cam;}
	public OrthographicCamera getHUDCamera() {return hudcam;}
	Texture img;

	@Override
	public void create() {
		
		background = Gdx.audio.newMusic(Gdx.files.internal("Audio/Annoying.mp3"));
		background.play();
		background.setLooping(true);
		sb = new SpriteBatch();
		cam = new MainCamera();
		cam.setToOrtho(false, WIDTH, HEIGHT);
		hudcam = new OrthographicCamera();
		hudcam.setToOrtho(false,WIDTH,HEIGHT);
		bmf=new BitmapFont();
		try {
			gsh = new GameStateHandler(this);
		} catch (IOException e) {
			// AUTO Auto-generated catch block
			e.printStackTrace();
		}
		sb = new SpriteBatch();
		
	}

	@Override
	public void resize(int width, int height) {
		gsh.resize(width, height);
	}

	@Override
	public void render() {
		
		Gdx.graphics.setTitle(TITLE + " :FPS: " + Gdx.graphics.getFramesPerSecond());
		accum += Gdx.graphics.getDeltaTime();
		while (accum >= STEP) {
			accum -= STEP;
			gsh.update(STEP);
			gsh.render();
			TouchInputHandler.update();
			cam.update();
			hudcam.update();
		}
	}

	@Override
	public void pause() {
		// AUTO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// AUTO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// AUTO Auto-generated method stub
		
	}
	public static BitmapFont getBitmapFont(){return bmf;}
		
}
