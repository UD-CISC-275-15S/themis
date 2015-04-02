package edu.udel.cisc275_15S.themis.game_entities;

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
//	public void touchHandler() {
//		float UpperBound = Themis.HEIGHT/4;
//		float LowerBound = Themis.HEIGHT - Themis.HEIGHT/4;
//		float LeftBound = Themis.WIDTH/3;
//		float RightBound = Themis.WIDTH - Themis.WIDTH/3;
//		if (TouchInputHandler.isClicked()) {
////				&&
////				TouchInputHandler.x == x &&
////				TouchInputHandler.y == y) {
////			for testing purposes
//			System.out.println("you are at screen cord: " + x + " , " + y);
	//			if (TouchInputHandler.x > Themis.WIDTH/2 && TouchInputHandler.y > UpperBound && TouchInputHandler.y < LowerBound) { x+=5; dir = RIGHT;}
	//			if (TouchInputHandler.x < Themis.WIDTH/2 && TouchInputHandler.y > UpperBound && TouchInputHandler.y < LowerBound) { x-=5; dir = LEFT;}
//			if (TouchInputHandler.y < Themis.HEIGHT/2 && TouchInputHandler.x < RightBound && TouchInputHandler.x > LeftBound) { y+=5; dir = UP;}
//			if (TouchInputHandler.y > Themis.HEIGHT/2 && TouchInputHandler.x < RightBound && TouchInputHandler.x > LeftBound) { y-=5; dir = DOWN;}
//
//			
//			}
//		
//	}
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
}
