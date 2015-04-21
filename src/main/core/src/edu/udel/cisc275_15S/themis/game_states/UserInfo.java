package edu.udel.cisc275_15S.themis.game_states;

import java.io.FileNotFoundException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;

import edu.udel.cisc275_15S.themis.handlers.GameStateHandler;
import edu.udel.cisc275_15S.themis.handlers.TextFieldListener;
import edu.udel.cisc275_15S.themis.handlers.TextInputHandler;
import edu.udel.cisc275_15S.themis.interactables.Buttons;

public class UserInfo extends GameState implements Screen{

	Table table;
	Stage stage;

	TextureAtlas menuAtlas;
	Skin menuSkin;

	Label info;
	BitmapFont mainFont;
	Label udeluser, college, name;
	TextField txtudeluser, txtcollege, txtname;
	
	Texture bg = new Texture(Gdx.files.internal("gfx/userinfobg.png"));
	TextInputHandler listener = new TextInputHandler();

	Buttons start;

	public UserInfo(GameStateHandler gsh) {
		super(gsh);
		start = new Buttons("Start", 200, 70);
		Gdx.input.getTextInput(listener, "Enter First Name", "", "Your first name here.");
	}


	@Override
	public void handleInput() {

		if(start.isDown()) {
			//				implement some sound here
			try {
				gsh.setState(GameStateHandler.PLAY);
				bg.dispose();
				start.dispose();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(float dt) {
		handleInput();
		start.update(dt);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1); 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		sb.draw(bg, 0, 0, 480, 320);
		sb.end();

		start.render(sb);
	}
	
	@Override
	public void dispose() {
		table = null;
		stage = null;
		info = null;
		mainFont = null;
		menuSkin = null;
	}


	@Override
	public void show() {
		stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        mainFont = new BitmapFont(Gdx.files.internal("data/fonts/main.fnt"), false);
        mainFont.setColor(1, 1, 1, 1);

        table = new Table();

        menuSkin = new Skin();
        menuAtlas = new TextureAtlas("data/packs/menu.pack");

        menuSkin.addRegions(menuAtlas);

        
        LabelStyle infoStyle = new LabelStyle(mainFont, Color.WHITE);

        info = new Label("Hello there and welcome.", infoStyle);
        info.setBounds(110, 355, 585, 90);
        info.setAlignment(2);

        udeluser = new Label("UDelNetID:", infoStyle);
        college = new Label("College:", infoStyle);


        TextFieldStyle txtStyle = new TextFieldStyle();
        txtStyle.fontColor = Color.WHITE;
        txtStyle.background = menuSkin.getDrawable("textbox");
        txtStyle.font = mainFont;

        txtudeluser = new TextField("", txtStyle);
        txtcollege = new TextField("", txtStyle);
        txtudeluser.setMessageText("test");


        table.add(udeluser).pad(2);
        table.row();
        table.add(txtudeluser).pad(2);
        table.row().pad(10);
        table.add(college).pad(2);
        table.row();
        table.add(txtcollege).pad(2);
        table.setBounds(110, 225, 585, 200);

        stage.addActor(info);
        stage.addActor(table);	}


	@Override
	public void render(float delta) {
		// AUTO Auto-generated method stub

	}


	@Override
	public void resize(int width, int height) {
		// AUTO Auto-generated method stub

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
	public void hide() {
		// AUTO Auto-generated method stub

	}
}
