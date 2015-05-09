package edu.udel.cisc275_15S.themis.handlers;

import java.io.FileNotFoundException;
import java.util.Stack;

import edu.udel.cisc275_15S.themis.Themis;
import edu.udel.cisc275_15S.themis.game_states.GameState;
import edu.udel.cisc275_15S.themis.game_states.Menu;
import edu.udel.cisc275_15S.themis.game_states.Pack;
import edu.udel.cisc275_15S.themis.game_states.Play;
import edu.udel.cisc275_15S.themis.game_states.UserInfo;
import edu.udel.cisc275_15S.themis.game_states.Web;

public class GameStateHandler {

		private Themis game;
		
		private Stack<GameState> gameStates;
		public static final int MENU = 1234;
		public static final int PLAY = 5678;
		public static final int WEB = 1357;
		public static final int USERINFO = 2468;
		public static final int PACK = 9876;

		
		public GameStateHandler(Themis game) throws FileNotFoundException {
			this.game = game;
			gameStates = new Stack<GameState>();
			pushState(WEB);
			pushState(MENU);
//			pushState(PLAY);

		}
		
		public Themis game() {return game;}
		
		public void update(float dt) {
//		Look at the current GameState without popping it off the stack, and use its update && render methods
			gameStates.peek().update(dt);
		}
		public void resize(int width, int height) {
//			gameStates.peek().resize(width, height);
		}
		public void render() {
			gameStates.peek().render();
		}
//		method only utilized by the GSH
		private GameState getState(int state) throws FileNotFoundException {
			if (state == PLAY) return new Play(this);
			if (state == MENU) return new Menu(this);
			if (state == WEB) return new Web(this);
			if (state == USERINFO) return new UserInfo(this);
			if (state == PACK) return new Pack(this);
			return null;
		}
		public void setState(int state) throws FileNotFoundException {
			popState();
			pushState(state);
		}
		public void pushState(int state) throws FileNotFoundException {
			gameStates.push(getState(state));
		}
		public void popState() {
			GameState g = gameStates.pop();
			g.dispose();
		}
}
