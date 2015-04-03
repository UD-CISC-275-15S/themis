package edu.udel.cisc275_15S.themis.game_entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import edu.udel.cisc275_15S.themis.Themis;
import edu.udel.cisc275_15S.themis.handlers.TouchInputHandler;

//	A character is an in-game entity that has a direction, and x,y pos, and sprite image
// 	Cannot be passed through

public class Character {

	public static final int UP = 0000;
	public static final int DOWN = 1111;
	public static final int LEFT = 2222;
	public static final int RIGHT = 3333;
//	This should be from a png file that contains 4 sprites: an up, down, left, and right direction facing sprite
//	Make sure each sprite file has the same order of directions to make it simple 
	TextureRegion[] sprite;
	private float x;
	private float y;
	private int dir;
	private String name;
	
	public Character(TextureRegion[] sprite, float x, float y, int dir, String name) {
		this.sprite = sprite;
		this.setX(x);
		this.setY(y);
		this.setDir(dir);
		this.name = name;
	}
	public void render(SpriteBatch sb) {
		sb.begin();
		if (getDir() == DOWN)
		sb.draw(sprite[0], getX(), getY());
		if (getDir() == LEFT)
		sb.draw(sprite[1], getX(), getY());
		if (getDir() == UP)
		sb.draw(sprite[2], getX(), getY());
		if (getDir() == RIGHT)
		sb.draw(sprite[3], getX(), getY());
		sb.end();
	}

	public void update() {
//		touchHandler();
	}
	public float getXpos() {return getX();}
	public float getYpos() {return getY();}
	public void setX(float num) { x = num;};
	public void setY(float num) { y = num;};
	public void setDir(String direction) { 
		if (direction == "up") { dir = UP;}
		if (direction == "right") { dir = RIGHT;}
		if (direction == "left") { dir = LEFT;}
		if (direction == "down") { dir = DOWN;}	
	}
	public String getName() {return name;}
	public Rectangle getRectangle() {
		int x0 = (int) getX();
		int y0 = (int) getY();
		int width = (int) (getX()+sprite[0].getRegionWidth());
		int height = (int) (getX()+sprite[0].getRegionHeight());
		return new Rectangle(x0, y0, width, height);
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public int getDir() {
		return dir;
	}
	public void setDir(int dir) {
		this.dir = dir;
	}
	public void setName(String nm) {
		name = nm;
	}
	public String getDirString(int n) {
		if (n == UP) { return "UP";}
		if (n == RIGHT) { return "RIGHT";}
		if (n == LEFT) { return "LEFT";}
		if (n == DOWN) { return "DOWN";}
		return "";
	}
}
