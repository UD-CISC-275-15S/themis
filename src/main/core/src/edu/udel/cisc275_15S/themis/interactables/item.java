package edu.udel.cisc275_15S.themis.interactables;

import com.badlogic.gdx.graphics.Texture;

//		An item is something in the players bag, and can be gotten by talking to NPC's or through Events
//		items should be defined in a textfile, in a format that says whether they are in the bag or not
public class item {
	private String name;
//	Optional features:
	private int quantity;
	private Texture icon;
	private String description;
	
	public item(String name) {
		this.name = name;
	}
	public item(Texture icon, String  name, int quantity, String description){
		this.name = name;
		this.quantity = quantity;
		this.description = description; 
	}
}
