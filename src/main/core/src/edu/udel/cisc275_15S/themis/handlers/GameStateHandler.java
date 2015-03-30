package edu.udel.cisc275_15S.themis.handlers;

import java.util.Stack;


import edu.udel.cisc275_15S.themis.Themis;
import edu.udel.cisc275_15S.themis.game_states.GameState;
import edu.udel.cisc275_15S.themis.game_states.Menu;
import edu.udel.cisc275_15S.themis.game_states.Play;

public class GameStateHandler {

		private Themis game;
		
		private Stack<GameState> gameStates;
		public static final int MENU = 1234;
		public static final int PLAY = 5678;
		
		public GameStateHandler(Themis game) {
			this.game = game;
			gameStates = new Stack<GameState>();
			pushState(MENU);
		}
		
		public Themis game() {return game;}
		
		public void update(float dt) {
//		Look at the current GameState without popping it off the stack, and use its update && render methods
			gameStates.peek().update(dt);
		}
		
		public void render() {
			gameStates.peek().render();
		}
//		method only utilized by the GSH
		private GameState getState(int state) {
			if (state == PLAY) return new Play(this);
			if (state == MENU) return new Menu(this);
			return null;
		}
		public void setState(int state) {
			popState();
			pushState(state);
		}
		public void pushState(int state) {
			gameStates.push(getState(state));
		}
		public void popState() {
			GameState g = gameStates.pop();
			g.dispose();
		}
}
