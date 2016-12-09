package uk.co.willhobson.hobicons.interfaces;

import java.util.LinkedList;

public interface Controllable
{
	public void register( );
	public void keyPress( LinkedList<String> inputsList );
	public void mouseMove( int x, int y);
}
