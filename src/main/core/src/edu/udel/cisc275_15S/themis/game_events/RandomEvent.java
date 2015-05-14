package edu.udel.cisc275_15S.themis.game_events;

import java.io.IOException;

import com.badlogic.gdx.utils.Array;

import edu.udel.cisc275_15S.themis.Data;
import edu.udel.cisc275_15S.themis.game_entities.Player;

public class RandomEvent extends Event{
	
		public RandomEvent(Player player, boolean valid, String name) throws IOException {
		super(player, valid, name);
		Data d = new Data();
		d.readRDialogue();
		String dialogue = d.loadRDialogue();
		StringBuilder sb = new StringBuilder(dialogue);
		sb.deleteCharAt(dialogue.length() -1);
		dialogue = sb.toString();
		Dias = new Array<String>();
		Dias.add(dialogue);
		
	}
}