package edu.udel.cisc275_15S.themis.game_entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import edu.udel.cisc275_15S.themis.Data;
import edu.udel.cisc275_15S.themis.Themis;
import edu.udel.cisc275_15S.themis.handlers.TouchInputHandler;
import edu.udel.cisc275_15S.themis.interactables.Backpack;
import edu.udel.cisc275_15S.themis.interactables.Objectives;
// A player is a Character that "owns" the following special attributes:
// Bag 
// Objectives
// Can trigger events
import edu.udel.cisc275_15S.themis.interactables.UDSIS;


public class Player extends Character {
	
	public Backpack bag;
	public Objectives objB;
	public Array<Objectives> obj;
	public UDSIS udsis;
	Texture left = new Texture("Sprites/left.png");
	Texture right = new Texture("Sprites/right.png");
	Texture up = new Texture("Sprites/forward.png");
	Texture down = new Texture("Sprites/back.png");
	float statetime = 0f;
	TextureRegion currentFrame;
	
	public Player(TextureRegion[] sprite, float x, float y, int dir, String name) {
		super(sprite, x, y, dir, name);
	}


	public void update(){
	}
	
	public void render(SpriteBatch sb) {
//		Testing Animation out, this should be structured out and optimized eventually
//		once we know what sprites we're definitely using 
		TextureRegion[][] tmp = TextureRegion.split(left, left.getWidth()/10,left.getHeight());
		TextureRegion[] walkleft = new TextureRegion[10];
		for (int i = 0; i < 10; i++) {
			walkleft[i] = tmp[0][i];
		}
		TextureRegion[][] tmp2 = TextureRegion.split(right, right.getWidth()/10,right.getHeight());
		TextureRegion[] walkright = new TextureRegion[10];
		for (int i = 0; i < 10; i++) {
			walkright[i] = tmp2[0][i];
		}
		TextureRegion[][] tmp3 = TextureRegion.split(up, up.getWidth()/10,up.getHeight());
		TextureRegion[] walkup = new TextureRegion[10];
		for (int i = 0; i < 10; i++) {
			walkup[i] = tmp3[0][i];
		}
		TextureRegion[][] tmp4 = TextureRegion.split(down, down.getWidth()/10,down.getHeight());
		TextureRegion[] walkdown = new TextureRegion[10];
		for (int i = 0; i < 10; i++) {
			walkdown[i] = tmp4[0][i];
		}
		Animation wleft = new Animation(1/12f, walkleft);
		Animation wright = new Animation(1/12f, walkright);
		Animation wup = new Animation(1/12f, walkup);
		Animation wdown = new Animation(1/12f, walkdown);
		
        statetime += Gdx.graphics.getDeltaTime();
        if (getDir() == LEFT && TouchInputHandler.isClicked()) {
        currentFrame = wleft.getKeyFrame(statetime, true);
        }
        if (getDir() == RIGHT && TouchInputHandler.isClicked()) {
        currentFrame = wright.getKeyFrame(statetime, true);
        }
        if (getDir() == DOWN && TouchInputHandler.isClicked()) {
        currentFrame = wdown.getKeyFrame(statetime, true);
        }
        if (getDir() == UP && TouchInputHandler.isClicked()) {
        currentFrame = wup.getKeyFrame(statetime, true);
        }
        sb.begin();
        sb.draw(currentFrame, getX(), getY());             
        sb.end();
	}
//	Methods to avoid hardcoding bag and objectives
//	Use I/O methods to read and set the players bag, objectives and X and Y positions
//	Also save the users X and Y position on update if we are avoiding a save button 
	public void setPos(File data) throws FileNotFoundException {
		Data d = new Data();
		setX(d.readPlayerX(data));
		setY(d.readPlayerY(data));
		setName(d.readPlayerName(data));
		setDir(d.readPlayerDir(data));

	}
	public Backpack setUserBag() {
		FileHandle filehandle = Gdx.files.internal("Button/bag.png");
		System.out.println(filehandle.path());
		Texture pack = new Texture(filehandle);
		bag = new Backpack(pack, 32f, 32f);
		//Read file, load items into Bag
		return bag;
	}
	public Array<Objectives> setUserOBJ() {
//		Read file, load obj
		Array<Objectives> obj= new Array<Objectives>();
//		add xyz objectives
		return obj;
	}
	public void setObjButton(){
		FileHandle filehandle = Gdx.files.internal("Button/info.png");
		System.out.println(filehandle.path());
		Texture objs = new Texture(filehandle);
		objB = new Objectives(objs, 96f, 32f);
	}
	public void setUDSIS(){
		FileHandle filehandle = Gdx.files.internal("Button/browser.png");
		System.out.println(filehandle.path());
		Texture sis = new Texture(filehandle);
		udsis = new UDSIS(sis, 160f, 32f);
	}
	public static void main(String[] args) throws IOException {
	}
	public Backpack getBag(){ return bag;}
	public Objectives getObjButton(){ return objB;}
	public UDSIS getUDSIS(){ return udsis;}

}
