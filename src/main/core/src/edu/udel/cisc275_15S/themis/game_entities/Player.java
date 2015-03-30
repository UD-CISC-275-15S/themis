package edu.udel.cisc275_15S.themis.game_entities;

import com.badlogic.gdx.graphics.Texture;
// A player is a Character that "owns" the following special attributes:
// Bag 
// Objectives
// Can trigger events

import com.badlogic.gdx.utils.Array;
import edu.udel.cisc275_15S.themis.interactables.Backpack;
import edu.udel.cisc275_15S.themis.interactables.Objectives;

public class Player extends Character {
	public Backpack bag;
	public Array<Objectives> obj;
	
	public Player(Texture sprite, float x, float y, int dir, String name) {
		super(sprite, x, y, dir, name);
		// TODO Auto-generated constructor stub
	}

	public void handleInput() {
//		if (TouchInputHandler.isClicked() && player is not reaching bounds of map
//				TouchInputHandler.x ><= defined threshold 
//				update x;
//				TouchInputHandler.y ><= defined threshold 
//				update y;
	}
	public void update(){
		handleInput();
		SaveUserData();
	}
	
//	Methods to avoid hardcoding bag and objectives
//	Use I/O methods to read and set the players bag, objectives and X and Y positions
//	Also save the users X and Y position on update if we are avoiding a save button 
	
	public void setPos() {
//		Read file, load XY
//		x = 
//		y = 
	}
//	public Backpack setUserBag() {
//		Backpack bag = new Backpack(sprite, x, x)
//		Read file, load items into Bag
//		return bag;
//	}
	public Array<Objectives> setUserOBJ() {
//		Read file, load obj
		Array<Objectives> obj= new Array<Objectives>();
//		add xyz objectives
		return obj;
	}
	public void SaveUserData() {
//		Read and overwrite file 
//		Can define a Delay constant for saving UserData
		
	}
	
}
