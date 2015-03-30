package edu.udel.cisc275_15S.themis.game_events;

import edu.udel.cisc275_15S.themis.game_entities.Player;
//	Event/Quest
//	An event occurs when the Player fulfills a specific condition.
//	By successfully completing the Event, the player will attain
//	information, backpack items, etc.
//	Also updates objectives and information text files 
//  Two types Events: Controllable, and nonControllable

public abstract class Event {
//	is the event currently valid, or has it been previously completed
	public Player player;
	public boolean valid;
	
}
