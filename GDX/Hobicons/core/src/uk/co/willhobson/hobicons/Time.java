package uk.co.willhobson.hobicons;

import com.badlogic.gdx.Gdx;

public class Time
{
	public static double time = 1.0d; // ratio between target framerate and
										// actual

	private static int defaultFPS = 60;

	public static void update()
	{
		int actualFPS = Gdx.graphics.getFramesPerSecond();
		actualFPS = (actualFPS == 0) ? 3000 : actualFPS;
		time = (double) defaultFPS / actualFPS;
	}
}
