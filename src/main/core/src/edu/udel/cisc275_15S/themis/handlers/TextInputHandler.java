package edu.udel.cisc275_15S.themis.handlers;

import com.badlogic.gdx.Input.TextInputListener;

public class TextInputHandler implements TextInputListener {

	private static String playername;
	
	public TextInputHandler() {
		// AUTO Auto-generated constructor stub
	}
	
	public void input(String text) {
		playername = text;
		System.out.println(playername);
	}

	public static String getPlayerName(){
		return playername;
	}
	
	@Override
	public void canceled() {
		// AUTO Auto-generated method stub

	}

}
