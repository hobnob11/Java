package uk.co.willhobson.hobicons.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import uk.co.willhobson.hobicons.Hobicons;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Hobicons BEST GAME OTHER GAMES SUCK IT";
		config.width = 1920;
		config.height = 1080;
		new LwjglApplication(new Hobicons(), config);
	}
}
