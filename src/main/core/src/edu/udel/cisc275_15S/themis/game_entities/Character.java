package edu.udel.cisc275_15S.themis.game_entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.udel.cisc275_15S.themis.handlers.TouchInputHandler;

//	A character is an in-game entity that has a direction, and x,y pos, and sprite image
// 	Cannot be passed through

public class Character {

	public static final int UP = 0000;
	public static final int DOWN = 1111;
	public static final int LEFT = 2222;
	public static final int RIGHT = 3333;
	
	private Texture sprite;
	private float x;
	private float y;
	private int dir;
	private String name;
	
	public Character(Texture sprite, float x, float y, int dir, String name) {
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.name = name;
	}
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.draw(sprite, x, y);
		sb.end();
	}
	public void touchHandler() {
		if (TouchInputHandler.isClicked() &&
				TouchInputHandler.x == x &&
				TouchInputHandler.y == y) {
//			for testing purposes
			System.out.println("You've selected" + name);
		}
	}
	public void update() {
		touchHandler();
	}
	public float getXpos() {return x;}
	public float getYpos() {return y;}
	public String getName() {return name;}
}
