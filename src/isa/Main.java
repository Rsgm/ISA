package isa;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class Main {
	private static Preferences	prefs;
	private static Preferences	save1;	// I may do saving if I have time

	public static void main(String[] args) {
		new LwjglApplication(new Isa(), "ISA: Operation Shapes", 800, 600, false);
		prefs = Gdx.app.getPreferences("isa-prefs");

// if (!prefs.getBoolean("played-before")) { // TODO uncomment this line
		newPrefs();
// }

		int w = prefs.getInteger("width");
		int h = prefs.getInteger("height");
		boolean f = prefs.getBoolean("fullscreen");

		Gdx.graphics.setDisplayMode(w, h, f);
	}

	private static void newPrefs() {
		/* the directory for these can't be changes,
		 * on linux it is /home/[user name]/.prefs/,
		 * on windows it is users/[user name]/.prefs/,
		 * I have not tested mac yet	*/
		prefs.putBoolean("played-before", true);
		prefs.putInteger("width", 800);
		prefs.putInteger("height", 600);
		prefs.putBoolean("fullscreen", false);
		prefs.putBoolean("vsync", false);
		prefs.putBoolean("sound", true);

		prefs.flush(); // unfortunately this makes this game non-portable, kind of
	}
}
