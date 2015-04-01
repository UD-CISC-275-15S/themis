package edu.udel.cisc275_15S.themis.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import edu.udel.cisc275_15S.themis.Themis;

public class DesktopLauncher {
	public static void main (String[] arg) {
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Themis.TITLE;
		config.width = Themis.WIDTH; // * scale
		config.height = Themis.HEIGHT; // * scale
		new LwjglApplication(new Themis(), config);
	}
}
